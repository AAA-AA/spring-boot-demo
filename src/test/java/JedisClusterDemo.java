import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/19 11:17
 * Email: renhongqiang1397@gmail.com
 */
public class JedisClusterDemo {

    public static void main(String[] args) throws IOException {
        HostAndPort ip_port_pair = new HostAndPort("127.0.0.1",7000);
        HostAndPort ip_port_pair2 = new HostAndPort("127.0.0.1",7001);
        HostAndPort ip_port_pair3 = new HostAndPort("127.0.0.1",7002);
        //HostAndPort ip_port_pair4 = new HostAndPort("127.0.0.1",7004);
        //HostAndPort ip_port_pair5 = new HostAndPort("127.0.0.1",7005);
        //HostAndPort ip_port_pair6 = new HostAndPort("127.0.0.1",7006);
        Set<HostAndPort> set = new HashSet<HostAndPort>();
        set.add(ip_port_pair);
        set.add(ip_port_pair2);
        set.add(ip_port_pair3);
        //set.add(ip_port_pair4);
        //set.add(ip_port_pair5);
        //set.add(ip_port_pair6);
        JedisCluster jedisCluster = new JedisCluster(set);

        jedisCluster.set("kaka","pengpeng");

        String kaka = jedisCluster.get("kaka");

        jedisCluster.close();


    }
}
