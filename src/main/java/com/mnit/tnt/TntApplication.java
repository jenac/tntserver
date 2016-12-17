package com.mnit.tnt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource("build.properties")
@SpringBootApplication
public class TntApplication {

	public static void main(String[] args) {
		SpringApplication.run(TntApplication.class, args);
	}
}
