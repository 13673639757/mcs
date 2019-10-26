package com.mcs.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName CheckUtil
 * @Description 校验
 * @Author szq
 * @Date 2019/9/27 10:05
 * @Version 1.0
 */
public class CheckUtil {
    public static void main(String[] args) {
        /*testPhone("15754332486");*/
        System.out.println(testPhone("1"));
    }
    public static boolean testPhone(String str) {
        Pattern pat = Pattern.compile("^[1][3578][0-9]{9}$");
        Matcher mat = pat.matcher(str);
        return mat.find();
    }
}
