package com.zhuyizhuo.generator.mybatis.database.vo;

import java.util.List;

public class JavaTableInfo {
	/** 数据库名称 */
	private String tableSchema;
	/** 数据库原表名 */
	private String dbTableName;
	/** 表注释 */
	private String tableComment;

	/**  转换成驼峰命名的表名 */
	private String tableName;
	/** 列信息 */
	private List<ColmJavaBean> javaColmBeans;

	public String getTableSchema() {
		return tableSchema;
	}

	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	public List<ColmJavaBean> getJavaColmBeans() {
		return javaColmBeans;
	}

	public void setJavaColmBeans(List<ColmJavaBean> javaColmBeans) {
		this.javaColmBeans = javaColmBeans;
	}

	public String getDbTableName() {
		return dbTableName;
	}

	public void setDbTableName(String dbTableName) {
		this.dbTableName = dbTableName;
	}

	@Override
	public String toString() {
		return "JavaTableInfo [tableSchema=" + tableSchema + ", tableName=" + tableName + ", dbTableName=" + dbTableName
				+ ", tableComment=" + tableComment + ", javaColmBeans=" + javaColmBeans + "]";
	}
	
	
}
