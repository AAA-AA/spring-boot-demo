package ren.com.cn.test;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/8/18 16:46
 * Email: renhongqiang1397@gmail.com
 */
public class JoinerTest {

    public static void main(String[] args) {

        Ordering ordering = Ordering.natural();
        List<String> names = Lists.newArrayList();
        names.add("Ram");
        names.add("Shyam");
        names.add("Mohan");
        names.add("Sohan");
        names.add("Ramesh");
        names.add("Suresh");
        names.add("Naresh");
        names.add("Mahesh");
        names.add(null);
        names.add("Vikas");
        names.add("Deepak");

        System.out.println("Another List: ");
        System.out.println(names);

        Collections.sort(names,ordering.nullsFirst());
        System.out.println("Null first then reverse sorted list: ");
        System.out.println(names);

        System.out.println(DigestUtils.sha256Hex("123456").toUpperCase());
    }
}
