/**
 * Created by IntelliJ IDEA ^_^
 * Author : hongqiangren.
 * Date: 2017/10/23 15:32
 * Email: renhongqiang1397@gmail.com
 */
public class ClassLoaderDemo {


    public static void main(String[] args) throws ClassNotFoundException {

        ClassLoader loader = Demo.class.getClassLoader();

        System.out.println(loader);

        loader.loadClass("Test2");

        Class.forName("Test2");

    }
}
