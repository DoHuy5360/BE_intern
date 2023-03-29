package com.example.project_api.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

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
}
