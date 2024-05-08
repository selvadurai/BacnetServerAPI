package com.libraries;


/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 * Author: Jonathan Kevin Selvadurai
 * Date: March 25 2024
 */


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeAPI {
	
	//Gets current TimeStamp
	public static String getCurrentTimeStamp() {
		 LocalDateTime currentDateTime = LocalDateTime.now();
	     
		 // Define the desired date and time format
	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-HH:mm:ss");

	     // Format the current date and time using the defined pattern
	     String formattedDateTime = currentDateTime.format(formatter);

	    return formattedDateTime;
	}

}
