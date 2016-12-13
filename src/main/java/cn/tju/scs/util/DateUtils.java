package cn.tju.scs.util;

import javax.validation.constraints.Null;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lichen.ll on 2016/9/7.
 */
public class DateUtils {

    public static int getDuration (Date start , Date end ){
        final long msOfDay = 86400000L;
        Long ret = (end.getTime()-start.getTime())/msOfDay;
        return ret.intValue() + 1;
    }

    //无用的申请true
    public static boolean checkUseless ( Date date ){
        Date now = new Date();
        DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String s1 = format.format( now );
        String s2 = format.format( date );
        for ( int i = 0 ; i < 4 ; i++ )
            if( !(s1.charAt(i) == s2.charAt(i)))
                return true;
        return false;
    }
    public static boolean checkUseless(Date date1 , Date date2){
        DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String s1 = format.format( date1 );
        String s2 = format.format( date2 );
        for ( int i = 0 ; i < 4 ; i++ )
            if( !(s1.charAt(i) == s2.charAt(i))){
                return true;
        }
        return false;
    }
    public static boolean checkUselessByMonth ( Date date,Date date1 ){
        DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String s1 = format.format( date );
        String s2 = format.format( date1 );
        for ( int i = 0 ; i < 7 ; i++ )
            if( !(s1.charAt(i) == s2.charAt(i)))
                return true;
        return false;
    }
    public static boolean isWeekend(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week_index = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if(week_index<0){
            week_index = 0;
        }
        if (week_index == 0 || week_index == 6) return true;
        return false;
    }
    public static Date getFirstDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE,1);
        return calendar.getTime();
    }
    public static Date getLastDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    public static Date parseDate ( String pattern ) throws ParseException{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(pattern);
    }

    public static Date splitDate(Date start,Date end,int x){
        Date start1 = start;
        Date end1 = end;
        if (x == 0){
            start1.setMonth(11);
            return getLastDay(start1);
        }
        else {
            end1.setMonth(0);
            return getFirstDay(end1);
        }
    }
}
