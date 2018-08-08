## code-generator

#### 简介

```
本项目致力于更简单易用的配置,更轻量快捷的生成代码.
```

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

#### 帮助改进文档...

不管你以何种方式发现了文档的不足，或是丢失对某一特性的描述，那么你能做的最好的事情莫过于去研究它并把文档写出来。

该文档 markdown 格式的源码文件可通过[项目的 Git 代码库](https://github.com/zhuyizhuo/code-generator)来获取。Fork 该源码库，做出更新，然后提交一个 pull request 吧。

你将成为本文档的最佳作者，code-generator 的用户定会过来查阅的。

#### 许可证

The code generator is released under version 2.0 of the [Apache License](http://www.apache.org/licenses/LICENSE-2.0).

#### 联系作者

```
如果在使用过程中遇到问题或者疑问,或提出改进建议,请联系作者.
```

添加请注明来源: code-generator

##### QQ: 2361883887

##### 微信:

![二维码](https://github.com/zhuyizhuo/notes/blob/master/wechat.png?raw=true)

