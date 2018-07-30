package com.zhuyizhuo.generator.mybatis.database.pojo;

import com.zhuyizhuo.generator.mybatis.dto.JavaColumnInfo;
import com.zhuyizhuo.generator.utils.GeneratorStringUtils;
import com.zhuyizhuo.generator.utils.TypeConversion;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库实体
 * 2018-7-27 21:15:01
 * @author yizhuo
 * @version 1.0
 */
public class DbTableInfo {
	/** 数据库名称 */
	private String tableSchema;
	/** 表名 */
	private String tableName;
	/** 表注释 */
	private String tableComment;
	/** 表字段 */
	private List<ColumnInfo> columnLists;
	/** java字段 */
	private List<JavaColumnInfo> javaColumnLists = new ArrayList<JavaColumnInfo>();

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

	public List<ColumnInfo> getColumnLists() {
		return columnLists;
	}

	public void setColumnLists(List<ColumnInfo> columnLists) {
		this.columnLists = columnLists;
		if (columnLists == null) {
			return;
		}
		ColumnInfo columnInfo = null;
		for (int j = 0; j < columnLists.size(); j++) {
			columnInfo = columnLists.get(j);
//			System.out.println(columnInfo);
			this.addJavaColumn(conversionColumn(columnInfo));
		}
	}

	private static JavaColumnInfo conversionColumn(ColumnInfo columnInfo) {
		JavaColumnInfo javaColumnInfo = new JavaColumnInfo();
		javaColumnInfo.setJavaColumnName(GeneratorStringUtils.changeColmName2Java(columnInfo.getColumnName(),"_"));
		javaColumnInfo.setJavaDataType(TypeConversion.mySqlDbType2Java(columnInfo.getDataType()));
		javaColumnInfo.setJavaDataTypeFullPath(TypeConversion.javaDataTypeFullPathMap.get(javaColumnInfo.getJavaDataType()));
		return javaColumnInfo;
	}

	public void addJavaColumn(JavaColumnInfo javaColumnInfo){
		this.javaColumnLists.add(javaColumnInfo);
	}

	public List<JavaColumnInfo> getJavaColumnLists() {
		return javaColumnLists;
	}

	public void setJavaColumnLists(List<JavaColumnInfo> javaColumnLists) {
		this.javaColumnLists = javaColumnLists;
	}

	@Override
	public String toString() {
		return "DbTableInfo{" +
				"tableSchema='" + tableSchema + '\'' +
				", tableName='" + tableName + '\'' +
				", tableComment='" + tableComment + '\'' +
				'}';
	}
}
