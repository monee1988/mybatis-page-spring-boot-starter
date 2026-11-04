package com.github.monee1988.mybatis.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * mybatis-page 配置项。
 *
 * <pre>
 * mybatis-page:
 *   enabled: true          # 总开关，默认开
 *   dialect: mysql         # mysql / oracle / sqlserver / postgresql，也可直接写实现类全限定名
 *   count-enabled: true    # 是否统计总数，默认开；个别 SQL 无法 count 时设为 false
 * </pre>
 *
 * @author monee1988
 */
@ConfigurationProperties(prefix = "mybatis-page")
public class MybatisPageProperties {

    /** 是否启用分页拦截器 */
    private boolean enabled = true;

    /** 分页方言。支持别名 mysql / oracle / sqlserver / postgresql，或 Dialect 实现类全限定名 */
    private String dialect = "mysql";

    /** 是否执行 count 查询。关闭后 totalCount 恒为 0，仅做 SQL 级分页 */
    private boolean countEnabled = true;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public boolean isCountEnabled() {
        return countEnabled;
    }

    public void setCountEnabled(boolean countEnabled) {
        this.countEnabled = countEnabled;
    }
}
