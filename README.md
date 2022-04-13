##mybatis-page-spring-boot-starter

mybatis-page-spring-boot-starter 是基于[mybais-page](https://github.com/monee1988/mybatis-page) springboot项目自动化分页starter

### maven 坐标 :

```
<dependency>
    <groupId>com.github.monee1988</groupId>
    <artifactId>mybatis-page-spring-boot-starter</artifactId>
    <version>1.0.0-RELEASE</version>
</dependency>
```
gradle
```
implementation 'com.github.monee1988:mybatis-page-spring-boot-starter:1.0.0-RELEASE'
```
### 说明
应用此插件默认开发者是基于springboot的项目开发
### 配置指南
#### 1. yml配置方式
```
mybatis:
  type-aliases-package: com.xxx.xxx.entity
  mapper-locations: classpath:/mappers/*Mapper.xml
  page-interceptor:
    interceptorEnabled: true     #开启分页
    dialectClassName: com.github.monee1988.mybatis.dialect.MySqlDialect      #MySqlDialect分页方言
#   dialectClassName: com.github.monee1988.mybatis.dialect.SqlServerDialect  #SqlServerDialect分页方言
#   dialectClassName: com.github.monee1988.mybatis.dialect.OracleDialect     #OracleDialect分页方言
```
#### 2. properties配置方式
```
mybatis.type-aliases-package=com.xxx.xxx.entity
mybatis.mapper-locations=classpath:/mappers/*Mapper.xml
#开启分页
mybatis.page-interceptor.interceptorEnabled=true
#MySqlDialect分页方言
mybatis.page-interceptor.dialectClassName=com.github.monee1988.mybatis.dialect.MySqlDialect
#SqlServerDialect分页方言
#mybatis.page-interceptor.dialectClassName=com.github.monee1988.mybatis.dialect.SqlServerDialect
#OracleDialect分页方言
#mybatis.page-interceptor.dialectClassName=com.github.monee1988.mybatis.dialect.OracleDialect
```
#### 3. 传统项目配置方式

[请参考mybatis-page项目的分页拦截器配置说明](https://github.com/monee1988/mybatis-page#1-%E5%88%86%E9%A1%B5%E6%8B%A6%E6%88%AA%E5%99%A8%E9%85%8D%E7%BD%AE?_blank)




