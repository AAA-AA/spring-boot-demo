package guava;

import com.google.common.base.Strings;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/16 18:42
 * Email: renhongqiang1397@gmail.com
 */
public class StringTest {
    public static void main(String[] args) {

        System.out.println(Strings.isNullOrEmpty(""));//返回true;
        System.out.println(Strings.nullToEmpty("lala"));
        System.out.println(Strings.nullToEmpty("chen"));//返回"chen"
        System.out.println(Strings.emptyToNull(""));//返回null
        System.out.println(Strings.emptyToNull("chen"));//返回"chen"
        System.out.println(Strings.commonPrefix("aaab", "aacb"));//"aa"否则返回""
        System.out.println(Strings.commonSuffix("aaac", "aacdcadac"));//"aac"否则返回""

    }
}
