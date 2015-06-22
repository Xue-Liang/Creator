/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gos.creator.constance;

/**
 *
 * @author Xue Liang
 */
public enum NormalName {

    Table("Table"), EntityPackageName("EntityPackageName"), DaoPackageName("DaoPackageName"),Now("Now");

    private final String value;

    private NormalName(String v) {
        this.value = v;
    }

    public String getValue() {
        return value;
    }
}
