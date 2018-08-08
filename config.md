##### 默认配置:

```properties
# 数据库配置

# 数据库驱动 DB_DRIVER
# 数据库链接 DB_URL
# 表空间 数据库名 DB_TABLE_SCHEMA
# 数据库用户名 DB_USERNAME
# 数据库密码 DB_PASSWORD
# 数据库类型 DB_TYPE

#MYSQL数据库配置
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
#DB_INCLUDE_TABLE_NAME=Test

#生成文件输出路径 不配置则默认为当前项目路径 路径请使用/ 或\\分隔 建议使用/
#FILE_OUT_PUT_PATH=C:/Users/yizhuo/Desktop
#生成文件的包名  不配置则默认为com.github.generator
BASE_PACKAGE=com.github.generator

#注释配置
# 作者 不配置此项则作者默认 TODO
AUTHOR=TODO
# 创建版本号 不配置此项则生成类无此注释
#SINCE_VERSION=1.0
#当前版本号 不配置此项默认1.0
VERSION=1.0

#XML中parameterType 是否使用别名 默认为false
PARAMETER_TYPE_USE_TYPE_ALIASES=false
#是否启用日志 默认开启
LOG_ENABLED=true

# 实体名称
POJO_NAME_FORMAT={0}POJO
# Service 层名称
SERVICE_NAME_FORMAT={0}Service
# Service 实现类名称
SERVICE_IMPL_NAME_FORMAT={0}ServiceImpl
# DAO 层名称
DAO_NAME_FORMAT={0}Dao
```
