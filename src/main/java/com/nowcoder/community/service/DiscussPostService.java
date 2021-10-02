package com.nowcoder.community.service;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DiscissPost的服务层
 *
 * @author KangJieyu
 * @date 2021/9/21 10:48
 */
@Service
public class DiscussPostService {

    @Autowired(required = false)
    private DiscussPostMapper discussPostMapper;

    /**
     * 查询某一页的帖子
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit) {
        return discussPostMapper.selectDiscussPosts(userId, offset, limit);
    }

    /**
     * 查询用户帖子行数的方法
     * @param userId
     * @return
     */
    public int selectRows(int userId) {
        return discussPostMapper.selectDiscussPostRows(userId);
    }

}
