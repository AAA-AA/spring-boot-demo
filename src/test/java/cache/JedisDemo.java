package cache;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;
import sun.misc.Cleaner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/5/3 19:43
 * Email: renhongqiang1397@gmail.com
 */
public class JedisDemo {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("10.101.91.246",8035,3000);
        for (int i = 0;i < 100;i++) {
            NumTest numTest = new NumTest();
            numTest.setValue(i);
            numTest.setDate(LocalDate.now());
            jedis.set(String.format("num_%s",i), JSON.toJSONString(numTest));
        }

        for (int i = 0;i < 100;i++) {
            String value = jedis.get(String.format("num_%s", i));
            NumTest numTest = JSON.parseObject(value, NumTest.class);
            System.out.println(numTest);
        }


        String lala = jedis.get("lala");


        jedis.sadd("set1","a","b","c");
        jedis.sadd("set2","c","d","k","123","fjasf");

        Set<String> set1 = jedis.spop("set1",10);
        //Set<String> set = jedis.sunion(jedis.get("set1"), jedis.get("set2"));

        System.out.println(set1);

        jedis.close();

    }

    static class NumTest {
        private Integer value;
        private LocalDate date;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return "NumTest{" +
                    "enable=" + value +
                    ", date=" + date +
                    '}';
        }
    }
}
