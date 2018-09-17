package com.github.zhuyizhuo.generator.mybatis.generator.support;

import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.mybatis.convention.ClassCommentInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.StratificationInfo;
import com.github.zhuyizhuo.generator.mybatis.database.pojo.ColumnInfo;
import com.github.zhuyizhuo.generator.mybatis.dto.JavaColumnInfo;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;
import com.google.common.collect.Lists;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * class: GenerateBeanDefinition <br>
 * description: 生成bean定义 <br>
 *
 * @author yizhuo <br>
 * @since 1.3.0
 */
public class GenerateBeanDefinition extends GenerateFileDefinition{
    /** 生成文件包全路径 */
    private String fullPackage;
    /** 类名 */
    private String className;
    /** 导入的类路径 */
    private Set<String> importPackages = new HashSet<String>();
    /** 类注释 */
    private ClassCommentInfo commentInfo;


}
