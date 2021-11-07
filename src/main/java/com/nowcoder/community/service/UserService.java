package com.nowcoder.community.service;

import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.util.CommunityConstant;
import com.nowcoder.community.util.CommunityUtil;
import com.nowcoder.community.util.MailClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author KangJieyu
 * @date 2021/9/21 11:03
 */
@Service
public class UserService implements CommunityConstant {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 获取配置文件中的域名
     */
    @Value("${community.path.domain}")
    private String domain;

    /**
     * 获取配置文件中的项目名
     */
    @Value("${server.servlet.context-path}")
    private String contextPath;

    /**
     * 根据userId查询用户
     */
    public User findUserById(int id) {
        return userMapper.selectById(id);
    }

    /**
     * 注册方法
     * @param user User对象，主要获取用户的信息，密码、邮箱等
     * @return
     */
    public Map<String, Object> register(User user) {
        Map<String, Object> map = new HashMap<>();
        // 判断用户对象为空
        if (user == null) {
            throw new IllegalArgumentException("参数不能为空！");
        }
        // 判断用户名、密码、邮箱为空
        if (StringUtils.isBlank(user.getUsername())) {
            map.put("usernameMessage", "账号不能为空！");
            return map;
        }
        if (StringUtils.isBlank(user.getPassword())) {
            map.put("passwordMessage", "密码不能为空！");
            return map;
        }
        if (StringUtils.isBlank(user.getEmail())) {
            map.put("emailMessage", "邮箱不能为空");
            return map;
        }
        // 验证账号，是否信息已经注册过
        User u = userMapper.selectByName(user.getUsername());
        if (u != null) {
            map.put("usernameMessage", "该账号已经存在！");
            return map;
        }
        // 验证邮箱，是否信息已经注册过
        u = userMapper.selectByEmail(user.getEmail());
        if (u != null) {
            System.out.println(user.getEmail() + "\t该邮箱已经注册！");
            map.put("mailMessage", "该邮箱已经注册！");
            return map;
        }

        System.out.println("开始注册用户了！");

        // 可以注册用户
        // 生成随机字符串
        user.setSalt(CommunityUtil.generateUUID().substring(0,5));
        // 对密码加密，添加到user对象中
        user.setPassword(CommunityUtil.md5(user.getPassword() + user.getSalt()));
        // 用户类型
        user.setType(0);
        // 用户状态
        user.setStatus(0);
        // 设置用户的激活码
        user.setActivationCode(CommunityUtil.generateUUID());
        // 设置用户的默认头像
        user.setHeaderUrl(String.format("images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));
        user.setCreateTime(new Date());
        // 用户注册信息入库
        userMapper.insertUser(user);
        // 发送邮件，激活邮件
        Context context = new Context();
        context.setVariable("email", user.getEmail());
        // http://localhost:8080/community/activation/101/code
        String url = domain + contextPath + "/activation/" + user.getId() + "/" + user.getActivationCode();
        context.setVariable("url", url);
        String content = templateEngine.process("/mail/activation", context);
        mailClient.sendMail(user.getEmail(), "激活账号", content);

        return map;
    }

    /**
     * 激活用户的账号:
     * 1、重复激活
     * 2、激活码写入错误
     * 3、激活成功
     * @param userId 激活账号的id
     * @param code  激活码
     * @return  返回账号是否激活成功
     */
    public int activation(int userId, String code) {
        // 查询用户
        User user = userMapper.selectById(userId);
        if (user.getStatus() == 1) {
            // 重复激活
            return ACTIVE_REPEAT;
        } else if (user.getActivationCode().equals(code)) {
            // 激活码匹对成功，修改用户状态，返回激活成功
            userMapper.updateStatus(userId, 1);
            return ACTIVE_SUCCESS;
        } else {
            // 激活失败
            return ACTIVE_FAIL;
        }
    }



}
