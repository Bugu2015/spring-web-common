package spring.web.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateU {

    public final static String FMT_YMD = "yyyy-MM-dd";

    public static String date2str(Date date, String format){
        return new SimpleDateFormat(format).format(date);
    }

    public static Date str2date(String dateStr, String format) {
        try {
            return new SimpleDateFormat(format).parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String transfer(String dateStr, String oldFormat, String newFormat) {
        try {
            Date date = new SimpleDateFormat(oldFormat).parse(dateStr);
            return new SimpleDateFormat(newFormat).format(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static int compare(Date fromDate, Date toDate){
        return Long.compare(fromDate.getTime(), toDate.getTime());
    }

    public static String format(Date date, String format){
        return new SimpleDateFormat(format).format(date);
    }

    public static Date pushSeconds(Date date, int num){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, num);
        return cal.getTime();
    }

    public static Date pushMinutes(Date date, int num){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, num);
        return cal.getTime();
    }

    public static Date pushHours(Date date, int num){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, num);
        return cal.getTime();
    }

    public static Date pushDays(Date date, int num){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, num);
        return cal.getTime();
    }

    public static Date pushMonths(Date date, int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, num);
        return cal.getTime();
    }

    public static Date pushYears(Date date, int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, num);
        return cal.getTime();
    }

    public static long diffMills(Date fromDate, Date toDate){
        Calendar calendar1 = new GregorianCalendar();
        calendar1.setTime(fromDate);
        Calendar calendar2 = new GregorianCalendar();
        calendar2.setTime(toDate);
        return calendar1.getTimeInMillis() - calendar2.getTimeInMillis();
    }

    public static long diffSeconds(Date fromDate, Date toDate){
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(fromDate);
        c1.set(Calendar.MILLISECOND, 0);
        c2.setTime(toDate);
        c2.set(Calendar.MILLISECOND, 0);
        return (c1.getTimeInMillis() - c2.getTimeInMillis()) / 1000;
    }

    public static long diffMinutes(Date fromDate, Date toDate){
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(fromDate);
        c1.set(Calendar.SECOND, 0);
        c1.set(Calendar.MILLISECOND, 0);
        c2.setTime(toDate);
        c2.set(Calendar.SECOND, 0);
        c2.set(Calendar.MILLISECOND, 0);
        return (c1.getTimeInMillis() - c2.getTimeInMillis()) / 1000 / 60;
    }

    public static long diffHours(Date fromDate, Date toDate){
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(fromDate);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        c1.set(Calendar.MILLISECOND, 0);
        c2.setTime(toDate);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);
        c2.set(Calendar.MILLISECOND, 0);
        return (c1.getTimeInMillis() - c2.getTimeInMillis()) / 1000 / 60 / 60;
    }

    public static long diffDays(Date fromDate, Date toDate){
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(fromDate);
        c1.set(Calendar.HOUR, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        c1.set(Calendar.MILLISECOND, 0);
        c2.setTime(toDate);
        c2.set(Calendar.HOUR, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);
        c2.set(Calendar.MILLISECOND, 0);
        return (c1.getTimeInMillis() - c2.getTimeInMillis()) / 1000 / 60 / 60 / 24;
    }

    public static Date getMonthStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    public static Date getMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    public static Date getNow(){
        return new Date();
    }

}
