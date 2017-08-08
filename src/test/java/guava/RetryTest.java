package guava;

import com.github.rholder.retry.*;
import com.google.common.base.Preconditions;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/16 17:39
 * Email: renhongqiang1397@gmail.com
 */
public class RetryTest {


    public static void main(String[] args) {
        Callable<Boolean> callable = new Callable<Boolean>() {
            public Boolean call() throws Exception {
                checker(null);
                int a = 2 / 0;
                return true; // do something useful here
            }
        };

        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfExceptionOfType(IOException.class)
                .retryIfRuntimeException()
                .withWaitStrategy(WaitStrategies.exponentialWait(10, 5, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .build();
        try {
            retryer.call(callable);
        } catch (RetryException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void checker(String demo) {
        Preconditions.checkNotNull(demo, "The demo may not be null");
    }
}
