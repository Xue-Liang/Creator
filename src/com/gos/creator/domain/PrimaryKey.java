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
public class PrimaryKey {

    private Collection<TableField> key;

    public Collection<TableField> getKey() {
        return key;
    }

    public void setKey(Collection<TableField> key) {
        this.key = key;
    }

}
