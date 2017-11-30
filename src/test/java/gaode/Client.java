package gaode;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.Asserts;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by IntelliJ IDEA ^_^
 *
 * @author : hongqiangren.
 * @date: 2017/11/16 00:35
 * @email: renhongqiang1397@gmail.com
 */
@Slf4j
public class Client {

    public static final RequestConfig DEFAULT_CONFIG = RequestConfig.custom()
            .setSocketTimeout(6000)
            .setConnectTimeout(6000).build();
    private RequestConfig requestConfig;

    public <T> T post(Class<T> clazz, String url, List<BasicNameValuePair> params) {
        return post(clazz,url,params,null);
    }

    public <T> T post(Class<T> clazz, String url, List<BasicNameValuePair> params, Header... headers) {
        T result = null;
        String response = post(url, params,headers);
        if (response != null && response.trim().length() > 0) {
            result = JSON.parseObject(response, clazz);
        }
        return result;

    }

    public String post(String url, List<BasicNameValuePair> params) {
        return post(url, params, null);
    }


    public String post(String url, List<BasicNameValuePair> params, Header... headers) {
        Asserts.notEmpty(url, "传入url为空");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        if (params != null && params.size() > 0) {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
            httpPost.setEntity(entity);
        }
        if (headers != null) {
            httpPost.setHeaders(headers);
        }
        log.info(String.format("request url:%s,request body:%s",url,JSON.toJSONString(params)));
        String response = getResponse(httpClient,httpPost);
        return response;
    }


    public String get(String url, List<NameValuePair> params) {
        return get(url,params,null);
    }

    public <T> T get(Class<T> clazz,String url,List<NameValuePair> params) {
        return get(clazz,url,params,null);
    }


    public <T> T get(Class<T> clazz,String url,List<NameValuePair> params,Header... headers) {
        T result = null;
        String response = get(url,params,headers);
        if (response != null && response.trim().length() > 0) {
            result = JSON.parseObject(response,clazz);
        }
        return result;
    }

    public String get(String url, List<NameValuePair> params, Header... headers) {
        Asserts.notEmpty(url, "传入url为空");
        Asserts.check(url.lastIndexOf("?") == -1,"url参数中包含非法字符");
        String realUrl = assembleGetUrl(url, params);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(realUrl);
        httpget.setConfig(requestConfig);
        httpget.setHeaders(headers);
        log.info(String.format("request url:%s",realUrl));
        String response = getResponse(httpclient, httpget);

        return response;
    }

    private String getResponse(CloseableHttpClient httpclient, HttpUriRequest request) {
        String response = null;
        try (CloseableHttpResponse httpResponse = httpclient.execute(request)) {
            StatusLine statusLine = httpResponse.getStatusLine();
            HttpEntity entity = httpResponse.getEntity();
            response = EntityUtils.toString(entity, Consts.UTF_8);
            log.info(String.format("http protocol:%s -- statusCode:%s -- reasonPhrase:%s -- responseStr:%s", statusLine.getProtocolVersion(), statusLine.getStatusCode(), statusLine.getReasonPhrase(),response));
        } catch (ClientProtocolException e) {
            log.error("");
        } catch (IOException e) {
            log.error("");
        }
        return response;
    }

    private String assembleGetUrl(String url, List<NameValuePair> params) {
        String realParamStr = null;
        URIBuilder uriBuilder = new URIBuilder();
        if (!url.contains("://")) {
            uriBuilder.setScheme("http");
        } else {
            uriBuilder.setScheme(url.substring(0, url.indexOf("://")));
            url = url.substring(url.indexOf("://") + 3);
        }
        if (url.indexOf("/") != -1) {
            String[] parts = url.split("/");
            uriBuilder.setHost(parts[0]);
            StringBuilder path = new StringBuilder();
            for (int i = 1; i < parts.length; i++) {
                path.append("/").append(parts[i]);
            }
            uriBuilder.setPath(path.toString());
        }else {
            uriBuilder.setHost(url);
        }
        if (params != null && params.size() > 0) {
            uriBuilder.setParameters(params);
        }
        try {
            realParamStr = uriBuilder.build().toString();
        } catch (URISyntaxException e) {
            log.error("");
        }
        return realParamStr;
    }


    public RequestConfig getRequestConfig() {
        if (this.requestConfig == null) {
            this.requestConfig = DEFAULT_CONFIG;
        }
        return requestConfig;
    }

    public void setRequestConfig(RequestConfig requestConfig) {
        this.requestConfig = requestConfig;
    }

    public Client(ClientBuilder builder) {
        this.requestConfig = builder.requestConfig;
    }

    private Client(RequestConfig config) {
        this.requestConfig = config;
    }

    public static Client newInstance() {
        return new Client(DEFAULT_CONFIG);
    }

    public static Client.ClientBuilder custom() {
        return new ClientBuilder();
    }


    public static class ClientBuilder {
        private RequestConfig requestConfig;

        ClientBuilder() {
            this.requestConfig = DEFAULT_CONFIG;
        }

        public ClientBuilder setRequestConfig(RequestConfig requestConfig) {
            this.requestConfig = requestConfig;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }




    public static void main(String[] args) {
        RequestConfig config = RequestConfig.custom()
                .setSocketTimeout(6000)
                .setConnectTimeout(6000).build();
        Client client = Client.custom().setRequestConfig(config).build();
        List<NameValuePair> nameValuePairs = Lists.newArrayList();
        nameValuePairs.add(new BasicNameValuePair("wd","fjdfjd"));


        System.out.println(client.get("www.baidu.com/s", nameValuePairs, null));


    }
}
