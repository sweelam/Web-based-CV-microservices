package com.web.cv.auth.config;

import com.web.common.config.DatabaseConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ConfigurationProperties(prefix = "cv.ds")
public class dbConfig extends DatabaseConfig {
    @Bean
    public HikariDataSource authDS() throws InterruptedException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        HikariDataSource ds = new HikariDataSource(config);
        return ds;
    }

    @Bean
    public JdbcTemplate authJdbcTemplate() throws InterruptedException {
        return new JdbcTemplate(authDS());
    }
}
