package com.github.zhuo;

import com.github.zhuyizhuo.generator.mybatis.generator.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CodeGeneratorBootStarterApplication {

    @Autowired
    Generator generator;

    public static void main(String[] args) {
        SpringApplication.run(CodeGeneratorBootStarterApplication.class, args);
    }

    @PostConstruct
    public void test(){
        generator.generate();
    }
}
