package ren.com.cn.dao;

import org.springframework.data.redis.core.DefaultTypedTuple;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/18 19:31
 * Email: renhongqiang1397@gmail.com
 */
public interface RedisCacheDao<T> {

    Long delKey(String key);

    Long getExpire(String key, TimeUnit timeUnit);

    Long hashIncreBy(String key, Object field, long incr);

    void putToValue(String key, T value, Long time, TimeUnit timeUnit);

    Boolean hasKey(String key);

    Boolean expireAt(String key, Date date);

    void putToHash(String key, Object objectKey, T value);

    void putToHash(String key, Object objectKey, T value, long time, TimeUnit timeUnit);

    void putToList(String key, T value);

    void putToList(String key, T value, long lIndex, long time, TimeUnit timeUnit);

    void putRightToList(String key, T value);

    void putToList(String key, T value, long lIndex);

    void leftPushAllToList(String key, Collection<T> values);

    void rightPushAllToList(String key, Collection<T> values);

    void putToSortSet(String key, T value, double score);

    void putToSortSet(String key, Set<DefaultTypedTuple> tuples);

    void flushRangeFromList(String key, Long start, Long end);

    T getFromValue(final String key, Class<T> tClass);

    T getFromHash(final String key, Object hashKey, Class<T> tClass);

    List multiGetFromHash(String key, Collection<T> hashKeyList, Class<T> tClass);

    List<T> getFromList(final String key, long size, Class<T> tClass);

    List<T> getFromList(final String key, long top, long tail, Class<T> tClass);

    Set<T> getFromSortSet(final String key, long topSize, Class<T> tClass);

    Set<T> getFromSortSet(String key, long top, long tail, boolean isDesc, Class<T> tClass);

    Set<T> getFromSortSetByScore(String cacheKey
            , long top, long tail, boolean isDesc, Class<T> tClass);

    Set<T> getFromSortSetByScoreWithScores(String cacheKey
            , String min, String max, int offset, int count, boolean isDesc, Class<T> tClass);

    Set<T> getFromSortSetByScore(String cacheKey
            , String min, String max, int offset, int count, boolean isDesc, Class<T> tClass);

    void removeFromHash(String key, Object objectKey);

    Long removeFromList(String key, T value);

    long removeFromSortSet(String key, T value);

    Long getSizeFromSortSet(String key);

    Boolean hasHashKey(String key, Object objectKey);

    long getSizeFromList(String key);

    Long zrank(final String key, Object member);

    Long flushRangeFromZSet(String key, long begin, Long size);

    void sort(String key);

    Double getScoreFromSortSet(String key, String member);

    Long getSizeFromSortSetByScore(String key, String begin, String end);

    Set<T> getFromSortSetByCursorIndex(String cacheKey, Long cursorIndex, Integer pageSize, Class<Long> tClass);

    Long trimSortSet(String key, long start, long end);

    Boolean setNX(String key, String v);

    Long increment(String key, Long number);

    void expire(String key, int seconds);

    void putToSet(String key, String... tuples);

    String popFromSet(String key);
}
