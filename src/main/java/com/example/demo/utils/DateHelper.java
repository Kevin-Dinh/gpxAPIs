package com.example.demo.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateHelper {
    public static Timestamp convertStringToTimeStamp(String date) {
        Instant i = Instant.parse(date);
        return Timestamp.from(i);
    }

    public static String convertTimestampToString(Timestamp time) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return formatter.format(time.toLocalDateTime());
    }
}
