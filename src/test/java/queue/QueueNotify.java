package queue;

import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/23 20:44
 * Email: renhongqiang1397@gmail.com
 */
public class QueueNotify {

    public static void main(String[] args) {
        Collections.synchronizedMap(new HashMap<>());
        final Demo demo = new Demo();
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i< 20;i++) {
                    demo.sub(i);
                }
            }
        }.start();

        for (int i = 0;i < 15;i++) {
            demo.main(i);
        }
    }
}

class Demo {

    BlockingQueue queue1 = new ArrayBlockingQueue<Integer>(1);

    BlockingQueue queue2 = new ArrayBlockingQueue<Integer>(1);

    {
        try {
            queue1.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void main(int loop) {
        try {
            queue2.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 1;i <= 100;i++) {
            System.out.println("main thread sequence of "+i+" loop of "+loop);
        }
        try {
            queue1.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void sub(int loop) {
        try {
            queue1.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 1;i <= 10;i++) {
            System.out.println("sub thread sequence of "+i+" loop of "+loop);
        }
        try {
            queue2.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
