package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

/**
 * 数据库访问接口实现类
 *
 * @author KangJieyu
 * @date 2021/9/12 15:04
 */

@Repository("hibernate")
public class AlphaDaoHibernateImpl implements AlphaDao {

    @Override
    public String select() {
        return "Hibernate select()";
    }
}
