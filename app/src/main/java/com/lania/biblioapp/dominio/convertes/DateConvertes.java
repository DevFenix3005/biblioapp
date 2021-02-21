package com.lania.biblioapp.dominio.convertes;

import androidx.room.TypeConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateConvertes {

    public static DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE_TIME;


    @TypeConverter
    public static LocalDateTime fromTimestamp(String value) {
        return value == null ? null : LocalDateTime.parse(value, FORMATTER);
    }

    @TypeConverter
    public static String fromTimestamp(LocalDateTime value) {
        return value == null ? null : value.format(FORMATTER);
    }

}


