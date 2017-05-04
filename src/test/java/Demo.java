/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/18 14:58
 * Email: renhongqiang1397@gmail.com
 */
public class Demo {
    public static void main(String[] args) {
        try {
            String demo = String.format("qwertyuiop[]%s%s",String.valueOf(9234927),"linda");
        }catch (Exception e) {
            System.out.println(String.format("error %s",e));
        }

    }
}
