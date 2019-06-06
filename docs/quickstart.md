### 1. 依赖引入

- [不同构建工具依赖](https://search.maven.org/artifact/com.github.zhuyizhuo/code-generator/1.3.1/jar)
- 查询最高版本方式:  [Maven 中央仓库](https://search.maven.org/search?q=com.github.zhuyizhuo) \| [Maven 阿里库](http://maven.aliyun.com/mvn/search)

此处以 `maven` 项目为例:首先引入 `maven` 依赖:

```xml
<dependency>
    <groupId>com.github.zhuyizhuo</groupId>
    <artifactId>code-generator</artifactId>
    <version>最高版本</version>
</dependency>
```

其次引入对应数据库驱动,以 `mysql` 数据库为例：

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>x.x.x</version>
</dependency>
```

### 2. 新增配置文件

默认配置如下:

- [默认配置](config.md)

### 3. java 代码:

```java
package com.github.zhuyizhuo.generator.client;

import com.github.zhuyizhuo.generator.mybatis.generator.Generator;
import com.github.zhuyizhuo.generator.mybatis.generator.GeneratorBuilder;
/**
 * 生成器使用
 */
public class TestGenerator {

	public static void main(String[] args) throws Exception {
		/** 此处使用 配置文件的绝对路径或者在项目中的相对路径 
		 * 本例配置文件路径在 maven 项目的 src/main/resources 文件夹下
		 */
        Generator generator = new GeneratorBuilder().build("config.properties");
		generator.generate();
	}
}
```

执行 `main` 方法,即可生成代码. 

### 4. 生成器扩展

```
1. 支持自定义模板
2. 支持替换系统模板
3. 支持新增或修改数据库类型映射至 java 类型
4. 支持自定义模块名格式化
5. 支持自定义方法名格式化
```

