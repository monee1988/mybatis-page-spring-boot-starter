package com.github.monee1988.config;

import com.github.monee1988.mybatis.MybatisInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 数据源配置类
 * @author monee1988
 */
@Configuration
@ConfigurationProperties(
        prefix = "mybatis.page-interceptor"
)
@ConditionalOnProperty(name = "mybatis.page-interceptor.interceptorEnabled",havingValue = "true")
public class PageInterceptorConfig {

    /**
     * 是否开启拦截器
     */
    private boolean interceptorEnabled = false;

    /**
     * 分页方言类名全路径
     */
    private String dialectClassName;

    public void setDialectClassName(String dialectClassName) {

        this.dialectClassName = dialectClassName;
    }

    public void setInterceptorEnabled(boolean interceptorEnabled) {
        this.interceptorEnabled = interceptorEnabled;
    }

    @Bean
    public MybatisInterceptor mybatisInterceptor(){

        return new MybatisInterceptor().setDialect(dialectClassName);
    }




}
