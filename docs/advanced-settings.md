返回 [快速开始](./quickstart.md)

* [高级设置](#高级设置)
    * [自定义方法注释](#自定义方法注释)
    * [自定义生成类名称](#自定义生成类名称)
    * [自定义映射类型](#自定义映射类型)
    * [自定义生成器](#自定义生成器)


# 高级设置

## 自定义方法注释

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

## 自定义生成类名称

自定义生成类名称后将覆盖配置文件配置

- 支持自定义生成 `xml `名称 (覆盖对应配置项 `XML_NAME_FORMAT`)
- 支持自定义生成 `pojo` 名称 (覆盖对应配置项 `POJO_NAME_FORMAT`)
- 支持自定义生成 `mapper` 名称 (覆盖对应配置项 `DAO_NAME_FORMAT`)

此处以`自定义生成 xml 名称`为例:

```java
import java.io.IOException;

import org.apache.ibatis.io.Resources;

import com.github.zhuyizhuo.generator.mybatis.generator.extension.FormatService;
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

`java8 ` lambda 表达式用法如下:

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

## 自定义映射类型

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

`java8` lambda 表达式

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

