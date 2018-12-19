package com.lhc.business;


import com.lhc.datamodel.DatabaseConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DatabaseConfig.class})
@ComponentScan(basePackages = "com.lhc.business")

public class BusinessConfig {

}
