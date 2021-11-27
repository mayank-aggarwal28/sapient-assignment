# Weather Forecast Application

This weather-forecast application is part of the assignment for Publicis Sapient's hiring process.

## Usage

The application takes a city name as input and returns the forecast for the next 3 days (including the current day).

For returning the forecast, it uses the free public API provided by [WeatherAPI](https://www.weatherapi.com/)

The number of days as well as the API key has been placed in the application.properties file and can be modified at will.

The API key has been encoded in Base64.

### Curl

curl --location --request GET 'localhost:8080/forecast' --header 'Content-Type: application/json' --data-raw '{"cityName":"London"}'

The API is also available using Swagger at the below URL (Replace localhost with appropriate URL)

http://localhost:8080/swagger-ui.html

Swagger Docs: http://localhost:8080/v2/api-docs

