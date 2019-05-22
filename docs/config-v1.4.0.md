返回 [快速开始](./quickstart.md)

```properties
########## 必选配置 start ###########
# 配置数据源为 mysql 数据库
db.type=mysql
# mysql 数据库配置
db.driver=com.mysql.cj.jdbc.Driver
db.url=jdbc:mysql://localhost:3306/test?useUnicode
db.table-schema=test
db.username=root
db.password=root

# oracle 数据库配置
db.driver=oracle.jdbc.driver.OracleDriver
db.url=jdbc:oracle:thin:@192.168.0.1:1521:test
db.table-schema=schema
db.username=root
db.password=root
########## 必选配置 end ###########


# 指定生成的表 多张表用英文逗号隔开,大小写不敏感,不配置则默认为 db.table-schema 下的全部表
generate.table-names=table1,table2
#是否启用日志 默认开启
generate.log.enabled=true

############### JAVA 配置 start ############
# 生成文件的公共包名 不配置则默认无公共包
generate.java.base-package=com.github.generator
# 生成 java 文件输出路径,不配置则默认为 [当前项目路径],路径请使用/或\\分隔
generate.java.base-out-put-path=C:/Users/admin/Desktop

# MAPPER 包名 如下配置 MAPPER 所在包路径为 {generate.java.base-out-put-path}.mapper
generate.java.mapper.package=mapper
# MAPPER 名称 默认配置 驼峰命名 + Mapper {0}Mapper
generate.java.mapper.name-format={0}Mapper
# MAPPER 文件输出路径 如下配置 最终输出路径为 {generate.java.base-out-put-path}/mapper
generate.java.mapper.path=/mapper

# POJO 包名 如下配置 POJO 所在包路径为 {generate.java.base-package}.pojo
generate.java.pojo.package=pojo
# 实体名称 默认配置 驼峰命名 {0}
generate.java.pojo.name-format={0}
# pojo文件输出路径 如下配置 最终输出路径为 {generate.java.base-out-put-path}/pojo
generate.java.pojo.path=/pojo

############## 注释 start ##############
# 作者 不配置此项则默认不生成 @author 注释信息
generate.java.comment.author=TODO
# 创建版本号 不配置此项则默认不生成 @since 注释信息
generate.java.comment.since-version=1.0
# 当前版本号 不配置此项默认 @version : 1.0
generate.java.comment.current-version=1.0
############## 注释 end ################

############### 方法配置 start ###############
# 新增数据，默认为 true
generate.java.method.insert.enabled=true
# 批量新增数据，默认为 true 
generate.java.method.batch-insert.enabled=true
# 根据传入参数删除数据，默认为 true
generate.java.method.delete-by-where.enabled=true
# 根据主键删除数据，默认为 true 如果表 未设置主键 则不生成此方法
generate.java.method.delete-by-primary-key.enabled=true
# 根据主键更新数据，默认为 true 如果表 未设置主键 则不生成此方法
generate.java.method.update-by-primary-key.enabled=true
# 根据传入参数查询数据列表，默认为 true
generate.java.method.query-by-where.enabled=true
# 根据主键查询数据，默认为 true 如果表 未设置主键 则不生成此方法 
generate.java.method.query-by-primary-key.enabled=true
# 统计符合条件的数据数量，默认为 true
generate.java.method.count-by-where.enabled=true
############### 方法配置 end ###############
############### JAVA 配置 end ############

##################### XML 配置 start ####################
# 生成资源文件输出路径,不配置则默认为 [当前项目路径],路径请使用/或\\分隔
generate.xml.out-put-path=${generate.java.base-out-put-path}
# XML 名称 默认 LOWER_CASE 表名称小写
# 下划线分隔的表名称转驼峰 例如 MY_USER -> MyUser.xml 
# xml.name.format=CAMEL
generate.xml.name-format=LOWER_CASE
# mybatis xml 的 parameterType 别名是否启用
generate.xml.mybatis.parameter-type.aliases.enabled=false
##################### XML 配置 end ####################
```