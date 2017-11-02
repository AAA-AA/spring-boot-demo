package ren.com.cn.config;

import com.alibaba.fastjson.JSON;
import org.yaml.snakeyaml.Yaml;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.InputStream;
import java.util.Map;

/**
 * Created by IntelliJ IDEA ^_^
 *
 * @author : hongqiangren.
 * @date: 2017/10/26 11:51
 * @email: renhongqiang1397@gmail.com
 */
public class RedisPool {

    private static JedisPool jedisPool = null;
    private static final String HOST= "host";
    private static final String PORT= "port";
    private static final String MIN_IDLE = "minIdle";
    private static final String MAX_IDLE = "maxIdle";
    private static final String MAX_TOTAL = "maxTotal";
    private static final String TIMEOUT = "timeout";
    private static final String MAX_WAIT_MILLIS = "maxWaitMillis";

    private static Map<String, Map<String, Object>> map = null;

    static {
        Yaml yaml = new Yaml();

        InputStream inputStream = RedisPool.class.getClass().getResourceAsStream("/application.yml");
        if (inputStream == null) {
            throw new IllegalArgumentException("配置文件未找到！");
        }
        map = (Map<String, Map<String, Object>>) yaml.load(inputStream);
        Map<String, Object> redisConfig = map.get("redis");
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            //连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
            config.setBlockWhenExhausted(true);
            //设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
            config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
            //是否启用pool的jmx管理功能, 默认true
            config.setJmxEnabled(true);
            //MBean ObjectName = new ObjectName("org.apache.commons.pool2:type=GenericObjectPool,name=" + "pool" + i); 默认为"pool", JMX不熟,具体不知道是干啥的...默认就好.
            config.setJmxNamePrefix("pool");
            //是否启用后进先出, 默认true
            config.setLifo(true);
            //最大空闲连接数, 默认8个
            Integer maxIdle = (Integer) redisConfig.get(MAX_IDLE);
            config.setMaxIdle(maxIdle);
            //最大连接数, 默认8个
            config.setMaxTotal((Integer) redisConfig.get(MAX_TOTAL));
            //获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
            Integer wait = (Integer) redisConfig.get(MAX_WAIT_MILLIS);
            config.setMaxWaitMillis(Long.valueOf(wait));
            //逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
            config.setMinEvictableIdleTimeMillis(1800000);
            //最小空闲连接数, 默认0
            config.setMinIdle((Integer) redisConfig.get(MIN_IDLE));
            //每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
            config.setNumTestsPerEvictionRun(3);
            //对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)
            config.setSoftMinEvictableIdleTimeMillis(1800000);
            //在获取连接的时候检查有效性, 默认false
            config.setTestOnBorrow(false);
            //在空闲时检查有效性, 默认false
            config.setTestWhileIdle(false);
            //逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
            config.setTimeBetweenEvictionRunsMillis(-1);
            jedisPool = new JedisPool(config, (String) redisConfig.get(HOST),
                    ((Integer) redisConfig.get(PORT)), (Integer) redisConfig.get(TIMEOUT));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void close(final Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
