package ren.com.cn.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/8/21 16:03
 * Email: renhongqiang1397@gmail.com
 */
public class FileChannelTest {
    public static void main(String[] args) {
        try {
            RandomAccessFile aFile = new RandomAccessFile("/Users/hongqiangren./Downloads/study/test.txt","rw");
            FileChannel channel = aFile.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(10);
            int byteRead = channel.read(buffer);
            while (byteRead != -1) {
                System.out.println("Read " + byteRead);
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }
                buffer.clear();
                byteRead = channel.read(buffer);
            }
            aFile.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getStackTrace());
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }
}
