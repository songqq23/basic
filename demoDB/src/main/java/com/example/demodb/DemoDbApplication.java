package com.example.demodb;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //开启注解方式的事务管理
@Slf4j
@MapperScan("com.example.demodb.Mapper")
@ServletComponentScan
public class DemoDbApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoDbApplication.class, args);
        log.info("server started");
    }

}
