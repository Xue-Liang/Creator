/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gos.creator.domain;

/**
 * @author Xue Liang
 */
public class TableField {

    private String tableFieldName;
    private String entityFieldName;
    private String entityPropertyName;

    public String getDataBaseFieldDataType() {
        return dataBaseFieldDataType;
    }

    public void setDataBaseFieldDataType(String dataBaseFieldDataType) {
        this.dataBaseFieldDataType = dataBaseFieldDataType;
    }

    private String dataBaseFieldDataType;
    private String javaDataType;
    private String resultSetMethodName;
    private Boolean isNull;
    private Boolean isAutoIncrement;
    private String description;

    public String getTableFieldName() {
        return tableFieldName;
    }

    public void setTableFieldName(String tableFieldName) {
        this.tableFieldName = tableFieldName;
    }

    public String getEntityFieldName() {
        return entityFieldName;
    }

    public void setEntityFieldName(String entityFieldName) {
        this.entityFieldName = entityFieldName;
    }

    public String getEntityPropertyName() {
        return entityPropertyName;
    }

    public void setEntityPropertyName(String entityPropertyName) {
        this.entityPropertyName = entityPropertyName;
    }

    public String getJavaDataType() {
        return javaDataType;
    }

    public void setJavaDataType(String javaDataType) {
        this.javaDataType = javaDataType;
    }

    public String getResultSetMethodName() {
        return this.resultSetMethodName;
    }

    public void setResultSetMethodName(String name) {
        this.resultSetMethodName = name;
    }

    public void setIsNull(boolean isNull) {
        this.isNull = isNull;
    }

    public boolean getIsNull() {
        return this.isNull;
    }

    public Boolean getIsAutoIncrement() {
        return this.isAutoIncrement;
    }

    public void setIsAutoIncrement(Boolean isAutoIncrement) {
        this.isAutoIncrement = isAutoIncrement;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
