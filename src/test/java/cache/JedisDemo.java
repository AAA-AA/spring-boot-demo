package cache;

import redis.clients.jedis.Jedis;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/5/3 19:43
 * Email: renhongqiang1397@gmail.com
 */
public class JedisDemo {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.80.31",6379,3000);
        jedis.set("lala","aaaa");
        String lala = jedis.get("lala");
        jedis.close();

    }
}
