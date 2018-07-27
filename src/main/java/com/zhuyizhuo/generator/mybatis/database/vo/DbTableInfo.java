package com.zhuyizhuo.generator.mybatis.database.vo;

import java.util.Date;

public class DbTableInfo {

	/**
	 * 数据库名称
	 */
	private String tableSchema;

	/**
	 * 表名
	 */
	private String tableName;

	/**
	 * 数据库引擎
	 */
	private String engine;

	/**
	 * 表创建时间
	 */
	private Date createTime;

	/**
	 * 表注释
	 */
	private String tableComment;
	
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

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}
	
}
