import ren.com.cn.domain.entity.Email;
import ren.com.cn.proxy.DynamicProxy;
import ren.com.cn.service.MailService;
import ren.com.cn.service.impl.MailServiceImpl;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/5/17 23:42
 * Email: renhongqiang1397@gmail.com
 */
public class ProxyTest {


    public static void main(String[] args) {

        MailService mailService = new MailServiceImpl();

        DynamicProxy proxy = new DynamicProxy();


        Email email = new Email();
        email.setFrom("820941512@qq.com");
        email.setTo("1296805631@qq.com");
        email.setSubject("this is a test");
        email.setContent("lalalal");







    }
}
