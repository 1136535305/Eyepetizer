package com.yjq.eyepetizer.util.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 文件： TimeUtil
 * 描述：
 * 作者： YangJunQuan   2018-5-28.
 */

public class TimeUtil {
    public static String timeStamp2Date(long timeStamp) {

        SimpleDateFormat sdf;
        if (isToday(timeStamp)) {
            sdf = new SimpleDateFormat("今天HH:mm", Locale.getDefault());
        } else if (isYesterday(timeStamp)) {
            sdf = new SimpleDateFormat("昨天HH:mm", Locale.getDefault());
        } else {
            sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        }
        Date date = new Date(timeStamp);
        return sdf.format(date);
    }

    private static boolean isToday(long timestamp) {
        Calendar c = Calendar.getInstance();
        clearCalendar(c, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND, Calendar.MILLISECOND);
        long firstOfDay = c.getTimeInMillis(); // 今天最早时间

        c.setTimeInMillis(timestamp);
        clearCalendar(c, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND, Calendar.MILLISECOND); // 指定时间戳当天最早时间

        return firstOfDay == c.getTimeInMillis();
    }

    private static boolean isYesterday(long timestamp) {
        Calendar c = Calendar.getInstance();
        clearCalendar(c, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND, Calendar.MILLISECOND);
        c.add(Calendar.DAY_OF_MONTH, -1);
        long firstOfDay = c.getTimeInMillis(); // 昨天最早时间

        c.setTimeInMillis(timestamp);
        clearCalendar(c, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND, Calendar.MILLISECOND); // 指定时间戳当天最早时间

        return firstOfDay == c.getTimeInMillis();
    }

    private static void clearCalendar(Calendar c, int... fields) {
        for (int f : fields) {
            c.set(f, 0);
        }
    }


    /**
     * 根据毫秒返回时分秒
     */
    public static String getFormatHMS(long t) {
        int h = (int) (t / 3600000);
        int m = (int) ((t % 3600000) / 60000);
        int s = (int) ((t % 60000) / 1000);


        if (h > 0)
            return String.format(Locale.getDefault(), "%02d:%02d:%02d", h, m, s);
        else
            return String.format(Locale.getDefault(), "%02d:%02d", m, s);
    }
}
