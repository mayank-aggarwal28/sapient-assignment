package com.sapient.weather.app.service;

import com.sapient.weather.app.exceptions.HttpCallFailedException;
import com.sapient.weather.app.exceptions.InvalidCityNameException;
import com.sapient.weather.app.models.WeatherResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface WeatherService {

    List<WeatherResponse> getForecastAndGuidance(String cityName) throws HttpCallFailedException, IOException, InvalidCityNameException;
}
