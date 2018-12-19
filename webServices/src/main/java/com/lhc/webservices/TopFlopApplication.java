package com.lhc.webservices;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Import(TopFlopConfig.class)
@ComponentScan(basePackages = {"com.lhc"})
public class TopFlopApplication {

	public static void main(String[] args) {
		SpringApplication.run(TopFlopApplication.class, args);
	}
}
