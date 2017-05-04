package ren.com.cn.common.utils;

import com.google.common.reflect.TypeToken;
import com.google.gson.*;

import java.util.Map;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/18 19:19
 * Email: renhongqiang1397@gmail.com
 */
public class JsonUtils {

    private static GsonBuilder gsonBuilder;

    static {
        gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gsonBuilder.enableComplexMapKeySerialization();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 转换为JSON树
     *
     * @param data
     * @return
     */
    public static JsonElement toJsonTree(Object data) {
        return gsonBuilder.create().toJsonTree(data);
    }

    /**
     * 解析为JSON元素对象
     *
     * @param data
     * @return
     */
    public static JsonObject parse(String data) {
        return (new JsonParser()).parse(data).getAsJsonObject();
    }

    /**
     * 获取JSON对象里的Int
     *
     * @param jsonObject
     * @param name
     * @return
     */
    public static Integer getInteger(JsonObject jsonObject, String name) {
        if (jsonObject.has(name)) {
            JsonElement jsonElement = jsonObject.get(name);
            if (jsonElement.isJsonNull()) return null;

            return jsonElement.getAsInt();
        } else {
            return null;
        }
    }

    /**
     * 获取JSON对象里的LONG
     *
     * @param jsonObject
     * @param name
     * @return
     */
    public static Long getLong(JsonObject jsonObject, String name) {
        if (jsonObject.has(name)) {
            JsonElement jsonElement = jsonObject.get(name);
            if (jsonElement.isJsonNull()) return null;

            return jsonElement.getAsLong();
        } else {
            return null;
        }
    }

    /**
     * 获取JSON对象里的字符串
     *
     * @param jsonObject
     * @param name
     * @return
     */
    public static String getString(JsonObject jsonObject, String name) {
        if (jsonObject.has(name)) {
            JsonElement jsonElement = jsonObject.get(name);
            if (jsonElement.isJsonNull()) return null;

            return jsonElement.getAsString();
        } else {
            return null;
        }
    }

    public static String toJson(Map<String, Object> params) {
        return gsonBuilder.create().toJson(params);
    }

    public static String toNotifyJson(Map<String, Object> params) {
        return gsonBuilder.create().toJson(params);
    }

    public static Map<String, String> jsonToMap(String json) {
        return gsonBuilder.create().fromJson(json,
                new TypeToken<Map<String, String>>() {
                }.getType());
    }

    public static String toJson(Object object) {
        return gsonBuilder.create().toJson(object);
    }

    public static Object fromJson(String json, Class toClass) {
        return gsonBuilder.create().fromJson(json, toClass);
    }

    public static boolean isOk(JsonObject json) {
        if (json.has("code") && 0 == getInteger(json, "code")) {
            return true;
        }
        return false;
    }
}
