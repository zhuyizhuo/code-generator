### 1.依赖引入

- [不同构建工具依赖](https://search.maven.org/artifact/com.github.zhuyizhuo/code-generator/1.3.1/jar)
- 查询最高版本方式:  [Maven中央仓库](https://search.maven.org/search?q=com.github.zhuyizhuo) | [Maven阿里库](http://maven.aliyun.com/mvn/search)

此处以maven项目为例:首先引入maven依赖:

```xml
<dependency>
    <groupId>com.github.zhuyizhuo</groupId>
    <artifactId>code-generator</artifactId>
    <version>最高版本</version>
</dependency>
```

其次引入对应数据库驱动,以mysql数据库为例：

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>x.x.x</version>
</dependency>
```

### 2.新增配置文件

默认配置如下:

- [默认配置(懒人福利)](config.md)

更多配置参考:

- [配置文件详解](config-v1.2.md)

### 3.java代码:

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
