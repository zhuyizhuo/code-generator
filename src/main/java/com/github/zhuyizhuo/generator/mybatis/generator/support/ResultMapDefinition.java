package com.github.zhuyizhuo.generator.mybatis.generator.support;

import java.util.ArrayList;
import java.util.List;

/**
 * class: ResultMapDefinition <br>
 * description: TODO <br>
 * time: 2018/9/13
 *
 * @author yizhuo <br>
 * @since #since#
 */
public class ResultMapDefinition {
    /** xml resultMap id */
    private String resultMapId;
    /** 类型 */
    private String type;
    /** result集合 */
    private List<Result> results;

    public ResultMapDefinition() {
        results = new ArrayList<Result>();
    }

    public String getResultMapId() {
        return resultMapId;
    }

    public void setResultMapId(String resultMapId) {
        this.resultMapId = resultMapId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Result> getResults() {
        return results;
    }

    public void addResult(Result result) {
        this.results.add(result);
    }

    public static class Result{
        private boolean primaryKey;
        private String column;
        private String property;

        public Result(boolean primaryKey, String column, String property) {
            this.primaryKey = primaryKey;
            this.column = column;
            this.property = property;
        }

        public boolean isPrimaryKey() {
            return primaryKey;
        }

        public void setPrimaryKey(boolean primaryKey) {
            this.primaryKey = primaryKey;
        }

        public String getColumn() {
            return column;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }
    }
}
