/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gos.creator.domain;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Xue Liang
 */
public class PrimaryKey {

    private List<TableField> key;

    public List<TableField> getKey() {
        return key;
    }

    public void setKey(List<TableField> key) {
        this.key = key;
    }

    public void addKey(TableField field) {
        if (this.key == null) {
            this.key = new LinkedList<>();
        }
        this.key.add(field);
    }

}
