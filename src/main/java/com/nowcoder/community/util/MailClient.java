package com.nowcoder.community.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


/**
 * 封装发送邮件的工具类，实现复用
 *
 * @author KangJieyu
 * @date 2021/10/2 10:01
 */
@Component
public class MailClient {
    private static final Logger logger = LoggerFactory.getLogger(MailClient.class);

    /**
     * 发送邮件的核心组件 Spring容器管理
     * 创建 MineMessage 对象，邮件主体 和 send方法 发送主体
     */
    @Autowired
    private JavaMailSender mailSender;

    /** 发件人 */
    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送邮件的方法，外部调用
     *
     * 构建 MimeMessage：通过 MineMessageHelper 的帮助类，构建详细内容
     *
     * @param to 发送目的地
     * @param subject 发送主题
     * @param context 发送内容
     */
    public void sendMail(String to, String subject, String context) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            // 支持html文本
            helper.setText(context, true);
            mailSender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            logger.error("发送邮件失败：" + e);
        }
    }

}
