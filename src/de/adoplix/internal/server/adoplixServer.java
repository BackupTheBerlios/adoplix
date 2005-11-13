package de.adoplix.internal.server;
import de.adoplix.adapter.TaskAdapter;
import de.adoplix.adapter.TaskAdapterToClass;
import de.adoplix.adapter.TaskAdapterToPort;
import de.adoplix.internal.runtimeInformation.constants.ErrorConstants;
import de.adoplix.internal.configuration.TaskConfiguration;
import de.adoplix.internal.runtimeInformation.AdopLog;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationKeyNotFoundException;
import de.adoplix.internal.runtimeInformation.exceptions.TaskNotFoundException;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import de.adoplix.internal.configuration.ServerConfiguration;
import de.adoplix.internal.tasks.Task;
import de.adoplix.internal.telegram.XMLContainer;
import de.adoplix.internal.connection.PortListener;

/**
 * This class implements the central server functionality
 * @author dirkg
 */
public class AdoplixServer {
    
    /** Absolute path of Configuration-File */
    private static String _pathConfiguration = "";
    /** Container of own configuration */
    private static ServerConfiguration _serverConfiguration = null;
    /** Container of task configuration */
    private static TaskConfiguration _taskConfiguration = null;
    /** Counter for generating ThreadID's */
    private static int _threadCounter = 0;
    /** Counter for max. number of Clients */
    private static int _maxClientThreadNumber = 0;
    /** Counter for active ClientThreads */
    private static int _activeClientThreadsCount = 0;
//    /** Hashmap to find the event adaptors fast */
//    private static Map _adapterEventList = new HashMap();
//    /** Hashmap to find the service adaptors fast */
//    private static Map _adapterServiceList = new HashMap();
//    /** Hashmap to find the admin adaptors fast */
//    private static Map _adapterAdminList = new HashMap();

    
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
        startPortListener();
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

    /*
     * start the three listeners
     */
    private void startPortListener() {
        PortListenerExternal portListenerExternal = new PortListenerExternal(_serverConfiguration.getPortExternal());
        PortListenerAdmin portListenerAdmin = new PortListenerAdmin(_serverConfiguration.getPortAdmin ());
        Thread portListenerExternalThread = new Thread(portListenerExternal);
        Thread portListenerAdminThread = new Thread(portListenerAdmin);
        portListenerExternalThread.start();
        portListenerAdminThread.start();
    }
    
    /**
     * Starts an TaskAdapter, depending to the TaskID. That means that the
     * AdoplixServer knows what to do.
     * @param xmlContainer Transports the message received from a client-application.
     */
    public static synchronized void startTaskAdapter (XMLContainer xmlContainer) {
        try {
            Task task = _taskConfiguration.getTask (xmlContainer.getTaskId ());
            switch (task.getLocalAdapterConnType ()) {
                case (0):   // connection via port
                    TaskAdapterToPort taskAdapterPort = new TaskAdapterToPort(task, xmlContainer);
                    Thread taskAdapterPortThread = new Thread(taskAdapterPort);
                    taskAdapterPortThread.start();
                    
                case (1):
                    TaskAdapterToClass taskAdapterClass = new TaskAdapterToClass(task, xmlContainer);
                    Thread taskAdapterClassThread = new Thread(taskAdapterClass);
                    taskAdapterClassThread.start();
            }
        }
        catch (ConfigurationKeyNotFoundException cknfEx) {
            logger.warning(cknfEx.getMessage () + "; TaskId / TaskAdapter");
        } catch (TaskNotFoundException tnfEx) {
            logger.warning(tnfEx.getMessage());
        }
    }
    
    /**
     * Generate a unique ID for thread. <br>
     * This ID later is used to identify or search a thread.
     * @param obj Is the thread-object for what the id will be generated.
     * @return The generated ThreadId
     */
    public static synchronized String generateThreadId(Object obj) {
        String threadId = "";
        Format dateF = new SimpleDateFormat ("ddMMYYhhmmss");
        Date now = new Date();
        threadId = dateF.format (now);
        Format decF = new DecimalFormat("000");
        threadId+= decF.format (_threadCounter++);
        threadId+= obj.hashCode ();
        
        return threadId;
    }
    
    public static synchronized void clientThreadStopped() {
        if (_activeClientThreadsCount++ > _maxClientThreadNumber) {
            _activeClientThreadsCount = _maxClientThreadNumber;
        }
    }
    
    public static synchronized boolean startClientThreadAllowed() {
        if (_maxClientThreadNumber-- > 0) {
            return true;
        }
        else {
            _maxClientThreadNumber = 0;
            return false;
        }
    }
    
}
