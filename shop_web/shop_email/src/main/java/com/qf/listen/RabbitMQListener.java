package com.qf.listen;

import com.qf.entity.Mail;
import com.qf.utils.MailUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/18 18:50
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Component
public class RabbitMQListener {

    @Autowired
    private MailUtil mailUtil;

    /**
     * 处理rabbitMQ的邮件对象
     */
    @RabbitListener(queues = "email_queue")
    public void emailHandler(Mail mail) {
        //发送邮件
        try {
            mailUtil.sendMail(mail);
            System.out.println("邮件发送成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
