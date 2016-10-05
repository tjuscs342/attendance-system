package cn.tju.scs.util;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.common.collect.Maps;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.List;

/**
 * Created by lichen.ll on 2016/8/28.
 */
public class JSONBuilder {

    public static Map<String, Object> buildSuccessReturn(Object data) {
        Map<String, Object> result = Maps.newHashMap();
        result.put("status", "success");
        result.put("data", data);
        return result;
    }

    public static Map<String,Object> buildSuccessReturn( List list ){
        Map<String, Object> result = Maps.newHashMap();
        result.put("status", "success");
        result.put("data", list);
        return result;
    }

    public static Map<String, Object> buildErrorReturn(String errorMsg) {
        Map<String, Object> result = Maps.newHashMap();
        result.put("status", "fail");
        result.put("errorMsg", errorMsg);
        return result;
    }

    /**
     * created by gmy 16/5/18
     * 继承定义对于json序列化和反序列化的类
     * 序列化时在属性的get方法标注@JsonSerialize(using = JsonUtil.JsonDateSerializer.class)
     * 反序列化时在属性的set方法标注@JsonDeserialize(using = JsonUtil.JsonDateDeserializer.class)
     */
    private static final String DEFAULT_DATE_FORMAT="yyyy-MM-dd HH:mm:ss";

    public static class JsonDateSerializer extends JsonSerializer<Date> {
        private SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

        @Override
        public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
                throws IOException {
            gen.writeString(dateFormat.format(date));
        }


    }

    public static class JsonDateDeserializer extends JsonDeserializer<Date> {
        private SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

        @Override
        public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            try {
                return dateFormat.parse(jp.getText());
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
