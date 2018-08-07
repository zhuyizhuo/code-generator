###### 首先引入maven依赖:

```xml
<dependency>
    <groupId>com.github.zhuyizhuo</groupId>
    <artifactId>code-generator</artifactId>
    <version>1.1</version>
</dependency>
```

###### 其次引入对应数据库驱动,版本号需改为所需版本号：

mysql数据库引入：

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>x.x.x</version>
</dependency>
```

oracle数据库引入：

```xml
<dependency>
    <groupId>com.oracle</groupId>
    <artifactId>ojdbc14</artifactId>
    <version>x.x.x</version>
</dependency>
```

###### 新增配置文件config.properties,内容参考:

- [配置文件详解](config-v1.0.md)

```properties
#MYSQL数据库配置
#DB_TYPE=MYSQL
#DB_DRIVER=com.mysql.cj.jdbc.Driver
#DB_URL=jdbc:mysql://localhost:3306/
#DB_TABLE_SCHEMA=test
#DB_USERNAME=root
#DB_PASSWORD=root

# ORACLE数据库配置
#DB_TYPE=ORACLE
#DB_DRIVER=oracle.jdbc.driver.OracleDriver
#DB_URL=jdbc:oracle:thin:@192.168.0.1:1521:test
#DB_TABLE_SCHEMA=test
#DB_USERNAME=root
#DB_PASSWORD=root
```

###### java代码如下:

```java

import org.apache.ibatis.io.Resources;

import com.github.zhuyizhuo.generator.mybatis.BootStrap;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;
/**
 * 生成器使用
 */
public class TestGenerator {

	public static void main(String[] args) throws Exception {
		/** 此处使用 配置文件的绝对路径或者在项目中的相对路径  */
        PropertiesUtils.loadProperties(Resources.getResourceAsStream("config.properties"));
		BootStrap.generate();
	}
}
```

执行main方法,即可生成代码.

其他详细配置参考[配置文件详解](config-v1.0.md)
