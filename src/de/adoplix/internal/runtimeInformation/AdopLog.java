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
    private static Level level = Level.ALL;
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
        
        returnLogger = Logger.getLogger (canonicalClassName);
        returnLogger.setLevel(level);
        if (fileHandler == null) {
            // Logger doesn't exists yet
            String adoplixPath = System.getProperty ("ADOPLIX_PATH", ".");
            try{
                fileHandler = new FileHandler(adoplixPath + "/log/adoplix_server.log", 1000000, 10, true);
            } catch (Throwable th) {
                System.out.println("Setting path for logfile not possible: " + th.getMessage());
                System.out.println("Please verify if directory exists: " + adoplixPath + "/log/adoplix_server.log");
                System.out.println("Therefore the programm is not be able to write or show log-messages it will be stopped now!");
                System.exit (1);
            }
            fileHandler.setFormatter (new SimpleFormatter());
            returnLogger.addHandler (fileHandler);
        }
        
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
    public static void setLevel (Level level) {
        AdopLog.level = level;
    }
    
    public static void setLevel(String level) {
        level = level.toUpperCase();
        if (level.equals("severe")) setLevel(Level.SEVERE);
        if (level.equals("all")) setLevel(Level.ALL);
        if (level.equals("fine")) setLevel(Level.FINE);
        if (level.equals("finer")) setLevel(Level.FINER);
        if (level.equals("finest")) setLevel(Level.FINEST);
        if (level.equals("info")) setLevel(Level.INFO);
        if (level.equals("config")) setLevel(Level.CONFIG);
        if (level.equals("warning")) setLevel(Level.WARNING);
        if (level.equals("off")) setLevel(Level.OFF);
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
