import java.util.Random;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/5/4 16:27
 * Email: renhongqiang1397@gmail.com
 */
public class RandomDemo {

    public static void main(String[] args) {
        Random random = new Random();
        for(int i= 0;i < 100;i++) {
            System.out.println(random.nextInt(7));

        }
    }
}
