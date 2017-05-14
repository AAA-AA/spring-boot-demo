import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ren.com.cn.Application;
import ren.com.cn.domain.entity.Email;
import ren.com.cn.service.MailService;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/5/11 21:56
 * Email: renhongqiang1397@gmail.com
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    public void testSimpleMail() {
        Email email = new Email();
        email.setFrom("820941512@qq.com");
        email.setTo("1296805631@qq.com");
        email.setSubject("this is a test");
        email.setContent("lalalal");
        mailService.send(email);
    }


}
