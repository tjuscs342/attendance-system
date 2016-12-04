package cn.tju.scs.util;

import cn.tju.scs.domain.ApplyDO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by yangwentao on 12/4/16.
 */
public class ApplyDoGenerator {
    Random r1 = new Random();
    String nextString(int sizeOf){
        String str = new String();
        for (int i = 0 ; i < sizeOf ; i ++){
            str += 'a' + r1.nextInt(26);
        }
        return str;
    }
    private static Date randomDate(String beginDate, String endDate) {  
        try {  
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
            Date start = format.parse(beginDate);// 构造开始日期  
            Date end = format.parse(endDate);// 构造结束日期  
            // getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。  
            if (start.getTime() >= end.getTime()) {  
                return null;  
            }  
            long date = random(start.getTime(), end.getTime());  
  
            return new Date(date);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
              
    private static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));  
        // 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值  
        if (rtn == begin || rtn == end) {  
            return random(begin, end);  
        }  
        return rtn;  
    }
    ApplyDO generator(int type){
        
        ApplyDO apd = new ApplyDO();
        apd.setUserId(r1.nextLong());
        apd.setUserName(nextString(5));
        apd.setApplicationId(r1.nextLong());
        
        apd.setApplyDate(randomDate("2000-01-01","2000-12-12"));
        apd.setAuditDate(randomDate("2000-01-01","2000-12-12"));
        apd.setStartDate(randomDate("2000-01-01","2000-12-12"));
        apd.setEndDate(randomDate("2000-01-01","2000-12-12"));
        apd.setReason(nextString(100));
        apd.setApplyType(type);
        apd.setOperatorId(r1.nextLong());
        apd.setOperatorName(nextString(3));
        apd.setRemark(nextString(100));
        return apd;
    }
}
