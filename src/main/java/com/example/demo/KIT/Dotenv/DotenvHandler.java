package com.example.demo.kit.Dotenv;

import io.github.cdimascio.dotenv.Dotenv;

public class DotenvHandler {
    public static Dotenv dotenv = Dotenv.configure().load();

    public static String get(String name) {
        return dotenv.get(name);
    }
}
