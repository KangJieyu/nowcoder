package com.nowcoder.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * 创建Bean案例
 * @author KangJieyu
 * @date 2021/9/12 15:17
 */

@Repository
@Primary
public class AlphaDaoMyBatisImpl implements AlphaDao {



    @Override
    public String select() {
        return "MyBatis select()";
    }


}
