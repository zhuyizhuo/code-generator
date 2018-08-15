package ${stratificationInfo.daoFullPackage};

import java.util.List;

import ${stratificationInfo.pojoFullPackage}.${stratificationInfo.pojoName};

/**
 * description : ${tableInfo.tableName} table dao layer interface <br/>
<#include "java/comment.ftl"/>
 */
public interface ${stratificationInfo.daoName} {

	/**
	 * ${methodInfo.insertMethodDescription} <br>
	 * @param ${stratificationInfo.pojoName?uncap_first} 传入参数  <br>
	 */
	int ${methodInfo.insertMethodName}(${stratificationInfo.pojoName} ${stratificationInfo.pojoName?uncap_first});<#if tableInfo.hasPrimaryKey>

	/**
	 * ${methodInfo.deleteByPrimaryKeyMethodDescription} <br>
<#if tableInfo.singlePrimaryKey>
	 * @param ${tableInfo.primaryKeyColumns[0].javaColumnName?uncap_first} ${tableInfo.primaryKeyColumns[0].columnComment}  <br>
	 */
    int ${methodInfo.deleteByPrimaryKeyMethodName}(${tableInfo.primaryKeyColumns[0].javaDataType} ${tableInfo.primaryKeyColumns[0].javaColumnName?uncap_first});
<#else>
	* @param ${stratificationInfo.pojoName?uncap_first} 传入参数  <br>
	 */
    int ${methodInfo.deleteByPrimaryKeyMethodName}(${stratificationInfo.pojoName} ${stratificationInfo.pojoName?uncap_first});</#if>
</#if>

	/**
	 * ${methodInfo.deleteMethodDescription} <br>
	 * @param ${stratificationInfo.pojoName?uncap_first} 传入参数  <br>
	 */
    int ${methodInfo.deleteMethodName}(${stratificationInfo.pojoName} ${stratificationInfo.pojoName?uncap_first});<#if tableInfo.hasPrimaryKey>

	/**
	 * ${methodInfo.updateByPrimaryKeyMethodDescription} <br>
<#if tableInfo.singlePrimaryKey>
	 * @param ${tableInfo.primaryKeyColumns[0].javaColumnName?uncap_first} ${tableInfo.primaryKeyColumns[0].columnComment}  <br>
	 */
    int ${methodInfo.updateByPrimaryKeyMethodName}(${tableInfo.primaryKeyColumns[0].javaDataType} ${tableInfo.primaryKeyColumns[0].javaColumnName?uncap_first});
<#else>
	 * @param ${stratificationInfo.pojoName?uncap_first} 传入参数  <br>
	 */
    int ${methodInfo.updateByPrimaryKeyMethodName}(${stratificationInfo.pojoName} ${stratificationInfo.pojoName?uncap_first});</#if>
</#if>

	/**
	 * ${methodInfo.updateMethodDescription} <br>
	 * @param ${stratificationInfo.pojoName?uncap_first} 传入参数  <br>
	 */
    int ${methodInfo.updateMethodName}(${stratificationInfo.pojoName} ${stratificationInfo.pojoName?uncap_first});

	/**
	 * ${methodInfo.countMethodDescription} <br>
	 * @param ${stratificationInfo.pojoName?uncap_first} 传入参数  <br>
	 */
    int ${methodInfo.countMethodName}(${stratificationInfo.pojoName} ${stratificationInfo.pojoName?uncap_first});

	/**
	 * ${methodInfo.queryMethodDescription} <br>
	 * @param ${stratificationInfo.pojoName?uncap_first} 传入参数  <br>
	 */
    List<${stratificationInfo.pojoName}> ${methodInfo.queryMethodName}(${stratificationInfo.pojoName} ${stratificationInfo.pojoName?uncap_first});

}