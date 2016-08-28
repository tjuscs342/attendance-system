package cn.tju.scs.util;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by lichen.ll on 2016/8/28.
 */
public class JSONBuilder {
    public static Map<String,Object> buildSuccessReturn (String data ){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("success" , "操作成功");
        result.put("data" , data );
        return result;
    }
}
