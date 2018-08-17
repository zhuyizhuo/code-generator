#### 依赖引入

- [不同构建工具依赖](https://search.maven.org/artifact/com.github.zhuyizhuo/code-generator/1.2.0/jar)

###### 此处以maven项目为例:首先引入maven依赖:

```xml
<dependency>
    <groupId>com.github.zhuyizhuo</groupId>
    <artifactId>code-generator</artifactId>
    <version>1.2.0</version>
</dependency>
```

###### 其次引入对应数据库驱动,以mysql数据库为例：

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>x.x.x</version>
</dependency>
```

###### 新增配置文件config.properties,可使用默认配置如下:

- [默认配置](config.md)

配置对应数据库信息：

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

配置文件内容参考:

- [配置文件详解](config-v1.2.md)

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
		/** 此处使用 配置文件的绝对路径或者在项目中的相对路径 
		 * 本例配置文件路径在maven项目的src/main/resources文件夹下
		 */
        Generator generator = new GeneratorBuilder()
            .build(Resources.getResourceAsStream("config.properties"));
		generator.generate();
	}
}
```

执行main方法,即可生成代码.

详细配置信息参考[配置文件详解](config-v1.2.md)

##### 高级设置[可选]

###### 自定义方法注释

```java
public static void main(String[] args) throws Exception {
		GeneratorBuilder generatorBuilder = new GeneratorBuilder();
    	//设置自定义方法注释
		MethodCommentInfo methodComment = generatorBuilder.getMethodComment();
    	//设置count方法注释
		methodComment.setCountMethodDescription(" this is count method ");
    	//设置@Param 参数对象注释
		methodComment.setParamsDescription(" this is param ");
		Generator build = generatorBuilder.build(
            Resources.getResourceAsStream("config.properties"));
		
		build.generate();
	}
```

###### 自定义生成类名称[自定义后将覆盖配置文件配置]

- 支持自定义生成xml名称
- 支持自定义生成pojo名称
- 支持自定义生成mapper名称

```java
//自定义生成xml文件名称 
	public static void main(String[] args) throws Exception{
		Generator generator = new GeneratorBuilder().addXmlNameFormat(new FormatService() {
			@Override
			public String formatTableName(String arg0) {
                //此处为xml原默认名称 即配置文件配置名称 
                System.out.println("xml原默认生成名称为：" + arg0);
				return arg0.toUpperCase();
			}
		}).build(Resources.getResourceAsStream("config.properties"));
		generator.generate();
	}
```

###### 自定义映射类型

```java

```

