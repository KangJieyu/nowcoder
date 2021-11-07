package com.nowcoder.community.controller;

import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CommunityConstant;
import com.nowcoder.community.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 登录模块请求
 *
 * @author KangJieyu
 * @date 2021/10/2 20:16
 */
@Controller
public class LoginController implements CommunityConstant {

    @Autowired
    private UserService userService;

    /**
     * 处理注册填写的信息
     * @param model
     * @param user
     * @return 注册成功，转到首页面；注册失败，在注册页面
     */
    @RequestMapping(value = "/register/message", method = RequestMethod.POST)
    public String register(Model model, User user) {
        System.out.println("register()...");
        System.out.println(user);
        Map<String, Object> map = userService.register(user);
        System.out.println("map=>"+map.toString());
        System.out.println("emailMes=>"+map.get("emailMessage"));
        if (map == null || map.isEmpty()) {
            model.addAttribute("mess", "注册成功，我们已经向您的邮箱发送了一封激活邮件，请尽快激活！");
            model.addAttribute("target", "/index");
            return "/site/operate-result";
        } else {
            model.addAttribute("usernameMessage", map.get("usernameMessage"));
            model.addAttribute("passwordMessage", map.get("passwordMessage"));
            model.addAttribute("emailMessage", map.get("mailMessage"));
            return "/site/register";
        }
    }

    /**
     * 用户激活账号的请求
     * 请求形式：http://localhost:8080/community/activation/101/code
     */
    @RequestMapping(path = "/activation/{userId}/{code}", method = RequestMethod.GET)
    public String activation(Model model, @PathVariable("userId") int id,
                             @PathVariable("code") String code) {
        // 激活成功、重复激活、激活失败
        int res = userService.activation(id, code);
        if (res == ACTIVE_SUCCESS) {
            model.addAttribute("mess", "激活成功，您的账号可以正常使用了！");
            model.addAttribute("target", "/login");
        } else if (res == ACTIVE_REPEAT) {
            model.addAttribute("mess", "该账号已经激活过了，请不要重复操作！");
            model.addAttribute("target", "/index");
        } else {
            model.addAttribute("mess", "激活失败，你写入的激活码错误！请重新激活！");
            model.addAttribute("target", "/index");
        }
        return "/site/operate-result";
    }






    /**
     * 处理注册页面的请求，获取注册页面
     * templates/site/register.html
     */
    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String getRegisterPage() {
        return "/site/register";
    }

    /**
     * 处理登录页面的请求，获取登录页面
     * @return
     */
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "/site/login";
    }


}
