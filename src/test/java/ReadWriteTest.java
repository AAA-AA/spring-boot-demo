import javafx.beans.binding.ObjectExpression;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/23 00:48
 * Email: renhongqiang1397@gmail.com
 */
public class ReadWriteTest {


    public static void main(String[] args) {

        final Queue3 q3 = new Queue3();

        for (int i = 0; i < 3; i++) {
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        q3.get();
                    }
                }
            }.start();

            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        q3.put(new Random().nextInt(10000));
                    }
                }
            }.start();
        }
    }
}

class Queue3 {

    private Object data = null;

    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public void get() {
        rwl.readLock().lock();
        System.out.println(Thread.currentThread().getName()+"  be ready to read ");
        try {
            Thread.sleep((long)Math.random()*1000);
            System.out.println(Thread.currentThread().getName()+" read done  "+data);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            rwl.readLock().unlock();
        }

    }

    public void put(Object data) {
        rwl.writeLock().lock();
        System.out.println(Thread.currentThread().getName()+"  be ready to write data ");
        try {
            Thread.sleep((long)Math.random()*1000);
            this.data = data;
            System.out.println(Thread.currentThread().getName()+" write done "+this.data);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            rwl.writeLock().unlock();
        }

    }
}
