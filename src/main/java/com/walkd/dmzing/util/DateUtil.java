package com.walkd.dmzing.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class DateUtil {

    public static Date nowToDate() {
        return Date.from(LocalDateTime.now().toInstant(ZoneOffset.ofHours(9)));
    }

    public static Date nowAfterDaysToDate(Long hours) {
        return Date.from(LocalDateTime.now().plusHours(hours).toInstant(ZoneOffset.ofHours(9)));
    }
}
