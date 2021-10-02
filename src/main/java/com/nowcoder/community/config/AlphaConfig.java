package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * 配置类
 *
 * @author KangJieyu
 * @date 2021/9/12 16:06
 */

@Configuration
public class AlphaConfig {

    /**
     * 使用第三方的Bean，beanName为方法名
     * @return
     */
    @Bean
    public SimpleDateFormat simpleDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }


}
