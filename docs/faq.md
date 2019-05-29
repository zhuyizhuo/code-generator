数据库类型:MYSQL
请检查是否添加对应数据库驱动依赖!
Exception: java.sql.SQLException: Error setting driver on UnpooledDataSource. Cause: java.lang.ClassNotFoundException: Cannot find class: com.mysql.cj.jdbc.Driver


查询数据库结构异常!Exception:
### Error querying database.  Cause: java.sql.SQLException: The server time zone value '�й���׼ʱ��' is unrecognized or represents more than one time zone. You must configure either the server or JDBC driver (via the serverTimezone configuration property) to use a more specifc time zone value if you want to utilize time zone support.
### The error may exist in mybatis/mappers/MysqlDataBaseMapper.xml
### The error may involve com.github.zhuyizhuo.generator.mybatis.database.mapper.MysqlDataBaseMapper.getTableNameListBySchema
### The error occurred while executing a query
### Cause: java.sql.SQLException: The server time zone value '�й���׼ʱ��' is unrecognized or represents more than one time zone. You must configure either the server or JDBC driver (via the serverTimezone configuration property) to use a more specifc time zone value if you want to utilize time zone support.



