package com.flowerhada.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Utils {
	public static LocalDateTime getZonedDateTimeNow(String timeZone) {
		return ZonedDateTime.now(ZoneId.of(timeZone)).toLocalDateTime();
	}
}
