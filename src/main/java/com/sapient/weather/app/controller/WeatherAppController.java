package com.sapient.weather.app.controller;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sapient.weather.app.models.WeatherRequest;
import com.sapient.weather.app.models.WeatherResponse;
import com.sapient.weather.app.service.WeatherService;
import com.sapient.weather.app.service.WeatherServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Api(value = "weather-app-controller")
@Controller
public class WeatherAppController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherAppController(WeatherServiceImpl weatherServiceImpl) {
        this.weatherService = weatherServiceImpl;
    }

    @ApiOperation(value = "Provide weather forecast", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, response = WeatherResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returned forecast"),
            @ApiResponse(code = 401, message = "Invalid API key provided"),
            @ApiResponse(code = 403, message = "Trying to access forbidden resource"),
            @ApiResponse(code = 404, message = "Resource being requested is not available"),
            @ApiResponse(code = 500, message = "Error occurred in the weather service")
    })
    @GetMapping("/forecast")
    @ResponseBody
    public List<WeatherResponse> getForecast(@RequestBody @JsonDeserialize(builder = WeatherRequest.WeatherRequestBuilder.class) WeatherRequest weatherRequest) {
        try {
            return weatherService.getForecastAndGuidance(weatherRequest.getCityName());
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
