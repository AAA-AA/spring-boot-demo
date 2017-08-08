package guava;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/16 18:32
 * Email: renhongqiang1397@gmail.com
 */
public class FileTest {

    public static void main(String[] args) throws IOException {

//复制移动文件
        File from=new File("/Users/hongqiangren./Downloads/a.txt");
        File to=new File("/Users/hongqiangren./Downloads/c.txt");
        Files.copy(from, to);
        //Files.move(from, to);//会删除掉原来的文件

        //一行代码读取内容存入list中
        List<String> list=Files.readLines(from, Charsets.UTF_8);

        //一行代码对文件进行内容的追加 1
        Files.append("zhuijianeirong",to, Charsets.UTF_8);
        List<String> strings = Files.readLines(to, Charsets.UTF_8);
        boolean apply = Files.isDirectory().apply(to);
        System.out.println(apply);


    }
}
