package ren.com.cn.test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author : hongqiangren.
 * @since: 2018/1/17 17:55
 */
public class Producer implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Producer.executorService.scheduleAtFixedRate(new Producer(200 * 1024 * 1024 / 1000, 5), 0, 100, TimeUnit.MILLISECONDS);
        Producer.executorService.scheduleAtFixedRate(new Producer(50 * 1024 * 1024 / 1000, 120), 0, 100, TimeUnit.MILLISECONDS);
        TimeUnit.MINUTES.sleep(10);
        Producer.executorService.shutdownNow();

    }



        private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

        private Deque<byte[]> deque;
        private int objectSize;
        private int queueSize;

        public Producer(int objectSize, int ttl) {
            this.deque = new ArrayDeque<byte[]>();
            this.objectSize = objectSize;
            this.queueSize = ttl * 1000;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                deque.add(new byte[objectSize]);
                if (deque.size() > queueSize) {
                    deque.poll();
                }
            }
        }
}
