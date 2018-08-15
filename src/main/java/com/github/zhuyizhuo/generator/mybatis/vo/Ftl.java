package com.github.zhuyizhuo.generator.mybatis.vo;

import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.mybatis.convention.CommentInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.MethodInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.StratificationInfo;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;

/**
 * @author yizhuo
 * @version 1.0
 * time: 2018/7/29 17:44
 */
public class Ftl {

    private StratificationInfo stratificationInfo;
    private MethodInfo methodInfo;
    private CommentInfo commentInfo;
    private TableInfoFtl tableInfo;

    /** xml 参数类型 */
    private String parameterType;
    /** xml resultMap id */
    private String resultMapId;

    public Ftl() {

    }

    public StratificationInfo getStratificationInfo() {
        return stratificationInfo;
    }

    public void setStratificationInfo(StratificationInfo stratificationInfo) {
        this.stratificationInfo = stratificationInfo;
    }

    public MethodInfo getMethodInfo() {
        return methodInfo;
    }

    public void setMethodInfo(MethodInfo methodInfo) {
        this.methodInfo = methodInfo;
    }

    public CommentInfo getCommentInfo() {
        return commentInfo;
    }

    public void setCommentInfo(CommentInfo commentInfo) {
        this.commentInfo = commentInfo;
    }

    public TableInfoFtl getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(TableInfoFtl tableInfo) {
        this.tableInfo = tableInfo;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getResultMapId() {
        return resultMapId;
    }

    public void setResultMapId(String resultMapId) {
        this.resultMapId = resultMapId;
    }

    public void init() {
        boolean useTypeAliases = PropertiesUtils.getBooleanPropertiesDefaultFalse(ConfigConstants.PARAMETER_TYPE_USE_TYPE_ALIASES);
        if (useTypeAliases){
            setParameterType(GeneratorStringUtils.firstLower(stratificationInfo.getPojoName()));
        } else {
            setParameterType(stratificationInfo.getPojoFullPackage()+"."+stratificationInfo.getPojoName());
        }
        setResultMapId(GeneratorStringUtils.firstLower(tableInfo.getJavaTableName())+"ResultMap");
    }
}
