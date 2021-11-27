package com.sapient.weather.app.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.weather.app.Constants;
import com.sapient.weather.app.clients.HttpClient;
import com.sapient.weather.app.config.ApplicationConfig;
import com.sapient.weather.app.exceptions.HttpCallFailedException;
import com.sapient.weather.app.exceptions.InvalidCityNameException;
import com.sapient.weather.app.models.Day;
import com.sapient.weather.app.models.DayWeather;
import com.sapient.weather.app.models.Temperature;
import com.sapient.weather.app.models.WeatherResponse;
import com.sapient.weather.app.utils.WeatherUtils;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final WeatherUtils weatherUtils;
    private final ApplicationConfig applicationConfig;
    private final CacheManager cacheManager;
    private final OkHttpClient httpClient;

    @Autowired
    public WeatherServiceImpl(WeatherUtils weatherUtils, ApplicationConfig applicationConfig, CacheManager cacheManager) {
        this.weatherUtils = weatherUtils;
        this.applicationConfig = applicationConfig;
        this.cacheManager = cacheManager;
        this.httpClient = HttpClient.getHttpClient();
    }

    @Override
    public List<WeatherResponse> getForecastAndGuidance(String cityName) throws HttpCallFailedException, IOException, InvalidCityNameException {

        if (Objects.isNull(cityName)) {
            throw new InvalidCityNameException("No cityName present in the request");
        }

        List<WeatherResponse> weatherResponse = new ArrayList<>();

        if (!Objects.isNull(cacheManager.getCache("forecast_cache").get(cityName))) {
            System.out.println("Data found in cache");
            return (ArrayList<WeatherResponse>) cacheManager.getCache("forecast_cache").get(cityName).get();
        }

        System.out.println("Data not found in cache");

        ObjectMapper objectMapper = new ObjectMapper();
        JsonParser jsonParser = null;

        Map<String, String> queryParamMap = new HashMap<>();

        queryParamMap.put("key", new String(Base64.getDecoder().decode(applicationConfig.getAppid().getBytes(StandardCharsets.UTF_8))));
        queryParamMap.put("days", applicationConfig.getDays());
        queryParamMap.put("q", cityName);
        queryParamMap.put("aqi", "no");
        queryParamMap.put("alerts", "no");

        String finalRequestUrl = weatherUtils.buildURL(applicationConfig.getRequestUrl(), queryParamMap);

        Request httpRequest = new Request.Builder().url(finalRequestUrl).build();

        Call requestcall = httpClient.newCall(httpRequest);

        Response response = requestcall.execute();

        List<DayWeather> responseData = new ArrayList<>();

        if (response.code() != HttpStatus.OK.value()) {
            System.out.printf("HTTP call failed with code %s - %s%n", response.code(), Objects.requireNonNull(response.body()).toString());
            throw new HttpCallFailedException(response.code() + Objects.requireNonNull(response.body()).toString());
        } else {
            System.out.println("HTTP call succeeded");
            JsonNode jsonNode = objectMapper.readTree(Objects.requireNonNull(response.body()).string()).get("forecast").get("forecastday");

            for (JsonNode arrayNode : jsonNode) {
                jsonParser = objectMapper.treeAsTokens(arrayNode.get("day"));
                Day dayData = objectMapper.readValue(jsonParser, Day.class);

                DayWeather dayWeatherData = DayWeather.DayWeatherBuilder.newInstance().setDay(dayData).setDate(arrayNode.get("date").toString()).setDateEpoch(arrayNode.get("date_epoch").asLong()).build();
                responseData.add(dayWeatherData);
            }

            weatherResponse = prepareWeatherResponse(responseData);

            cacheManager.getCache("forecast_cache").put(cityName, weatherResponse);

            return weatherResponse;
        }
    }

    private List<WeatherResponse> prepareWeatherResponse(List<DayWeather> responseData) {

        List<WeatherResponse> response = new ArrayList<>();

        responseData.forEach(data -> {
            WeatherResponse weatherResponse = null;
            int length = data.getDate().length() - 1;
            LocalDate date = weatherUtils.returnParsedDate(data.getDate().substring(1, length));

            Temperature temperatureObj = Temperature.TemperatureBuilder.getInstance().setMinimumTemperature(data.getDay().getMinTemp_C()).setMaximumTemperature(data.getDay().getMaxTemp_C()).build();
            WeatherResponse.WeatherResponseBuilder weatherResponseBuilder = WeatherResponse.WeatherResponseBuilder.newInstance().setDate(date).setDayOfWeek(date.getDayOfWeek().name()).setForecast(temperatureObj);

            if (data.getDay().getDailyWillItRain() == 1) {
                weatherResponse = weatherResponseBuilder.setStatus(Constants.RAINY_STATUS).build();
            } else if (data.getDay().getMaxTemp_C() > 40) {
                weatherResponse = weatherResponseBuilder.setStatus(Constants.HIGH_TEMPERATURE_STATUS).build();
            } else if (data.getDay().getMaxWind_Mph() > 10) {
                weatherResponse = weatherResponseBuilder.setStatus(Constants.WINDY_STATUS).build();
            } else if (data.getDay().getCondition().getText().contains("Thunder")) {
                weatherResponse = weatherResponseBuilder.setStatus(Constants.THUNDERSTORMS_STATUS).build();
            } else {
                weatherResponse = weatherResponseBuilder.build();
            }

            response.add(weatherResponse);
        });

        return response;
    }
}
