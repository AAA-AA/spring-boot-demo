/**
 * Created by IntelliJ IDEA ^_^
 * Author : hongqiangren.
 * Date: 2017/10/23 15:32
 * Email: renhongqiang1397@gmail.com
 */
public class Test2 {
    public static void main(String[] args) throws ClassNotFoundException {
        String tablePrefix = "t_sms_template";
        String ignore = "t_";
        System.out.println(tablePrefix.replaceFirst(ignore,""));

        Test2.class.getClassLoader().loadClass(StaticClassTest.class.getName());
        Class.forName(StaticClassTest.class.getName());

    }

}
