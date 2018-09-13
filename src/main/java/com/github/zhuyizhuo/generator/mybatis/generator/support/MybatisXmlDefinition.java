package com.github.zhuyizhuo.generator.mybatis.generator.support;

import java.util.ArrayList;
import java.util.List;

/**
 * class: MybatisXmlDefinition <br>
 * description: TODO <br>
 *
 * @author yizhuo <br>
 * @since #since#
 */
public class MybatisXmlDefinition extends TableDefinition {

    private List<String> mybatisHeader;
    /** 命名空间 */
    private String nameSpace;
    /** 结果集 */
    private ResultMapDefinition resultMap;
    /** xml 参数类型 */
    private String parameterType;

    public MybatisXmlDefinition() {
        mybatisHeader = new ArrayList<String>();
        resultMap = new ResultMapDefinition();
    }

    public List<String> getMybatisHeader() {
        return mybatisHeader;
    }

    public void addMybatisXmlHeaderLine(CharSequence headerLine){
        this.mybatisHeader.add(String.valueOf(headerLine));
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public ResultMapDefinition getResultMap() {
        return resultMap;
    }

    public void setResultMap(ResultMapDefinition resultMap) {
        this.resultMap = resultMap;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

}
