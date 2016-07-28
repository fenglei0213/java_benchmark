package org.wind3stone.benchmark.util;


import org.joda.time.DateTime;
import org.joda.time.Days;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by fenglei on 2016/6/22.
 */
public class DateUtils {

    /**
     * getDateTimeString getDateTimeString
     *
     * @param timeFormat
     * @return
     */
    public static String getDateTimeString(String timeFormat) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * getDateTimeString getDateTimeString
     *
     * @param timeFormat
     * @param timeDiff
     * @return
     */
    public static String getDateTimeString(String timeFormat, int calendarTimeLevel, int timeDiff) {
        SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.add(calendarTimeLevel, timeDiff);//当前系统时间 的   前10 秒的时间
        String timeString = formatter.format(calendar.getTime());
        return timeString;
    }

    /**
     * diffDays diffDays
     * <p>
     * dateSecond - dateFirst
     *
     * @param dateFirst
     * @param dateSecond
     * @return
     */
    public static int diffDays(String dateFirst, String dateSecond) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date d1 = format.parse(dateFirst);
        Date d2 = format.parse(dateSecond);
        DateTime dt1 = new DateTime(d1);
        DateTime dt2 = new DateTime(d2);
        return Days.daysBetween(dt1, dt2).getDays();
    }

    /**
     * diffDaysFromNowOn diffDaysFromNowOn
     *
     * @param timestampLong
     * @return
     */
    public static int diffDaysFromNowOn(long timestampLong) throws ParseException {
        Timestamp timestamp = new Timestamp(timestampLong);
        Date d1 = new Date();
        Date d2 = new Date(timestamp.getTime());
        DateTime dt1 = new DateTime(d1);
        DateTime dt2 = new DateTime(d2);
        return Days.daysBetween(dt1, dt2).getDays();
    }

    /**
     * diffDaysFromNowOn diffDaysFromNowOn
     * <p>
     * diffDays diffDays
     *
     * @param dateTime
     * @return
     */
    public static int diffDaysFromNowOn(String dateTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date d1 = new Date();
        Date d2 = format.parse(dateTime);
        DateTime dt1 = new DateTime(d1);
        DateTime dt2 = new DateTime(d2);
        return Days.daysBetween(dt1, dt2).getDays();
    }

    public static void main(String[] args) {
        try {
            int diff = DateUtils.diffDays("20160621", "20160629");
            System.out.println(diff);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
