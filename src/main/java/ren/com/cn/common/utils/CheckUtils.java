package ren.com.cn.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/8/18 16:17
 * Email: renhongqiang1397@gmail.com
 */
public class CheckUtils {
    @SuppressWarnings("unused")
    private final static Logger log	= LoggerFactory.getLogger(CheckUtils.class);

    /** Don't let anyone instantiate this class. */
    private CheckUtils(){}

    /**
     * 判断元素是否为Null或者Empty
     * <p>
     * 目前可以判断一下元素
     * <ul>
     * <li>Collection,使用其isEmpty()</li>
     * <li>Map,使用其isEmpty()</li>
     * <li>Object[],判断length==0</li>
     * <li>String,使用.trim().length()效率高</li>
     * <li>Enumeration,使用hasMoreElements()</li>
     * <li>Iterator,使用hasNext()</li>
     * </ul>
     *
     * @param value
     *            可以是Collection,Map,Object[],String,Enumeration,Iterator类型
     * @return 空返回true
     * @since 1.0
     */
    @SuppressWarnings("rawtypes")
    public final static boolean isNullOrEmpty(Object value){
        if (null == value){
            return true;
        }
        /*****************************************************************************/
        boolean flag = false;
        // 字符串
        if (value instanceof String){
            // 比较字符串长度, 效率高
            flag = value.toString().trim().length() <= 0;
        }
        // Object[]object数组
        else if (value instanceof Object[]){
            flag = ((Object[]) value).length == 0;
        }
        // ***********************************************************
        // 集合
        else if (value instanceof Collection){
            flag = ((Collection) value).isEmpty();
        }
        // map
        else if (value instanceof Map){
            flag = ((Map) value).isEmpty();
        }
        // 枚举
        else if (value instanceof Enumeration){
            flag = !((Enumeration) value).hasMoreElements();
        }
        // Iterator迭代器
        else if (value instanceof Iterator){
            flag = !((Iterator) value).hasNext();
        }
        return flag;
    }

    /**
     * 判断元素是否不为Null或者Empty,调用<code>!isNullOrEmpty</code>方法
     * <p>
     * 目前可以判断一下元素
     * <ul>
     * <li>Collection,使用其isEmpty()</li>
     * <li>Map,使用其isEmpty()</li>
     * <li>Object[],判断length==0</li>
     * <li>String,使用.trim().length()效率高</li>
     * <li>Enumeration,使用hasMoreElements()</li>
     * <li>Iterator,使用hasNext()</li>
     * </ul>
     *
     * @param value
     *            可以是Collection,Map,Object[],String,Enumeration,Iterator类型
     * @return 不为空返回true
     * @since 1.0
     */
    public final static boolean isNotNullOrEmpty(Object value){
        return !isNullOrEmpty(value);
    }

    public final static boolean isNullOrEmpty(Object... objs){
        if(objs == null) return true;
        else {
            for(Object obj : objs){
                if(isNullOrEmpty(obj))
                    return true;
            }
        }
        return false;
    }

    public static List<String> getValueForPattern2(String key){
        String pattern = "\\$\\$(.*?)\\$\\$";
        return getValueForPattern(key, pattern);
    }

    public static List<String> getValueForPattern(String key){
        String pattern = "#\\[(.*?)\\]";
        return getValueForPattern(key, pattern);
    }

    public static List<String> getValueForPattern(String key, String pattern){
        Matcher matcher = Pattern.compile(pattern).matcher(key);
        List<String> list = new ArrayList();
        while (matcher.find())
            list.add(matcher.group(1));
        return list;
    }

    public static String replaceForPattern(String key, String newString){
        String pattern = "#\\[(.*?)\\]";
        return replaceForPattern(key, pattern, newString);
    }

    public static String replaceForPattern(String key, String pattern, String newString){
        String result = "";
        try {
            result = key.replaceAll(pattern, newString);
        }catch (IllegalArgumentException e){
            log.error("目标字符串含有非法字符:{} ", newString);
        }
        return result;
    }
}
