/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gos.creator.domain;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Xue Liang
 */
public class Table {

    private String name;
    private String entityClassName;
    private PrimaryKey primaryKey;
    private List<TableField> fields;
    private String description;

    public Table(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntityClassName() {
        return entityClassName;
    }

    public void setEntityClassName(String entityClassName) {
        this.entityClassName = entityClassName;
    }

    public List<TableField> getFields() {
        return fields;
    }

    public void setFields(List<TableField> fields) {
        this.fields = fields;
    }

    public PrimaryKey getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(PrimaryKey primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void addPrimaryKey(TableField field) {
        if (this.primaryKey == null) {
            this.primaryKey = new PrimaryKey();
        }
        this.primaryKey.addKey(field);
    }

    public void addField(TableField field) {
        if (this.fields == null) {
            this.fields = new LinkedList<>();
        }
        this.fields.add(field);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
