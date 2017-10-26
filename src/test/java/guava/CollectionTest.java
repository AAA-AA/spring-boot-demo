package guava;

import com.google.common.collect.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/16 18:25
 * Email: renhongqiang1397@gmail.com
 */
public class CollectionTest {

    public static void main(String[] args) {
        List<String> stringList= Lists.newArrayList();//创建List
        Set<String> stringSet= Sets.newHashSet();//创建Set
        Map<String,Map<String,List<String>>> map= Maps.newHashMap();//创建Map

        //初始化时，就指定包含元素
        List<String> listWithElem=Lists.newArrayList("chen","lei","xing");
        Set<String> setWithElem=Sets.newHashSet("chen","lei","xing");

//简单创建不可变集合
        ImmutableList<String> noList= ImmutableList.of("aa","bb","cc");
        ImmutableMap<String,String> noMap= ImmutableMap.of("key1","value1","key2","value2");

//找出2个Map的不同之处与相同之处，以Map形式返回
        ImmutableMap<String,Integer> oneMap=ImmutableMap.of("key1",1,"key2",2);
        ImmutableMap<String,Integer> twoMap=ImmutableMap.of("key11",11,"key2",2);
        MapDifference<String,Integer> diffHadle=Maps.difference(oneMap,twoMap);
        Map<String,Integer> commonMap=diffHadle.entriesInCommon();//{"key2",2},若无共同Entry，则返回长度为0的Map
        Map<String,Integer> diffOnLeft=diffHadle.entriesOnlyOnLeft();//返回左边的Map中不同或者特有的元素
        Map<String,Integer> diffOnRight=diffHadle.entriesOnlyOnRight();//返回右边的Map中不同或者特有的元素
        for(Map.Entry<String, Integer> entry:diffOnRight.entrySet()){
            System.out.println("共同Map中key:"+entry.getKey()+"  enable:"+entry.getValue());
        }

//找出2个Set的不相同的元素和相同的元素，以Set形式返回
        Set<String> oneSet=Sets.newHashSet("chen","lei","java");
        Set<String> twoSet=Sets.newHashSet("chen","lei","hadoop");
        Sets.SetView<String> diffSetHandle=Sets.difference(oneSet, twoSet);//是得到左边中不同或者特有的元素，若无，则返回长度为0的集合
        Set<String> diffImmutable=diffSetHandle.immutableCopy();//返回一个不可变的左边Set中特有元素集合的Set拷贝
        Iterator iter=diffSetHandle.iterator();
        while(iter.hasNext()){
            System.out.println("Set的不同元素："+iter.next().toString());
        }
        Sets.SetView<String> commonSet=Sets.intersection(oneSet, twoSet);
        Sets.SetView<String> union = Sets.union(oneSet, twoSet);
        for (String s: union) {
            System.out.println(s);
        }
        Set<String> commonImmutable=commonSet.immutableCopy();//返回一个不可变的2个Set中共同元素集合的Set拷贝
    }
}
