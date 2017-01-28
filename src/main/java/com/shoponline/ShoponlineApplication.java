package com.shoponline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootApplication()
@WebAppConfiguration
public class ShoponlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoponlineApplication.class, args);
	}
}
