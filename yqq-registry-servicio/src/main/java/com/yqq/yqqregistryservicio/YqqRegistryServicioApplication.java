package com.yqq.yqqregistryservicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class YqqRegistryServicioApplication {

    public static void main(String[] args) {
        SpringApplication.run(YqqRegistryServicioApplication.class, args);
    }

}
