package com.example.demo.kit.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Time {
    public static String getCurrentTime() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        String currentDate = dateFormat.format(date);
        return currentDate;
    }

    public static String getCurrentDate() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy / hh:mm:ss");
        String currentDate = dateFormat.format(date);
        return currentDate;
    }

    public static String getDeadCurrentDate() {
        return Instant.now().atZone(ZoneId.systemDefault()).toString();
    }

    public static String getCurrentTimeThangFormat() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter TimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'");
        String currentTime = now.format(TimeFormat);
        return currentTime;
    }
}
