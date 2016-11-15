/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gos.creator.service.impl;

import com.gos.creator.constance.NormalName;
import com.gos.creator.domain.DataBase;
import com.gos.creator.domain.Table;
import com.gos.creator.service.MySqlCreatorService;
import com.gos.creator.util.UnderScoreNameParser;
import com.gos.creator.util.VelocityEngineUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Xue Liang
 */
public class MySqlCreatorServiceImpl implements MySqlCreatorService {
    private static final Logger logger = LoggerFactory.getLogger(MySqlCreatorServiceImpl.class);

    @Override
    public boolean createEntities(DataBase dataBase, String dir, String packageName) throws Exception {
        String packageDirectory = dir + "/" + packageName.replaceAll("\\.", "/");
        File target = new File(packageDirectory);
        if (!target.exists()) {
            if (!target.mkdirs()) {
                return false;
            }
        }
        String here = this.getClass().getResource("/com/gos/creator/template/Entity.vm").toString().replace("file:", "");

        Template template = VelocityEngineUtil.getTemplate(new File(here));
        for (Table table : dataBase.getTables()) {
            File sourceCodeFile = new File(packageDirectory + "/" + table.getEntityClassName() + ".java");
            try (Writer writer = new FileWriter(sourceCodeFile)) {
                Context context = new VelocityContext();
                context.put(NormalName.EntityPackageName.getValue(), packageName);
                context.put(NormalName.Table.getValue(), table);
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                context.put(NormalName.Now.getValue(), formatter.format(Calendar.getInstance(Locale.PRC).getTime()));
                if (template != null)
                    template.merge(context, writer);
            }
        }
        return true;
    }

    @Override
    public boolean createDao(DataBase dataBase, String dir, String entityPackageName, String daoPackageName) throws Exception {
        String packageDirectory = dir + "/" + daoPackageName.replaceAll("\\.", "/");
        File target = new File(packageDirectory);
        if (!target.exists()) {
            if (!target.mkdirs()) {
                return false;
            }
        }
        String here = this.getClass().getResource("../../template/AbstractDao.vm").toString().replace("file:", "");
        Template template = VelocityEngineUtil.getTemplate(new File(here));
        for (Table table : dataBase.getTables()) {
            File sourceCodeFile = new File(packageDirectory + "/Abstract" + table.getEntityClassName() + "Dao.java");
            try (Writer writer = new FileWriter(sourceCodeFile)) {
                Context context = new VelocityContext();
                context.put(NormalName.EntityPackageName.getValue(), entityPackageName);
                context.put(NormalName.DaoPackageName.getValue(), daoPackageName);
                context.put(NormalName.Table.getValue(), table);
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                context.put(NormalName.Now.getValue(), formatter.format(Calendar.getInstance(Locale.PRC).getTime()));
                if (template != null)
                    template.merge(context, writer);
            }
        }

        here = this.getClass().getResource("../../template/Dao.vm").toString().replace("file:", "");
        template = VelocityEngineUtil.getTemplate(new File(here));
        for (Table table : dataBase.getTables()) {
            File sourceCodeFile = new File(packageDirectory + "/" + table.getEntityClassName() + "Dao.java");
            try (Writer writer = new FileWriter(sourceCodeFile)) {
                Context context = new VelocityContext();
                context.put(NormalName.EntityPackageName.getValue(), entityPackageName);
                context.put(NormalName.DaoPackageName.getValue(), daoPackageName);
                context.put(NormalName.Table.getValue(), table);
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                context.put(NormalName.Now.getValue(), formatter.format(Calendar.getInstance(Locale.PRC).getTime()));
                if (template != null)
                    template.merge(context, writer);
            }
        }


        here = this.getClass().getResource("../../template/SqlBuilder.vm").toString().replace("file:", "");
        template = VelocityEngineUtil.getTemplate(new File(here));
        File sourceCodeFile = new File(packageDirectory + "/" + "SqlBuilder.java");
        try (Writer writer = new FileWriter(sourceCodeFile)) {
            Context context = new VelocityContext();
            context.put(NormalName.EntityPackageName.getValue(), entityPackageName);
            context.put(NormalName.DaoPackageName.getValue(), daoPackageName);
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            context.put(NormalName.Now.getValue(), formatter.format(Calendar.getInstance(Locale.PRC).getTime()));
            if (template != null)
                template.merge(context, writer);
        }
        return true;
    }

    @Override
    public boolean createService(DataBase dataBase, String dir, String entityPackageName, String daoPackageName,
                                 String servicePackageName) throws Exception {
        String packageDirectory = dir + "/" + servicePackageName.replaceAll("\\.", "/");
        File target = new File(packageDirectory);
        if (!target.exists()) {
            if (!target.mkdirs()) {
                return false;
            }
        }
        String here = this.getClass().getResource("../../template/Service.vm").toString().replace("file:", "");

        Template template = VelocityEngineUtil.getTemplate(new File(here));
        for (Table table : dataBase.getTables()) {
            File sourceCodeFile = new File(packageDirectory + "/" + table.getEntityClassName() + "Service.java");
            try (Writer writer = new FileWriter(sourceCodeFile)) {
                Context context = new VelocityContext();
                context.put("dao", UnderScoreNameParser.toCamel(table.getEntityClassName() + "Dao", true));
                context.put(NormalName.EntityPackageName.getValue(), entityPackageName);
                context.put(NormalName.DaoPackageName.getValue(), daoPackageName);
                context.put(NormalName.ServicePackageName.getValue(), servicePackageName);
                context.put(NormalName.Table.getValue(), table);
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                context.put(NormalName.Now.getValue(), formatter.format(Calendar.getInstance(Locale.PRC).getTime()));
                if (template != null)
                    template.merge(context, writer);
            }
        }


        packageDirectory = (dir + "/" + servicePackageName + "/impl").replaceAll("\\.", "/");
        target = new File(packageDirectory);
        if (!target.exists()) {
            if (!target.mkdirs()) {
                return false;
            }
        }
        here = this.getClass().getResource("../../template/ServiceImpl.vm").toString().replace("file:", "");

        template = VelocityEngineUtil.getTemplate(new File(here));
        for (Table table : dataBase.getTables()) {
            File sourceCodeFile = new File(packageDirectory + "/" + table.getEntityClassName() + "ServiceImpl.java");
            try (Writer writer = new FileWriter(sourceCodeFile)) {
                Context context = new VelocityContext();
                context.put("dao", UnderScoreNameParser.toCamel(table.getEntityClassName() + "Dao", true));
                context.put(NormalName.EntityPackageName.getValue(), entityPackageName);
                context.put(NormalName.DaoPackageName.getValue(), daoPackageName);
                context.put(NormalName.ServicePackageName.getValue(), servicePackageName);
                context.put(NormalName.Table.getValue(), table);
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                context.put(NormalName.Now.getValue(), formatter.format(Calendar.getInstance(Locale.PRC).getTime()));
                if (template != null)
                    template.merge(context, writer);
            }
        }
        return true;
    }

}
