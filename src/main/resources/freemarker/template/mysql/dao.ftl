package ${stratificationInfo.daoFullPackage};

import java.util.List;

import ${stratificationInfo.pojoFullPackage}.${stratificationInfo.pojoName};

/**
 * description : ${dbTableInfo.tableName} table dao layer interface <br/>
<#include "base/java/comment.ftl"/>
 */
public interface ${stratificationInfo.daoName} {

	void ${methodInfo.insertMethodName}(${stratificationInfo.pojoName} ${stratificationInfo.pojoName?uncap_first});

}