package com.pracs.todo.helper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public class Helper {

    public static Date parseDate(LocalDateTime localDateTime){

        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();

        Date date = Date.from(instant);
        return date;
    }
}
