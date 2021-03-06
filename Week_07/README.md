作业 1 [插入 100 万订单模拟数据](https://github.com/yilefeng/JAVA-01/tree/main/Week_07/src/main/java/com/yilefeng/insert)
作业 2 [读写分离 - 动态切换数据源版本 1.0](https://github.com/yilefeng/JAVA-01/tree/main/Week_07/src/main/java/com/yilefeng/dynamic_src)
作业 3 [读写分离 - 数据库框架版本 2.0](https://github.com/yilefeng/JAVA-01/tree/main/Week_07/src/main/java/com/yilefeng/sharding)

读写分离1.0 与 2.0 使用application.yml配置切换
spring:
  profiles:
    include: sharding | dynamic