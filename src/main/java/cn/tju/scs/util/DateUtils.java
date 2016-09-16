package cn.tju.scs.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lichen.ll on 2016/9/7.
 */
public class DateUtils {

    public static int getDuration (Date start , Date end ){
        final long msOfDay = 86400000L;
        Long ret = (end.getTime()-start.getTime())/msOfDay;
        return ret.intValue();
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

    public static Date parseDate ( String pattern ) throws ParseException{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(pattern);
    }
}
