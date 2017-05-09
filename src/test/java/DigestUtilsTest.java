import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/5/7 22:55
 * Email: renhongqiang1397@gmail.com
 */
public class DigestUtilsTest {

    public static void main(String[] args) {
        try {
            String hex = DigestUtils.md5DigestAsHex("llafas92701DSD&".getBytes("utf-8"));
            System.out.println("**********"+hex+"**********");
            System.out.println("**********"+hex.length()+"**********");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
