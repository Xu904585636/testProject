package com.kingleadsw.ysm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author  zhoujie
 *
 * 平台所有时间相关操作写在这里
 */
public class Dates {

    public static long now()
    {
        return  new Date().getTime();
    }

    
    /**
     *<p>获取精确到天的当前时间</p>
     *<p>description</p>
     * @return
     */
    public static Long getCurrDate() {
    	long time=now();
    	String fmt="yyyy-MM-dd";
    	String dateString=format(time,"yyyy-MM-dd");
    	return dateTimeStamp(dateString,fmt);
    }
    
    public static Date addDay(Date date, int day)
    {
        return DateUtils2.addDays(date, day);
    }
    

    public static String format(Object date)
    {
        return format(date, "yyyy-MM-dd");
    }

    public static String format(Object date, String pattern)
    {
        if (date == null) {
            return null;
        }
        if (pattern == null) {
            return format(date);
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    public  static Long timeYear(Integer amount) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);//设置起时间
       cal.add(Calendar.YEAR, amount);//增加年

        return cal.getTime().getTime();

    }


    /**
     * 返回当前时间的年份或者返回当前时间戳是今年的第几周
     * @param  flag  1 返回当前时间年份  2.返回今年第几周
     * @return
     */
    public static Integer getNowWeekOrYear(Integer flag){

        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);//设置星期一为一周开始的第一天
        calendar.setMinimalDaysInFirstWeek(4);
        calendar.setTimeInMillis(Dates.now());//获得当前的时间戳
        int weekYear = calendar.get(Calendar.YEAR);//获得当前的年
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);//获得当前日期属于今年的第几周

        return  flag == 1? weekYear :weekOfYear;
    }

    public  static Long timeDate(Integer amount) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);//设置起时间
        cal.add(Calendar.DATE, amount);//增加天

        return cal.getTime().getTime();

    }
  

    /**
     * 日期格式字符串转换成时间戳
     * @param
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Long dateTimeStamp(String dateStr,String format){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateStr).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public  static Long timeMonth(Integer amount) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);//设置起时间
        cal.add(Calendar.MONTH, amount);//增加月份

        return cal.getTime().getTime();

    }

    public static String format(Long date, String pattern)
    {
        if (date == null) {
            return null;
        }
        if (pattern == null) {
            return format(date);
        }
        
        SimpleDateFormat df = new SimpleDateFormat(pattern);
      //  FastDateFormat fdf = FastDateFormat.getInstance(pattern);
        return df.format(date);
    }



    public  static  String stampToDate(Long value){
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        return df.format(value);
    }

    public static String format(String pattern)
    {
        return new SimpleDateFormat(pattern).format(new Date());
    }
    
	/**
	 *<p>获取本周，周一日期</p>
	 *<p>description</p>
	 * @param date
	 * @return
	 */
	public static Date getThisWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 获得当前日期是一个星期的第几天
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		return cal.getTime();
	}
	
	/**
	 *<p>下周一日期</p>
	 *<p>description</p>
	 * @param date
	 * @return
	 */
	public static Date getNextWeekMonday(Date date) {
		Date thisWeekMonday=getThisWeekMonday(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(thisWeekMonday);
		// 获得当前日期是一个星期的第几天
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		return cal.getTime();
	}
	
	
	public static Date getNextWeekDay(Date date,int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE, day);
		return cal.getTime();
	}
	
	public static int getWeekDay(Date date) {
		 int[] weeks= {7,1,2,3,4,5,6};
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
    	 int w = cal.get(Calendar.DAY_OF_WEEK)-1;
         if (w < 0) {
             w = 0;
		 }
         return weeks[w];
	}
	
	public static int getWeekDay(long time) {
		Date date=new Date(time);
		return getWeekDay(date);
	}
	
	public static int getWeekDay(String timeString) {
	    long time=	dateTimeStamp(timeString,"yyyy-MM-dd");
	    Date date=new Date(time);
		return getWeekDay(date);
	}
	
    private static Calendar getCalendarFormYear(int year){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.YEAR, year);
        return cal;
    }

    public static void main(String[] args) {
    	 
    }

    /**
     * 根据年和周 获取这周开始时间
     * @param year
     * @param weekNo
     * @return
     */
    public static  String getStartDayOfWeekNo(int year,int weekNo){
        Calendar cal = getCalendarFormYear(year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +
                cal.get(Calendar.DAY_OF_MONTH);

    }

    /**
     * 根据年和周 获取这周结束时间
     * @param year
     * @param weekNo
     * @return
     */
    public static String getEndDayOfWeekNo(int year,int weekNo){
        Calendar cal = getCalendarFormYear(year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        cal.add(Calendar.DAY_OF_WEEK, 6);
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +
                cal.get(Calendar.DAY_OF_MONTH);
    }
	
	public static Date getFirstDayMonth(Date date) {
        
        //获取前月的第一天
        Calendar   cal=Calendar.getInstance();//获取当前日期 
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        return cal.getTime();
	}
	
	public static Date getLastDayMonth(Date date) {
	      //获取前月的最后一天
        Calendar cale = Calendar.getInstance();   
        cale.setTime(date);
        cale.set(Calendar.DAY_OF_MONTH, cale.getActualMaximum(Calendar.DAY_OF_MONTH));  
        return cale.getTime();
	}
	
	
	/**
	 * 返回两个日期之间相差多少天。
	 * 
	 * @param startDate
	 *            格式"yyyy/MM/dd"
	 * @param endDate
	 *            格式"yyyy/MM/dd"
	 * @return 整数。
	 */
	public static int getDiffDays(String startDate, String endDate,String fmt) {
		long diff = 0;
		SimpleDateFormat ft = new SimpleDateFormat(fmt);
		try {
			Date sDate = ft.parse(startDate + " 00:00:00");
			Date eDate = ft.parse(endDate + " 00:00:00");
			diff = eDate.getTime() - sDate.getTime();
			diff = diff / 86400000;// 1000*60*60*24;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (int) diff;
	}
	
	public static int getDiffDays(Long startDate, Long endDate,String fmt) {
		long diff = 0;
		SimpleDateFormat ft = new SimpleDateFormat(fmt);
		try {
			Date sDate = ft.parse(format(startDate) + " 00:00:00");
			Date eDate = ft.parse(format(endDate) + " 00:00:00");
			diff = eDate.getTime() - sDate.getTime();
			diff = diff / 86400000;// 1000*60*60*24;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (int) diff;
	}
	
	public static int getDiffDays(Long startDate, Long endDate) {
		long diff = 0;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date sDate = ft.parse(format(startDate) + " 00:00:00");
			Date eDate = ft.parse(format(endDate) + " 00:00:00");
			diff = eDate.getTime() - sDate.getTime();
			diff = diff / 86400000;// 1000*60*60*24;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (int) diff;
	}
}
