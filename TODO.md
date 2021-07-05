# 1.5.1
- 代码优化
    - 优化 properties 文件加载

- GeneratorBuilder.addJavaTemplate 方法是否可重命名 见名知意

- 文档优化
    commonMethodNameFormatService 需配合各方法的 name-format 使用

- 优化自定义生成器的使用 JavaModuleInfo 增加构造器使用 枚举类型

# 1.6.0
- 提供 spring-boot starter 版本
- 支持 yaml 配置
- 修改项目为多模块项目
    - 将 samples 和 starter 放入项目中

- 集成 slf4j 打印日志
- 支持在模板中直接获取配置文件的配置
- 支持 java 参数传入获取变量 #{xxx}
- 增加属性，支持生成建表语句
- 如使用自定义模板 可不校验映射 懒加载