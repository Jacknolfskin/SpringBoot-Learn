# SpringBoot-Learn

## 介绍
SpringBoot-Learn基于SpringBoot,是个人学习SpringBoot集成示例，将它分享出来目的在于帮助自己和他人更好更快的学习SpringBoot这款优秀的微服务框架,也可以直接作为一个系统的脚手架项目! 

#技术架构
* 前端:Thymeleaf渲染引擎、Html、Ajax
* 后台:SpringBoot、SpringData JPA、Spring Security、Kaptcha、Swagger、WebSocket、ActiveMQ、AliBaba-Driud、FastJson、Spring Session等
* 缓存:Redis
* 数据库:Mysql

## 实现功能
* 登录集成SpringSecurity,根据不同角色展示不同功能权限
* 实现Rememeber Me及验证码功能
* 实现同一用户异地登录只允许一人在线
* 采用Spring Session实现用户Session持久化到Redis，方便Session分布式管理
* 使用JWT实现接口安全设计
* 采用FastJson和thymeleaf实现页面渲染及跳转，返回Json数据用户前后分离
* 实现跨域访问
* 采用ActiveMQ实现消息队列及异步任务处理
* 采用Swagger2生成统一接口文档
* 全局异常配置
* 微信第三方登录及微信和支付宝支付功能
* WebSocket连接实现
* 更多功能自我发现

## 项目特点
1. 基于SpringBoot,简化了大量项目配置和maven依赖,让您更专注于业务开发,独特的分包方式,代码多而不乱。
2. 完善的日志记录体系，可记录登录日志，业务操作日志(可记录操作前和操作后的数据)
3. 重写SpringSecurity MyAuthenticationProvider接口使其支持记住我功能
4. 采用Spring Data JPA简化数据库操作
5. 利用Redis对经常调用的查询进行缓存，提升运行速度，具体请见ConstantFactory类中@Cacheable标记的方法。
6. 防止XSS攻击,通过XssFilter类对所有的输入的非法字符串进行过滤以及替换。
8. 简单可用的代码生成体系，生成带有主页跳转和增删改查的通用控制器、html页面以及相关的js，还可以生成Service和Dao
9. 控制器层统一的异常拦截机制,利用@ControllerAdvice统一对异常拦截,具体见com.personal.exception.GlobalExceptionHandler类。
10. 使用Swagger2统一生成接口文档
11. 使用消息队列异步消息处理及任务调度
12. 基于javabean方式的spring配置，抛弃了传统的易错,臃肿xml配置,采用javabean的方式配置spring,简化了项目的配置
13. 全局异常配置及处理
## 技术讨论 & [博客地址](http://www.codeteen.cn/)
如果对项目有任何疑问或者建议,欢迎到我的博客留言!