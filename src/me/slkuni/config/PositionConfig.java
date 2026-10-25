package me.slkuni.config;

import io.github.cdimascio.dotenv.Dotenv;
import me.slkuni.Parser;

public class PositionConfig {
    private final double longitude;
    private final double latitude;

    public PositionConfig(Dotenv dotenv) {
        this.longitude = Parser.tryParseDouble(dotenv.get("LONGITUDE"), 0);
        this.latitude = Parser.tryParseDouble(dotenv.get("LATITUDE"), 0);
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }


}
