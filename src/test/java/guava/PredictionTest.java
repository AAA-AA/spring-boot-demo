package guava;

import com.google.common.base.Preconditions;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/16 22:31
 * Email: renhongqiang1397@gmail.com
 */
public class PredictionTest {
    public static void main(String[] args) {
        String name = "as";
        int age = -1;

        String nameStr = Preconditions.checkNotNull(name, "name为null");
        Preconditions.checkArgument(name.length()>0, "neme为\'\'");
        Preconditions.checkArgument(age>0, "age 必须大于0");
        System.out.println("a person age:"+age+",neme:"+name);




    }
}
