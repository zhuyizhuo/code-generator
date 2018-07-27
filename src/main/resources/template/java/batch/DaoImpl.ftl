package ${daoFullPackage}.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import ${javaBeanFullPackage}.${tableInfo.tableName};
import ${daoFullPackage}.${tableInfo.tableName}Dao;

import ${baseDaoPackage}.${baseDaoName};

/**
 * description : 操作${tableInfo.dbTableName}表的DAO层实现	<br/>
 */
@Repository
public class ${tableInfo.tableName}DaoImpl extends ${baseDaoName} implements ${tableInfo.tableName}Dao {

	private static final long serialVersionUID = 1L;

	/**
	 * 方法: insert${tableInfo.tableName} <br>
	 * @see ${daoFullPackage}.${tableInfo.tableName}Dao#insert${tableInfo.tableName}(${javaBeanFullPackage}.${tableInfo.tableName})
	 */
	@Override
	public void insert${tableInfo.tableName}(${tableInfo.tableName} ${tableInfo.tableName?uncap_first}){
		this.insert("${daoFullPackage}.${tableInfo.tableName}Dao.insert${tableInfo.tableName}", ${tableInfo.tableName?uncap_first});
	};

	/**
	 * 方法: update${tableInfo.tableName} <br>
	 * @see ${daoFullPackage}.${tableInfo.tableName}Dao#update${tableInfo.tableName}(${javaBeanFullPackage}.${tableInfo.tableName})
	 */
	@Override
	public int update${tableInfo.tableName}(${tableInfo.tableName} ${tableInfo.tableName?uncap_first}){
		return this.update("${daoFullPackage}.${tableInfo.tableName}Dao.update${tableInfo.tableName}", ${tableInfo.tableName?uncap_first});
	};

	/**
	 * 方法: delete${tableInfo.tableName} <br>
	 * @see ${daoFullPackage}.${tableInfo.tableName}Dao#delete${tableInfo.tableName}(${javaBeanFullPackage}.${tableInfo.tableName})
	 */
	@Override
	public int delete${tableInfo.tableName}(String ${tableInfo.javaColmBeans[0].javaName}){
		return this.delete("${daoFullPackage}.${tableInfo.tableName}Dao.delete${tableInfo.tableName}", ${tableInfo.javaColmBeans[0].javaName});
	};

	/**
	 * 方法: query${tableInfo.tableName}ListByWhere <br>
	 * @see ${daoFullPackage}.${tableInfo.tableName}Dao#query${tableInfo.tableName}ListByWhere(${javaBeanFullPackage}.${tableInfo.tableName})
	 */
	@Override
	public List<${tableInfo.tableName}> query${tableInfo.tableName}ListByWhere(${tableInfo.tableName} ${tableInfo.tableName?uncap_first}){
		return this.queryForList("${daoFullPackage}.${tableInfo.tableName}Dao.query${tableInfo.tableName}ListByWhere", ${tableInfo.tableName?uncap_first});
	};

	/**
	 * 方法: getOne <br>
	 * @see ${daoFullPackage}.${tableInfo.tableName}Dao#getOne(${javaBeanFullPackage}.${tableInfo.tableName})
	 */
	@Override
	public ${tableInfo.tableName} getOne(${tableInfo.tableName} ${tableInfo.tableName?uncap_first}){
		return this.getOne("${daoFullPackage}.${tableInfo.tableName}Dao.getOne", ${tableInfo.tableName?uncap_first});
	}

	/**
	 * 方法: count${tableInfo.tableName}TotalByWhere <br>
	 * @see ${daoFullPackage}.${tableInfo.tableName}Dao#queryTotal(${javaBeanFullPackage}.${tableInfo.tableName})
	 */
	@Override
	public int count${tableInfo.tableName}TotalByWhere(${tableInfo.tableName} ${tableInfo.tableName?uncap_first}) {
		return (Integer)this.getOne("${daoFullPackage}.${tableInfo.tableName}Dao.count${tableInfo.tableName}TotalByWhere", ${tableInfo.tableName?uncap_first});
	};
	
	/**
	 * 方法: batchInsert${tableInfo.tableName} <br>
	 * @see ${daoFullPackage}.${tableInfo.tableName}Dao#batchInsert${tableInfo.tableName}(${javaBeanFullPackage}.${tableInfo.tableName})
	 */
	@Override
	public void batchInsert${tableInfo.tableName}(List<${tableInfo.tableName}> ${tableInfo.tableName?uncap_first}List) {
		this.insert("${daoFullPackage}.${tableInfo.tableName}Dao.batchInsert${tableInfo.tableName}", ${tableInfo.tableName?uncap_first}List);
	};

}