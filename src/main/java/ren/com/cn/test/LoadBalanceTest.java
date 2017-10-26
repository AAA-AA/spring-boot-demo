package ren.com.cn.test;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/8/26 06:27
 * Email: renhongqiang1397@gmail.com
 */
public class LoadBalanceTest {


    public static void main(String[] args) {
        Share share1 = new Share();
        share1.setName("sms1");
        share1.setWeight(1);


        Share share2 = new Share();
        share2.setName("sms2");
        share2.setWeight(3);

        Share share3 = new Share();
        share3.setName("sms3");
        share3.setWeight(6);

        List<Share> list = Lists.newArrayList();
        list.add(share1);
        list.add(share2);
        list.add(share3);

        List<Share> balancedList = Lists.newArrayList();

        for (Share share : list) {
            for (int i = 0; i < share.getWeight(); i++) {
                balancedList.add(share);
            }
        }

        dispatchShare(balancedList);


    }

    private static void dispatchShare(List<Share> balancedList) {
        int total = 100000;
        double counter1 = 0;
        double counter2 = 0;
        double counter3 = 0;
        for (int i = 0; i < total; i++) {
            //int index = RandomUtils.nextInt(0,balancedList.size()-1);
            int index = i % balancedList.size();
            Share share = balancedList.get(index);
            if (share.getName().equals("sms1")) {
                counter1++;
                System.out.println(String.format("%s    send     sms    %s     times", share.getName(), share.getWeight()));
            }
            if (share.getName().equals("sms2")) {
                counter2++;
                System.out.println(String.format("%s    send     sms    %s     times", share.getName(), share.getWeight()));
            }
            if (share.getName().equals("sms3")) {
                counter3++;
                System.out.println(String.format("%s    send     sms    %s     times", share.getName(), share.getWeight()));
            }
        }
        System.out.println(String.format("sms1 send total %s, sms2 send total %s, sms3 total %s", counter1, counter2, counter3));
        System.out.println(String.format("sms1 send share %.2f, sms2 send share %.2f, sms3 share %.2f", counter1 / (counter1 + counter2 + counter3), counter2 / (counter1 + counter2 + counter3), counter3 / (counter1 + counter2 + counter3)));

    }


    static class Share {
        private String name;
        private int weight;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }
}
