package com.github.zhuo;

import com.github.zhuyizhuo.generator.mybatis.generator.Generator;
import com.github.zhuyizhuo.generator.mybatis.generator.GeneratorBuilder;

public class TestJar {
    public static void main(String[] args) {
        /** [建议]要生成的表名 多个可用英文逗号隔开; 缺省为当前表空间下所有表; mysql 数据库表名大小写敏感 */
        String tableName = "";
        /** [可选]可设置基础生成路径，默认生成至系统变量 user.dir 路径下，可修改为指定路径 */
        String outPath = System.getProperty("user.dir") + "/generator";

        Generator generator = new GeneratorBuilder()
                .properties("generate.table-names=" + tableName)
                .properties("generate.base.out-put-path=" + outPath)
                .build("test.properties");
        generator.generate();
    }
}
