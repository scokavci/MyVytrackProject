package com.automation.utilities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeUtilities {

    /**
     * This method returns current date as a String
     * provide a format as a parameter
     * MM-02
     * MMM-Jan
     * dd -01
     * yyyy -2020
     * @param format for example MMM dd, yyyy = Mar 29, 2020
     * @return current date as a string
     */
    public static String getCurrentDate(String format){
        return LocalDate.now().format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * This method returns difference between end and start time
     * @param start time
     * @param end time
     * @param format like h:mm a -->> 5:15 AM, 8:07PM
     * @return difference between end time and start time as a long
     */
    public static long getTimeDifference(String start, String end, String format){
       LocalTime startTime = LocalTime.parse(start,DateTimeFormatter.ofPattern(format));
       LocalTime endTime = LocalTime.parse(end,DateTimeFormatter.ofPattern(format));
       return ChronoUnit.HOURS.between(startTime,endTime);
    }
}
