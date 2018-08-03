package com.zhuyizhuo.generator.mybatis.database.pojo;

/**
 * class: MysqlDbInfo <br>
 * description: 字段实体 <br>
 * time: 2018/7/27 21:43
 *
 * @author yizhuo <br>
 * @version 1.0
 */
public class ColumnInfo {
	/** 列名 */
	private String columnName;
	/** 数据库字段类型 */
	private String dataType;
	/** 字段备注 */
	private String columnComment;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	@Override
	public String toString() {
		return "{" +
				"columnName='" + columnName + '\'' +
				", dataType='" + dataType + '\'' +
				", columnComment='" + columnComment + '\'' +
				'}';
	}
}
