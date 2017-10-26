package ren.com.cn.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/8/21 15:18
 * Email: renhongqiang1397@gmail.com
 */
public class SocketChannelReader {

    private Charset charset = Charset.forName("UTF-8");

    private SocketChannel channel;

    public static void main(String[] args) {
        new SocketChannelReader().getHTMLContent();
    }

    public void getHTMLContent() {

        try {
            connect();
            sendRequest();
            readResponse();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(channel!=null){
                try{
                    channel.close();
                }catch(IOException e){}
            }
        }
    }

    private void readResponse() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (channel.read(buffer) != -1) {
            buffer.flip();
            System.out.println(charset.decode(buffer));
            //使用Charset.decode方法将字节转换为字符串
            buffer.clear();//清空缓冲
        }
    }

    private void sendRequest() throws IOException {
        channel.write(charset.encode("GET /document\r\n\r\n"));

    }

    private void connect() throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress("http://www.baidu.com",80);
        channel=SocketChannel.open(socketAddress);
    }
}
