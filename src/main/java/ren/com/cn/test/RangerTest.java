package ren.com.cn.test;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/8/18 17:27
 * Email: renhongqiang1397@gmail.com
 */
public class RangerTest {

    public static void main(String[] args) {

        //create a range [a,b] = { x | a <= x <= b}
        Range<Integer> range1 = Range.closed(0, 9);

        String test = null;

        System.out.println(StringUtils.isEmpty(test));

        System.out.print("[0,9] : ");
        printRange(range1);
    }

    private static void printRange(Range<Integer> range1) {
        for (int num: ContiguousSet.create(range1, DiscreteDomain.integers())) {
            System.out.print(num);
        }

    }
}
