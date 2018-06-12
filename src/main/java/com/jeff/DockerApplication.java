package com.jeff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.jeff.service.*")
public class DockerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DockerApplication.class, args);
	}
}
