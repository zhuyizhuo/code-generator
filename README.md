## code-generator

###### 轻量级的代码生成器

* 配置简单轻量
* 基于freemarker,可轻松自定义生成模板

###### 支持的数据库

* mysql
* oracle

###### 所需环境

* jdk1.7 +

#### 版本信息

| 版本 |                             功能                             | 主键策略                                                     |
| :--: | :----------------------------------------------------------: | ------------------------------------------------------------ |
| v1.0 | 从指定mysql或oracle数据库生成dao、pojo及mybatis3.x 的xml文件 | 默认取表的创建时间最早字段为主键                             |
| v1.1 |    移除oracle及mysql驱动依赖,由使用者自己按需引用对应驱动    | 默认取表的创建时间最早字段为主键                             |
| v1.2 |                            待发布                            | 支持多种主键策略<br/>1.生成的表无主键情况:<br/>  a.则取创建时间最早字段为主键<br/>  b.配置指定字段<br/>2.使用数据库主键为主键 |

#### 参考文档

- ##### [快速开始](./quickstart.md)

- ##### [配置文件详解](./config-v1.0.md)

#### License

The code generator is released under version 2.0 of the [Apache License](http://www.apache.org/licenses/LICENSE-2.0).

```
本项目致力于更简单易用的配置,更轻量快捷的生成代码.
如果在使用过程中遇到问题或者疑问,或提出改进建议,请联系作者.
```

#### 联系作者

添加请注明来源: code-generator

##### QQ: 2361883887

##### 微信:

![二维码](https://github.com/zhuyizhuo/notes/blob/master/wechat.png?raw=true)

