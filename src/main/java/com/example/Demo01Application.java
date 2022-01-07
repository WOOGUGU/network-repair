package com.example;

import com.example.repaire.mapper.AdministratorAccountMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.example。repaire.mapper")
@SpringBootApplication
public class Demo01Application {
    @Autowired
    AdministratorAccountMapper administratorAccountMapper;
    public static void main(String[] args) {
        SpringApplication.run(Demo01Application.class, args);
    }

}
