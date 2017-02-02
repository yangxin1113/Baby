package com.zyx.baby.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zyx on 2017/1/28.
 */

public class TimeUtils {

    private static Calendar calendar ;
    private TimeUtils() {
        throw new AssertionError();
    }

    public static int getActualMaximum(int year, int month){
        if(calendar == null){
            calendar = calendar.getInstance();
        }
        calendar.set(Calendar.YEAR, year);//先指定年份
        calendar.set(Calendar.MONTH, month - 1);//再指定月份 Java月份从0开始算
        int daysCountOfMonth = calendar.getActualMaximum(Calendar.DATE);//获取指定年份中指定月份有几天
        return daysCountOfMonth;
    }

    public static String getDateString(int year, int day, int monthOfYear,String format){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateInString = day + "/" + monthOfYear + "/" + year;
            Date date = formatter.parse(dateInString);
            formatter = new SimpleDateFormat(format);
            String dateString = formatter.format(date).toString();
            return dateString;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }


}
