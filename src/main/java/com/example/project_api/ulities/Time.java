package com.example.project_api.ulities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class Time {
    public static String getCurrentDate() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd,hh:mm:ss");
        String currentDate = dateFormat.format(date);
        return currentDate;
    }

    public static String getDeadCurrentDate() {
        return Instant.now().atZone(ZoneId.systemDefault()).toString();
    }

    public static String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter TimeFormat =  DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'");
        String currentTime = now.format(TimeFormat);
        return currentTime;
    }
}
