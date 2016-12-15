package com.lhmtech.server.scheduleserver

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.PropertySource
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@PropertySource("build.properties")
@SpringBootApplication
@ComponentScan(basePackages = ["com.lhmtech.server.scheduleserver", "com.lhmtech.integration.messaging.rabbit"] )
class Application {

	static void main(String[] args) {
		SpringApplication.run Application, args
	}
}
