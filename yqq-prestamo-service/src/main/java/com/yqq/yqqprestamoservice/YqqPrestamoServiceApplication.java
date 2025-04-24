package com.yqq.yqqprestamoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class YqqPrestamoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(YqqPrestamoServiceApplication.class, args);
    }

}
