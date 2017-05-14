import testentity.Food;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/5/9 18:30
 * Email: renhongqiang1397@gmail.com
 */
public class CollectionSortDemo {




    public static void main(String[] args) {

        Food food1 = new Food(LocalDate.now(),"food1");

        Food food2 = new Food(LocalDate.now().plusDays(2L),"food2");
        Food food3 = new Food(LocalDate.now().plusDays(3L),"food3");

        Food food4 = new Food(LocalDate.now().plusDays(1L),"food4");

        List<Food> list = new ArrayList<>();
        list.add(food1);
        list.add(food2);
        list.add(food3);
        list.add(food4);

        Collections.sort(list, new Comparator<Food>() {
            @Override
            public int compare(Food o1, Food o2) {
                if (o1.getMtime().isBefore(o2.getMtime())) {
                    return 1;
                }else {
                    return -1;
                }

            }
        });

        list.stream().forEach(food -> {
            System.out.println(food.getName());
        });




    }
}
