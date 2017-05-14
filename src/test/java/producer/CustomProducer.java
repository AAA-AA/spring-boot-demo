package producer;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/24 10:14
 * Email: renhongqiang1397@gmail.com
 */
public class CustomProducer {

    public static Map<String,BlockingQueue> map = new ConcurrentHashMap<String, BlockingQueue>();

    static BlockingQueue queue = new ArrayBlockingQueue<>(20);

    static {
        map.put("topic",queue);
    }

    public static void produce(String message) {
        while (true) {
            try {
                Thread.sleep((long)Math.random()*1000);
                queue.offer(message);
                System.out.println("put to queue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }


    public static void main(String[] args) {
        for (int i = 0;i < 4;i++) {
            new Thread(){
                @Override
                public void run() {
                    CustomProducer.produce("pengpengpeng");
                }
            }.start();
        }
    }
}
