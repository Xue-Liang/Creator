/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gos.creator.service.impl;

import com.gos.creator.domain.DataBase;
import com.gos.creator.domain.Table;
import com.gos.creator.service.MetaDataReader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class MetaDataReaderImple implements MetaDataReader {

    @Override
    public DataBase readFromMySql(Driver driver, String host, int port,String dataBaseName, String user, String password) {
        String url = "jdbc:mysql://"+host+":"+port;
        Properties properties = new Properties();
        properties.put("user", user);
        properties.put("password",password);
        
        
        try(Connection conn = driver.connect(url, properties)){
            String[] sql = {"use `" + dataBaseName+"`", "show tables"};
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(sql[0]);
                try (ResultSet rs = stmt.executeQuery(sql[1])) {
                    while (rs.next()) {
                        Table bean = new Table(rs.getString(1));
                    }
                }
            } catch (Exception e) {
                return null;
            }
            
        }catch(Exception e){
        
        }
        return null;
    }
}
