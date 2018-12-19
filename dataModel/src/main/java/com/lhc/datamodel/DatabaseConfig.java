package com.lhc.datamodel;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({
        "com.lhc.datamodel"
})
@EnableJpaRepositories("com.lhc.datamodel.repository")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@EnableAutoConfiguration
public class DatabaseConfig {


}
