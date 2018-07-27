package com.zhuyizhuo.generator.utils;

/**
 * class: StringUtils <br>
 * description: 字符串工具类 <br>
 * time: 2018/7/27 11:35
 *
 * @author yizhuo <br>
 * @version 1.0
 */
public class StringUtils {

    /**
     * 表名转java驼峰命名(首字母大写)
     * @param tableName
     * @return
     */
    public static String changeTableName2Java(String tableName,String tableRegex) {
        String[] split = tableName.split(tableRegex);
        StringBuffer s = new StringBuffer(split[0]);
        for (int i = 1; i < split.length; i++) {
            s.append(firstUpper(split[i]));
        }
        return s.toString();
    }

    /**
     * 数据库字段名转java驼峰命名(首字母小写 驼峰处转大写)  连接符colmRegex可指定  默认为_
     * @param columnName
     * @return
     */
    public static String changeColmName2Java(String columnName,String colmRegex) {
        String[] split = columnName.split(colmRegex);
        StringBuffer s = new StringBuffer();
        char[] charArray = split[0].toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            s.append(toL(charArray[i]));
        }
        for (int i = 1; i < split.length; i++) {
            s.append(firstUpper(split[i]));
        }
        return s.toString();
    }

    /**
     * 首字母大写 其他字母小写
     * @param name
     * @return
     */
    public static String firstUpper(String name) {
        char[] cs = name.toCharArray();
        cs[0] = toU(cs[0]);
        for (int i = 1; i < cs.length; i++) {
            cs[i] = toL(cs[i]);
        }
        return String.valueOf(cs);
    }

    /**
     * 字母转大写
     * @param c
     * @return
     */
    public static char toU(char c) {
        if (c >= 'a' && c <= 'z') {
            return (char)(c - 32);
        }
        return c;
    }

    /**
     * 字母转小写
     * @param c
     * @return
     */
    public static char toL(char c) {
        if (c >= 'A' && c <= 'Z') {
            return (char)(c + 32);
        }
        return c;
    }

}
