package com.nowcoder.community;

import com.nowcoder.community.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * 测试邮件发送
 *
 * @author KangJieyu
 * @date 2021/10/2 10:25
 */
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MailTests {

    /**
     * Spring Boot 框架管理的模板引擎
     */
    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private MailClient mailClient;

    @Test
    public void mailTests() {
        for (int i = 0; i < 20; i++) {
            mailClient.sendMail("1449550732@qq.com", "sb", "sb");
        }
    }

    @Test
    public void htmlMailTest() {
        // context对象，模板引擎参数构造
        Context context = new Context();
        context.setVariable("username", "sunday");
        // html静态内容与动态内容整合，并返回结构字符串
        String process = templateEngine.process("/mail/demo", context);
        System.out.println(process);
        mailClient.sendMail("1449550732@qq.com", "TEST", process);

    }
}
