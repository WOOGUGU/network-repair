package com.example.repair.util;

import java.util.Calendar;
import java.util.Date;

public class CalendarUtils {
    public static String datOfWeek(Date date){
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        System.out.println(w);
        return weekDays[w];
    }
}
