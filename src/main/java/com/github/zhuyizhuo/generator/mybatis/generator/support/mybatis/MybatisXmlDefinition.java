package com.github.zhuyizhuo.generator.mybatis.generator.support.mybatis;

import com.github.zhuyizhuo.generator.mybatis.dto.JavaColumnInfo;
import com.github.zhuyizhuo.generator.mybatis.generator.support.TableDefinition;
import com.github.zhuyizhuo.generator.utils.TypeConversion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * class: MybatisXmlDefinition <br>
 * description: TODO <br>
 *
 * @author yizhuo <br>
 * @since 1.3.2
 */
public class MybatisXmlDefinition extends TableDefinition {
    /** mybatis header xml头信息 */
    private List<String> mybatisHeader;
    /** 命名空间 */
    private String nameSpace;
    /** 结果集 */
    private ResultMapDefinition resultMap;
    /** 方法定义 */
    private Map<String,MybatisMethodDefinition> methods;
    /** xml 参数类型  -> method 关联? */
    private String parameterType;
    /** 列信息定义 */
    private List<MybatisColumnDefinition> columns;

    public MybatisXmlDefinition() {
        mybatisHeader = new ArrayList<String>();
        resultMap = new ResultMapDefinition();
        columns = new ArrayList<MybatisColumnDefinition>();
        methods = new HashMap<String, MybatisMethodDefinition>();
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

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public List<MybatisColumnDefinition> getColumns() {
        return columns;
    }

    public void addColumn(JavaColumnInfo columnInfo) {
        MybatisColumnDefinition mybatisColumnDefinition = new MybatisColumnDefinition(columnInfo);
        this.columns.add(mybatisColumnDefinition);
        getResultMap().addResult(mybatisColumnDefinition);
    }

    public Map<String, MybatisMethodDefinition> getMethods() {
        return methods;
    }

    public void addMethod(String methodName ,MybatisMethodDefinition method) {
        this.methods.put(methodName,method);
    }

    /**
     * 列定义
     */
    public class MybatisColumnDefinition extends JavaColumnInfo {
        /** test表达式 如果是string类型会判断是否空串 */
        private String testNotBlankExpression;
        /** test表达式 */
        private String testNotNullExpression;
        /** mybatis xml中 JDBC类型  */
        private String jdbcType;
        /** mybatis xml中 parameterType */
        private String parameterType;
        /** */
        private String column;

        private String property;

        public MybatisColumnDefinition(JavaColumnInfo javaColumnInfo) {
            super(javaColumnInfo);
            if (javaColumnInfo == null){
                throw new IllegalArgumentException("Init MybatisColumnDefinition error ! javaColumnInfo is null !");
            }
            this.jdbcType = TypeConversion.type2JdbcType(javaColumnInfo.getDataType());
            this.parameterType = TypeConversion.getTypeByMap(TypeConversion.parameterTypeMap,javaColumnInfo.getJavaDataType());
            this.column = javaColumnInfo.getColumnName();
            this.property = javaColumnInfo.getJavaColumnName();
            initTestExpression();
        }

        private void initTestExpression() {
            String javaColumnName = getJavaColumnName();
            this.testNotNullExpression = javaColumnName + " != null";
            this.testNotBlankExpression = this.testNotNullExpression;
            if ("STRING".equalsIgnoreCase(getJavaDataType())){
               this.testNotBlankExpression += " and " +javaColumnName+ " != '' ";
            }
        }

        public String getColumn() {
            return column;
        }

        public String getProperty() {
            return property;
        }

        public String getTestNotBlankExpression() {
            return testNotBlankExpression;
        }

        public String getTestNotNullExpression() {
            return testNotNullExpression;
        }

        public String getJdbcType() {
            return jdbcType;
        }

        public void setJdbcType(String jdbcType) {
            this.jdbcType = jdbcType;
        }

        public String getParameterType() {
            return parameterType;
        }

        public void setParameterType(String parameterType) {
            this.parameterType = parameterType;
        }

        @Override
        public String toString() {
            return super.toString() + "\n\t MybatisColumnDefinition{" +
                    "testNotBlankExpression='" + testNotBlankExpression + '\'' +
                    ", testNotNullExpression='" + testNotNullExpression + '\'' +
                    ", jdbcType='" + jdbcType + '\'' +
                    ", parameterType='" + parameterType + '\'' +
                    ", column='" + column + '\'' +
                    ", property='" + property + '\'' +
                    '}';
        }
    }

    /**
     * ResultMap 定义
     */
    public class ResultMapDefinition {
        /** xml resultMap id */
        private String id;
        /** 类型 */
        private String type;
        /** result集合 */
        private List<MybatisColumnDefinition> results;

        public ResultMapDefinition() {
            results = new ArrayList<MybatisColumnDefinition>();
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<MybatisColumnDefinition> getResults() {
            return results;
        }

        public void addResult(MybatisColumnDefinition columnInfo) {
            this.results.add(columnInfo);
        }

        @Override
        public String toString() {
            return "ResultMapDefinition{" +
                    "id='" + id + '\'' +
                    ", type='" + type + '\'' +
                    ", results=" + results +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MybatisXmlDefinition{" +
                "mybatisHeader=" + mybatisHeader +
                ", nameSpace='" + nameSpace + '\'' +
                ", resultMap=" + resultMap +
                ", parameterType='" + parameterType + '\'' +
                ", columns=" + columns +
                '}';
    }
}
