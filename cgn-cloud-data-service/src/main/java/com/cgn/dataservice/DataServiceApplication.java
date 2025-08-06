package com.cgn.dataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 数据服务应用程序
 * <p>
 * 2025/08/06
 *
 * @author zyan
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class DataServiceApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(DataServiceApplication.class, args);
    }
}
