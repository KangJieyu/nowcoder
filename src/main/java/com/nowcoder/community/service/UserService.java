package com.nowcoder.community.service;

import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author KangJieyu
 * @date 2021/9/21 11:03
 */
@Service
public class UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    /**
     * 根据userId查询用户
     */
    public User findUserById(int id) {
        return userMapper.selectById(id);
    }



}
