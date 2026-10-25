package me.slkuni;

import me.slkuni.config.Config;
import me.slkuni.weather.Weather;
import me.slkuni.weather.WeatherResponse;
import me.slkuni.weather.YandexWeather;

public class Main {

    public static void main(String[] args) {
        Config config = new Config();
        Weather weather = new YandexWeather(config.getYandex());

        try {
            WeatherResponse response = weather.getWeather(config.getPosition().getLongitude(), config.getPosition().getLatitude());
            System.out.printf("Температура: %d °C (lon: %.2f, lat: %.2f)\n",
                    response.getTemperature(),
                    config.getPosition().getLongitude(), config.getPosition().getLatitude()
            );
            System.out.printf("Средняя температура за %d дней: %d °C\n",
                    config.getYandex().getDayLimit(), response.getAvgTemperature()
            );
            System.out.printf("Полный ответ: %s\n", response.getRawResponse());
        } catch (Exception e) {
            System.err.println("Ошибка во время запроса данных о погоде:");
            e.printStackTrace();
        }
    }
}
