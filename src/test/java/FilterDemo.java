import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : hongqiangren.
 * Date: 2017/10/19 11:17
 * Email: renhongqiang1397@gmail.com
 */
public class FilterDemo {

    public static void main(String[] args) {
        List<String> lines = Arrays.asList("spring", "node", "mkyong");

    /* The equivalent example in Java 8, using stream.filter() to
  filter a list, and collect() to convert a stream.
   */
        List<String> result1 = lines.stream()  // convert list to stream
                .filter(line -> {
                    return !(line.length() == 4);
                }) // filter the line which equals to "mkyong"
                .collect(Collectors.toList());  // collect the output and convert streams to a list

        result1.forEach(System.out::println); // output "spring", "node"
    }
}
