package lock;

import java.util.HashMap;

public class LockTest {

    private HashMap map = new HashMap();

    public LockTest() {
        while (true) {
            HashMap map = new HashMap();
            System.out.println(String.format("%s is running",Thread.currentThread().getName()));
        }

    }

    public static void main(String[] args) {

        new LockTest();
    }
}
