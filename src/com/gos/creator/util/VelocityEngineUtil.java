/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gos.creator.util;

import java.io.File;

import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

/**
 *
 * @author Xue Liang
 */
public class VelocityEngineUtil {

    private static final VelocityEngine engine = new VelocityEngine();

    static {
        engine.addProperty(RuntimeConstants.ENCODING_DEFAULT, "UTF-8");
        engine.addProperty(RuntimeConstants.MAX_NUMBER_LOOPS, 2 << 10);
        engine.addProperty(RuntimeConstants.INPUT_ENCODING, "UTF-8");
        engine.addProperty(RuntimeConstants.OUTPUT_ENCODING, "UTF-8");
    }

    public static VelocityEngine getVelocityEngine() {
        return engine;
    }

    public static Template getTemplate(File templateFile) throws Exception {
        if (templateFile.exists()) {
            if (templateFile.isFile()) {
                engine.addProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, templateFile.getParent());
                return engine.getTemplate(templateFile.getName());
            }
        }
        return null;
    }
}
