import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/8/5 23:21
 * Email: renhongqiang1397@gmail.com
 */
public class Demo2 {

    public static void main(String[] args) {
        DateTime dateTime = new DateTime();
        System.out.println(Demo2());
        System.out.println(dateTime.toString("yyyy年MM月dd日 HH:mm:ss EE"));
    }

    public static int Demo2() {
        return 2;
    }
}
