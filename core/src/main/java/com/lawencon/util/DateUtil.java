package com.lawencon.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    
    public static LocalDateTime parseStringToDate(String date) {
        System.out.println(date);
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		LocalDateTime dateInput = LocalDateTime.parse(date,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		return dateInput;
	}
}
