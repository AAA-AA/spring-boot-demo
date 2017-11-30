/**
 * Created by IntelliJ IDEA ^_^
 *
 * @author : hongqiangren.
 * @date: 2017/11/27 17:07
 * @email: renhongqiang1397@gmail.com
 */
public class StaticClassTest {

    static {
        System.out.println("执行了静态代码块");
    }

    StaticClassTest(){
        System.out.println("执行构造器");
    }

    public static void main(String[] args) throws ClassNotFoundException {
    }
}
