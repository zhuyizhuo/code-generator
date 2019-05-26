package com.github.zhuyizhuo.generator.mybatis.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * class: ClassCommentDefinition <br>
 * description: TODO 注释信息定义 <br>
 * time: 2018/9/11 21:44
 *
 * @author yizhuo <br>
 * @since 1.3.0
 */
public class CommentDefinition {
    /** 是否生成注释 */
    private boolean commentEnabled;
    /** 注释 */
    private List<String> docLines = new ArrayList<String>();

    public List<String> getDocLines() {
        return docLines;
    }

    public void addDocLine(CharSequence docLine){
        this.docLines.add(String.valueOf(docLine));
    }

}
