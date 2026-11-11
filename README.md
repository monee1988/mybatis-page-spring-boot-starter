# mybatis-page-spring-boot-starter

[mybatis-page](https://github.com/monee1988/mybatis-page) 的 Spring Boot 自动配置。引入依赖即注册分页拦截器，无需手写 `mybatis-config.xml`。

| 项 | 内容 |
|---|---|
| Maven 坐标 | `com.github.monee1988:mybatis-page-spring-boot-starter:1.1.1-RELEASE` |
| 兼容版本 | Spring Boot 2.x（JDK 8+）与 3.x（JDK 17+） |
| 许可 | Apache License 2.0 |

## 使用

```xml
<dependency>
    <groupId>com.github.monee1988</groupId>
    <artifactId>mybatis-page-spring-boot-starter</artifactId>
    <version>1.1.1-RELEASE</version>
</dependency>
```

同一份 jar 同时兼容 Boot 2.x 与 3.x：装配声明提供 `META-INF/spring.factories`（Boot ≤ 2.6 读取）与 `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`（Boot 2.7 / 3.x 读取）两套，Boot 2.7 会把两者合并去重，不会重复注册。

引入后拦截器自动生效，SQL 里不需要写 `LIMIT`。查询对象准备 `page` 字段、Service 里 `setPage` 的用法见 [mybatis-page 文档](https://github.com/monee1988/mybatis-page)。

## 配置

```yaml
mybatis-page:
  enabled: true          # 总开关，默认 true
  dialect: mysql         # 方言，默认 mysql
  count-enabled: true    # 是否统计总数，默认 true
```

| 配置项 | 默认值 | 说明 |
|---|---|---|
| `mybatis-page.enabled` | `true` | 关闭后拦截器不注册 |
| `mybatis-page.dialect` | `mysql` | 方言别名，或 `Dialect` 实现类全限定名 |
| `mybatis-page.count-enabled` | `true` | `false` 时 `totalCount` 恒为 0，只做 SQL 级分页 |

启动时会输出一行日志，便于确认装配结果：

```
mybatis-page: 已启用: dialect=MySqlDialect, countEnabled=true
```

## 方言别名

| 别名 | 实现类 |
|---|---|
| `mysql`、`mariadb` | `MySqlDialect` |
| `postgresql`、`postgres`、`kingbase`、`opengauss` | `PostgreSqlDialect` |
| `oracle` | `OracleDialect` |
| `sqlserver`、`mssql` | `SqlServerDialect` |

别名不满足需求时直接写实现类的全限定名，例如自定义方言：

```yaml
mybatis-page:
  dialect: com.example.mybatis.DmDialect
```

> 自定义方言需要在类路径上且带无参构造器，starter 通过反射实例化。

## 依赖说明

本 starter 依赖 `mybatis-page`（会自动传递），对 `spring-boot-autoconfigure`、`mybatis`、`slf4j-api` 均为 `provided`，不会把版本强加给使用方。

其中只有 starter 自身用 `slf4j`——Spring Boot 应用必然带 slf4j（`spring-boot-starter-logging`），这是 starter 的惯例。**`mybatis-page` 核心库本身不依赖任何日志框架**，日志跟随 MyBatis `logImpl` 自动探测，纯 MyBatis 项目即使没有 slf4j 也能正常运行。

## 关闭自动配置

只想在部分环境启用时，除了 `mybatis-page.enabled=false`，也可以按标准方式排除：

```java
@SpringBootApplication(exclude = MybatisPageAutoConfiguration.class)
```

## 常见问题

**分页没生效**：确认 `mybatis-page.enabled` 未被设为 `false`；确认查询参数里能取到 `Page` 对象（字段名必须叫 `page`）；把 `logging.level.com.github.monee1988.mybatis` 设为 `debug` 后应能看到 `mybatis-page: 分页` 日志。

**Boot 3 启动报 `javax.servlet` 相关错误**：说明用的还是 1.0.x 的 `mybatis-page`，升级到 1.1.1-RELEASE。

**日志里出现 `mybatis-page: 参数 X 中未找到 page 对象`**：说明该查询的参数是 Map 或实体 Bean 但没有 `page` 属性，插件按普通查询执行了。这是提示不是错误，同类参数只会提示一次；如果该查询本来就不需要分页，忽略即可。

## 许可

[Apache License 2.0](LICENSE)
