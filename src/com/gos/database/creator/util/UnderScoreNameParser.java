/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gos.database.creator.util;

/**
 *
 * @author Xue Liang
 */
public class UnderScoreNameParser {

    /**
     * 下划线名称转换
     *
     * @param name 带下划线的名称
     * @param firstCharLowerCase 首字母是否小写, true首字母小写，否则首字母大写
     * @return
     */
    public static String toCamel(String name, boolean firstCharLowerCase) {
        if (name == null || name.length() < 1) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        if (firstCharLowerCase) {
            result.append(name.substring(0, 1).toLowerCase());
        } else {
            result.append(name.substring(0, 1).toUpperCase());
        }

        for (int i = 1; i < name.length(); i++) {
            String s = name.substring(i, i + 1);
            if ("_".equals(s)) {
                i++;
                s = name.substring(i, i + 1).toUpperCase();
                result.append(s);
            } else {
                result.append(s);
            }
        }
        return result.toString();
    }
}
