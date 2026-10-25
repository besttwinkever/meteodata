package me.slkuni.weather;

import me.slkuni.config.YandexConfig;
import org.apache.hc.core5.net.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class YandexWeather implements Weather {

    private final YandexConfig config;
    private static final HttpClient client = HttpClient.newHttpClient();

    public YandexWeather(YandexConfig config) {
        this.config = config;
    }

    @Override
    public WeatherResponse getWeather(double longitude, double latitude) throws URISyntaxException, IOException, InterruptedException {
        return new YandexWeatherResponse(
                makeRequest(longitude, latitude)
        );
    }

    private String makeRequest(double longitude, double latitude) throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URIBuilder(config.getApiEndpoint())
                .addParameter("lat", String.valueOf(latitude))
                .addParameter("lon", String.valueOf(longitude))
                .addParameter("limit", String.valueOf(config.getDayLimit()))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("X-Yandex-Weather-Key", this.config.getApiKey())
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IOException("HTTP error: " + response.statusCode() + " " + response.body());
        }

        return response.body();
    }
}
