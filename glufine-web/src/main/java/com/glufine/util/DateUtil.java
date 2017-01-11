package com.glufine.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

	private final static SimpleDateFormat sdfDay = new SimpleDateFormat(
			"yyyy-MM-dd");
	
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat(
	"yyyyMMdd");

	private final static SimpleDateFormat sdfTime = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	
	public final static String  formentDateType= "yyyy-MM-dd";
	public final static String  formentTimeType= "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 获取YYYY格式
	 * 
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * 
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}
	
	/**
	 * 获取YYYYMMDD格式
	 * 
	 * @return
	 */
	public static String getDays(){
		return sdfDays.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * 
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}

	/**
	* @Title: compareDate
	* @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	* @param s
	* @param e
	* @return boolean  
	* @throws
	* @author luguosui
	 */
	public static boolean compareDate(String s, String e) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >=fomatDate(e).getTime();
	}

	/**
	 * 格式化日期
	 * 
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	   public static Date fomatDate(String date,String formentType) {
	        DateFormat fmt = new SimpleDateFormat(formentType);
	        try {
	            return fmt.parse(date);
	        } catch (ParseException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	/**
	 * 校验日期是否合法
	 * 
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
	public static int getDiffYear(String startTime,String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			long aa=0;
			int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}
	  /**
     * <li>功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long 
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate = null;
        java.util.Date endDate = null;
        
            try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);
      
        return day;
    }
    
    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }
    
    public static String getAfterDayDate(String days,String formentType) {
        int daysInt = Integer.parseInt(days);
        
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat(formentType);
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }
    
    public static Date getAfterDayDate(int daysInt) {
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        return date;
    }
    /**
     * 根据指定类型格式化日期
     * @param date
     * @param formentType
     * @return
     */
    public static  String formatDate(Date date, String formentType){
        SimpleDateFormat sdfd = new SimpleDateFormat(formentType);
        return sdfd.format(date);
    }
    
    
    @SuppressWarnings("deprecation")
    public static  String dateOfMonth(Date date){
        return date.getDate()+"";
    }
    
    
    public static  String dateOfWeek(Date date){
        return "";
    }
    
    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static  Date getAfterDayDate(Date date,int day) {
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.setTime(date);
        canlendar.add(Calendar.DATE, day); // 日期减 如果不够减会将月变动
        return canlendar.getTime();
    }
    
    /**
     * 得到n天之后是周几
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        
        return dateStr;
    }
    public static String builtDateParam(String start,String end,String forment){
        Date startTime = DateUtil.fomatDate(start, forment);
        Date endTime = DateUtil.fomatDate(end, forment);
        String params = "\'"+DateUtil.formatDate(startTime, forment)+"\' ,";
        while(startTime.before(endTime)){
            startTime = DateUtil.getAfterDayDate(startTime,1);
            params+= "\'" + DateUtil.formatDate(startTime, forment)+"\' ,";
        }
        return params.substring(0, params.length()-1);
    }
    public static String lastWeek(int week){
    	     Date date = new Date();
    	     int year=Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
    	     int month=Integer.parseInt(new SimpleDateFormat("MM").format(date));
    	     int day=Integer.parseInt(new SimpleDateFormat("dd").format(date))-7*week;
    	    
    	     if(day<1){
    	      month-=1;
    	      if(month==0){
    	      year-=1;month=12;
    	     }
    	     if(month==4||month==6||month==9||month==11){
    	      day=30+day;
    	     }else if(month==1||month==3||month==5||month==7||month==8||month==10||month==12)
    	     {
    	      day=31+day;
    	     }else if(month==2){
    	      if(year%400==0||(year %4==0&&year%100!=0))day=29+day;
    	      else day=28+day;
    	     }     
    	    }
    	    String y = year+"";String m ="";String d ="";
    	    if(month<10) m = "0"+month;
    	    else m=month+"";
    	    if(day<10) d = "0"+day;
    	    else d = day+"";
    	   
    	    return y+"-"+m+"-"+d;
    	 }
    
    public static String lastMonth(int allMonth) {
        Date date = new Date();
           int year=Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
           int month=Integer.parseInt(new SimpleDateFormat("MM").format(date))-allMonth;
           int day=Integer.parseInt(new SimpleDateFormat("dd").format(date));
           if(month <= 0){
               int yearFlag = (month*(-1))/12 + 1;
               int monthFlag = (month *(-1))%12;
               year -= yearFlag;
               month=monthFlag*(-1) +12;
           }
           else if(day>28){
               if(month==2){
                   if(year%400==0||(year %4==0&&year%100!=0)){
                       day=29;
                   }else day=28;
               }else if((month==4||month==6||month==9||month==11)&&day==31){
                   day=30;
               }
           }
           String y = year+"";String m ="";String d ="";
           if(month<10) m = "0"+month;
           else m=month+"";
           if(day<10) d = "0"+day;
           else d = day+"";
          
           return y+"-"+m+"-"+d;
    }
    
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
    public static 	java.text.DateFormat format1 = new java.text.SimpleDateFormat(
    	"yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) throws ParseException {
        System.out.println(DateUtil.getWeekOfDate(new Date()));
    }

}
