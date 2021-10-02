package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 删除bean案例
 * @author KangJieyu
 * @date 2021/9/12 15:31
 */

@Service("alpha")
public class AlphaService {

    @Autowired
    private AlphaDao alphaDao;

    public AlphaService() {
        System.out.println("AlphaService .......");
    }

    /**
     * 容器管理Bean的初始化
     * 在构造器之后
     */
    @PostConstruct
    public void init() {
        System.out.println("初始化 AlphaService init()");
    }

    /**
     * 容器管理Bean的销毁
     * 在对象销毁之前
     */
    @PreDestroy
    public void destroy() {
        System.out.println("销毁 AlphaService destroy()");
    }

    public String find() {
        return alphaDao.select();
    }

}
