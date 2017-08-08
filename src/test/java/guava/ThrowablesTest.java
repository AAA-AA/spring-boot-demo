package guava;

import com.google.common.base.Throwables;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/16 23:24
 * Email: renhongqiang1397@gmail.com
 */
public class ThrowablesTest {

    @Test
    public void testThrowables(){
        try {
            throw new Exception();
        } catch (Throwable t) {
            String ss = Throwables.getStackTraceAsString(t);
            System.out.println("ss:"+ss);
            Throwables.propagate(t);
        }
    }

    @Test
    public void call() throws IOException {
        try {
            throw new IOException();
        } catch (Throwable t) {
            Throwables.propagateIfInstanceOf(t, IOException.class);
            throw Throwables.propagate(t);
        }
    }

    @Test
    public void testCheckException(){
        try {
            URL url = new URL("http://ociweb.com");
            final InputStream in = url.openStream();
            // read from the input stream
            in.close();
        } catch (Throwable t) {
            throw Throwables.propagate(t);
        }
    }
}
