/**
 *
 */
package com.redpack.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 时间工具类
 *
 * @author Administrator
 *
 */
public class DateUtil {

    public final static DateFormat YYYY_MM_DD_MM_HH_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public final static DateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");

    public final static DateFormat YYYYMMDDMMHHSSSSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    public final static DateFormat YYYYMMDDHHMMSS = new SimpleDateFormat("yyyyMMddHHmmss");

    public final static DateFormat HHMMssSSS = new SimpleDateFormat("HHmmssSSS");

    public static final DateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");

    public static final DateFormat YYMMDD = new SimpleDateFormat("yyMMdd");
    
    
	/**
	 * 常用时间格式
	 */
	public static final String Format_Date = "yyyy-MM-dd";
	public static final String Format_Date_back_slant = "yyyy/MM/dd";
	public static final String Format_Time = "HH:mm:ss";
	public static final String Format_DateTime = "yyyy-MM-dd HH:mm:ss";


	/** 年月日时分秒(无下划线) yyyyMMddHHmmss */
	public static final String dtLong = "yyyyMMddHHmmss";

	/** 完整时间 yyyy-MM-dd HH:mm:ss */
	public static final String simple = "yyyy-MM-dd HH:mm:ss";

	/** 年月日(无下划线) yyyyMMdd */
	public static final String dtShort = "yyyyMMdd";

	/** 年月日(下划线) yyyy-MM-dd */
	public static final String _dtShort = "yyyy-MM-dd";

	public static final String year ="yyyy";
	
	
	/**
	 * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
	 * 
	 * @return 以yyyyMMddHHmmss为格式的当前系统时间
	 */
	public static String getOrderNum() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(dtLong);
		return df.format(date);
	}

	/**
	 * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getDateFormatter() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(simple);
		return df.format(date);
	}

	/**
	 * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
	 * 
	 * @return
	 */
	public static String getDate() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(dtShort);
		return df.format(date);
	}

	/**
	 * 产生随机的三位数
	 * 
	 * @return
	 */
	public static String getThree() {
		Random rad = new Random();
		return rad.nextInt(1000) + "";
	}

	
	/**   
	 * @MethodName: getMonthFirstDay  
	 * @Param: UtilDate  
	 * @Author: gang.lv
	 * @Date: 2013-3-22 下午07:14:34
	 * @Return:    
	 * @Descb: 获取当月的第一天
	 * @Throws:
	*/
	public static String getMonthFirstDay() {
		Calendar cal = Calendar.getInstance();
		Calendar f = (Calendar) cal.clone();
		f.clear();
		f.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		f.set(Calendar.MONTH, cal.get(Calendar.MONTH));
		String firstday = new SimpleDateFormat("yyyy-MM-dd")
				.format(f.getTime());
		firstday = firstday +" 00:00:00";
		return firstday;

	}

	
	/**   
	 * @MethodName: getMonthLastDay  
	 * @Param: UtilDate  
	 * @Author: gang.lv
	 * @Date: 2013-3-22 下午07:14:41
	 * @Return:    
	 * @Descb: 获取当月的最后一天
	 * @Throws:
	*/
	public static String getMonthLastDay() {
		Calendar cal = Calendar.getInstance();
		Calendar l = (Calendar) cal.clone();
		l.clear();
		l.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		l.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
		l.set(Calendar.MILLISECOND, -1);
		String lastday = new SimpleDateFormat("yyyy-MM-dd").format(l.getTime());
		lastday = lastday+" 23:59:59";
		return lastday;
	}
	 
	
	/**   
	 * @MethodName: getYesterDay  
	 * @Param: UtilDate  
	 * @Author: gang.lv
	 * @Date: 2013-4-14 上午01:22:46
	 * @Return:    
	 * @Descb: 获取昨天日期
	 * @Throws:
	*/
	public static Date getYesterDay(){
		 Date date = new Date();
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(date);
		 calendar.set(Calendar.HOUR, 0);
		 calendar.set(Calendar.MINUTE, 0);
		 calendar.set(Calendar.SECOND, 0);
		 calendar.set(Calendar.MILLISECOND, 0);
		 calendar.add(Calendar.DATE,-1);//把日期往后增加一天.整数往后推,负数往前移动
		 return calendar.getTime();
	}
	
	/**
	 * 两日期之间的天数差
	 * @param minuendDate 被减数
	 * @param subtrahendDate 减数
	 * @return
	 */
	public static int diffDateDay(Date minuendDate, Date subtrahendDate ){
		
		long diff =  minuendDate.getTime() - subtrahendDate.getTime();
		return (int) diff/(360*24*60);
		
	}

	
	

    /**
     * 时间转换为yyyy-MM-dd HH:mm:ss格式的字符串
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        return YYYY_MM_DD_MM_HH_SS.format(date);
    }

    public static Date strToDate(String dateString) {
        Date date = null;
        try {
            date = YYYY_MM_DD_MM_HH_SS.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取当前时间 HHmmssSSS加一个随机数的10位数字符串
     */
    public static String getTrxNumber() {
        return HHMMssSSS.format(new Date()) + (int) (Math.random() * 10);
    }

    public static Date strToYYMMDDDate(String dateString) {
        Date date = null;
        try {
            date = YYYY_MM_DD.parse(dateString);
        } catch (ParseException e) {
            System.out.println(DateUtil.class.getName()+".strToYYMMDDDate"+"参数："+dateString+"转换日期出错");
        }
        return date;
    }

    /**
     * 计算两个时间之间相差的天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long diffDays(Date startDate, Date endDate) {
        long days = 0;
        long start = startDate.getTime();
        long end = endDate.getTime();
        // 一天的毫秒数1000 * 60 * 60 * 24=86400000
        days = (end - start) / 86400000;
        return days;
    }

    /**
     * 计算两个时间之间相差的秒数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long diffDate(Date startDate, Date endDate) {
        long ss = 0;
        long start = startDate.getTime();
        long end = endDate.getTime();
        // 一天的毫秒数1000 * 60 * 60 * 24=86400000
        ss = (end - start) / 1000;
        return ss;
    }

    /**
     * 日期加上月数的时间
     *
     * @param date
     * @param month
     * @return
     */
    public static Date dateAddMonth(Date date, int month) {
        return add(date, Calendar.MONTH, month);
    }

    /**
     * 日期加上天数的时间
     *
     * @param date
     * @param month
     * @return
     */
    public static Date dateAddDay(Date date, int day) {
        return add(date, Calendar.DAY_OF_YEAR, day);
    }

    /**
     * 日期加上年数的时间
     *
     * @param date
     * @param year
     * @return
     */
    public static Date dateAddYear(Date date, int year) {
        return add(date, Calendar.YEAR, year);
    }

    /**
     * 计算剩余时间 (多少天多少时多少分)
     *
     * @param startDateStr
     * @param endDateStr
     * @return
     */
    public static String remainDateToString(Date startDate, Date endDate) {
        StringBuilder result = new StringBuilder();
        if (endDate == null) {
            return "过期";
        }
        long times = endDate.getTime() - startDate.getTime();
        if (times < -1) {
            result.append("过期");
        } else {
            long temp = 1000 * 60 * 60 * 24;
            // 天数
            long d = times / temp;

            // 小时数
            times %= temp;
            temp /= 24;
            long m = times / temp;
            // 分钟数
            times %= temp;
            temp /= 60;
            long s = times / temp;

            result.append(d);
            result.append("天");
            result.append(m);
            result.append("小时");
            result.append(s);
            result.append("分");
        }
        return result.toString();
    }

    private static Date add(Date date, int type, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(type, value);
        return calendar.getTime();
    }

    /**
     * @MethodName: getLinkUrl
     * @Param: DateUtil flag ： true 转换 false 不转换
     * @Author: gang.lv
     * @Date: 2013-5-8 下午02:52:44
     * @Return:
     * @Descb:
     * @Throws:
     */
    public static String getLinkUrl(boolean flag, String content, String id) {
        if (flag) {
            content = "<a href='finance.do?id=" + id + "'>" + content + "</a>";
        }
        return content;
    }

    /**
     * 时间转换为时间戳
     *
     * @param format
     * @param date
     * @return
     * @throws ParseException
     */
    public static long getTimeCur(String format, String date) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.parse(sf.format(date)).getTime();
    }

    /**
     * 时间转换为时间戳
     *
     * @param format
     * @param date
     * @return
     * @throws ParseException
     */
    public static long getTimeCur(String format, Date date) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.parse(sf.format(date)).getTime();
    }

    /**
     * 获取当前年份
     */
    public static String getTimeYear() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        return df.format(new Date()).substring(0, 4);// new Date()为获取当前系统时间
    }

    /**
     * 将时间戳转为字符串
     *
     * @param cc_time
     * @return
     */
    public static String getStrTime(String cc_time) {
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime;
    }

    /**
     * 获取某个时间距离当前时间的秒数
     *
     * @param date
     * @return
     */
    public static Long getMsecondsDiff(Date date) {
        Long secend = date.getTime();
        secend -= System.currentTimeMillis();
        return secend / 1000;
    }

    /**
     * 将时间转化为yyyyMMdd格式
     */
    public static String dateToYMD(Date date) {
        return YYYYMMDD.format(date);
    }

    /**
     * 当月天数
     * @return
     * @author: zhangyunhmf
     * @date: 2014年9月23日下午2:08:11
     */
    public static int getDaysOfMonth(){
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        return a.get(Calendar.DATE);// 当月天数
    }
    
  	
	/**
	 * 取得当前月份和偏离月份
	 * @author jiangwmf
	 * @return eg:2015/4~2015/6
	 * @throws ParseException
	 */
	 public static String getFutureMonth(int diverge){
		 return  getYearAfter(Calendar.MONTH,diverge)+ "-" + getMonthAfter(Calendar.MONTH,diverge);
	 }
	/**
	 * 取得当前日期（只有日期，没有时间，或者可以说是时间为0点0分0秒）
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static Date getCurrentDate() throws ParseException {
		Date date = new Date();
		date = YYYY_MM_DD.parse(YYYY_MM_DD.format(date));//
		return date;
	}

	/**
	 * 取得当前时间（包括日期和时间）
	 * 
	 * @return 当前时间
	 */
	public static Date getCurrentDateTime() {
		Date date = new Date();
		return date;
	}

	/**
	 * 获取指定格式的当前系统日期时间
	 * 
	 * @param format
	 *            自定义日期格式器
	 * @return 前系统日期时间
	 */
	public static String getCurrentDateTime(String format) {
		SimpleDateFormat t = new SimpleDateFormat(format);
		return t.format(new Date());
	}

	/**
	 * 用默认的日期格式，格式化一个Date对象
	 * 
	 * @param date
	 *            待被格式化日期
	 * @return “yyyy-MM-dd”格式的日期字符串
	 */
	public static String formatDate(Date date) {
		return date == null ? "" : YYYY_MM_DD.format(date);
	}

	/**
	 * 根据传入的格式，将日期对象格式化为日期字符串
	 * 
	 * @param date
	 *            待被格式化日期
	 * @param format
	 *            自定义日期格式器
	 * @return 格式后的日期字符串
	 */
	public static String formatDate(Date date, String format) {
		String s = "";

		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			s = sdf.format(date);
		}

		return s;
	}

	/**
	 * 用默认的日期时间格式，格式化一个Date对象
	 * 
	 * @param date
	 *            待被格式化日期
	 * @return “yyyy-MM-dd HH:mm:ss”格式的日期时间字符串
	 */
	public static String formatTime(Date date) {
		return date == null ? "" : YYYY_MM_DD_MM_HH_SS.format(date);
	}

	/**
	 * 根据传入的格式，将日期对象格式化为时间字符串
	 * 
	 * @param date
	 *            待被格式化日期
	 * @param format
	 *            自定义日期格式器
	 * @return 格式后的日期时间字符串
	 */
	public static String formatTime(Date date, String format) {
		String s = "";
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			s = sdf.format(date);
		}

		return s;
	}

	/**
	 * 获取指定天数后的日期
	 * 
	 * @param baseDate
	 *            基准日期
	 * @param day
	 *            后推天数
	 * @return 后推后的天数
	 */
	public static Date getDateAfter(Date baseDate, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(baseDate);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}
	
	public static Date getMonthAfter(Date baseDate, int month) {
		Calendar now = Calendar.getInstance();
		now.setTime(baseDate);
		now.set(Calendar.MONTH, now.get(Calendar.MONTH) + month);
		return now.getTime();
	}

	/**
	 * 利用默认的格式（yyyy-MM-dd）将一个表示日期的字符串解析为日期对象
	 * 
	 * @param dateStr
	 *            待格式化日期字符串
	 * @return 格式化后日期对象
	 * @throws RuntimeException
	 */
	public static Date parseDate(String dateStr) {
		Date date = null;
		try {
			date = YYYY_MM_DD.parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

		return date;
	}
	
	public static Date parseDateOrNull(String dateStr,String format) {
		Date date = null;
		try {
			date = new SimpleDateFormat(format).parse(dateStr);
		} catch (ParseException e) {
			return null;
		}

		return date;
	}

	/**
	 * 利用默认的格式（yyyy-MM-dd HH:mm:ss）将一个表示时间的字符串解析为日期对象
	 * 
	 * @param timeStr
	 *            时间字符串
	 * @return 格式化后的日期对象
	 * @throws ParseException
	 */
	public static Date parseTime(String timeStr) throws ParseException {
		return YYYY_MM_DD_MM_HH_SS.parse(timeStr);
	}

	/**
	 * 将一个字符串，按照特定格式，解析为日期对象
	 * 
	 * @param datetimeStr
	 *            日期、时间、日期时间字符串
	 * @param format
	 *            自定义日期格式器
	 * @return 格式化后的日期对象
	 * @throws ParseException
	 */
	public static Date parseDateTime(String datetimeStr, String format)
			throws ParseException {
		Date date = null;
		try {
			date = (new SimpleDateFormat(format)).parse(datetimeStr);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

		return date;
	}

	/**
	 * 得到当前年份
	 * 
	 * @return 当前年份
	 */
	public static int getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 得到年份
	 * field - the calendar field.
	 * amount - the amount of date or time to be added to the field
	 * @return 
	 */
	public static int getYearAfter(int field, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.add(field, amount);
		return cal.get(Calendar.YEAR);
	}
	/**
	 * 得到当前月份（1至12）
	 * 
	 * @return 当前月份（1至12）
	 */
	public static int getCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH) + 1;
	}
	/**
	 * 得到年份
	 * field - the calendar field.
	 * amount - the amount of date or time to be added to the field
	 * @return 
	 */
	public static int getMonthAfter(int field, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.add(field, amount);
		return cal.get(Calendar.MONTH)+1;
	}
	/**
	 * 获取yyyy-MM-dd格式的当前系统日期
	 * 
	 * @return 当前系统日期
	 */
	public static String getCurrentDateAsString() {
		return new SimpleDateFormat(Format_Date).format(new Date());
	}
	
	public static String getStringByBackSlant(Date date) {
		return new SimpleDateFormat(Format_Date_back_slant).format(date);
	}
	
	public static String getCurrentDateAsStringByBackSlant() {
		return new SimpleDateFormat(Format_Date_back_slant).format(new Date());
	}

	/**
	 * 获取指定格式的当前系统日期
	 * 
	 * @param format
	 *            自定义日期格式器
	 * @return 当前系统日期
	 */
	public static String getCurrentDateAsString(String format) {
		SimpleDateFormat t = new SimpleDateFormat(format);
		return t.format(new Date());
	}

	/**
	 * 获取HH:mm:ss格式的当前系统时间
	 * 
	 * @return 当前系统时间
	 */
	public static String getCurrentTimeAsString() {
		return new SimpleDateFormat(Format_Time).format(new Date());
	}

	/**
	 * 获取指定格式的当前系统时间
	 * 
	 * @param format
	 *            自定义日期格式器
	 * @return 当前系统时间
	 */
	public static String getCurrentTimeAsString(String format) {
		SimpleDateFormat t = new SimpleDateFormat(format);
		return t.format(new Date());
	}

	/**
	 * 获取格式为yyyy-MM-dd HH:mm:ss的当前系统日期时间
	 * 
	 * @return 当前系统日期时间
	 */
	public static String getCurrentDateTimeAsString() {
		return getCurrentDateTime(Format_DateTime);
	}

	/**
	 * 获取当前为星期几,从星期日~星期六对应的值是1~7
	 * 
	 * @return 星期几
	 * @date: 2013年12月31日下午3:35:08
	 */
	public static int getDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取指定日期为星期几,从星期日~星期六对应的值是1~7
	 * 
	 * @param date
	 *            指定日期
	 * @return 星期几
	 * @date: 2013年12月31日下午3:45:35
	 */
	public static int getDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取星期几的中文名称
	 * 
	 * @param date
	 *            指定日期
	 * @return 星期几
	 */
	public static String getChineseDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		return getChineseDayOfWeek(cal.getTime());
	}

	/**
	 * 获取星期几的中文名称
	 * 
	 * @param date
	 *            指定日期
	 * @return 星期几
	 */
	public static String getChineseDayOfWeek(String date) {
		return getChineseDayOfWeek(parseDate(date));
	}

	/**
	 * 获取星期几的中文名称
	 * 
	 * @param date
	 *            指定日期
	 * @return 星期几
	 */
	public static String getChineseDayOfWeek(Date date) {
		int dateOfWeek = getDayOfWeek(date);
		if (dateOfWeek == Calendar.MONDAY) {
			return "星期一";
		} else if (dateOfWeek == Calendar.TUESDAY) {
			return "星期二";
		} else if (dateOfWeek == Calendar.WEDNESDAY) {
			return "星期三";
		} else if (dateOfWeek == Calendar.THURSDAY) {
			return "星期四";
		} else if (dateOfWeek == Calendar.FRIDAY) {
			return "星期五";
		} else if (dateOfWeek == Calendar.SATURDAY) {
			return "星期六";
		} else if (dateOfWeek == Calendar.SUNDAY) {
			return "星期日";
		}
		return null;
	}

	/**
	 * 获取当天为几号
	 * 
	 * @return 几号
	 * @date: 2013年12月31日下午3:50:11
	 */
	public static int getDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取指定日期为几号
	 * 
	 * @param date
	 *            指定的日期
	 * @return 几号
	 * @date: 2013年12月31日下午3:50:40
	 */
	public static int getDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取指定日期所在月份的最后一天是几号
	 * 
	 * @param date
	 *            指定日期
	 * @return 指定日期所在月份的最后一天是几号
	 * @date: 2013年12月31日下午3:51:07
	 */
	public static int getMaxDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取指定日期所在月份的第一天
	 * 
	 * @param date
	 *            指定日期
	 * @return 指定日期所在月份的第一天
	 * @date: 2013年12月31日下午4:16:56
	 */
	public static String getFirstDayOfMonth(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parseDate(date));
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return new SimpleDateFormat(Format_Date).format(cal.getTime());
	}
	
	
	/**
     * 获取指定日期所在月份的第一天
     * 
     * @param date
     *            指定日期
     * @return 指定日期所在月份的最后一天
     * @date: 2013年12月31日下午4:16:56
     */
    public static String getLastDayOfMonth(String date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(parseDate(date));
        int dayOfMonth = getMaxDayOfMonth(cal.getTime());
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        return new SimpleDateFormat(Format_Date).format(cal.getTime());
    }

	/**
	 * 获取当天为一年中第几天
	 * 
	 * @return 一年中第几天
	 * @date: 2013年12月31日下午4:03:57
	 */
	public static int getDayOfYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 获取指定日期为一年中第几天
	 * 
	 * @param date
	 *            指定日期
	 * @return 一年中第几天
	 * @date: 2013年12月31日下午4:04:21
	 */
	public static int getDayOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 获取指定日期为星期几,从星期日~星期六对应的值是1~7
	 * 
	 * @param date
	 *            指定日期
	 * @return 星期几
	 * @date: 2013年12月31日下午3:45:35
	 */
	public static int getDayOfWeek(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parseDate(date));
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取指定日期为几号
	 * 
	 * @param date
	 *            指定的日期
	 * @return 几号
	 * @date: 2013年12月31日下午3:50:40
	 */
	public static int getDayOfMonth(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parseDate(date));
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * * 根据传过来的时间和时间格式<br>
	 * 以及传过来对应当前传过来时间的前后n天来做处理<br>
	 * 返回一个你想要的天数时间<br>
	 * 例如：String generatedTime = getEveryTime("yyyy-MM-dd HH:mm:ss",new Date(),-30);<br>
	 * Date = 2013-09-12 17:07:37<br>
	 * simpleDateFormat = yyyy-MM-dd HH:mm:ss<br>
	 * 想取的前30天的这个时间   2013-08-13 17:07:37<br>
     * @return String 
     * @auther liminglmf
	 * @date 2015年6月9日
	 * @param simpleDateFormat 时间格式
	 * @param date 比较的时间
	 * @param dateCount   相差的天数
     * @throws
     */
	
	public static String getEveryTime(String simpleDateFormat,Date date,int dateCount ){
        Date beforeDate = new Date();
        //得到日历
        Calendar calendar = Calendar.getInstance(); 
        //把当前时间赋给日历
        calendar.setTime(date);
        //设置为前后n天
        calendar.add(Calendar.DAY_OF_MONTH, dateCount);  
        //得到前后n天的时间
        beforeDate = calendar.getTime();   
        //设置时间格式
        SimpleDateFormat sdf = new SimpleDateFormat(simpleDateFormat); 
        //格式化前后n天时间
        String generatedTime = sdf.format(beforeDate);    
        //格式化当前时间
        //String nowDate = sdf.format(date); 
        //System.out.println("当天的时间是：" + nowDate); 
        //System.out.println("生成的时间是：" + generatedTime);
        return generatedTime;
        
	}
	
	/**
	 * 获取指定日期为一年中第几天
	 * 
	 * @param date
	 *            指定日期
	 * @return 一年中第几天
	 * @date: 2013年12月31日下午4:04:21
	 */
	public static int getDayOfYear(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parseDate(date));
		return cal.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 时间戳转换:把距离GMT时间的毫秒数转为日期，中国处在东八区，所以是：GMT时间+8小时
	 * 
	 * @param time
	 *            距离GTM时刻的毫秒数
	 * @return 获取到的北京时区日期时间字符串
	 */
	public static String longTimeToDateTimeString(Long time) {
		SimpleDateFormat format = new SimpleDateFormat(Format_DateTime);
		String d = format.format(time);
		return d;
	}

	/**
	 * 时间戳转换:把距离GMT时间的毫秒数转为日期，中国处在东八区，所以是：GMT时间+8小时
	 * 
	 * @param time
	 *            距离GTM时刻的毫秒数
	 * @return 获取到的北京时区日期时间对象
	 */
	public static Date longTimeToDateTime(Long time) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(Format_DateTime);
		String d = format.format(time);
		return parseTime(d);
	}

	public static Date addMonths(Date startDate, int i) {
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(startDate);
		 calendar.add(Calendar.MONTH,i);//月份加
		 return calendar.getTime();
	}

	
}
