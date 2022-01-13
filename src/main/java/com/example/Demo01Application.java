package com.example;

import com.example.repair.mapper.AdministratorAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@MapperScan("com.example.repair.mapper")
@SpringBootApplication
public class Demo01Application {
    @Autowired
    AdministratorAccountMapper administratorAccountMapper;

    public static void main(String[] args) {
        SpringApplication.run(Demo01Application.class, args);
    }

}
