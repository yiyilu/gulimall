package com.atguigu.gulimall.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//启动服务的注册和发现（配置nacos注册中心地址）
@EnableDiscoveryClient
//因为网关层引入了common包，common包里面导入了mybatis，也就是需要有一些数据源的配置。
//网关层这里不需要，所以排除一下数据源的配置就行
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class GulimallGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallGatewayApplication.class, args);
    }

}
