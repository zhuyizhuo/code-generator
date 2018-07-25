package com.zhuyizhuo.generator.mybatis.client;

import com.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import org.apache.ibatis.io.Resources;

import java.io.InputStream;
import java.util.Properties;

public class MybatisGenerator {

    private static final String PROPERTIES_FILE_PATH = "generate-config.properties";

    public static void main(String[] args) throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream(PROPERTIES_FILE_PATH);
        Properties proInfo = new Properties();
		proInfo.load(resourceAsStream);

        String type = proInfo.getProperty(ConfigConstants.DB_TYPE);
        System.out.println(type);

        
    }
	
}
