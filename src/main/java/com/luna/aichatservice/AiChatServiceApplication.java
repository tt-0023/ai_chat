package com.luna.aichatservice;

import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
public class AiChatServiceApplication implements CommandLineRunner {


    @Autowired
    private DataSource dataSource;
    @Autowired
    private StringRedisTemplate redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(AiChatServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("===========开始测试环境===========");
//测试mysql
        try (Connection conn = dataSource.getConnection()) {
            System.out.println("mysql连接成功！数据库是：" + conn.getCatalog());
        } catch (Exception e) {
            System.out.println("mysql连接失败！" + e.getMessage());
        }
//        测试redis
        try{
            redisTemplate.opsForValue().set("test_key", "hello Redis!");
            String value = redisTemplate.opsForValue().get("test_key");
            System.out.println("redis连接成功！" + value);
        }catch (Exception e){
            System.out.println("redis连接失败！" + e.getMessage());
        }
        System.out.println("===========结束测试环境===========");
    }
}
