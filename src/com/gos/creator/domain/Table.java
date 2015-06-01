/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gos.creator.domain;

import java.util.Collection;

/**
 *
 * @author Administrator
 */
public class Table {

    private String name;
    private PrimaryKey primaryKey;
    private Collection<TableField> fields;

    public Table(String name){
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<TableField> getFields() {
        return fields;
    }

    public void setFields(Collection<TableField> fields) {
        this.fields = fields;
    }

    public PrimaryKey getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(PrimaryKey primaryKey) {
        this.primaryKey = primaryKey;
    }

}
