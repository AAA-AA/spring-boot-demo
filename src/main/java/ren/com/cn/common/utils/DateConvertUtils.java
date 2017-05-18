package ren.com.cn.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期工具类
 *
 * @author zhangjing3
 */
public class DateConvertUtils {
    public static final String TIME_INTERVAL_SECOND = "second";
    public static final String TIME_INTERVAL_MINUTE = "minute";
    public static final String TIME_INTERVAL_HOUR = "hour";
    public static final String TIME_INTERVAL_DAY = "day";
    public static final String TIME_INTERVAL_WEEK = "week";
    public static final String TIME_INTERVAL_MONTH = "month";
    public static final String TIME_INTERVAL_QUARTER = "quarter";
    public static final String TIME_INTERVAL_YEAR = "year";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_GMT = "EEE d MMM yyyy HH:mm:ss 'GMT'";

    public static Date parse(String dateString, String dateFormat) {
        return parse(dateString, dateFormat, Date.class);
    }

    public static <T extends Date> T parse(String dateString,
                                           String dateFormat, Class<T> targetResultType) {
        if (StringUtils.isEmpty(dateString))
            return null;
        DateFormat df = new SimpleDateFormat(dateFormat);
        try {
            T t = targetResultType.getConstructor(new Class[]{Long.TYPE})
                    .newInstance(
                            new Object[]{Long.valueOf(df.parse(dateString)
                                    .getTime())});
            return t;
        } catch (ParseException e) {
            String errorInfo = "cannot use dateformat:" + dateFormat
                    + " parse datestring:" + dateString;
            throw new IllegalArgumentException(errorInfo, e);
        } catch (Exception e) {
            throw new IllegalArgumentException("error targetResultType:"
                    + targetResultType.getName(), e);
        }
    }

    public static String format(Date date, String dateFormat) {
        if (date == null)
            return null;
        DateFormat df = new SimpleDateFormat(dateFormat);
        return df.format(date);
    }

    public static String getDateString(String datePattern) {
        return new SimpleDateFormat(datePattern).format(new Date());
    }

    public static String getYesterdayString(String dateFormat) {
        Date yesterday = add(Calendar.DAY_OF_YEAR, new Date(), -1);
        return DateConvertUtils.format(yesterday, dateFormat);
    }

    public static Date add(int field, Date date, int value) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int fieldNewValue = (c.get(field) + value);
        c.set(field, fieldNewValue);
        return c.getTime();
    }

    public static long dateDiff(String timeInterval, Date date1, Date date2) {
        Calendar calendar = Calendar.getInstance();
        if (timeInterval.equals(TIME_INTERVAL_YEAR)) {
            calendar.setTime(date1);
            int time = calendar.get(Calendar.YEAR);
            calendar.setTime(date2);
            return time - calendar.get(Calendar.YEAR);
        }

        if (timeInterval.equals(TIME_INTERVAL_QUARTER)) {
            calendar.setTime(date1);
            int time = calendar.get(Calendar.YEAR) * 4;
            calendar.setTime(date2);
            time -= calendar.get(Calendar.YEAR) * 4;
            calendar.setTime(date1);
            time += calendar.get(Calendar.MONTH) / 4;
            calendar.setTime(date2);
            return time - calendar.get(Calendar.MONTH) / 4;
        }

        if (timeInterval.equals(TIME_INTERVAL_MONTH)) {
            calendar.setTime(date1);
            int time = calendar.get(Calendar.YEAR) * 12;
            calendar.setTime(date2);
            time -= calendar.get(Calendar.YEAR) * 12;
            calendar.setTime(date1);
            time += calendar.get(Calendar.MONTH);
            calendar.setTime(date2);
            return time - calendar.get(Calendar.MONTH);
        }

        if (timeInterval.equals(TIME_INTERVAL_WEEK)) {
            calendar.setTime(date1);
            int time = calendar.get(Calendar.YEAR) * 52;
            calendar.setTime(date2);
            time -= calendar.get(Calendar.YEAR) * 52;
            calendar.setTime(date1);
            time += calendar.get(Calendar.WEEK_OF_YEAR);
            calendar.setTime(date2);
            return time - calendar.get(Calendar.WEEK_OF_YEAR);
        }

        if (timeInterval.equals(TIME_INTERVAL_DAY)) {
            calendar.setTime(date1);
            int time = calendar.get(Calendar.DAY_OF_YEAR)
                    + calendar.get(Calendar.YEAR) * 365;
            calendar.setTime(date2);
            return time
                    - (calendar.get(Calendar.DAY_OF_YEAR) + calendar
                    .get(Calendar.YEAR) * 365);
        }

        if (timeInterval.equals(TIME_INTERVAL_HOUR)) {
            long time = date1.getTime() / 1000 / 60 / 60;
            return time - date2.getTime() / 1000 / 60 / 60;
        }

        if (timeInterval.equals(TIME_INTERVAL_MINUTE)) {
            long time = date1.getTime() / 1000 / 60;
            return time - date2.getTime() / 1000 / 60;
        }

        if (timeInterval.equals(TIME_INTERVAL_SECOND)) {
            long time = date1.getTime() / 1000;
            return time - date2.getTime() / 1000;
        }

        return date1.getTime() - date2.getTime();
    }

    public static long dateDiff(String timeInterval, Long unixTime1,
                                Long unixTime2) {
        return dateDiff(timeInterval, new Date(unixTime1), new Date(unixTime2));
    }

    /**
     * 获取所传日期之前的时间
     * @param date
     * @param before
     * @return
     */
    public static Date beforeDateLastTime(Date date, int before){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)+before, 23, 59, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 获取所传日期之前的时间
     * @param date
     * @param before
     * @return
     */
    public static String beforeDateLastTimeStr(Date date, int before, String dateFormat){
        return format(beforeDateLastTime(date, before), dateFormat);
    }




    public static void main(String[] args) {
        Date date2 = parse("2011-09-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        Date date1 = parse("2012-01-01 16:00:00", "yyyy-MM-dd HH:mm:ss");
        // System.out.println(dateDiff(TIME_INTERVAL_MONTH, date1, date2));
        // System.out.println(dateDiff(TIME_INTERVAL_YEAR, date1, date2));
        System.out.println(dateDiff(TIME_INTERVAL_DAY, date1, date2));
    }

    public static String getGMTDateStr() {
        Calendar cd = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_GMT, Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT")); // 设置时区为GMT
        return sdf.format(cd.getTime());
    }
}