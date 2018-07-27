package com.zhuyizhuo.generator.mybatis.database;

import com.zhuyizhuo.generator.mybatis.database.vo.DbTableInfo;
import com.zhuyizhuo.generator.mybatis.database.vo.MysqlDbInfo;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    private static MysqlDbInfo getMysqlAllTabName() throws Exception {
        /**
         * mysql query all tables
         */
//		SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '数据库名'
        MysqlDbInfo mysqlDbInfo = new MysqlDbInfo();

        String mysqlDbName = "";
        ResultSet executeQuery = stm.executeQuery(" SELECT TABLE_SCHEMA,TABLE_NAME,TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '"+mysqlDbName+"'");

        while (executeQuery.next()) {
            DbTableInfo tableInfo = new DbTableInfo();
            String table = executeQuery.getString("TABLE_NAME");
            String tableName = "";
            if (tableName != null && !"".equals(tableName.trim()) && !table.equalsIgnoreCase(tableName)) {
                continue;
            }
            tableInfo.setTableName(table);
            tableInfo.setTableSchema(executeQuery.getString("TABLE_SCHEMA"));
            tableInfo.setTableComment(executeQuery.getString("TABLE_COMMENT"));
            mysqlDbInfo.addTableInfoList(tableInfo);
        }
        return mysqlDbInfo;
    }

    /*public static void main(String[] args) {
        try {
            List<JavaTableInfo> javaTableList = getList();

            boolean haveBatch = false;
            FtlWriter.writeAll(javaTableList);

            System.out.println("------------finished-----------");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeJDBC();
        }
    }



    *//**
     * 数据库类型转为java类型和import全路径
     * @param dbColmMap
     * @param ltn
     * @return
     * @throws Exception
     *//*
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
//				System.out.print("columnName: " + columnName);
//				System.out.println("  db columnType: " + columnType);
//				System.out.print("java full type: " + typeImport);
//				System.out.println("  java type: " + typeName);

                String key = (tableName + "." + columnName).toUpperCase();

                if (dbColmMap.get(key) != null) {
                    ColmDbBean colmDbBean = dbColmMap.get(key);
                    ColmJavaBean col = new ColmJavaBean();
                    col.setDbColmName(columnName);
                    col.setDbColmType(columnType);
                    col.setColmJdbcType(type2JdbcType(columnType));
                    col.setJavaName(changeColmName2Java(columnName));
                    col.setTypeName(typeDbToJava(typeName));
                    col.setTypeImport(typeDbToJava(typeImport));
                    col.setComment(colmDbBean.getComment());
                    l.add(col);
                }
            }

            jTable.setDbTableName(tableName);
            jTable.setTableName(changeTableName2Java(tableName));
            jTable.setTableComment(tableInfo.getTableComment());
            jTable.setTableSchema(tableInfo.getTableSchema());
            jTable.setJavaColmBeans(l);
            jTableList.add(jTable);
        }
//		System.out.println(JSON.toJSONString(jTableList));
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

    *//**
     * @param ltn 数据库表对象实体list
     * @return 列对象map  键为表名.字段名  值为列对象
     * @throws Exception
     *//*
    private static List<JavaTableInfo> getMysqlTabColName(List<DbTableInfo> ltn) throws Exception {
        //查询列信息  主要是为了查询列备注
        //select column_name, column_comment from information_schema.columns where table_name = 'user' and table_schema = 'my_project';

        //列对象map  键为表名.字段名  值为列对象
        Map<String, ColmDbBean> m = new HashMap<String, ColmDbBean>();
        for (int i = 0; i < ltn.size(); i++) {
            DbTableInfo tableInfo = ltn.get(i);
            String table = tableInfo.getTableName();
            //查询字段备注
            ResultSet executeQuery = stm.executeQuery("SELECT COLUMN_NAME, COLUMN_COMMENT,DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + table + "' and table_schema = '"+mysqlDbName+"'");

            //获取主键信息
			*//*ResultSet pkRSet = conn.getMetaData().getPrimaryKeys(null, mysqlDbName, table);
			System.out.println("--------------"+table+"-------------");
			while( pkRSet.next() ) {
				System.err.println("****** Comment ******");
				System.err.println("TABLE_CAT : "+pkRSet.getObject(1));
				System.err.println("TABLE_SCHEM: "+pkRSet.getObject(2));
				System.err.println("TABLE_NAME : "+pkRSet.getObject(3));
				System.err.println("COLUMN_NAME: "+pkRSet.getObject(4));
				System.err.println("KEY_SEQ : "+pkRSet.getObject(5));
				System.err.println("PK_NAME : "+pkRSet.getObject(6));
				System.err.println("****** ******* ******");
			}*//*

            while(executeQuery.next()){
                String name = executeQuery.getString("COLUMN_NAME");
                String comment = executeQuery.getString("COLUMN_COMMENT");
                String type = executeQuery.getString("DATA_TYPE");

                ColmDbBean c = new ColmDbBean();
                c.setColmName(name);
                c.setComment(comment);
                c.setColmType(type);

                m.put((table+"."+name).toUpperCase(), c);
            }
        }
//		System.out.println(JSON.toJSONString(m));
        return changeDb2Java(m,ltn);
    }

    private static Statement stm;
    private static String colmRegex = "_";
    private static String tableRegex = "_";
*/
}
