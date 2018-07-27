package ${package};

import com.alibaba.fastjson.JSON;

/**
 * 类: BaseService <br>
 * 描述: service基类 所有service需继承此类 <br>
 * 作者: 朱一卓 <br>
 */
public class BaseService{

	protected void printJson(String msg,Object obj) {
		System.out.println(msg+JSON.toJSONString(obj));
	}
	
}
