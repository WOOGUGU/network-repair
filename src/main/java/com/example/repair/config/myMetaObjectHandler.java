package com.example.repair.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Slf4j
@Configuration
public class myMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("maintenanceTime",new Date(),metaObject);
        this.setFieldValByName("preliminarTime",new Date(),metaObject);
        this.setFieldValByName("initiationTime",new Date(),metaObject);
        this.setFieldValByName("preliminarTime",new Date(),metaObject);
        this.setFieldValByName("initiationTime",new Date(),metaObject);
        this.setFieldValByName("releaseTime",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        this.setFieldValByName("updateTime",new Date(),metaObject);
        this.setFieldValByName("releaseTime",new Date(),metaObject);
        this.setFieldValByName("preliminarTime",new Date(),metaObject);
    }
}
