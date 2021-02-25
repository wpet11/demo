package com.robin.test.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    /**
     * 创建 test 数据源的配置对象
     */
    @Primary
    @Bean(name = "testDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.test")
    public DataSourceProperties testDataSourceProperties(){
        return new DataSourceProperties();
    }

    /**
     * 创建 test 数据源
     */
    @Bean(name = "testDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test.hikari")
    public DataSource testDataSource(){
        DataSourceProperties properties =  this.testDataSourceProperties();
        return createHikariDataSource(properties);
    }

    /**
     * 创建 user 数据源的配置对象
     */
    @Bean(name = "userDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.user")
    public DataSourceProperties userDataSourceProperties(){
        return new DataSourceProperties();
    }

    /**
     * 创建 user 数据源
     */
    @Bean(name = "userDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.user.hikari")
    public DataSource userDataSource(){
        DataSourceProperties properties =  this.userDataSourceProperties();
        return createHikariDataSource(properties);
    }

    private DataSource createHikariDataSource(DataSourceProperties properties) {
        // 创建 HikariDataSource 对象
        HikariDataSource dataSource = properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
        // 设置线程池名
        if (StringUtils.hasText(properties.getName())) {
            dataSource.setPoolName(properties.getName());
        }
        return dataSource;
    }

}
