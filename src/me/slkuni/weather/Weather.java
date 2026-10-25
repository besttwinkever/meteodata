package me.slkuni.weather;

import java.io.IOException;
import java.net.URISyntaxException;

public interface Weather {
    public WeatherResponse getWeather(double longitude, double latitude) throws URISyntaxException, IOException, InterruptedException;
}
