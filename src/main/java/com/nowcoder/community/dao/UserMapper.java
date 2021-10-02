package com.nowcoder.community.dao;

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据库访问组件
 *
 * @author KangJieyu
 * @date 2021/9/20 17:03
 */
@Mapper
public interface UserMapper {

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User selectById(int id);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User selectByName(String username);

    /**
     * 根据邮箱查询用户
     * @param email
     * @return
     */
    User selectByEmail(String email);

    /**
     * 增加一个用户
     * @param user
     * @return 插入数据的行数
     */
    int insertUser(User user);

    /**
     * 修改用户的状态
     * @param id
     * @param status
     * @return 修改数据的行数
     */
    int updateStatus(int id, int status);

    /**
     * 更新头像
     * @param id
     * @param headerUrl
     * @return
     */
    int updateHeader(int id, String headerUrl);

    /**
     * 更新密码
     * @param id
     * @param password
     * @return
     */
    int updatePassword(int id, String password);
}
