/*
 * AdopLog.java
 *
 * Created on 4. November 2005, 21:10
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package de.adoplix.internal.tools;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author dirk
 */
public class AdopLog {
    
    public  static AdopLog adoplog;
    private static Logger logger;
    private static FileHandler fileHandler;
    private static Level level = Level.INFO;
    
    public AdopLog (Class clazz) {
        String adoplixPath = System.getProperty ("ADOPLIX_PATH", ".");
        try{
            fileHandler = new FileHandler (adoplixPath + "/adoplix_server.log", 1000000, 10, Boolean.TRUE);
        } catch (IOException ioEx) {}
        
        System.out.println(clazz.getCanonicalName ());
        logger = Logger.getLogger (clazz.getCanonicalName ());
        fileHandler.setFormatter (new SimpleFormatter());
        logger.addHandler (fileHandler);
        String name = LogManager.getLogManager ().getLoggerNames ().nextElement ();
    }
    
    public static AdopLog getLogger (Class clazz) {
        if (null == adoplog) {
            adoplog = new AdopLog(clazz);
        }
        return adoplog;
    }
    
    public void log(String msg) {
        logger.log(level, msg);
    }
    
    public void setLevel (Level newLevel) {
        level = newLevel;
    }
    
    public void error (String errNr) {
        logger.log(Level.SEVERE, errNr);
    }
    
    public void info(String infoNr) {
        
        String name = logger.getName ();
        logger.log(Level.INFO, infoNr);
    }
}
