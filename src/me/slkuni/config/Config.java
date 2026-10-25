package me.slkuni.config;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    private final YandexConfig yandex;
    private final PositionConfig position;

    public Config() {
        Dotenv dotenv = Dotenv.load();
        this.yandex = new YandexConfig(dotenv);
        this.position = new PositionConfig(dotenv);
    }

    public YandexConfig getYandex() {
        return yandex;
    }

    public PositionConfig getPosition() {
        return position;
    }
}

