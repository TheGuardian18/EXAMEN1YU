package com.yqq.yqqusuarioservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class YqqUsuarioServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(YqqUsuarioServiceApplication.class, args);
    }

}
