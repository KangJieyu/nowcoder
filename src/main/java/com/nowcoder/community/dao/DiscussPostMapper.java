package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数据库讨论帖
 * @author KangJieyu
 * @date 2021/9/20 20:07
 */
@Mapper
public interface DiscussPostMapper {

    /**
     * 分页查询帖子
     * @param userId 默认为0
     * @param offset 每页起始行的行号
     * @param limit 每页显示的最大数据
     * @return 帖子对象的集合
     */
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    /**
     * 查询表中一共有几条数据(帖子)
     *
     * @Param("alais") 用于给参数取别名
     *            如果只有一个参数，且在if中使用，则必须加别名
     *
     * @param userId 默认为0
     * @return
     */
    int selectDiscussPostRows(@Param("userId") int userId);



}
