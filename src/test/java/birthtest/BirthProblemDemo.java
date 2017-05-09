package birthtest;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/5/7 21:17
 * Email: renhongqiang1397@gmail.com
 */
public class BirthProblemDemo {


    public static void main(String[] args) {

        float res = 1;
        for (int i = 1;i <= 100;i++) {

            res *=  (365-i+1.0)/365;

        }
        res = 1-res;
        System.out.println("*******结果为:"+String.format("%.8f%s",res*100,"%"));
    }
}
