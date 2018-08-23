# 默认配置:

详细配置信息参考[配置文件详解](config-v1.2.md)

返回 [快速开始](./quickstart.md)

```properties
# 数据库配置[必须]
# MYSQL数据库配置
#DB_TYPE=MYSQL
#DB_DRIVER=com.mysql.cj.jdbc.Driver
#DB_URL=jdbc:mysql://localhost:3306/test...
#DB_TABLE_SCHEMA=test
#DB_USERNAME=root
#DB_PASSWORD=root

# ORACLE数据库配置
#DB_TYPE=ORACLE
#DB_DRIVER=oracle.jdbc.driver.OracleDriver
#DB_URL=jdbc:oracle:thin:@192.168.0.1:1521:test...
#DB_TABLE_SCHEMA=test
#DB_USERNAME=root
#DB_PASSWORD=root

# 需生成的表 多张表用英文逗号隔开,大小写不敏感,不配置则默认为全部  USER,order
# DB_INCLUDE_TABLE_NAME=Test

# 生成文件输出路径 不配置则默认为当前项目路径 路径请使用/ 或\\分隔 建议使用/
# FILE_OUT_PUT_PATH=C:/Users/admin/Desktop
# 生成文件的包名  不配置则默认为当前项目路径src/main/java/路径
# BASE_PACKAGE=com.github.generator
# xml文件包名 默认xml
XML_OUT_PUT_PATH=xml
# mapper文件包名 默认dao
DAO_OUT_PUT_PATH=dao
# 数据库映射实体包名 默认pojo
POJO_OUT_PUT_PATH=pojo

# XML中parameterType 是否使用别名 默认为false
PARAMETER_TYPE_USE_TYPE_ALIASES=false
# 是否启用日志 默认开启
LOG_ENABLED=true

# 实体名称 不配置默认表名_转驼峰
POJO_NAME_FORMAT={0}
# Mapper名称 不配置默认表名_转驼峰+Mapper
DAO_NAME_FORMAT={0}Mapper
# XML 名称 不配置 默认表名称小写 
XML_NAME_FORMAT=TABLE_NAME_LOWERCASE
# xml名称 驼峰命名配置如下:
# XML_NAME_FORMAT=CAMEL

# 注释配置
# 作者 不配置此项则默认不生成@author注释信息
AUTHOR=TODO
# 创建版本号 不配置此项则默认不生成@since注释信息
SINCE_VERSION=1.0
# 当前版本号 不配置此项默认1.0
VERSION=1.0
```
