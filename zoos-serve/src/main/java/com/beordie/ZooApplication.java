package com.beordie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description //TODO
 * @Version 1
 * @Date 2023/3/1 9:59
 * @User beordie
 */
@SpringBootApplication
@MapperScan("com.beordie.mammalian.mapper")
public class ZooApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZooApplication.class,args);
    }
}
