package cn.tju.scs.util;


import com.google.common.collect.Maps;
import java.util.Map;

/**
 * Created by lichen.ll on 2016/8/28.
 */
public class JSONBuilder {

    public static Map<String,Object> buildSuccessReturn ( Object data ){
        Map<String,Object> result = Maps.newHashMap();
        result.put("success" , "true");
        result.put("data" , data );
        return result;
    }

    public static Map<String,Object> buildErrorReturn ( String errorMsg ){
        Map<String,Object> result = Maps.newHashMap();
        result.put("success" , "false");
        result.put("errorMsg" , errorMsg);
        return result;
    }
}
