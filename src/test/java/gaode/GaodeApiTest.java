package gaode;

import com.google.common.collect.Lists;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by IntelliJ IDEA ^_^
 *
 * @author : hongqiangren.
 * @date: 2017/11/6 15:45
 * @email: renhongqiang1397@gmail.com
 */
public class GaodeApiTest {

    private static final String appkey = "ff28c11f31d9957a1e6cd92990eebe02";
    private static final String secret = "07ecb94d09d8fa4bb8f9e4a69536eb8c";

    public static void main(String[] args) throws Exception {

        String url = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(6000).setConnectTimeout(6000).build();

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeaders(null);



        String baseUrl = "http://restapi.amap.com/v3/place/text";

        List<NameValuePair> params = getParams();


    }

    private static List<NameValuePair> getParams() throws UnsupportedEncodingException {
        List<NameValuePair> pairs = Lists.newArrayList();
        pairs.add(new BasicNameValuePair("key", appkey));
        pairs.add(new BasicNameValuePair("output", "JSON"));
        pairs.add(new BasicNameValuePair("keywords", "近铁城市广场"));


        String sigRaw = getSign(pairs,secret);

        List<NameValuePair> demo = Lists.newArrayList();
        demo.add(new BasicNameValuePair("a",String.valueOf(23)));
        demo.add(new BasicNameValuePair("b",String.valueOf(12)));
        demo.add(new BasicNameValuePair("f",String.valueOf(8)));
        demo.add(new BasicNameValuePair("c",String.valueOf(67)));
        demo.add(new BasicNameValuePair("d",String.valueOf(48)));

        String demoSig = getSign(demo,"bbbbb");
        System.out.println(DigestUtils.md5Hex(URLEncoder.encode(demoSig, "utf-8")));

        pairs.add(new BasicNameValuePair("sig", DigestUtils.md5Hex(URLEncoder.encode(sigRaw, "utf-8"))));

        return pairs;
    }

    private static String getSign(List<NameValuePair> pairs, String s) {
        Map<String, String> treeMap = new HashMap<>();
        for (NameValuePair pair : pairs) {
            treeMap.put(pair.getName(), pair.getValue());
        }
        String params = formatUrlMap(treeMap, false, true);

        return String.format("%s%s", params, s);
    }

    public static String formatUrlMap(Map<String, String> paraMap, boolean urlEncode, boolean naturalOrd) {
        StringBuilder sb = new StringBuilder();
        Comparator<String> comparator;
        if (naturalOrd) {
            comparator = Comparator.naturalOrder();
        } else {
            comparator = Comparator.reverseOrder();
        }
        Map<String, String> sortedMap = new TreeMap<>(comparator);
        sortedMap.putAll(paraMap);
        sortedMap.forEach((k, v) -> {
            if (urlEncode) {
                try {
                    v = URLEncoder.encode(v, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            sb.append(k).append("=").append(v).append("&");

        });
        String queryString = StringUtils.stripEnd(sb.toString(), "&");
        return queryString;
    }
}
