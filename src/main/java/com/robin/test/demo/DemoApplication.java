package com.robin.test.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
@ComponentScan(basePackages = {"com.csf.common.http.config", "com.robin.test.demo"})
@MapperScan(basePackages = "com.robin.test.demo.dao")
public class DemoApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Resource(name = "testDataSource")
    private DataSource testDataSource;

    @Resource(name = "userDataSource")
    private DataSource userDataSource;


    @Override
    public void run(String... args) throws Exception {
        try (Connection conn = testDataSource.getConnection()) {
            // 这里，可以做点什么
            logger.info("[run][testDataSource 获得连接：{}]", conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // user 数据源
        try (Connection conn = userDataSource.getConnection()) {
            // 这里，可以做点什么
            logger.info("[run][userDataSource 获得连接：{}]", conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
