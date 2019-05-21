package com.github.zhuyizhuo.generator.mybatis.dto;

/**
 * class: MethodDescription <br>
 * description: TODO <br>
 * time: 2019/5/21
 *
 * @author yizhuo <br>
 * @since 1.4.0
 */
public class MethodDescription {
    /** 是否生成方法 */
   private boolean enabled;
   /** 方法名 */
   private String methodName;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
