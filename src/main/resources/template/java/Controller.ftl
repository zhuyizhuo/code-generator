package ${controllerFullPackage};

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ${serviceFullPackage}.${tableInfo.tableName}Service;
import ${javaBeanFullPackage}.${tableInfo.tableName};

/**
 * 类: ${tableInfo.tableName}Controller <br>
 * 描述: TODO <br>
 * 作者: ${author} <br>
 * 时间: ${date?string("yyyy-MM-dd HH:mm:ss")}
 */
@Controller
@RequestMapping({ "/${tableInfo.tableName?uncap_first}/" })
public class ${tableInfo.tableName}Controller {

	@Autowired
	private ${tableInfo.tableName}Service ${tableInfo.tableName?uncap_first}ServiceImpl;
	
	/**
	 * 方法: query${tableInfo.tableName}List <br>
	 * 描述: 分页查询 <br>
	 * 作者: ${author} <br>
	 * 时间: ${date?string("yyyy-MM-dd HH:mm:ss")} 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryList")
	public ModelAndView query${tableInfo.tableName}List(${tableInfo.tableName} ${tableInfo.tableName?uncap_first}) {
		try {/*
			int totalSize = ${tableInfo.tableName?uncap_first}ServiceImpl.count${tableInfo.tableName}TotalByWhere(${tableInfo.tableName?uncap_first});
			${tableInfo.tableName?uncap_first}.setTotalSize(totalSize);
			
			List<${tableInfo.tableName}> dataList = ${tableInfo.tableName?uncap_first}ServiceImpl.query${tableInfo.tableName}ListByWhere(${tableInfo.tableName?uncap_first});
			
			${tableInfo.tableName?uncap_first}.setData(dataList);*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/htglxt/pages/${tableInfo.tableName}List","data",${tableInfo.tableName?uncap_first});
	}
	
	/**
	 * 方法: edit${tableInfo.tableName} <br>
	 * 描述: 编辑信息 <br>
	 * 作者: ${author} <br>
	 * 时间: ${date?string("yyyy-MM-dd HH:mm:ss")} 
	 */	
	@RequestMapping(value = "/edit")
	public ModelAndView edit${tableInfo.tableName}(${tableInfo.tableName} ${tableInfo.tableName?uncap_first}) {
		try {
			${tableInfo.tableName?uncap_first}ServiceImpl.update${tableInfo.tableName}(${tableInfo.tableName?uncap_first});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/${tableInfo.tableName?uncap_first}/queryList");
	}
	
	/**
	 * 方法: delete${tableInfo.tableName} <br>
	 * 描述: 根据主键删除 <br>
	 * 作者: ${author} <br>
	 * 时间: ${date?string("yyyy-MM-dd HH:mm:ss")} 
	 */		
	@RequestMapping(value = "/delete")
	public ModelAndView delete${tableInfo.tableName}(${tableInfo.tableName} ${tableInfo.tableName?uncap_first}) {
		try {
			${tableInfo.javaColmBeans[0].typeName} ${tableInfo.javaColmBeans[0].javaName} = ${tableInfo.tableName?uncap_first}.get${tableInfo.javaColmBeans[0].javaName?cap_first}(); 
			${tableInfo.tableName?uncap_first}ServiceImpl.delete${tableInfo.tableName}(${tableInfo.javaColmBeans[0].javaName});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/${tableInfo.tableName?uncap_first}/queryList");
	}
	
	/**
	 * 方法: insert${tableInfo.tableName} <br>
	 * 描述: 插入数据 <br>
	 * 作者: ${author} <br>
	 * 时间: ${date?string("yyyy-MM-dd HH:mm:ss")} 
	 */		
	@RequestMapping(value = "/insert")
	public ModelAndView insert${tableInfo.tableName}(${tableInfo.tableName} ${tableInfo.tableName?uncap_first}) {
		try {
			${tableInfo.tableName?uncap_first}ServiceImpl.insert${tableInfo.tableName}(${tableInfo.tableName?uncap_first});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/${tableInfo.tableName?uncap_first}/queryList");
	}
	
	/**
	 * 方法: getOne <br>
	 * 描述: 获取一条数据 <br>
	 * 作者: ${author} <br>
	 * 时间: ${date?string("yyyy-MM-dd HH:mm:ss")} 
	 */		
	@RequestMapping(value = "/getOne")
	public ModelAndView getOne(${tableInfo.tableName} ${tableInfo.tableName?uncap_first}) {
		${tableInfo.tableName} one = null;
		try {
			one = ${tableInfo.tableName?uncap_first}ServiceImpl.getOne(${tableInfo.tableName?uncap_first});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("htglxt/pages/${tableInfo.tableName}Edit","data",one);
	}
}