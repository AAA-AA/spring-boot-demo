import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/26 17:51
 * Email: renhongqiang1397@gmail.com
 */
public class ConsumerMsgTask implements Runnable {

    private KafkaStream m_stream;
    private int m_threadNumber;

    public ConsumerMsgTask(KafkaStream stream, int threadNumber) {
        m_threadNumber = threadNumber;
        m_stream = stream;
    }


    @Override
    public void run() {
        ConsumerIterator<byte[], byte[]> it = m_stream.iterator();
        while (it.hasNext())
            System.out.println("Thread " + m_threadNumber + ": "
                    + new String(it.next().message()));
        System.out.println("Shutting down Thread: " + m_threadNumber);
    }
}
