package com.github.zhuyizhuo.generator;

import com.github.zhuyizhuo.generator.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.mybatis.convention.FileOutPathInfo;
import com.github.zhuyizhuo.generator.mybatis.generator.DefaultGenerator;
import com.github.zhuyizhuo.generator.mybatis.generator.Generator;
import com.github.zhuyizhuo.generator.mybatis.generator.factory.GenerateServiceFactory;
import com.github.zhuyizhuo.generator.mybatis.generator.service.GenerateService;
import com.github.zhuyizhuo.generator.mybatis.generator.support.ContextHolder;
import com.github.zhuyizhuo.generator.mybatis.generator.support.MethodInfo;
import com.github.zhuyizhuo.generator.utils.CheckUtils;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ConditionalOnClass({DefaultGenerator.class})
//@ConditionalOnSingleCandidate(DataSource.class)
@EnableConfigurationProperties({CodeGeneratorProperties.class})
@AutoConfigureAfter({DataSourceAutoConfiguration.class})
@ConditionalOnProperty(prefix = "code-generator", value = "enabled", matchIfMissing = true)
public class CodeGeneratorAutoConfiguration {

    private final CodeGeneratorProperties codeGeneratorProperties;

    public CodeGeneratorAutoConfiguration(CodeGeneratorProperties codeGeneratorProperties) {
        this.codeGeneratorProperties = codeGeneratorProperties;
    }

    @Bean
    @ConditionalOnMissingBean(Generator.class)
    public Generator generatorService() {
        Properties properties = new Properties();
        properties.put(ConfigConstants.DB_TYPE, codeGeneratorProperties.getType());
        properties.put(ConfigConstants.DRIVER, codeGeneratorProperties.getDriver());
        properties.put(ConfigConstants.URL, codeGeneratorProperties.getUrl());
        properties.put(ConfigConstants.USERNAME, codeGeneratorProperties.getUsername());
        properties.put(ConfigConstants.PASSWORD, codeGeneratorProperties.getPassword());
        properties.put(ConfigConstants.TABLE_SCHEMA, codeGeneratorProperties.getTableSchema());
        PropertiesUtils.customConfiguration.putAll(properties);
        CheckUtils.checkDatabaseConfig(properties);
        ContextHolder.newInstance(properties);
        GenerateService generateService = GenerateServiceFactory.getGenerateService();
        FileOutPathInfo fileOutPathInfo = ContextHolder.getBean("FileOutPathInfo");
        // 需先设置格式化 service
        fileOutPathInfo.init();
        DefaultGenerator generator = new DefaultGenerator(fileOutPathInfo, new MethodInfo(), generateService);
        return generator;
    }

}
