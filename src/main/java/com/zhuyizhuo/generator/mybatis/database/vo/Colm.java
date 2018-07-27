package com.zhuyizhuo.generator.mybatis.database.vo;


public class Colm {
	
	/**
	 * 表名
	 */
	private String dbName;
	
	private String name;
	
	//数据库字段类型
	private String type;
	
	private String describe;
	
	//java字段类型
	private String typeName;
	
	//java字段类型全称
	private String typeImport;

	private String comment;
	
	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeImport() {
		return typeImport;
	}

	public void setTypeImport(String typeImport) {
		this.typeImport = typeImport;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	
}
