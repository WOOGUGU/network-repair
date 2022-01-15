package com.example.repair.util;


import java.text.SimpleDateFormat;
import java.util.Date;
public class DataUtils {
    public static String DataString(Date date){

       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      return simpleDateFormat.format(date)+"\t"+CalendarUtils.datOfWeek(date);

    }
}
