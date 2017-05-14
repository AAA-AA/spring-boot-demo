package testentity;

import lombok.Data;

import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/5/9 18:31
 * Email: renhongqiang1397@gmail.com
 */
@Data
public class Food {

    private LocalDate mtime;

    private String name;

    public Food(LocalDate mtime, String name) {
        this.mtime = mtime;
        this.name = name;
    }
}
