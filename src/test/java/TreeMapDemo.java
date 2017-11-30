import org.apache.commons.collections.CollectionUtils;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA ^_^
 *
 * @author : hongqiangren.
 * @date: 2017/11/15 19:52
 * @email: renhongqiang1397@gmail.com
 */
public class TreeMapDemo {

    public static void main(String[] args) {
        Map<String,String> parmaMap = new TreeMap<>(Comparator.reverseOrder());
        parmaMap.put("sign","sssss");
        parmaMap.put("token","$&Y%fjsu=");
        parmaMap.put("salt","3431");
        parmaMap.put("appId","spring.boot");

        for(Map.Entry<String,String> entry: parmaMap.entrySet()) {
            System.out.println(String.format("key:%s,value%s",entry.getKey(),entry.getValue()));
        }


    }
}
