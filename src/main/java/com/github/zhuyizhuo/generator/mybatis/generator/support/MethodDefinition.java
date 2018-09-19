package com.github.zhuyizhuo.generator.mybatis.generator.support;

/**
 * class: MethodDefinition <br>
 * description: TODO <br>
 * time: 2018/9/14
 *
 * @author yizhuo <br>
 * @since #since#
 */
public class MethodDefinition {

    /** 是否生成方法 */
    private boolean methodEnabled;
    /** 方法注释 */
    private CommentDefinition methodComment;
    /** 方法名 */
    private String methodName;
    /** 返回类型 */
    private String returnType;
    /** 方法参数 */
    private ParameterDefinition parameter;

    public class ParameterDefinition{
        /** 参数类型 */
        private String type;
        /** 参数名 */
        private String name;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
