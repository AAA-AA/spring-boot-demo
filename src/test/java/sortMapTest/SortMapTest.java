package sortMapTest;

import com.google.common.base.Charsets;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;

import java.net.URLEncoder;
import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/19 17:03
 * Email: renhongqiang1397@gmail.com
 */
public class SortMapTest {

    public static void main(String[] args) {

        String url = "lalalalaal";
        String sign = "sign";
        String token = "jfdjjf&*243";
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String nonce = String.valueOf(3);
        String echostr = "demo";

        TreeMap<String, String> paramTreemap = new TreeMap<>();
        paramTreemap.put("token", token);
        paramTreemap.put("timeStamp", timeStamp);
        paramTreemap.put("nonce", nonce);
        paramTreemap.put("sign", sign);


        for (String key : paramTreemap.keySet()) {
            System.out.println(String.format("key is :%s, enable is :%s", key, paramTreemap.get(key)));
        }

        String webUrl = "";
        for (String key : paramTreemap.keySet()) {
            if (!key.equals("sign")) {
                webUrl += String.format("%s=%s&", key, paramTreemap.get(key));
            }
        }
        System.out.println(webUrl.substring(0, webUrl.lastIndexOf("&")));
        String s = String.valueOf(DigestUtils.sha1("nonce=3&timeStamp=1500457596305&token=jfdjjf&*243"));
        System.out.println(s);

        byte[] signStr = DigestUtils.sha1(StringUtils.getBytesUtf8("nonce=3&timeStamp=1500457596305&token=jfdjjf&*243"));

        String reverseSign = String.valueOf(signStr);
        System.out.println(String.format("reverseSign :%s", reverseSign));
        System.out.println(reverseSign.equals(s));


    }
}
