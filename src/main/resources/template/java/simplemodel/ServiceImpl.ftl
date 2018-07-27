package ${serviceFullPackage}.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${javaBeanFullPackage}.${tableInfo.tableName};
import ${daoFullPackage}.${tableInfo.tableName}Dao;
import ${serviceFullPackage}.${tableInfo.tableName}Service;

/**
 * description : 操作${tableInfo.dbTableName}表的Service层实现	<br/>
 */
@Service("${tableInfo.tableName?uncap_first}ServiceImpl")
public class ${tableInfo.tableName}ServiceImpl implements ${tableInfo.tableName}Service {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ${tableInfo.tableName}Dao ${tableInfo.tableName?uncap_first}DaoImpl;

	/**
	 * 方法: insert${tableInfo.tableName} <br>
	 * @see ${serviceFullPackage}.${tableInfo.tableName}Service#insert${tableInfo.tableName}(${javaBeanFullPackage}.${tableInfo.tableName})
	 */
	@Override
	public void insert${tableInfo.tableName}(${tableInfo.tableName} ${tableInfo.tableName?uncap_first}){
		${tableInfo.tableName?uncap_first}DaoImpl.insert${tableInfo.tableName}(${tableInfo.tableName?uncap_first});
	};

	/**
	 * 方法: update${tableInfo.tableName} <br>
	 * @see ${serviceFullPackage}.${tableInfo.tableName}Service#update${tableInfo.tableName}(${javaBeanFullPackage}.${tableInfo.tableName})
	 */
	@Override
	public int update${tableInfo.tableName}(${tableInfo.tableName} ${tableInfo.tableName?uncap_first}){
		return ${tableInfo.tableName?uncap_first}DaoImpl.update${tableInfo.tableName}(${tableInfo.tableName?uncap_first});
	};

	/**
	 * 方法: delete${tableInfo.tableName} <br>
	 * @see ${serviceFullPackage}.${tableInfo.tableName}Service#delete${tableInfo.tableName}(${javaBeanFullPackage}.${tableInfo.tableName})
	 */
	@Override
	public int delete${tableInfo.tableName}(${tableInfo.javaColmBeans[0].typeName} ${tableInfo.javaColmBeans[0].javaName}){
		return ${tableInfo.tableName?uncap_first}DaoImpl.delete${tableInfo.tableName}(${tableInfo.javaColmBeans[0].javaName});
	};

	/**
	 * 方法: query${tableInfo.tableName}ListByWhere <br>
	 * @see ${serviceFullPackage}.${tableInfo.tableName}Service#query${tableInfo.tableName}ListByWhere(${javaBeanFullPackage}.${tableInfo.tableName})
	 */
	@Override
	public List<${tableInfo.tableName}> query${tableInfo.tableName}ListByWhere(${tableInfo.tableName} ${tableInfo.tableName?uncap_first}){
		return ${tableInfo.tableName?uncap_first}DaoImpl.query${tableInfo.tableName}ListByWhere(${tableInfo.tableName?uncap_first});
	};

	/**
	 * 方法: getOne <br>
	 * @see ${serviceFullPackage}.${tableInfo.tableName}Service#getOne(${javaBeanFullPackage}.${tableInfo.tableName})
	 */
	@Override
	public ${tableInfo.tableName} getOne(${tableInfo.tableName} ${tableInfo.tableName?uncap_first}){
		return ${tableInfo.tableName?uncap_first}DaoImpl.getOne(${tableInfo.tableName?uncap_first});
	}

	/**
	 * 方法: count${tableInfo.tableName}TotalByWhere <br>
	 * @see ${serviceFullPackage}.${tableInfo.tableName}Service#queryTotal(${javaBeanFullPackage}.${tableInfo.tableName})
	 */
	@Override
	public int count${tableInfo.tableName}TotalByWhere(${tableInfo.tableName} ${tableInfo.tableName?uncap_first}) {
		return ${tableInfo.tableName?uncap_first}DaoImpl.count${tableInfo.tableName}TotalByWhere(${tableInfo.tableName?uncap_first});
	};

}