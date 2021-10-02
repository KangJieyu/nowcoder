package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * 示例
 *
 * @author KangJieyu
 * @date 2021/9/11 21:12
 */
@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello! cow coder!";
    }

    /**
     * 模拟查询请求
     */
    @RequestMapping("/date")
    @ResponseBody
    public String getDate() {
        return alphaService.find();
    }


    /**
     * 最原始的处理请求，基于servlet
     * @param request
     * @param response
     */
    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {
        // 获取请求数据:请求方式、路径、请求行key-value、参数
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        // 得到所有请求行的key，后遍历得到值   Enumeration迭代器
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            // 请求行名
            String name = enumeration.nextElement();
            // 请求行值
            String value = request.getHeader(name);
            System.out.println(name + ":" + value);
        }
        System.out.println(request.getParameter("code"));
        System.out.println(request.getParameter("name"));

        // 返回响应数据
        response.setContentType("text/html;charset=utf-8");
        // try (流) {} catch() {} 流有close方法时不用手动关闭
        try (
                // 网页输出流
                PrintWriter writer = response.getWriter();
                ) {
            writer.write("<h1>牛客网</h1?>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理GET请求 /student?current=1&limit=20
     * @return
     */
    @RequestMapping(path = "/student", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(//int current, int limit) {
            // name参数名、required可以不传参数、defaultValue参数默认值
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "20") int limit) {
        System.out.println("current = " + current + "\t" + "limit = " + limit);
        return "some students";
    }

    /**
     * 处理GET请求 /student/123
     */
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id) {
        System.out.println("id = " + id);
        return "a student id = " + id;
    }

    /**
     * POST请求
     * @return
     */
    @RequestMapping(path = "/student/save", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age) {
        System.out.println(name + "\t" + age);
        return "name = " + name + ", age = " + age;
    }


    /**
     * 响应数据，返回HTML
     * 将Model对象和View对象放入ModelAndView中
     * @return
     */
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name","张三");
        modelAndView.addObject("age", "21");
        // 模板的路径和名字 templates/demo/view.html
        modelAndView.setViewName("/demo/view");

        return modelAndView;
    }


    /**
     * 响应数据，返回动态HTML
     *
     * @param model 自动实例化
     * @return html模板的路径
     */
    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model) {
        model.addAttribute("name", "北大");
        return "/demo/view";
    }

    /**
     * 通过异步请求响应JSON数据
     * JSON：Java对象返回给浏览器————JS解析————通过JSON（字符串格式），得到一个js对象
     *
     * 在当前页面无刷新的情况下，访问服务器
     *
     * DispatcherServlet调用此方法，找到ResponseBody注解，返回Map类型数据，
     * 自动将map转为JSON字符串，返回浏览器
     */
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "张三");
        map.put("age", 21);
        map.put("salary", 10000.00);
        return map;
    }
    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmpS() {

        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "李四");
        map.put("age", 22);
        map.put("salary", 1000.00);
        list.add(map);
        map = new HashMap<>();
        map.put("name", "王五");
        map.put("age", 10);
        map.put("salary", 10000.00);
        list.add(map);
        return list;
    }













}
