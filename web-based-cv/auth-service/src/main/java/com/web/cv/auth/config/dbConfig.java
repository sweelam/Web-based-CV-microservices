package com.web.cv.auth.config;

import com.web.common.config.DatabaseConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "cv.ds")
public class dbConfig extends DatabaseConfig {
    @Bean
    public DataSource authDS() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        return dataSourceBuilder.url(url)
                .username(username)
                .password(password)
                .build();

    }

    @Bean
    public JdbcTemplate authJdbcTemplate() {
        return new JdbcTemplate(authDS());
    }
}