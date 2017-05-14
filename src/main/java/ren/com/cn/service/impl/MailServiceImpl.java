package ren.com.cn.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ren.com.cn.domain.entity.Email;
import ren.com.cn.service.MailService;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/5/11 20:41
 * Email: renhongqiang1397@gmail.com
 */
@Service
public class MailServiceImpl implements MailService {

    private Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void send(Email email) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email.getFrom());
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getContent());
        try {
            mailSender.send(message);
        }catch (Exception e) {
            LOGGER.error("发送邮件失败！{}",e);
        }

    }
}
