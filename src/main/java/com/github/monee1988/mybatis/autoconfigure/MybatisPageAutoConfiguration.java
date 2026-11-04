package com.github.monee1988.mybatis.autoconfigure;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.monee1988.mybatis.MybatisInterceptor;

/**
 * mybatis-page 自动配置。
 *
 * <p>关键点：MyBatis-Spring 的 {@code SqlSessionFactoryBean} 会自动收集容器中所有
 * {@code org.apache.ibatis.plugin.Interceptor} 类型的 Bean 并注册为插件，
 * 因此这里只需要把 {@link MybatisInterceptor} 注册成 Bean，
 * <b>不需要</b>用户手工给 SqlSessionFactory 配 plugins。</p>
 *
 * <p>同时兼容 Spring Boot 2.x 与 3.x：</p>
 * <ul>
 *   <li>Boot 2.6 及以下读 {@code META-INF/spring.factories}</li>
 *   <li>Boot 2.7 ~ 3.x 读 {@code META-INF/spring/...AutoConfiguration.imports}</li>
 * </ul>
 * 两个文件都已提供，Boot 2.7 会合并去重，不会重复注册。
 *
 * @author monee1988
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({SqlSessionFactory.class, MybatisInterceptor.class})
@ConditionalOnProperty(prefix = "mybatis-page", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(MybatisPageProperties.class)
public class MybatisPageAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MybatisPageAutoConfiguration.class);

    @Bean
    @ConditionalOnMissingBean(MybatisInterceptor.class)
    public MybatisInterceptor mybatisPageInterceptor(MybatisPageProperties properties) {
        String dialectClassName = Dialects.resolve(properties.getDialect());
        MybatisInterceptor interceptor = new MybatisInterceptor();
        interceptor.setDialectClassName(dialectClassName);
        interceptor.setCountEnabled(properties.isCountEnabled());

        if (log.isInfoEnabled()) {
            log.info("mybatis-page 已启用: dialect={}, countEnabled={}",
                    dialectClassName.substring(dialectClassName.lastIndexOf('.') + 1),
                    properties.isCountEnabled());
        }
        return interceptor;
    }
}
