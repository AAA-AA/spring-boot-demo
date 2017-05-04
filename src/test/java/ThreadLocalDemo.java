import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/22 18:51
 * Email: renhongqiang1397@gmail.com
 */
public class ThreadLocalDemo {

    private static int data = 0;

    private static ThreadLocal<Integer> threadData = new ThreadLocal<Integer>();

    private static Map<Thread, Integer> map = new HashMap<Thread, Integer>();

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        int data = new Random().nextInt(20);
                        threadData.set(data);
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + " put   data is " + threadData.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();

        }
    }
    class A {
        public void get() {
            System.out.println(Thread.currentThread().getName() + "  A get the data:" + threadData.get());
        }
    }

    class B {
        public void get() {
            System.out.println(Thread.currentThread().getName() + "  B get the data:" + threadData.get());
        }
    }
}




class MyThreadScopeData {

    private MyThreadScopeData instance = null;

    private static ThreadLocal<MyThreadScopeData> map = new ThreadLocal<>();

    private MyThreadScopeData() {
    }

    public synchronized MyThreadScopeData getInstance() {
        MyThreadScopeData instance = map.get();
        if (instance == null) {
            instance = new MyThreadScopeData();
            map.set(instance);
        }
        return instance;
    }
}



