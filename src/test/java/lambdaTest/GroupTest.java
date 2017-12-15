package lambdaTest;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.lang.RandomStringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA ^_^
 *
 * @author : hongqiangren.
 * @date: 2017/12/15 17:38
 * @email: renhongqiang1397@gmail.com
 */
public class GroupTest {


    public static void main(String[] args) {

        List<DemoDto> list = Lists.newArrayList();
        for (int i = 0; i < 5; i++) {
            DemoDto dto = new DemoDto();
            dto.setId((long) i);
            dto.setName(RandomStringUtils.randomAlphabetic(4));
            list.add(dto);
        }

        for (int i = 2; i < 6; i++) {
            DemoDto dto = new DemoDto();
            dto.setId((long) i);
            dto.setName(RandomStringUtils.randomAlphabetic(4));
            list.add(dto);
        }


        Map<Long, List<DemoDto>> collect = list.stream().collect(Collectors.groupingBy(DemoDto::getId));
        System.out.println(JSON.toJSONString(collect));

        Map<Long,Integer> counterMap = new HashMap<>();


    }


    public static class DemoDto {
        private Long id;
        private String name;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
