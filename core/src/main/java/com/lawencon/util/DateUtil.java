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

	public static String parseLocalDateTimeToDate(LocalDateTime time){
		String date = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

		return date;
	}

	public static String parseLocalDateTimetoString(LocalDate date){
		String dateInput = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

		return dateInput;
	}

	public static LocalDateTime parseToLocalDateTime(String date){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateInput = LocalDateTime.parse(date, formatter);

		return dateInput;
	}

	public static LocalDate parseToLocalDate(String date){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dateInput = LocalDate.parse(date,formatter);

		return dateInput;
	}

	public static String parseLocalDateToString(LocalDate date){
		String dateInput = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		return dateInput;
	}
}
