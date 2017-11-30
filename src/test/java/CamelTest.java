import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/8/5 23:21
 * Email: renhongqiang1397@gmail.com
 */
public class CamelTest {

    private static final Pattern line = Pattern.compile("_(\\w)");

    private static final Pattern brace = Pattern.compile("(\\{\\})");

    public static void main(String[] args) {
        String demo = "t_sms_template_test";

        String demo2 = "lala is a {}, her name is {}";

        String result = changeToCamel(demo);
        System.out.println(result);

        String camel = lineToCamel(demo);

        String replace = replaceWithBrace(demo2);

    }

    private static String replaceWithBrace(String demo2) {
        Matcher matcher = brace.matcher(demo2);
        StringBuffer buffer = new StringBuffer();
        String[] params = {"123","456","789"};
        for (int i = 0;i < params.length && matcher.find();i++) {
            matcher.appendReplacement(buffer,params[i]);
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = line.matcher(str);
        StringBuffer bu = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(bu, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(bu);
        return bu.toString();
    }



    private static String lineToCamel(String demo) {
        Pattern pattern = line;


        return null;
    }


    private static String changeToCamel(String demo) {
        String result = demo.replaceFirst("t_","");
        String[] splits = result.split("_");
        StringBuilder builder = new StringBuilder("");
        for (String s : splits) {
            if (null == s || s.length() == 0) {
                continue;
            }
            builder.append(s.substring(0,1).toUpperCase());
            if (s.length() > 1) {
                builder.append(s.substring(1));
            }

        }

        return builder.toString();
    }

    public static int Demo2() {
        return 2;
    }
}
