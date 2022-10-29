## 说明：
### 一个基于 seata 的微服务分布式事务管理的演示，使用了以下技术：
1. 基于 springcloud+springboot+openfeign
2. 服务注册中心使用了 nacos
3. seata tc server,是基于1.1，启动方式可以参见： https://github.com/johnyu666/seata-base-demo 中的zip包
4. seata 也注册到了 nacos中，实现了高可用

### 其它：
1. data.sql 数据库初始化
2. test.http 进行接口测试
