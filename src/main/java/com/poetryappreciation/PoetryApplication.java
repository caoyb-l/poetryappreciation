package com.poetryappreciation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.poetryappreciation.mapper")
public class PoetryApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoetryApplication.class, args);
    }

}
