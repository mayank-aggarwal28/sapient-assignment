package com.sapient.weather.app.service;

import com.sapient.weather.app.config.ApplicationConfig;
import com.sapient.weather.app.exceptions.HttpCallFailedException;
import com.sapient.weather.app.exceptions.InvalidCityNameException;
import com.sapient.weather.app.utils.WeatherUtils;
import okhttp3.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ResponseBody.class)
@TestPropertySource("classpath:application.properties")
public class WeatherServiceTest {

    @InjectMocks
    private WeatherServiceImpl mockWeatherService;

    @Autowired
    ApplicationConfig applicationConfig;

    @Mock
    private WeatherUtils mockWeatherUtils;

    @Mock
    private CacheManager mockCacheManager;

    @Mock
    private OkHttpClient mockHttpClient;

    @Test
    public void getForecastAndGuidanceTestSuccess() {

        try {
            String testCityName = "London";

            String mockResponseString = "\"{\\\"forecast\\\":{\\\"forecastday\\\":[{\\\"date\\\":\\\"2021-11-27\\\",\\\"date_epoch\\\":1637971200,\\\"day\\\":{\\\"maxtemp_c\\\":6.7,\\\"maxtemp_f\\\":44.1,\\\"mintemp_c\\\":2.3,\\\"mintemp_f\\\":36.1,\\\"avgtemp_c\\\":4.0,\\\"avgtemp_f\\\":39.1,\\\"maxwind_mph\\\":20.1,\\\"maxwind_kph\\\":32.4,\\\"totalprecip_mm\\\":7.6,\\\"totalprecip_in\\\":0.3,\\\"avgvis_km\\\":7.3,\\\"avgvis_miles\\\":4.0,\\\"avghumidity\\\":86.0,\\\"daily_will_it_rain\\\":1,\\\"daily_chance_of_rain\\\":98,\\\"daily_will_it_snow\\\":0,\\\"daily_chance_of_snow\\\":0,\\\"condition\\\":{\\\"text\\\":\\\"Moderaterain\\\",\\\"icon\\\":\\\"//cdn.weatherapi.com/weather/64x64/day/302.png\\\",\\\"code\\\":1189},\\\"uv\\\":1.0}},{\\\"date\\\":\\\"2021-11-28\\\",\\\"date_epoch\\\":1638057600,\\\"day\\\":{\\\"maxtemp_c\\\":3.9,\\\"maxtemp_f\\\":39.0,\\\"mintemp_c\\\":1.4,\\\"mintemp_f\\\":34.5,\\\"avgtemp_c\\\":2.3,\\\"avgtemp_f\\\":36.1,\\\"maxwind_mph\\\":17.4,\\\"maxwind_kph\\\":28.1,\\\"totalprecip_mm\\\":0.4,\\\"totalprecip_in\\\":0.02,\\\"avgvis_km\\\":9.6,\\\"avgvis_miles\\\":5.0,\\\"avghumidity\\\":71.0,\\\"daily_will_it_rain\\\":0,\\\"daily_chance_of_rain\\\":60,\\\"daily_will_it_snow\\\":0,\\\"daily_chance_of_snow\\\":0,\\\"condition\\\":{\\\"text\\\":\\\"Patchyrainpossible\\\",\\\"icon\\\":\\\"//cdn.weatherapi.com/weather/64x64/day/176.png\\\",\\\"code\\\":1063},\\\"uv\\\":1.0}},{\\\"date\\\":\\\"2021-11-29\\\",\\\"date_epoch\\\":1638144000,\\\"day\\\":{\\\"maxtemp_c\\\":4.6,\\\"maxtemp_f\\\":40.3,\\\"mintemp_c\\\":0.6,\\\"mintemp_f\\\":33.1,\\\"avgtemp_c\\\":2.4,\\\"avgtemp_f\\\":36.3,\\\"maxwind_mph\\\":6.7,\\\"maxwind_kph\\\":10.8,\\\"totalprecip_mm\\\":0.6,\\\"totalprecip_in\\\":0.02,\\\"avgvis_km\\\":9.8,\\\"avgvis_miles\\\":6.0,\\\"avghumidity\\\":74.0,\\\"daily_will_it_rain\\\":1,\\\"daily_chance_of_rain\\\":80,\\\"daily_will_it_snow\\\":0,\\\"daily_chance_of_snow\\\":3,\\\"condition\\\":{\\\"text\\\":\\\"Patchyrainpossible\\\",\\\"icon\\\":\\\"//cdn.weatherapi.com/weather/64x64/day/176.png\\\",\\\"code\\\":1063},\\\"uv\\\":1.0}}]}}\"";

            Call mockCall = Mockito.mock(Call.class);
            Response mockResponse = Mockito.mock(Response.class);
            ResponseBody mockResponseBody = PowerMockito.mock(ResponseBody.class);

            Mockito.when(mockWeatherUtils.buildURL(Mockito.anyString(), Mockito.anyMap())).thenReturn("/abc");
            Mockito.when(mockHttpClient.newCall(Mockito.any(Request.class))).thenReturn(mockCall);
            Mockito.when(mockCall.execute()).thenReturn(mockResponse);
            Mockito.when(mockResponse.body()).thenReturn(mockResponseBody);
            Mockito.when(mockResponseBody.string()).thenReturn(mockResponseString);

            System.out.println(mockWeatherService.getForecastAndGuidance(testCityName));

        } catch (IOException | HttpCallFailedException | InvalidCityNameException e) {
            Assert.fail();
        }

    }
}
