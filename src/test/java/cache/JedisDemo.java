package cache;

import redis.clients.jedis.Jedis;

import java.util.Set;

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


        jedis.sadd("set1","a","b","c");
        jedis.sadd("set2","c","d","k","123","fjasf");

        Set<String> set1 = jedis.spop("set1",10);
        //Set<String> set = jedis.sunion(jedis.get("set1"), jedis.get("set2"));

        System.out.println(set1);

        jedis.close();

    }
}
