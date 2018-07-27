package com.zhuyizhuo.generator.mybatis.database.vo;

/**
 * 数据库colm对象实体
 * @author worker
 *
 */
public class ColmDbBean {

	// 数据库字段名
	private String colmName;

	// 数据库字段类型
	private String colmType;

	// 字段备注
	private String comment;
	
	//可否为空
	private String nullable;
	
	//字段长度
	private String dataLength;

	public String getColmName() {
		return colmName;
	}

	public void setColmName(String colmName) {
		this.colmName = colmName;
	}

	public String getColmType() {
		return colmType;
	}

	public void setColmType(String colmType) {
		this.colmType = colmType;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

}
