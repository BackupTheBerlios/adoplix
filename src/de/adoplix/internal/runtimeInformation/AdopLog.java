/*
 * AdopLog.java
 *
 * Created on 4. November 2005, 21:10
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package de.adoplix.internal.runtimeInformation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author dirk
 */
public class AdopLog {
    
//    public  static AdopLog adoplog;
    private static FileHandler fileHandler;
    private static Level level = Level.INFO;
    private static List loggerList = new ArrayList();
    private static String vid = "AdopLog.java,v 1.1 2005/11/10 22:33:29 dirkg Exp";
    
    public AdopLog () {
    }
    
    public static Logger getLogger (String canonicalClassName) {
        // look if Logger exists
        Logger returnLogger = searchLogger(canonicalClassName);
        if (null != returnLogger) {
            return returnLogger;
        }
        
        // Logger doesn't exists yet
        String adoplixPath = System.getProperty ("ADOPLIX_PATH", ".");
        try{
            fileHandler = new FileHandler(adoplixPath + "/adoplix_server.log", 1000000, 10, true);
        } catch (IOException ioEx) {}
        
        returnLogger = Logger.getLogger (canonicalClassName);
        fileHandler.setFormatter (new SimpleFormatter());
        returnLogger.addHandler (fileHandler);
        loggerList.add (returnLogger);
        return returnLogger;
    }
    
    public static Logger getLogger (Class clazz) {
        return getLogger (clazz.getName ());
    }
    
//    public static void log(Class clazz, String msg) {
//        log (clazz.getCanonicalName (), msg);
//    }
//    
//    public static void log (String canonicalClassName, String msg) {
//        Logger classLogger = searchLogger(canonicalClassName);
//        if (null == classLogger) {
//            classLogger = getLogger(canonicalClassName);
//        }
//        classLogger.log(level, msg);
//    }
//    
    public static void setLevel (Level newLevel) {
        level = newLevel;
    }
    
    private static Logger searchLogger(String canonicalClassName) {
        Logger returnLogger = null;
        for (int i = 0; i < loggerList.size (); i++ ) {
            if (((Logger)loggerList.get (i)).getName ().equals (canonicalClassName)) {
                returnLogger = (Logger)loggerList.get (i);
            }
        }
        if (null != returnLogger) {
            return returnLogger;
        }
        return null;
    }

    private static Logger searchLogger(Class clazz) {
        return searchLogger(clazz.getName ());
    }
}
