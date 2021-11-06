package com.nowcoder.community.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * 项目的工具类
 *
 * @author KangJieyu
 * @date 2021/11/3 21:35
 */
public class CommunityUtil {

    /**
     * 生成随机的字符串，UUID 8-4-4-4-16字符
     * 使用 java.util.UUID 类 randomUUID() 返回一个 UUID 对象
     * @return
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 使用 MD5 算法加密
     * MD5 只能加密，不能解密，安全性不高
     * 用户输入密码中加入一个随机字符串，再加密，提高了安全性
     * @param key
     * @return
     */
    public static String md5(String key) {
        // 判断 key 是否为 null、"" 或 "  "
        if (StringUtils.isBlank(key)) {
            return null;
        }
        // spring工具 将加密的结果为16进制的字符串返回
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }




}
