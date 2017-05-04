package queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/23 20:05
 * Email: renhongqiang1397@gmail.com
 */
public class BlockingQueueTest2 {

    public static void main(String[] args) {

        final BlockingQueue queue = new ArrayBlockingQueue<>(3);

        for (int i = 0; i < 2; i++) {
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep((long) Math.random() * 1000);
                            System.out.println(Thread.currentThread().getName() + " 准备放数据");
                            queue.put(1);
                            System.out.println(Thread.currentThread().getName() + " 已经放了数据，队列目前有" + queue.size() + " 个数据");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep((long) Math.random() * 1000);
                        System.out.println(Thread.currentThread().getName() + " 准备取数据");
                        queue.take();
                        System.out.println(Thread.currentThread().getName() + " 已经取数据，队列目前有" + queue.size() + " 个数据");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
