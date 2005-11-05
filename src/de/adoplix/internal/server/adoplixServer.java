package de.adoplix.internal.server;

import de.adoplix.internal.runtimeInformation.constants.ErrorConstants;
import de.adoplix.internal.tasks.TaskConfiguration;
import de.adoplix.internal.tools.AdopLog;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements the central server functionality
 * @author dirkg
 */
public class AdoplixServer {
    
    /** Absolute path of Configuration-File */
    private static String _pathConfiguration = "";
    /** Container of own configuration */
    private ServerConfiguration _serverConfiguration = null;
    /** Container of task configuration */
    private TaskConfiguration _taskConfiguration = null;
    
    private static Logger logger = AdopLog.getLogger (AdoplixServer.class);
    
    public static void main (String[] args) {
        logger.info("adoplix start");
        parseArguments (args);
        AdoplixServer server = new AdoplixServer (args);
        while (null != server) {
            try {
                Thread.sleep ( 1000 );
            } catch (Exception ex) {}
        }
        System.exit (0);
    }
    
    /**
     * Analyse of Calling Arguments
     * @param args Arguments used when program was called <br>
     * [0] = H: Hilfe <br>
     * [0] = F: File;  [1] = Dateiname
     */
    private static void parseArguments (String[] args) {
        logger.info ("try to analyse arguments");

        if (null != args) {
            if (args.length > 0) {
                args[0] = args[0].toUpperCase ();
                if (args[0].indexOf ("H") >= 0) {
                    showArguments ();
                }
                if (args[0].indexOf ("F") >= 0) {
                    if (args.length > 1) {
                        _pathConfiguration = args[1];
                    } else {
                        logger.severe (ErrorConstants.getErrorMsg (10));
                        System.out.println (ErrorConstants.getErrorMsg (10));
                        System.exit (1);
                    }
                }
                else {
                    // no configuration file selected - server exit
                    logger.severe (ErrorConstants.getErrorMsg (10));
                }
            }
        }
    }
    
    /**
     * Shows used arguments when program was started
     */
    private static void showArguments () {
        
    }
    
    
    /**
     * Creates a new instance of AdoplixServer
     * Reads the own configuration and the task-configuration
     * @param args Arguments used when program was called
     */
    public AdoplixServer (String[] args) {
        System.out.println ("adoplix");
        System.out.flush ();
        
        readConfigurations();
    }
    
    private void readConfigurations () {
        long timeToReadConfiguration = System.currentTimeMillis ();
        _serverConfiguration = new ServerConfiguration (_pathConfiguration);
        timeToReadConfiguration = System.currentTimeMillis () - timeToReadConfiguration;
        System.out.println (timeToReadConfiguration);
        
        String taskConfiguration = _serverConfiguration.getPathTaskConfiguration ();
        if (null != taskConfiguration &&
                taskConfiguration.length () > 0) {
            _taskConfiguration = new TaskConfiguration (taskConfiguration);
        }
    }
}
