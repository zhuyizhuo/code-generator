package com.github.zhuyizhuo.generator.mybatis.dto;

import com.github.zhuyizhuo.generator.mybatis.database.pojo.ColumnInfo;

/**
 * class: JavaColumnInfo <br>
 * description: java字段实体 <br>
 * time: 2018/7/29 16:06
 *
 * @author yizhuo <br>
 * @version 1.0
 */
public class JavaColumnInfo extends ColumnInfo {
	/** 转为驼峰命名后的字段名 */
	private String javaColumnName;
	/** java字段类型 */
	private String javaDataType;
	/** java字段类型全称 */
	private String javaDataTypeFullPath;

	public JavaColumnInfo() {
	}

	public String getJavaColumnName() {
		return javaColumnName;
	}

	public void setJavaColumnName(String javaColumnName) {
		this.javaColumnName = javaColumnName;
	}

	public String getJavaDataType() {
		return javaDataType;
	}

	public void setJavaDataType(String javaDataType) {
		this.javaDataType = javaDataType;
	}

	public String getJavaDataTypeFullPath() {
		return javaDataTypeFullPath;
	}

	public void setJavaDataTypeFullPath(String javaDataTypeFullPath) {
		this.javaDataTypeFullPath = javaDataTypeFullPath;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		JavaColumnInfo that = (JavaColumnInfo) o;

		if (javaColumnName != null ? !javaColumnName.equals(that.javaColumnName) : that.javaColumnName != null)
			return false;
		if (javaDataType != null ? !javaDataType.equals(that.javaDataType) : that.javaDataType != null) return false;
		return javaDataTypeFullPath != null ? javaDataTypeFullPath.equals(that.javaDataTypeFullPath) : that.javaDataTypeFullPath == null;
	}

	@Override
	public int hashCode() {
		int result = javaColumnName != null ? javaColumnName.hashCode() : 0;
		result = 31 * result + (javaDataType != null ? javaDataType.hashCode() : 0);
		result = 31 * result + (javaDataTypeFullPath != null ? javaDataTypeFullPath.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return super.toString() + "\n\t JavaColumnInfo{" +
				"javaColumnName='" + javaColumnName + '\'' +
				", javaDataType='" + javaDataType + '\'' +
				", javaDataTypeFullPath='" + javaDataTypeFullPath + '\'' +
				'}';
	}
}
