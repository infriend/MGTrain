package com.magus.a4;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

@MapperScan(
        basePackages = "com.magus.a4.dao",
        annotationClass = Repository.class
)
@SpringBootApplication
public class A4Application {

    public static void main(String[] args) {
        SpringApplication.run(A4Application.class, args);
    }

}
