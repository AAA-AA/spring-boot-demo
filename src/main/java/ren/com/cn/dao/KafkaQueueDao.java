package ren.com.cn.dao;




/**
 * Created by zhangjing on 15-11-22.
 */
public interface KafkaQueueDao<T> {

    public void send(String topic, T msg) throws Exception;

    /**
     * 获取某个topic下某个消费groupId未消费的队列数
     *
     * @param topic
     * @param groupId
     * @return
     */
}
