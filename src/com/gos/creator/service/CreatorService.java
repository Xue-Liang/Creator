/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gos.creator.service;

import com.gos.creator.domain.DataBase;

/**
 *
 * @author Administrator
 */
public interface CreatorService {
    public boolean createEntities(DataBase dataBase,String dir,String packageName);
    
    public boolean createDao(DataBase dataBase,String dir,String packageName);
}
