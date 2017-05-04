package ren.com.cn.common.factory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/18 22:14
 * Email: renhongqiang1397@gmail.com
 */
public class GsonFactory {

    public static Gson createGson() {
        Gson gson = new GsonBuilder()//.excludeFieldsWithoutExposeAnnotation() 不导出实体中没有用@Expose注解的属性
                .enableComplexMapKeySerialization() //支持Map的key为复杂对象的形式
                .serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss")//时间转化为特定格式
                .setPrettyPrinting() //对json结果格式化.
                .setVersion(1.0)    //有的字段不是一开始就有的,会随着版本的升级添加进来,那么在进行序列化和返序列化的时候就会根据版本号来选择是否要序列化.
                //@Since(版本号)能完美地实现这个功能.还的字段可能,随着版本的升级而删除,那么
                //@Until(版本号)也能实现这个功能,GsonBuilder.setVersion(double)方法需要调用.
                .create();
        return gson;
    }

    public static RedisGson createRedisCacheGson() {
        return new RedisGson();
    }
}
