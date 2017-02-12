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

import java.net.URI;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Xue Liang
 */
public class MySqlMetaDataReaderImpl implements MySqlMetaDataReader {

    @Override
    public DataBase read(Driver driver, String url, String user, String password) throws Exception {
        DataBase dataBase = new DataBase();
        URI connURI = URI.create(url);
        URI subURI = URI.create(connURI.getSchemeSpecificPart());
        String databaseName = subURI.getPath().substring(1);
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            //String sql = "show tables";
            String sql = "select * from information_schema.tables where table_schema='"+databaseName+"'";
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet tables = stmt.executeQuery(sql)) {
                    while (tables.next()) {
                        String tableName = tables.getString("TABLE_NAME");
                        String tableDescription = tables.getString("TABLE_COMMENT");
                        Table table = new Table(tableName);
                        table.setDescription(tableDescription);

                        String entityClassName = UnderScoreNameParser.toCamel(tableName, false);
                        table.setEntityClassName(entityClassName);
                        sql = "select * from information_schema.columns where"
                                +" table_schema='"+databaseName+"' and table_name='"+tableName+"'";
                        try (Statement descStatment = conn.createStatement()) {
                            try (ResultSet fields = descStatment.executeQuery(sql)) {
                                while (fields.next()) {
                                    TableField field = new TableField();
                                    String name = fields.getString("COLUMN_NAME");
                                    field.setTableFieldName(name);

                                    String columnDescription = fields.getString("COLUMN_COMMENT");
                                    field.setDescription(columnDescription);

                                    String entityPropertyName = UnderScoreNameParser.toCamel(name, false);
                                    field.setEntityPropertyName(entityPropertyName);
                                    
                                    String entityFieldName = UnderScoreNameParser.toCamel(name, true);
                                    field.setEntityFieldName(entityFieldName);
                                    
                                    String type = fields.getString("DATA_TYPE").toLowerCase();
                                    type = MySqlMapper.getInstance().toJavaType(type);
                                    field.setJavaDataType(type);
                                    
                                    boolean isNull = "YES".equals(fields.getString("IS_NULLABLE").toUpperCase());
                                    field.setIsNull(isNull);
                                    
                                    Boolean isAutoIncrement ="AUTO_INCREMENT".equals(fields.getString("EXTRA").toUpperCase());
                                    field.setIsAutoIncrement(isAutoIncrement);
                                    
                                    boolean isKey = "PRI".equals(fields.getString("COLUMN_KEY").toUpperCase());
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
