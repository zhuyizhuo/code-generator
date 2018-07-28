package com.zhuyizhuo.generator.mybatis.database;

import com.zhuyizhuo.generator.mybatis.database.pojo.DbTableInfo;
import com.zhuyizhuo.generator.mybatis.database.pojo.MysqlDbInfo;
import com.zhuyizhuo.generator.mybatis.database.vo.ColmDbBean;
import com.zhuyizhuo.generator.mybatis.database.vo.ColmJavaBean;
import com.zhuyizhuo.generator.mybatis.database.vo.JavaTableInfo;
import com.zhuyizhuo.generator.utils.StringUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * class: MysqlHandler <br>
 * description: mysql数据库处理handler <br>
 * time: 2018/7/27 19:10
 *
 * @author yizhuo <br>
 * @version 1.0
 */
public class MysqlHandler {
    protected static Statement stm;

   /**
     * 数据库类型转为java类型和import全路径
     * @param dbColmMap
     * @param ltn
     * @return
     * @throws Exception
     */
    private static List<JavaTableInfo> changeDb2Java(
            Map<String, ColmDbBean> dbColmMap, List<DbTableInfo> ltn) throws Exception {
        List<JavaTableInfo> jTableList = new ArrayList<JavaTableInfo>();
        for (int i = 0; i < ltn.size(); i++) {
            DbTableInfo tableInfo = ltn.get(i);
            String tableName = tableInfo.getTableName();
            ResultSet rs = stm.executeQuery("select * from " + tableName + " where 1 < 0");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            System.out.println(tableName + " have total "+columnCount +" Columns");
            JavaTableInfo jTable = new JavaTableInfo();
            List<ColmJavaBean> l = new ArrayList<ColmJavaBean>();
            for (int j = 1; j <= columnCount; j++) {
                String columnName = rsmd.getColumnLabel(j);
                String columnType = rsmd.getColumnTypeName(j);
                String typeImport = rsmd.getColumnClassName(j);
                String typeName = typeImport.substring(typeImport.lastIndexOf(".") + 1, typeImport.length());
                String key = (tableName + "." + columnName).toUpperCase();
                if (dbColmMap.get(key) != null) {
                    ColmDbBean colmDbBean = dbColmMap.get(key);
                    ColmJavaBean col = new ColmJavaBean();
                    col.setDbColmName(columnName);
                    col.setDbColmType(columnType);
                    col.setColmJdbcType(type2JdbcType(columnType));
                    col.setJavaName(StringUtils.changeColmName2Java(columnName,"_"));
                    col.setTypeName(typeDbToJava(typeName));
                    col.setTypeImport(typeDbToJava(typeImport));
                    col.setComment(colmDbBean.getComment());
                    l.add(col);
                }
            }
            jTable.setDbTableName(tableName);
            jTable.setTableName(StringUtils.changeTableName2Java(tableName,"_"));
            jTable.setTableComment(tableInfo.getTableComment());
            jTable.setTableSchema(tableInfo.getTableSchema());
            jTable.setJavaColmBeans(l);
            jTableList.add(jTable);
        }
        return jTableList;
    }

    public static String typeDbToJava(String type) {
        if ("".equals(type)) {
            return "String";
        } else if (type.equalsIgnoreCase("TIMESTAMP")) {
            return "Date";
        } else if (type.equalsIgnoreCase("oracle.sql.TIMESTAMP")
                || type.equalsIgnoreCase("java.sql.Timestamp")
                || type.equalsIgnoreCase("java.sql.Date")) {
            return "java.util.Date";
        } else if (type.equalsIgnoreCase("double")) {
            return "BigDecimal";
        } else if(type.equalsIgnoreCase("java.lang.Double")){
            return "java.math.BigDecimal";
        }
        else {
            return type;
        }
    }

    public static String type2JdbcType(String dbColmType) {
        if (dbColmType.equals("INT")) {
            return "INTEGER";
        } else if (dbColmType.equals("NUMBER")) {
            return "NUMERIC";
        } else if (dbColmType.equals("DATETIME")) {
            return "TIMESTAMP";
        } else if (dbColmType.equals("VARCHAR2")) {
            return "VARCHAR";
        } else if (dbColmType.equals("NUMBER")) {
            return "NUMERIC";
        } else if (dbColmType.equals("DATE")) {
            return "TIMESTAMP";
        }
        return dbColmType;
    }

}
