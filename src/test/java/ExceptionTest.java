import ren.com.cn.exception.NoCheckException;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/25 10:17
 * Email: renhongqiang1397@gmail.com
 */
public class ExceptionTest {


    public static void main(String[] args) {

        ExceptionTest exceptionTest = new ExceptionTest();
        try {
            exceptionTest.demo();
        }catch (Exception e) {
            if (e instanceof NoCheckException) {
                System.out.println("error");
            }
        }
    }

    public void demo() {
        throw new NoCheckException(2,"jfdasl)");
    }
}
