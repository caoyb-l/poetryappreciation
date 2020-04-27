package com.poetryappreciation.util;

public class StringUtil {

    public static boolean isTrimEmpty(String str) {
        boolean flag = false;
        if (str == null || "".equals(str.trim()) || "null".equals(str))  {
            //字符串不为空或空格
            flag = true;
        }
        return flag;
    }
}
