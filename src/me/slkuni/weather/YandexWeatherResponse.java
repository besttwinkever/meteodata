package me.slkuni.weather;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class YandexWeatherResponse implements WeatherResponse{

    private final String response;
    private final JsonObject json;

    public YandexWeatherResponse(String response) {
        this.response = response;
        this.json = JsonParser.parseString(response).getAsJsonObject();
    }

    @Override
    public int getTemperature() {
        return json.getAsJsonObject("fact").get("temp").getAsInt();
    }

    @Override
    public int getAvgTemperature() {
        JsonArray forecasts = json.getAsJsonArray("forecasts");
        int temperatureSum = 0;
        int totalDays = forecasts.size();
        for (JsonElement element : forecasts) {
            temperatureSum += element.getAsJsonObject().
                    getAsJsonObject("parts").
                    getAsJsonObject("day").
                    get("temp_avg").
                    getAsInt();
        }
        return temperatureSum / totalDays;
    }

    @Override
    public String getRawResponse() {
        return this.response;
    }
}
