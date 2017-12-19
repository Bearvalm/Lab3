package com.university.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Created by redric on 10.11.17.
 * Configuration for beans and dependency injection
 */
@Configuration
@ComponentScan("com.university")
@PropertySource("datasource.properties")
public class AppConfig {
    @Autowired
    private Environment env;

    @Bean
    public JdbcTemplate jdbcTemplate() {
        try {
            Class.forName(env.getProperty("classDriver"));
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setPassword(env.getProperty("password"));
        dataSource.setUrl(env.getProperty("url"));
        dataSource.setUsername(env.getProperty("username"));
        return new JdbcTemplate(dataSource);
    }
}
