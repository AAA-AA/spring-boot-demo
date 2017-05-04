package ren.com.cn.common.factory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/18 22:15
 * Email: renhongqiang1397@gmail.com
 */
public class RedisGson<T> {

    public static final Gson gson = new GsonBuilder().disableHtmlEscaping()
            .enableComplexMapKeySerialization() //支持Map的key为复杂对象的形式
            .serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss")//时间转化为特定格式
            .setPrettyPrinting() //对json结果格式化.
            .setVersion(1.0)    //有的字段不是一开始就有的,会随着版本的升级添加进来,那么在进行序列化和返序列化的时候就会根据版本号来选择是否要序列化.
            //@Since(版本号)能完美地实现这个功能.还的字段可能,随着版本的升级而删除,那么
            //@Until(版本号)也能实现这个功能,GsonBuilder.setVersion(double)方法需要调用.
            .create();


    public String toJson(T value) {
        if (value == null)
            throw new NullPointerException
                    ("This Object is Null And can't ToJson to Cache~");
        return gson.toJson(value);
    }

    public String toJsonList(List<T> value) {
        if (value == null)
            throw new NullPointerException
                    ("This Object is Null And can't ToJson to Cache~");
        return gson.toJson(value);
    }

    public T fromJson(String value, Class<T> tClass) {
        if (value == null || value.equals("{}")) return null;
        else return gson.fromJson(value, tClass);
    }

    public List<T> fromJson(String value){
        if (value == null || value.equals("{}")) return null;
        else return gson.fromJson(value,
                new TypeToken<List<T>>() {
                }.getType());
    }




}
