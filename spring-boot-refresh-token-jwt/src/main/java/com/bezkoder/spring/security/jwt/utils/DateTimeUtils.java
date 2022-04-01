package com.bezkoder.spring.security.jwt.utils;

import java.time.LocalDateTime;

public class DateTimeUtils {
    public static LocalDateTime convertLocalDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime);
    }
}
