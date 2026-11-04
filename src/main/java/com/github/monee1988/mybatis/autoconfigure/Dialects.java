package com.github.monee1988.mybatis.autoconfigure;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 方言别名解析。
 *
 * <p>把配置里的简写（mysql / oracle / sqlserver / postgresql）映射到实现类全限定名；
 * 若配置值本身就是一个类的全限定名，则原样返回，便于用户接入自定义方言。</p>
 *
 * @author monee1988
 */
public final class Dialects {

    private static final String PACKAGE = "com.github.monee1988.mybatis.dialect.support.";

    private static final Map<String, String> ALIASES;

    static {
        Map<String, String> map = new HashMap<>();
        map.put("mysql", PACKAGE + "MySqlDialect");
        map.put("mariadb", PACKAGE + "MySqlDialect");
        map.put("oracle", PACKAGE + "OracleDialect");
        map.put("sqlserver", PACKAGE + "SqlServerDialect");
        map.put("mssql", PACKAGE + "SqlServerDialect");
        map.put("postgresql", PACKAGE + "PostgreSqlDialect");
        map.put("postgres", PACKAGE + "PostgreSqlDialect");
        map.put("kingbase", PACKAGE + "PostgreSqlDialect");
        map.put("opengauss", PACKAGE + "PostgreSqlDialect");
        ALIASES = Collections.unmodifiableMap(map);
    }

    private Dialects() {
    }

    /**
     * 解析方言配置值为全限定类名。
     *
     * @param dialect 别名或全限定类名
     * @return 全限定类名
     */
    public static String resolve(String dialect) {
        if (dialect == null || dialect.trim().isEmpty()) {
            return ALIASES.get("mysql");
        }
        String value = dialect.trim();
        String alias = ALIASES.get(value.toLowerCase(Locale.ROOT));
        return alias != null ? alias : value;
    }

    /** 已注册的别名集合，主要用于报错提示 */
    public static Map<String, String> aliases() {
        return ALIASES;
    }
}
