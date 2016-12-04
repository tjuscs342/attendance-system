package cn.tju.scs.util;

import cn.tju.scs.domain.UserDO;

import java.util.Random;

/**
 * Created by yangwentao on 12/4/16.
 */
public class UserDoGenerator {
    String nextString(int sizeOf){
        String str = new String();
        for (int i = 0 ; i < sizeOf ; i ++){
            str += 'a' + r1.nextInt(26);
        }
        return str;
    }
    Random r1 = new Random();
    UserDO generator(){
        UserDO ud = new UserDO();
        ud.setAge(r1.nextInt(50));
        ud.setBossId(r1.nextLong());
        ud.setChildNum(r1.nextInt(5));
        ud.setUserName(nextString(5));
        ud.setUserId(r1.nextLong());
        ud.setPassword(nextString(10));
        ud.setPhone(nextString(11));
        ud.setMarryTimes(r1.nextInt(3));
        ud.setSex(r1.nextInt(2));
        return ud;
    }
}
