package consumer;

import producer.Producer;

import java.util.concurrent.BlockingQueue;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/24 10:23
 * Email: renhongqiang1397@gmail.com
 */
public class Consumer {

    static BlockingQueue queue = Producer.map.get("topic");


    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            new Thread(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                        System.out.println("取出数据 "+queue.poll());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }.start();
        }
    }
}
