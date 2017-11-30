package testentity;

import ren.com.cn.service.MailService;
import ren.com.cn.service.impl.MailServiceImpl;

/**
 * Created by IntelliJ IDEA ^_^
 *
 * @author : hongqiangren.
 * @date: 2017/11/20 16:36
 * @email: renhongqiang1397@gmail.com
 */
public class Demo {

    public static void main(String[] args) {
        MailService mailService = new MailServiceImpl();


        if (MailServiceImpl.class == mailService.getClass()) {
            System.out.println(mailService.getClass());
        }




    }
}
