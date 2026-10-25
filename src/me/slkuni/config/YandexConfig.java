package me.slkuni.config;

import io.github.cdimascio.dotenv.Dotenv;
import me.slkuni.Parser;

import java.util.Optional;

public class YandexConfig {
    private final String apiEndpoint;
    private final String apiKey;
    private final int dayLimit;

    public YandexConfig(Dotenv dotenv) {
        this.apiEndpoint = Optional.ofNullable(dotenv.get("YANDEX_API_ENDPOINT")).
                orElse("https://api.weather.yandex.ru/v2/forecast");
        this.apiKey = Optional.ofNullable(dotenv.get("YANDEX_API_KEY")).
                orElseThrow(() -> new RuntimeException("YANDEX_API_KEY is not set"));
        this.dayLimit = Parser.tryParseInt(dotenv.get("YANDEX_RESPONSE_DAYS_LIMIT"), 7);
    }

    public String getApiEndpoint() {
        return apiEndpoint;
    }

    public String getApiKey() {
        return apiKey;
    }

    public int getDayLimit() {
        return dayLimit;
    }
}