/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gos;

import com.gos.database.creator.domain.DataBase;
import com.gos.database.creator.service.MySqlCreatorService;
import com.gos.database.creator.service.MySqlMetaDataReader;
import com.gos.database.creator.service.impl.MySqlCreatorServiceImpl;
import com.gos.database.creator.service.impl.MySqlMetaDataReaderImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Driver;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xue Liang
 */
@SpringBootApplication(scanBasePackages = {"com.gos"})
public class Starter {

    public static void main(String... args) {
        SpringApplication.run(Starter.class, args);
    }

    @Controller
    public static class IndexController {
        @RequestMapping(path = "/gen", method = RequestMethod.POST)
        @ResponseBody
        public Object create(
                @RequestParam(name = "driver", required = false) String driver,
                @RequestParam(name = "url", required = false) String url,
                @RequestParam(name = "user", required = false) String user,
                @RequestParam(name = "password", required = false) String password,
                @RequestParam(name = "entity", required = false) String entity,
                @RequestParam(name = "dao", required = false) String dao,
                @RequestParam(name = "service", required = false) String service,
                @RequestParam(name = "directory", required = false) String directory,
                HttpServletRequest req, HttpServletResponse resp) {
            Map<String, Object> nr = new HashMap<>();
            Driver dri;
            try {
                dri = (Driver) Class.forName(driver).newInstance();
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
                nr.put("message", "注册数据库驱动不存在.");
                nr.put("status", "ERR");
                return nr;
            }
            if ((directory = directory.trim()).length() < 1) {
                nr.put("message", "请填写代码文件保存目录.");
                nr.put("status", "ERR");
                return nr;
            } else {
                File codeDirectory = new File(directory);
                if (!codeDirectory.exists()) {
                    boolean success = codeDirectory.mkdirs();
                    if(!success){
                        nr.put("message", "代码文件保存目录:"+codeDirectory+"不存在,自动创建目录失败.");
                        nr.put("status", "ERR");
                        return nr;
                    }
                }
            }
            if ((user = user.trim()).length() < 1) {
                nr.put("message", "请填写数据库用户名.");
                nr.put("status", "ERR");
                return nr;
            }
            if ((url = url.trim()).length() < 1) {
                nr.put("message", "请填写Url.");
                nr.put("status", "ERR");
                return nr;
            }
            if ((password = password.trim()).length() < 1) {
                nr.put("message", "请填写数据库密码.");
                nr.put("status", "ERR");
                return nr;
            }

            MySqlMetaDataReader reader = new MySqlMetaDataReaderImpl();
            DataBase dataBase;
            try {
                dataBase = reader.read(dri, url, user, password);
            } catch (Exception e) {
                nr.put("message", "读取数据库失败." + e.getMessage());
                nr.put("status", "ERR");
                return nr;
            }
            MySqlCreatorService creatorService = new MySqlCreatorServiceImpl();

            boolean success = true;
            try {
                success &= creatorService.createEntities(dataBase, directory, entity);
                success &= creatorService.createDao(dataBase, directory, entity, dao);
                success &= creatorService.createService(dataBase, directory, entity, dao, service);
            } catch (Exception e) {
                try(Writer writer = new StringWriter(1024)) {
                    e.printStackTrace(new PrintWriter(writer));
                    nr.put("message", writer.toString());
                    nr.put("status", "ERR");
                }catch(Exception ex){

                }
                return nr;
            }
            nr.put("message", "生成成功...");
            nr.put("status", "OK");
            return nr;
        }
    }

    @Configuration
    public static class ResourceSetting extends WebMvcConfigurerAdapter {

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/**").addResourceLocations("classpath:/pages/");
        }
    }

}
