# spring-boot3-dynamictp-demo

![jdk 17](https://img.shields.io/static/v1?label=jdk&message=17&color=blue)
![spring-boot](https://img.shields.io/static/v1?label=spring-boot&message=3.5.7&color=green)
![dynamic-tp](https://img.shields.io/static/v1?label=dynamic-tp&message=1.2.2-x&color=D58522)

Spring Boot 3 + Dynamic Thread Pool 集成演示项目，展示如何配置和使用动态线程池管理。  

## 项目特性

- ✅ Spring Boot 3.5.7 + Java 17
- ✅ Dynamic-TP 1.2.2-x 动态线程池
- ✅ 线程池监控和告警配置
- ✅ 异步任务执行示例
- ✅ 动态线程池参数调整

## 快速开始

### 环境要求

- JDK 17+
- Maven 3.6+

### 启动应用

```bash
mvn clean package
java -jar target/spring-boot3-dynamictp-demo.jar
```

应用将在 `http://localhost:23398` 启动

## 配置说明

### 动态线程池配置

项目包含以下线程池配置：

1. **threadPoolTaskExecutor** - 异步任务执行器（通过 `Sb3dtdAsyncConfigurer` 配置）
   - 核心线程数：4
   - 最大线程数：10  
   - 队列容量：10
   - 拒绝策略：CallerRunsPolicy

2. **dtpExecutor1** - 动态线程池（通过 `dynamic-tp.yml` 配置）
   - 核心线程数：1
   - 最大线程数：8
   - 队列容量：128
   - 线程名前缀：sb3dtd

### 配置文件

- `src/main/resources/application.yaml` - Spring Boot 应用配置
- `src/main/resources/dynamic-tp.yml` - 动态线程池详细配置

## API 接口

### 线程池管理

- `GET /dynamictp/env?key={key}` - 获取环境变量
- `GET /dynamictp/executor?name={name}` - 获取线程池信息
- `POST /dynamictp/execute?name={name}` - 执行异步任务
- `POST /dynamictp/calc1?value={value}` - 执行计算任务1（使用dtpExecutor1）
- `POST /dynamictp/calc2?value={value}` - 执行计算任务2（使用默认线程池）

### 监控端点

应用启用了 Spring Boot Actuator，可通过以下端点监控线程池状态：

- `/actuator/metrics` - 监控指标
- `/actuator/health` - 健康检查

## 动态线程池特性

### 告警配置

项目配置了多种告警类型：
- 线程池参数变更通知
- 队列容量使用率告警（阈值80%）
- 线程池活性告警（阈值80%）
- 任务拒绝告警
- 任务执行超时告警
- 任务排队超时告警

### 通知平台

支持多种告警通知方式：
- Email 邮件通知
- Slf4j 日志通知

## 项目结构

```
src/main/java/cn/netbuffer/spring/boot3/dynamictp/demo/
├── component/
│   ├── CalcComponent.java          # 异步计算组件
│   ├── Slf4jDtpNotifier.java       # 动态线程池通知器
│   └── Slf4jNotifier.java          # Slf4j通知器
├── config/
│   ├── DynamicTpConfig.java        # 动态线程池配置
│   └── Sb3dtdAsyncConfigurer.java  # 异步配置器
├── controller/
│   └── DynamicTpController.java    # 动态线程池控制器
└── SpringBoot3DynamicTpDemoApplication.java # 主应用类
```

## 相关链接

- [DynamicTP 官方文档](https://dynamictp.cn/guide/use/quick-start.html)
- [Spring Boot 文档](https://docs.spring.io/spring-boot/3.5.7/reference/)
- [Spring Web](https://docs.spring.io/spring-boot/3.5.7/reference/web/servlet.html)
- http://127.0.0.1:23398/actuator/dynamictp

## 开发指南

### 添加新的动态线程池

1. 在 `dynamic-tp.yml` 中添加新的执行器配置
2. 在代码中使用 `@DynamicTp("线程池名称")` 注解
3. 通过 `DtpRegistry.getExecutor("线程池名称")` 获取线程池实例

### 自定义告警通知

实现 `DtpNotifier` 接口并注册为Spring Bean，参考 `Slf4jDtpNotifier` 示例。

## 许可证

MIT License

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.5.7/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.5.7/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.5.7/reference/web/servlet.html)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)