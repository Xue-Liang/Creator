/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gos.creator.mapper;

import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class MySqlMapper {

    private static PropertyResourceBundle bundle = null;

    private static Pattern pattern = Pattern.compile("([a-zA-Z0-9_-]+)");

    static {
        try {
            bundle = new PropertyResourceBundle(MySqlMapper.class.getResourceAsStream("./MySqlMapper.properties"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "没有找到" + MySqlMapper.class.getPackage() + ".MySqlMapper.properties");
        }
    }

    private MySqlMapper() {
    }
    private static final MySqlMapper mapper = new MySqlMapper();

    public static MySqlMapper getInstance() {
        return mapper;
    }

    /**
     * 把jdbc类型 转为 java 类型
     *
     * @param jdbcType
     * @return
     */
    public String toJavaType(String jdbcType) {
        if (jdbcType != null) {
            Matcher matcher = pattern.matcher(jdbcType);
            if (matcher.find()) {
                jdbcType = matcher.group(1).toLowerCase();
            }
        }
        String type = null;
        return bundle == null ? "" : bundle.getString(jdbcType);
    }
}
