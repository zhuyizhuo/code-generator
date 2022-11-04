package com.github.zhuo;

import com.github.zhuyizhuo.generator.mybatis.generator.Generator;
import com.github.zhuyizhuo.generator.utils.TypeConversion;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@SpringBootApplication
public class CodeGeneratorBootStarterApplication {

    @Autowired
    Generator generator;

    public static void main(String[] args) {
        SpringApplication.run(CodeGeneratorBootStarterApplication.class, args);
    }

    @PostConstruct
    public void test(){
        HashMap<String, Class<?>> typeMapper = new HashMap<>();
        typeMapper.put("smallint", Integer.class);
        TypeConversion.init(typeMapper);
        TypeConversion.addType2JdbcType("char", JdbcType.VARCHAR);
        TypeConversion.addType2JdbcType("longblob", JdbcType.VARCHAR);

        generator.generate();
    }
}
