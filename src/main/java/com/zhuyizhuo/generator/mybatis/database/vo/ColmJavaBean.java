package com.zhuyizhuo.generator.mybatis.database.vo;

/**
 * 数据库colm转为java colm实体
 * @author worker
 *
 */
public class ColmJavaBean {

	// 数据库原字段名
	private String dbColmName;
	
	//数据库数据类型
	private String dbColmType;
	
	//可否为空
	private String nullable;
	
	//字段长度
	private String dataLength;
	
	//转为驼峰命名后的字段名
	private String javaName;
	
	//java字段类型
	private String typeName;
	
	//java字段类型全称
	private String typeImport;
	
	// 字段备注
	private String comment;
	
	//JDBC类型
	private String colmJdbcType;

	public String getJavaName() {
		return javaName;
	}

	public void setJavaName(String javaName) {
		this.javaName = javaName;
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

	public String getDbColmName() {
		return dbColmName;
	}

	public void setDbColmName(String dbColmName) {
		this.dbColmName = dbColmName;
	}

	public String getDbColmType() {
		return dbColmType;
	}

	public void setDbColmType(String dbColmType) {
		this.dbColmType = dbColmType;
	}

	public String getNullable() {
		return nullable;
	}

	public void setNullable(String nullable) {
		this.nullable = nullable;
	}

	public String getDataLength() {
		return dataLength;
	}

	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}

	public String getColmJdbcType() {
		return colmJdbcType;
	}

	public void setColmJdbcType(String colmJdbcType) {
		this.colmJdbcType = colmJdbcType;
	}

	@Override
	public String toString() {
		return "ColmJavaBean [dbColmName=" + dbColmName + ", dbColmType=" + dbColmType + ", nullable=" + nullable
				+ ", dataLength=" + dataLength + ", javaName=" + javaName + ", typeName=" + typeName + ", typeImport="
				+ typeImport + ", comment=" + comment + ", colmJdbcType=" + colmJdbcType + "]";
	}
}
