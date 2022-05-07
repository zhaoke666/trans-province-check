package com.cqhg.ensure;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@Slf4j
@MapperScan("com.cqhg.ensure.mapper")
@EnableScheduling
@SpringBootApplication
public class TransProvinceCheckApplication {

    public static void main(String[] args) {
         SpringApplication.run(TransProvinceCheckApplication.class, args);

    }

}
