package mutiThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/17 23:27
 * Email: renhongqiang1397@gmail.com
 */
public class MutiThreadTest {


    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(100);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            list.add(i);
        }
        System.out.println("start!!!");
        Long now = System.currentTimeMillis();
        for (int i = 0;i < list.size();i++) {
            int finalI = i;
            service.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    caculate(list.get(finalI));
                    return null;
                }
            });
        }
        service.shutdown();
        Long cost = System.currentTimeMillis()-now;
        System.out.println(String.format("cost : %ss",cost/1000));
    }

    public static void caculate(Integer e) {
        System.out.println(String.format("******%s*******",e)+Thread.currentThread().getName());
    }
}
