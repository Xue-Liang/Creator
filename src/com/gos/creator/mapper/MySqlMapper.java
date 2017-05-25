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
 * @author Xue Liang
 */
public class MySqlMapper {

    private static PropertyResourceBundle MySqlDataTypeMapper = null;

    private static PropertyResourceBundle ResultSetMethodMapper = null;

    private static Pattern pattern = Pattern.compile("([a-zA-Z0-9_-]+)");

    static {
        try {
            MySqlDataTypeMapper = new PropertyResourceBundle(MySqlMapper.class.getResourceAsStream("MySqlDataTypeMapper.properties"));
        } catch (IOException ex) {
        }

        try {
            ResultSetMethodMapper = new PropertyResourceBundle(MySqlMapper.class.getResourceAsStream("ResultSetMethodMapper.properties"));
        } catch (IOException ex) {
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
        return MySqlDataTypeMapper == null ? "" : MySqlDataTypeMapper.getString(jdbcType);
    }

    public String getJdbcType(String jdbcType) {
        if (jdbcType != null) {
            Matcher matcher = pattern.matcher(jdbcType);
            if (matcher.find()) {
                return jdbcType = matcher.group(1).toLowerCase();
            }
        }
        return "";
    }

    public String getResultSetMethodName(String jdbcType) {
        if (jdbcType != null) {
            Matcher matcher = pattern.matcher(jdbcType);
            if (matcher.find()) {
                jdbcType = matcher.group(1).toLowerCase();
            }
        }
        return ResultSetMethodMapper == null ? "" : ResultSetMethodMapper.getString(jdbcType);
    }


}
