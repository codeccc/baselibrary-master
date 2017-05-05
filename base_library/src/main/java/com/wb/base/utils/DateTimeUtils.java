package com.wb.base.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理工具类
 */
public class DateTimeUtils {

    /**
     * @param time   毫秒数
     * @param format 输出格式,如yyyy-MM-dd HH:mm:ss
     * @return format格式如yyyy-MM-dd HH:mm:ss字符串
     */
    public static String formartTimeWithDate(long time, String format)
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.format(time));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 得到当前时间
     *
     * @param format 输出格式,如yyyy-MM-dd HH:mm:ss
     * @return 制定输出格式日期
     */
    public static String getNowTime(String format)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        return sdf.format(new Date(System.currentTimeMillis()));
    }

    /**
     * 将字符串数据转化为毫秒数
     */
    public static long timeStr2Millis(String timeStr, String formart)
    {
        Calendar c = Calendar.getInstance();
        try
        {
            c.setTime(new SimpleDateFormat(formart).parse(timeStr));
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        return c.getTimeInMillis();
    }


    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date_str 字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str, String format)
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取增加多少月的时间
     *
     * @return addMonth - 增加多少月
     */
    public static Date getAddMonthDate(int addMonth)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, addMonth);
        return calendar.getTime();
    }

    /**
     * 获取增加多少天的时间
     *
     * @return addDay - 增加多少天
     */
    public static Date getAddDayDate(int addDay)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, addDay);
        return calendar.getTime();
    }

    /**
     * 获取增加多少小时的时间
     *
     * @return addDay - 增加多少消失
     */
    public static Date getAddHourDate(int addHour)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, addHour);
        return calendar.getTime();
    }

    /**
     * 显示时间格式为 hh:mm
     *
     * @param context
     * @param when
     * @return String
     */
    @SuppressLint("SimpleDateFormat")
    public static String formatTimeShort(Context context, long when)
    {
        String formatStr = "HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        String temp = sdf.format(when);
        if (temp != null && temp.length() == 5 && temp.substring(0, 1).equals("0"))
        {
            temp = temp.substring(1);
        }
        return temp;
    }

    /**
     * 是否同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDate(long date1, long date2)
    {
        long days1 = date1 / (1000 * 60 * 60 * 24);
        long days2 = date2 / (1000 * 60 * 60 * 24);
        return days1 == days2;
    }

    //判断给定的毫秒数为上午还是下午 apm=0 表示上午，apm=1表示下午

    public static int isAmOrPm(long time)
    {
        final Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(time);

        int hour = mCalendar.get(Calendar.HOUR);

        int apm = mCalendar.get(Calendar.AM_PM);
        return apm;
    }


    /**
     * 将日期信息转换成今天、明天、后天、星期
     *
     * @param date
     * @return
     */
    public static String getDateDetail(String date)
    {
        Calendar today = Calendar.getInstance();
        Calendar target = Calendar.getInstance();

        DateFormat df = new SimpleDateFormat("yyy-MM-dd hh:mm:ss");
        try
        {
            today.setTime(df.parse(df.format(System.currentTimeMillis()) + ""));
            today.set(Calendar.HOUR, 0);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);
            target.setTime(df.parse(date));
            target.set(Calendar.HOUR, 0);
            target.set(Calendar.MINUTE, 0);
            target.set(Calendar.SECOND, 0);
        } catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        long intervalMilli = target.getTimeInMillis() - today.getTimeInMillis();
        int xcts = (int) (intervalMilli / (24 * 60 * 60 * 1000));
        return showDateDetail(xcts, target);

    }

    /**
     * 处理显示今天明天后天
     *
     * @return addDay - 增加多少天
     */


    public static String IsToday(String time)
    {

        String istoday = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try
        {
            date = sdf.parse(time);
            Date now = new Date();
            now = sdf.parse(sdf.format(now));
            long sl = date.getTime();
            long el = now.getTime();
            long ei = sl - el;
            int value = (int) (ei / (1000 * 60 * 60 * 24));
            if (value == 0)
            {
                istoday = "Today";
            } else if (value == 1)
            {
                istoday = "Tmr";
            } else
            {
                istoday = "";
            }
        } catch (ParseException e)
        {
            e.printStackTrace();
        }


        return istoday;
    }

    /**
     * 将日期差显示为日期或者星期
     *
     * @param xcts
     * @param target
     * @return
     */
    private static String showDateDetail(int xcts, Calendar target)
    {
        switch (xcts)
        {
            case 0:
                return MyConstants.TODAY;
            case 1:
                return MyConstants.TOMORROW;
            case 2:
                return MyConstants.AFTER_TOMORROW;
            case -1:
                return MyConstants.YESTERDAY;
            case -2:
                return MyConstants.BEFORE_YESTERDAY;
            default:
                int dayForWeek = 0;
                dayForWeek = target.get(Calendar.DAY_OF_WEEK);
                switch (dayForWeek)
                {
                    case 1:
                        return MyConstants.SUNDAY;
                    case 2:
                        return MyConstants.MONDAY;
                    case 3:
                        return MyConstants.TUESDAY;
                    case 4:
                        return MyConstants.WEDNESDAY;
                    case 5:
                        return MyConstants.THURSDAY;
                    case 6:
                        return MyConstants.FRIDAY;
                    case 7:
                        return MyConstants.SATURDAY;
                    default:
                        return null;
                }

        }
    }

    class MyConstants {
        /**
         * 日期
         */
        public static final String TODAY = "Today";
        public static final String YESTERDAY = "Yesterday";
        public static final String TOMORROW = "Tomorrow";
        public static final String BEFORE_YESTERDAY = "Before_Yesterday";
        public static final String AFTER_TOMORROW = "After_Yesterday";
        public static final String SUNDAY = "Sunday";
        public static final String MONDAY = "Monday";
        public static final String TUESDAY = "Tuesday";
        public static final String WEDNESDAY = "Wendesday";
        public static final String THURSDAY = "Thursday";
        public static final String FRIDAY = "Friday";
        public static final String SATURDAY = "Saturday";
    }

}