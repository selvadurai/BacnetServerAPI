package com.libraries;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeAPI {
	
	public static String getCurrentTimeStamp() {
		 LocalDateTime currentDateTime = LocalDateTime.now();
	     
		 // Define the desired date and time format
	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-HH:mm:ss");

	     // Format the current date and time using the defined pattern
	     String formattedDateTime = currentDateTime.format(formatter);

	    return formattedDateTime;
	}

}
