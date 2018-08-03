package ${stratificationInfo.daoFullPackage};

import java.util.List;

import ${stratificationInfo.pojoFullPackage}.${stratificationInfo.pojoName};

/**
 * description : ${tableInfo.tableName} table dao layer interface <br/>
<#include "base/java/comment.ftl"/>
 */
public interface ${stratificationInfo.daoName} {

	void ${methodInfo.insertMethodName}(${stratificationInfo.pojoName} ${stratificationInfo.pojoName?uncap_first});

    int ${methodInfo.deleteMethodName}(${stratificationInfo.pojoName} ${stratificationInfo.pojoName?uncap_first});

    int ${methodInfo.updateMethodName}(${stratificationInfo.pojoName} ${stratificationInfo.pojoName?uncap_first});

    List<${stratificationInfo.pojoName}> ${methodInfo.queryMethodName}(${stratificationInfo.pojoName} ${stratificationInfo.pojoName?uncap_first});
}