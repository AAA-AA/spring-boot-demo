package ren.com.cn.dao.impl;

import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConverters;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.Tuple;
import ren.com.cn.common.factory.RedisGson;
import ren.com.cn.dao.RedisCacheDao;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/18 22:09
 * Email: renhongqiang1397@gmail.com
 */
public class RedisCacheDaoImpl<T> implements RedisCacheDao<T> {

    private final static Logger LOGGER = LoggerFactory.getLogger(RedisCacheDaoImpl.class);

    private RedisGson redisCacheGson;

    private JedisCluster jedisCluster;

    public Long getExpire(String key, TimeUnit timeUnit) {
        Long result = null;
        try {
            result = timeUnit.convert(jedisCluster.ttl(key), TimeUnit.SECONDS);
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache ttl Error! %s", ex), ex);
        }
        return result;
    }

    @Override
    public Long hashIncreBy(String key, Object field, long incr) {
        Long result = 0L;
        try {
            result = jedisCluster.hincrBy(key, String.valueOf(field), incr);
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache hincrBy Error! %s", ex), ex);
        }
        return result;
    }

    public Long delKey(String key) {
        try {
            return jedisCluster.del(key);
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache del Error! %s", ex), ex);
        }
        return 0L;
    }

    public Long hashIncrBy(String key, Object field, long incr) {
        Long result = 0L;
        try {
            result = jedisCluster.hincrBy(key, String.valueOf(field), incr);
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache hincrBy Error! %s", ex), ex);
        }
        return result;
    }

    public void putToValue(String key, T value, Long time, TimeUnit timeUnit) {
        try {
            jedisCluster.setex(key, (int) TimeoutUtils.
                    toSeconds(time, timeUnit), redisCacheGson.toJson(value));
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache setex Error! %s", ex), ex);
        }
    }

    @Override
    public Boolean hasKey(String key) {
        boolean result = Boolean.FALSE;
        try {
            result = jedisCluster.exists(key);
        } catch (Exception e) {
            LOGGER.error(String.format(" Redis Cache exists Error! %s", e), e);
            e.printStackTrace();
        }
        return result;
    }

    public Boolean expireAt(String key, Date date) {
        try {
            return JedisConverters.toBoolean(jedisCluster.pexpireAt(key, date.getTime()));
        } catch (Exception e) {
            LOGGER.error(String.format(" Redis Cache expireAt Error! %s", e), e);
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

    public String trimList(String key, Integer end) {
        try {
            return jedisCluster.ltrim(key, 0, Long.valueOf(end));
        } catch (Exception e) {
            LOGGER.error(String.format(" Redis Cache ltrim Error! %s", e), e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * hash格式存储数据
     *
     * @param key
     * @param objectKey
     * @param value
     */
    public void putToHash(String key, Object objectKey, T value) {
        try {
            jedisCluster.hset(key, String.valueOf
                    (objectKey), redisCacheGson.toJson(value));
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache hset Error! %s", ex), ex);
        }
    }
    /**
     * hash格式存储数据,设置过期时间
     *
     * @param key
     * @param objectKey
     * @param value
     */
    @Override
    public void putToHash(String key, Object objectKey, T value,long time,TimeUnit timeUnit) {
        try {
            jedisCluster.hset(key, String.valueOf
                    (objectKey), redisCacheGson.toJson(value));
            jedisCluster.expire(key,(int)TimeoutUtils.toSeconds(time,timeUnit));
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache hset Error! %s", ex), ex);
        }
    }

    /**
     * list格式缓存
     *
     * @param key
     * @param value
     */
    public void putToList(String key, T value) {
        try {
            jedisCluster.lpush(key, redisCacheGson.toJson(value));
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache lpush Error! %s", ex), ex);
        }
    }

    /**
     * list格式缓存
     *
     * @param key
     * @param values
     */
    public void leftPushAllToList(String key, Collection<T> values) {
        try {
            int index = 0;
            String[] jsonValues = new String[values.size()];
            for (T value : values) {
                jsonValues[index++] = redisCacheGson.toJson(value);
            }

            jedisCluster.lpush(key, jsonValues);
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache lpush Error! %s", ex), ex);
        }
    }

    /**
     * list格式缓存
     *
     * @param key
     * @param values
     */
    public void rightPushAllToList(String key, Collection<T> values) {
        try {
            int index = 0;
            String[] jsonValues = new String[values.size()];
            for (T value : values) {
                jsonValues[index++] = redisCacheGson.toJson(value);
            }

            jedisCluster.rpush(key, jsonValues);
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache rpush Error! %s", ex), ex);
        }
    }

    /**
     * list格式缓存
     *
     * @param key
     * @param value
     */
    public void putRightToList(String key, T value) {
        try {
            jedisCluster.rpush(key, redisCacheGson.toJson(value));
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache rpush Error! %s", ex), ex);
        }
    }

    @Override
    public void putToList(String key, T value, long lIndex) {
        try {
            jedisCluster.linsert(key, BinaryClient.LIST_POSITION.BEFORE
                    , String.valueOf(lIndex), redisCacheGson.toJson(value));
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache linsert Error! %s", ex), ex);
        }
    }

    @Override
    public void putToList(String key, T value, long lIndex,long time,TimeUnit timeUnit) {
        try {
            jedisCluster.linsert(key, BinaryClient.LIST_POSITION.BEFORE
                    , String.valueOf(lIndex), redisCacheGson.toJson(value));
            jedisCluster.expire(key,(int) TimeoutUtils.toSeconds(time, timeUnit));
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache linsert Error! %s", ex), ex);
        }
    }
    /**
     * sortSet格式缓存
     *
     * @param key
     * @param value
     * @param score
     */
    public void putToSortSet(String key, T value, double score) {
        try {
            jedisCluster.zadd(key, score
                    , redisCacheGson.toJson(value));
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache zadd Error! %s", ex), ex);
        }
    }

    @Override
    public void putToSortSet(String key, Set<DefaultTypedTuple> tuples) {
        try {
            Map<String, Double> sortMap = new
                    LinkedHashMap<String, Double>(tuples.size(), 1);
            for (DefaultTypedTuple tuple : tuples) {
                sortMap.put(redisCacheGson.toJson
                        (tuple.getValue()), tuple.getScore());
            }

            jedisCluster.zadd(key, sortMap);
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache zadd Error! %s", ex), ex);
        }
    }

    /**
     * 读取一个value存储的对象
     *
     * @param key
     * @return
     */
    public T getFromValue(final String key, Class<T> tClass) {
        T result = null;
        try {
            result = (T) redisCacheGson.fromJson(jedisCluster.get(key), tClass);
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache get Error! %s", ex), ex);
        }
        return result;
    }

    /**
     * 读取一个hash存储的对象
     *
     * @param key
     * @param hashKey
     * @return
     */
    public T getFromHash(final String key, Object hashKey, Class<T> tClass) {
        T result = null;
        try {
            result = (T) redisCacheGson.fromJson(jedisCluster.
                    hget(key, String.valueOf(hashKey)), tClass);
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache hget Error! %s", ex), ex);
        }
        return result;
    }


    /**
     * 批量读取一个hash存储的对象
     *
     * @param key
     * @param hashKeyList
     * @return
     */
    public List multiGetFromHash(String key, Collection<T> hashKeyList, Class<T> tClass) {
        List<T> result = new ArrayList();
        try {

            int index = 0;
            String[] jsonValues = new String[hashKeyList.size()];
            for (T value : hashKeyList) {
                jsonValues[index++] = redisCacheGson.toJson(value);
            }

            List<String> list = jedisCluster.hmget(key, jsonValues);
            for (String value : list) {
                result.add((T) redisCacheGson.fromJson(value, tClass));
            }
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache hmget Error! %s", ex), ex);
        }
        return result;
    }


    /**
     * 获取前N条List格式存储的记录
     *
     * @param key
     * @param topSize
     * @return
     */
    public List<T> getFromList(final String key, long topSize, Class<T> tClass) {
        List<T> result = new ArrayList();
        try {
            List<String> list = jedisCluster.lrange(key, 0L, topSize - 1);
            for (String value : list) {
                result.add((T) redisCacheGson.fromJson(value, tClass));
            }

        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache lrange Error! %s", ex), ex);
        }
        return result;
    }

    @Override
    public List<T> getFromList(String key, long top, long tail, Class<T> tClass) {
        List<T> result = new ArrayList();
        try {
            List<String> list = jedisCluster.lrange(key, top, tail);
            for (String value : list) {
                result.add((T) redisCacheGson.fromJson(value, tClass));
            }
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache lrange Error! %s", ex), ex);
        }

        return result;
    }

    /**
     * 获取前N条SortSet格式存储的记录
     *
     * @param key
     * @param topSize
     * @return
     */
    public Set<T> getFromSortSet(final String key, long topSize, Class<T> tClass) {
        Set<T> result = new LinkedHashSet<T>();
        try {
            Set<String> set = jedisCluster.zrange(key, 0L, topSize);

            for (String value : set) {
                result.add((T) redisCacheGson.fromJson(value, tClass));
            }

        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache zrange Error! %s", ex), ex);
        }
        return result;
    }

    /**
     * 获取前N条SortSet格式存储的记录
     *
     * @param key
     * @param member
     * @return
     */
    public Long zrank(final String key, Object member) {
        try {
            return jedisCluster.zrank(key, String.valueOf(member));
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache sismember Error! %s", ex), ex);
        }
        return null;
    }

    @Override
    public Set<T> getFromSortSet(String key, long top, long tail, boolean isDesc, Class<T> tClass) {
        Set<T> result = new LinkedHashSet<T>();
        try {
            Set<String> set = null;
            if (isDesc) {
                set = jedisCluster.zrevrange(key, top, tail);
            } else {
                set = jedisCluster.zrange(key, top, tail);
            }
            for (String value : set) {
                result.add((T) redisCacheGson.fromJson(value, tClass));
            }

        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache zrange Error! %s", ex), ex);
        }
        return result;
    }

    @Override
    public Set<T> getFromSortSetByScore(String key, long top, long tail, boolean isDesc, Class<T> tClass) {
        Set<T> result = new LinkedHashSet<T>();
        try {
            Set<Tuple> set = null;
            if (isDesc)
                set = jedisCluster.zrevrangeWithScores(key, top, tail);
            else
                set = jedisCluster.zrangeWithScores(key, top, tail);

            for (Tuple tuple : set) {
                result.add((T) new DefaultTypedTuple(redisCacheGson.
                        fromJson(tuple.getElement(), tClass), tuple.getScore()));
            }
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache zrangeWithScores Error! %s", ex), ex);
        }
        return result;
    }

    @Override
    public void removeFromHash(String key, Object objectKey) {
        try {
            jedisCluster.hdel(key, String.valueOf(objectKey));
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache hdel Error! %s", ex), ex);
        }
    }

    /**
     * 清空缓存内的所有内容
     *
     * @param key
     */
    @Override
    public void flushRangeFromList(String key, Long start, Long end) {
        try {
            jedisCluster.ltrim(key, start, end);
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache ltrim Error! %s", ex), ex);
        }
    }

    /**
     * 从list中删除所有与Value相等的值
     *
     * @param key
     * @param value
     * @return
     */
    public Long removeFromList(String key, T value) {
        long result = 0L;
        try {
            result = jedisCluster.lrem(key, 0, redisCacheGson.toJson(value));
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache lrem Error! %s", ex), ex);
        }
        return result;
    }

    /**
     * 从set中删除所有与Value相等的值
     */
    public long removeFromSortSet(String key, T value) {
        long result = 0L;
        try {
            result = jedisCluster.zrem(key, redisCacheGson.toJson(value));
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache zrem Error! %s", ex), ex);
        }
        return result;
    }

    @Override
    public Long getSizeFromSortSet(String key) {

        long result = 0L;
        try {
            result = jedisCluster.zcard(key);
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache zcard Error! %s", ex), ex);
        }
        return result;
    }

    @Override
    public Boolean hasHashKey(String key, Object objectKey) {
        boolean result = Boolean.FALSE;
        try {
            result = jedisCluster.hexists(key, String.valueOf(objectKey));
        } catch (Exception e) {
            LOGGER.error(String.format(" Redis Cache hexists Error! %s", e), e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public long getSizeFromList(String key) {

        long result = 0L;
        try {
            result = jedisCluster.llen(key);
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache llen Error! %s", ex), ex);
        }
        return result;
    }

    @Override
    public Long flushRangeFromZSet(String key, long begin, Long size) {
        long result = 0L;
        try {
            result = jedisCluster.zremrangeByRank(key, begin, size);
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache zremrangeByRank Error! %s", ex), ex);
        }
        return result;
    }

    @Override
    public void sort(String key) {
        try {
            jedisCluster.sort(key);
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache sort Error! %s", ex), ex);
        }
    }

    @Override
    public Long getSizeFromSortSetByScore(String key, String begin, String end) {
        long result = 0L;
        try {
            return jedisCluster.zcount(key, begin, end);
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache size Error! %s", ex), ex);
        }
        return result;
    }

    @Override
    public Set<T> getFromSortSetByCursorIndex(String cacheKey, Long offset, Integer count, Class<Long> tClass) {
        Set<T> result = new LinkedHashSet<T>();
        try {
            Set<String> set = null;
            set = jedisCluster.zrangeByScore(cacheKey, "+inf", "-inf", offset.intValue(), count);

            for (String value : set) {
                result.add((T) redisCacheGson.fromJson(value, tClass));
            }
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache zrangeByScore Error! %s", ex), ex);
        }
        return result;
    }

    @Override
    public Double getScoreFromSortSet(String key, String member) {
        Double result = (double) 0;
        try {
            return jedisCluster.zscore(key, member);
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache size Error! %s", ex), ex);
        }
        return result;
    }

    @Override
    public Set<T> getFromSortSetByScore(String cacheKey
            , String min, String max, int offset, int count, boolean isDesc, Class<T> tClass) {
        Set<T> result = new LinkedHashSet<T>();
        try {
            Set<String> set = null;

            if (isDesc) {
                set = jedisCluster.zrevrangeByScore(cacheKey, min, max, offset, count);
            } else {
                set = jedisCluster.zrangeByScore(cacheKey, min, max, offset, count);
            }

            for (String value : set) {
                result.add((T) redisCacheGson.fromJson(value, tClass));
            }
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache zrangeByScore Error! %s", ex), ex);
        }
        return result;
    }


    @Override
    public Set<T> getFromSortSetByScoreWithScores(String cacheKey
            , String min, String max, int offset, int count, boolean isDesc, Class<T> tClass) {
        Set<T> result = new LinkedHashSet<T>();
        try {
            Set<Tuple> set = null;

            if (isDesc) {
                set = jedisCluster.zrevrangeByScoreWithScores(cacheKey, min, max, offset, count);
            } else {
                set = jedisCluster.zrangeByScoreWithScores(cacheKey, min, max, offset, count);
            }
            for (Tuple tuple : set) {
                result.add((T) new DefaultTypedTuple(redisCacheGson.
                        fromJson(tuple.getElement(), tClass), tuple.getScore()));
            }
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache zrangeByScore Error! %s", ex), ex);
        }
        return result;
    }

    @Override
    public Long trimSortSet(String key, long start, long end) {
        Long result = 0L;
        try {
            result = jedisCluster.zremrangeByRank(key, start, end);
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache zrangeByScore Error! %s", ex), ex);
        }
        return result;
    }

    private void redisDelete(String key) {
        try {
            jedisCluster.del(key);
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache Delete Error! %s", ex), ex);
        }
    }

    @Override
    public Boolean setNX(String key, String v) {
        try {
            return BooleanUtils.toBoolean(jedisCluster.setnx(key, v).intValue());
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache setNX Error! %s", ex), ex);
        }
        return BooleanUtils.toBoolean(0);
    }

    @Override
    public Long increment(String key, Long number){
        Long count = null;
        try {
            return jedisCluster.incrBy(key, number);
        } catch (Exception ex){
            LOGGER.error(String.format(" Redis Cache increment number Error! %s", ex), ex);
        }
        return count;
    }

    @Override
    public void expire(String key, int seconds) {
        try {
            jedisCluster.expire(key, seconds);
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache expire Error! %s", ex), ex);
        }
    }

    @Override
    public void putToSet(String key, String... tuples) {
        try {

            jedisCluster.sadd(key, tuples);
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache sadd Error! %s", ex), ex);
        }
    }

    @Override
    public String popFromSet(String key) {
        String result = null;
        try {

            result = jedisCluster.spop(key);
        } catch (Exception ex) {
            LOGGER.error(String.format(" Redis Cache lpop Error! %s", ex), ex);
        }
        return result;
    }

    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

}
