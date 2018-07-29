package com.zhuyizhuo.generator.jdbc;

import com.zhuyizhuo.generator.mybatis.constants.ConfigConstants;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class DBConnector {

    protected static Connection conn;
    protected static Statement stm;

    private String dbDriver;
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;

    public DBConnector(Properties properties){
        this.dbDriver = properties.getProperty(ConfigConstants.DRIVER);
        this.dbUrl = properties.getProperty(ConfigConstants.URL);
        this.dbUsername = properties.getProperty(ConfigConstants.USERNAME);
        this.dbPassword = properties.getProperty(ConfigConstants.PASSWORD);
    }

    /**
     * 方法: connect <br>
     * 描述: 获取数据库连接 <br>
     * 作者: 朱一卓 <br>
     * @throws Exception
     */
    public void connect() {
        try {
            DriverManager.registerDriver((Driver) Class.forName(dbDriver).newInstance());
            conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            stm = conn.createStatement();
            System.out.println("----------connect database success----------");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("----------connect database error----------");
        }
    }
}
