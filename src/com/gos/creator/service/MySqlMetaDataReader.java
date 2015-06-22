/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gos.creator.service;

import com.gos.creator.domain.DataBase;
import java.sql.Driver;

/**
 *
 * @author Administrator
 */
public interface MySqlMetaDataReader {
     DataBase read(Driver driver,String url,String user,String password) throws Exception;
}
