package com.lawencon.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    
    public static LocalDateTime parseStringToDate(String date) {
		LocalDateTime dateInput = LocalDateTime.parse(date,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		return dateInput;
	}
	public static LocalDate parseToDateOnly(String date){
		LocalDate dateInput = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		return dateInput;
	}
}
