package com.yqq.yqqconfigservidor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class YqqConfigServidorApplication {

	public static void main(String[] args) {
		SpringApplication.run(YqqConfigServidorApplication.class, args);
	}

}
