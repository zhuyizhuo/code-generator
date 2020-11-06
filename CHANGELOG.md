# CHANGELOG

## v1.5.0
- 新增 travis-ci.com 自动检测集成
- 增加数据库类型和 org.apache.ibatis.type.JdbcType 的映射
- 改善用户体验/可用性
- 移除 guava 依赖
- 修复添加类型转换未转大写的问题
- 默认将数据库日期类型映射为 LocalDateTime
- 新增支持作为 SpringBoot 子项目中使用
- 增加 timestamp 和 org.apache.ibatis.type.JdbcType.TIMESTAMP 的映射
- 内置 swagger vo 模板
- 内置 mybatis plus 代码模板支持,支持直接生成 mybatis plus 代码模板
- mapper 模板默认方法名简化
- 需新增模板常量类 统一管理所有模板路径