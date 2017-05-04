package queue;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/20 23:24
 * Email: renhongqiang1397@gmail.com
 */
public class BlockingQueueTest {

    private List queue = new LinkedList();

    private int limit = 10;

    public BlockingQueueTest(int limit) {
        this.limit = limit;
    }

    public synchronized void enqueue(Object item) throws InterruptedException {
        while (this.queue.size() == this.limit) {
            wait();
        }
        if (this.queue.size() == 0) {
            notifyAll();
        }
        this.queue.add(item);
    }

    public synchronized Object dequeue() throws InterruptedException {
        while (this.queue.size() == 0) {
            wait();
        }

        if (this.queue.size() == this.limit) {
            notifyAll();
        }
        return this.queue.remove(0);
    }
}
