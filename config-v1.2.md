#### 数据源配置[必选]:

 数据库驱动 `DB_DRIVER`
 数据库链接 `DB_URL`
 表空间 数据库名  `DB_TABLE_SCHEMA`
 数据库用户名 `DB_USERNAME`
 数据库密码 `DB_PASSWORD`
 数据库类型 `DB_TYPE`

##### `mysql`数据库配置

```properties
DB_TYPE=MYSQL
DB_DRIVER=com.mysql.cj.jdbc.Driver
DB_URL=jdbc:mysql://localhost:3306/
DB_TABLE_SCHEMA=test
DB_USERNAME=root
DB_PASSWORD=root
```
##### `ORACLE`数据库配置

```properties
DB_TYPE=ORACLE
DB_DRIVER=oracle.jdbc.driver.OracleDriver
DB_URL=jdbc:oracle:thin:@192.168.0.1:1521:test
DB_TABLE_SCHEMA=test
DB_USERNAME=root
DB_PASSWORD=root
```

#### 可选的配置项：

##### 指定生成的表

```properties
# 多张表用英文逗号隔开,大小写不敏感,不配置则默认为DB_TABLE_SCHEMA下的全部表
DB_INCLUDE_TABLE_NAME=需生成的表名1,需生成的表名2
```
##### 指定生成路径

```properties
# 生成文件输出路径 不配置则默认为 [当前项目路径] 路径请使用/ 或\\分隔 建议使用/
FILE_OUT_PUT_PATH=C:/Users/admin/Desktop
```
##### 指定生成类所在的包名

```properties
# 生成文件的公共包名 不配置则默认无公共包
BASE_PACKAGE=com.github.generator
# MAPPER包名 如下配置 MAPPER所在包路径为 {BASE_PACKAGE}.dao
DAO_PACKAGE=dao
# POJO包名 如下配置 POJO所在包路径为 {BASE_PACKAGE}.pojo
POJO_PACKAGE=pojo
# XML包名 如下配置 XML文件所在包路径为 {BASE_PACKAGE}.xml
XML_PACKAGE=xml
```

##### 指定生成类名称

```properties
# 实体名称 默认配置 驼峰命名 {0}
POJO_NAME_FORMAT={0}
# MAPPER 名称 默认配置 驼峰命名 + Dao {0}Dao
DAO_NAME_FORMAT={0}Dao
```

###### 生成`java`类名称支持3种配置，以`POJO_NAME_FORMAT`为例，例如数据库表名为`test_user`

1. 取数据库表名称转为驼峰命名 以下配置生成的`POJO`类名为`TestUser`

   ```properties 
   POJO_NAME_FORMAT={0}
   ```

2. 取数据库表名称转为驼峰命名并添加指定的前后缀，以下配置生成的`POJO`类名为`TestUserPOJO`

   ```properties
   POJO_NAME_FORMAT={0}POJO
   ```

3. 指定别名 以下配置生成的`POJO`类名为`User` , 适用于单表生成

   ```properties
   POJO_NAME_FORMAT= User
   ```

##### 指定生成xml名称策略

```properties
# XML 名称 默认表名称小写  非CAMEL 则默认为表名称小写
# 支持两种配置 
# 默认 TABLE_NAME_LOWERCASE 表名称小写
XML_NAME_FORMAT=TABLE_NAME_LOWERCASE
# 表名称转驼峰
# XML_NAME_FORMAT=CAMEL
```

##### `paameterType`别名

 XML中`parameterType` 是否使用别名 默认为`false`
 例如`POJO`全路径为 `test.generator.pojo.UserBean`
` PARAMETER_TYPE_USE_TYPE_ALIASES=false` 则 取`POJO`类完全限定名 :
` parameterType="test.generator.pojo.UserBean"`
` PARAMETER_TYPE_USE_TYPE_ALIASES=true` 则 取`POJO`的名称首字母小写为别名 :
 `parameterType="userBean"`

```properties
PARAMETER_TYPE_USE_TYPE_ALIASES=false
```
##### 注释配置


```properties
# 是否生成注释 默认为true
COMMENT_ENABLED=true
# 作者 不配置此项则生成类无此注释
AUTHOR=TODO
# 创建版本号 不配置此项则生成类无此注释
SINCE_VERSION=1.0
#当前版本号 不配置此项默认1.0
VERSION=1.0
```
##### 生成指定方法配置

```properties
# 新增数据   默认为true
INSERT_METHOD_ENABLED=true
# 根据传入参数删除数据   默认为true
DELETE_METHOD_ENABLED=true
# 根据主键删除数据  默认为true 如果表未设置主键则不生成此方法
DELETE_BY_PRIMARY_KEY_METHOD_ENABLED=true
# 根据传入参数更新数据   默认为true
UPDATE_METHOD_ENABLED=true
# 根据主键更新数据  默认为true 如果表未设置主键则不生成此方法
UPDATE_BY_PRIMARY_KEY_METHOD_ENABLED=true
# 根据传入参数查询数据列表   默认为true
QUERY_METHOD_ENABLED=true
# 根据主键查询数据 默认为true 如果表未设置主键则不生成此方法 
QUERY_BY_PRIMARY_KEY_ENABLED=true
# 统计符合条件的数据数量   默认为true
COUNT_METHOD_ENABLED=true
```

##### 开启关闭日志

```properties
#是否启用日志 默认开启
LOG_ENABLED=true
```

##### 指定单个文件输出路径

> 适用场景: 当将类生成至指定位置(例如桌面)时,为了减少路径层级,可指定文件路径,该配置不影响生成类所在的包结构

 如不指定输出路径,则默认输出路径为: `{FILE_OUT_PUT_PATH}/{BASE_PACKAGE}/{生成类所在的包名}`
 指定文件输出路径则以文件输出路径为准. 文件输出路径支持`多层路径`


```properties
# xml文件输出路径 如下配置 最终输出路径为 {FILE_OUT_PUT_PATH}/xml
XML_OUT_PUT_PATH=xml
# mapper文件输出路径 如下配置 最终输出路径为 {FILE_OUT_PUT_PATH}/dao
DAO_OUT_PUT_PATH=/dao
# pojo文件输出路径 如下配置 最终输出路径为 {FILE_OUT_PUT_PATH}/MultiCatalog/pojo
POJO_OUT_PUT_PATH=MultiCatalog/pojo
```
