/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gos.creator.service.impl;

import com.gos.creator.domain.DataBase;
import com.gos.creator.domain.Table;
import com.gos.creator.service.CreatorService;

/**
 *
 * @author Administrator
 */
public class CreatorServiceImpl implements CreatorService {

    @Override
    public boolean createEntities(DataBase dataBase, String dir, String packageName) {
        if (dataBase == null) {

        }
        if (dir == null || (dir = dir.trim()).length() < 1) {

        }
        if (packageName == null || (packageName = packageName.trim()).length() < 1) {

        }
        if (dataBase.getTables() == null || dataBase.getTables().size() < 1) {

        }

        for (Table table : dataBase.getTables()) {
            //TODO something...

        }
        return true;
    }

    public boolean createDao(DataBase dataBase, String dir, String packageName) {

        return true;
    }

}
