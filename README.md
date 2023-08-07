# 个人博客系统（SpringBoot+MyBatis）

## 一、简介

这是一个基于Java语言及 SpringBoot+MyBatis 架构实现的个人博客系统。使用的技术栈包括：Spring Boot + Spring MVC + MyBatis + MySQL + Redis （+ HTML + CSS + JS）。

该项目实现了用户注册，用户登录，用户查询、修改、删除自己发表的文章以及查询其他人发表的文章等博客系统的基本功能，完成对用户表和文章表的操作。

该项目的**亮点**有：

1. **实现了更安全的加密算法。**
2. **改造了 Session 默认存储内存，存储到 Redis。**
3. **复杂查询使用多线程实现，并发编程效率更高。**
4. 实现了分页查询功能。
5. 使用自定义的拦截器进行登录验证。

该项目已部署到云服务器，可通过此链接进行访问：



## 二、功能特点

- 用户注册与登录（session与redis）
- 用户个人文章列表的查询与管理，包括创建、编辑、删除
- 查询所有账号用户的已发表文章（分页查询）
- 文章详情页（并发查询以及展现阅读量）
- 用户权限管理

## 三、技术栈

指明你所使用的主要技术和框架，例如：

- 后端：Spring Boot、SpringMVC、MyBatis
- 前端：HTML、CSS、JavaScript、jQuery等
- 数据库：MySQL
- 中间件：Redis
- 依赖管理：Maven

## 四、环境设置

- JDK版本：JDK 8.0
- Spring Boot版本：2.7.14
- 数据库配置：MySQL 8.0
- Redis配置：Redis 3.2.1
- IDE配置：IntelliJ IDEA Utimate 2022

## 五、安装与配置

1. 克隆项目到本地

2. 按照环境配置，导入项目到IDE

3. 配置数据库连接

   ```java
   spring.datasource.url=jdbc:mysql://127.0.0.1:3306/mycnblog?characterEncoding=utf8
   spring.datasource.username=
   spring.datasource.password=
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   ```

4. 配置MyBatis

   ```java
   mybatis.mapper-locations=classpath:mapper/*Mapper.xml
   mybatis.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
   logging.level.com.example.demo=debug
   ```

5. 配置Redis

   ```java
   spring.redis.host=127.0.0.1
   spring.redis.port=6379
   spring.redis.database=
   spring.redis.password=
   ```

6. 配置session存储信息

   ```java
   spring.session.store-type=redis
   server.servlet.session.timeout=1800
   spring.session.redis.flush-mode=on_save
   spring.session.redis.namespace=spring:session
   ```

7. 运行项目。

## 六、数据库设计

articleinfo文章表：(id（主键）, title（标题）, content（内容）, createtime（创建时间）, updatetime（更新时间）, uid（作者id）, rcount（阅读量）, state（文章状态）)

userinfo用户表：(id（主键）, username（用户名）, password（密码），photo（头像），createtime（创建时间），updatetime（更新时间），state（状态）)

vedioinfo视频表：相关功能，敬请期待！

## 七、使用示例



## 八、联系方式

欢迎讨论项目或提出问题！

微信：riptides_Rq

邮箱：yd.wang.1109@gmail.com





