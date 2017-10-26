import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/18 14:58
 * Email: renhongqiang1397@gmail.com
 */
public class Demo {
    public static void main(String[] args) {


        List<Integer> list = Arrays.asList(2,1,6,5,3,4,55,4,2,3,4,5);

        List<NumTest> numTests = new ArrayList<>();
        NumTest numTest = new NumTest();
        numTest.setValue(1L);

        NumTest numTest2 = new NumTest();
        numTest2.setValue(3L);

        NumTest numTest3 = new NumTest();
        numTest3.setValue(2L);

        NumTest numTest4 = new NumTest();
        numTest4.setValue(4L);

        NumTest numTest5 = new NumTest();
        numTest5.setValue(2L);

        numTests.add(numTest);
        numTests.add(numTest2);
        numTests.add(numTest3);
        numTests.add(numTest4);
        numTests.add(numTest5);
        System.out.println("before sort:");
        for (NumTest numTest1:numTests) {
            System.out.print(numTest1.getValue()+"\t");
        }
        System.out.println("after sort:");
        numTests.sort(Comparator.comparingLong(NumTest::getValue));
        for (NumTest num:numTests) {
            System.out.print(num.getValue()+"\t");
        }
        System.out.println("****************");

        Integer a = 187;
        Integer b = new Integer("187");
        System.out.println(a.equals(b));




        Long test = 1L;
        System.out.println(String.format("it is %s",test));

        String date = "2017-09-12 08:02:03";
        StringBuffer buffer = new StringBuffer("2017-09-12");
        buffer.append(" ").append("08:02:03");

        String bufferParse = buffer.toString();

        LocalDateTime parse = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        LocalDateTime parse2 = LocalDateTime.parse(bufferParse, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        System.out.println(parse);
        System.out.println(parse2);

        List list1 = list.subList(0,1);
        list.forEach(e -> {
            if (e == 1) {
                return;
            }else {
                System.out.println(e);
            }
        });

        System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    static class NumTest {
        Long value;

        public Long getValue() {
            return value;
        }

        public void setValue(Long value) {
            this.value = value;
        }
    }

}
