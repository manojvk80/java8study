package org.mvk.java8study.dataobjects;

import java.util.Calendar;
import java.util.Date;

public class MyDate {
    private Date todaysDate;

    public MyDate() {
        todaysDate = new Date();
    }

    public Date today() {
        return todaysDate;
    }

    public Date tomorrow() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(todaysDate);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }

    public Date getFutureDateIncrementedBy(Integer incrementByDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(todaysDate);
        calendar.add(Calendar.DAY_OF_YEAR, incrementByDays);
        return calendar.getTime();
    }

    public static boolean isWeekend(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
    }

    public boolean isMonday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
    }

    public boolean isTodayMonday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(todaysDate);
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
    }
}