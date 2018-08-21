#### 依赖引入

- [不同构建工具依赖](https://search.maven.org/artifact/com.github.zhuyizhuo/code-generator/1.3.0/jar)

###### 此处以maven项目为例:首先引入maven依赖:

```xml
<dependency>
    <groupId>com.github.zhuyizhuo</groupId>
    <artifactId>code-generator</artifactId>
    <version>x.x.x</version>
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



##### 高级设置[可选]

###### 自定义方法注释

```java
import java.io.IOException;

import org.apache.ibatis.io.Resources;

import com.github.zhuyizhuo.generator.mybatis.convention.MethodCommentInfo;
import com.github.zhuyizhuo.generator.mybatis.generator.Generator;
import com.github.zhuyizhuo.generator.mybatis.generator.GeneratorBuilder;

public class TestGenerator {
    public static void main(String[] args) throws IOException {
        MethodCommentInfo methodCommentInfo = new MethodCommentInfo();
        //修改count方法注释
        methodCommentInfo.setCountMethodDescription(" count method description ");
        Generator generator = new GeneratorBuilder()
            .addMethodComment(methodCommentInfo)
            .build(Resources.getResourceAsStream("config.properties"));
        generator.generate();
    }
}
```

###### 自定义生成类名称[自定义后将覆盖配置文件配置]

- 支持自定义生成xml名称
- 支持自定义生成pojo名称
- 支持自定义生成mapper名称

此处以`自定义生成xml名称`为例:

```java
import java.io.IOException;

import org.apache.ibatis.io.Resources;

import com.github.zhuyizhuo.generator.mybatis.extension.service.FormatService;
import com.github.zhuyizhuo.generator.mybatis.generator.Generator;
import com.github.zhuyizhuo.generator.mybatis.generator.GeneratorBuilder;

public class TestGenerator {
    //自定义生成xml文件名称 
    public static void main(String[] args) throws Exception{
        Generator generator = new GeneratorBuilder()
            .addXmlNameFormat(new FormatService() {
            @Override
            public String formatTableName(String tableName) {
                //此处为数据库表名称大写,用户可将参数自定义处理后返回
                System.out.println("数据库表名称大写：" + tableName);
                return tableName + "_sql";
            }
        }).build(Resources.getResourceAsStream("config.properties"));
        generator.generate();
    }
}
```

`java8 ` lambda表达式用法如下:

```java
import java.io.IOException;

import org.apache.ibatis.io.Resources;

import com.github.zhuyizhuo.generator.mybatis.generator.Generator;
import com.github.zhuyizhuo.generator.mybatis.generator.GeneratorBuilder;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;

public class TestGenerator {
	
	public static void main(String[] args) throws IOException {
		Generator generator = new GeneratorBuilder()
				.addBeanNameFormat((tableName) ->{
                    System.out.println("数据库表名称大写：" + tableName);
					//自定义数据库映射对象名称
					return GeneratorStringUtils
                        .changeTableName2CamelFirstUpper(tableName, "_")+"Bean";
				})
				.build(Resources.getResourceAsStream("config.properties"));
		generator.generate();
	}
}
```

###### 自定义映射类型

```java

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.type.JdbcType;

import com.github.zhuyizhuo.generator.mybatis.generator.Generator;
import com.github.zhuyizhuo.generator.mybatis.generator.GeneratorBuilder;

public class TestGenerator {
	
	public static void main(String[] args) throws IOException {
		/*
	     * 自定义数据库与java类型映射
	     * 例如数据库类型为NUMBER 生成器默认映射为Integer 可自定义映射 将生成类型指定为String
	     * oracle 数据库类型 TIMESTAMP 默认映射为 java.util.Date 此处更改映射为java.sql.Date
	     */
		Generator generator = new GeneratorBuilder()
				.addTypeMapper("NUMBER",JdbcType.VARCHAR,String.class)
				.addTypeMapper("TIMESTAMP", JdbcType.TIMESTAMP, java.sql.Date.class)
				.build(Resources.getResourceAsStream("config.properties"));
		generator.generate();
	}
}
```

###### 自定义生成器

> 适用场景: 如果需要根据表信息生成更多内容 例如 生成页面/service/controller等  提供生成器的扩展配置  将数据库内省的表结构封装为对象 可自行扩展

```java
import java.io.IOException;
import java.util.List;

import org.apache.ibatis.io.Resources;

import com.github.zhuyizhuo.generator.mybatis.dto.JavaColumnInfo;
import com.github.zhuyizhuo.generator.mybatis.extension.service.GeneratorService;
import com.github.zhuyizhuo.generator.mybatis.generator.Generator;
import com.github.zhuyizhuo.generator.mybatis.generator.GeneratorBuilder;
import com.github.zhuyizhuo.generator.mybatis.vo.GenerateInfo;

public class TestGenerator {
    	public static void main(String[] args) throws IOException {
		Generator generator = new GeneratorBuilder().addGeneratorService(new GeneratorService() {
            //GenerateInfo 为数据库信息处理后对象 
            //可将此对象转为json打印出来查看结构 自己根据需要从中获取字段 用以自定义生成更多内容
			@Override
			public void generate(GenerateInfo generateInfo) {
				System.out.println("tableName:" + generateInfo.getTableInfo().getTableName());
				List<JavaColumnInfo> columnLists = generateInfo.getTableInfo().getColumnLists();
				for (int i = 0; i < columnLists.size(); i++) {
					JavaColumnInfo javaColumnInfo = columnLists.get(i);
					System.out.println("\t private " + javaColumnInfo.getJavaDataType() + " " + javaColumnInfo.getJavaColumnName() + ";");
				}
			}
		}).build(Resources.getResourceAsStream("config.properties"));
		generator.generate();
	}
}

```

`java8` lambda表达式

```java
public static void main(String[] args) throws IOException {
    Generator generator = new GeneratorBuilder()
        .addGeneratorService((generateInfo)->{
            System.out.println("tableName:" + generateInfo.getTableInfo().getTableName());
            List<JavaColumnInfo> columnLists = generateInfo.getTableInfo().getColumnLists();
            for (int i = 0; i < columnLists.size(); i++) {
                JavaColumnInfo javaColumnInfo = columnLists.get(i);
                System.out.println("\t private " + javaColumnInfo.getJavaDataType() + " " + javaColumnInfo.getJavaColumnName() + ";");
            }
        }).build(Resources.getResourceAsStream("config.properties"));
    generator.generate();
}
```

