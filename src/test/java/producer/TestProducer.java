package producer;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/5/10 04:58
 * Email: renhongqiang1397@gmail.com
 */
public class TestProducer {


    public static void main(String[] args) throws InterruptedException {
        long events = Long.parseLong("100000");
        Properties props = new Properties();
        props.put("metadata.broker.list", "127.0.0.1:9093,127.0.0.1:9094");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("partitioner.class", "kafka.producer.DefaultPartitioner");
        props.put("request.required.acks", "1");
        ProducerConfig config = new ProducerConfig(props);
        Producer<String, String> producer = new Producer<String, String>(config);
        while (true) {
            try {
                Date runtime = new Date();
                String ip = "127.0.0.1";
                String msg = runtime + ",www.example.com," + ip;
                KeyedMessage<String, String> data = new KeyedMessage<String, String>("demo", ip, msg);
                producer.send(data);
                Thread.sleep(10);
            }catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
