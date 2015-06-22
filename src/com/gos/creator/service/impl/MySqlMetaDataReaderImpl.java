/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gos.creator.service.impl;

import com.gos.creator.domain.DataBase;
import com.gos.creator.domain.Table;
import com.gos.creator.domain.TableField;
import com.gos.creator.mapper.MySqlMapper;
import com.gos.creator.service.MySqlMetaDataReader;
import com.gos.creator.util.UnderScoreNameParser;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Administrator
 */
public class MySqlMetaDataReaderImpl implements MySqlMetaDataReader {

    @Override
    public DataBase read(Driver driver, String url, String user, String password) throws Exception {
        DataBase dataBase = new DataBase();
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "show tables";
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet tables = stmt.executeQuery(sql)) {
                    while (tables.next()) {
                        String tableName = tables.getString(1);
                        Table table = new Table(tableName);
                        String entityClassName = UnderScoreNameParser.toCamel(tableName, false);
                        table.setEntityClassName(entityClassName);
                        String desc = "desc " + tableName;
                        try (Statement descStatment = conn.createStatement()) {
                            try (ResultSet fields = descStatment.executeQuery(desc)) {
                                while (fields.next()) {
                                    TableField field = new TableField();
                                    String name = fields.getString("Field");
                                    field.setTableFieldName(name);
                                    
                                    String entityPropertyName = UnderScoreNameParser.toCamel(name, false);
                                    field.setEntityPropertyName(entityPropertyName);
                                    
                                    String entityFieldName = UnderScoreNameParser.toCamel(name, true);
                                    field.setEntityFieldName(entityFieldName);
                                    
                                    
                                    String type = fields.getString("Type");
                                    type = MySqlMapper.getInstance().toJavaType(type);
                                    field.setJavaDataType(type);
                                    
                                    boolean isNull = "YES".equals(fields.getString("Null"));
                                    boolean isKey = "PRI".equals(fields.getString("Key"));

                                    field.setIsNull(isNull);

                                    if (isKey) {
                                        table.addPrimaryKey(field);
                                    }
                                    table.addField(field);
                                }
                            }
                        }
                        dataBase.addTable(table);
                    }
                }
            }
        }
        return dataBase;
    }
}
