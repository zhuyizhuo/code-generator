package com.github.zhuyizhuo.generator.mybatis.dto;

/**
 * class: JavaClassDefinition <br>
 * description: java 类定义 <br>
 * time: 2019/5/23
 *
 * @author yizhuo <br>
 * @since 1.4.0
 */
public class JavaClassDefinition {
    /** 包路径 */
    private String fullPackage;
//    /** 导入类型 */
//    private Set<String> imports;
//    /** 注释信息 */
//    private ClassCommentInfo classComment;
    /** 类名 */
    private String className;
//    /** 字段信息 */
//    private List<FieldDefinition> fields;
//    /** 方法信息 */
//    private List<MethodDefinition> methods;

    public JavaClassDefinition() {
    }

    public JavaClassDefinition(String fullPackage) {
        this.fullPackage = fullPackage;
    }

    public JavaClassDefinition(String fullPackage, String className) {
        this.fullPackage = fullPackage;
        this.className = className;
    }

    public String getFullPackage() {
        return fullPackage;
    }

    public void setFullPackage(String fullPackage) {
        this.fullPackage = fullPackage;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
