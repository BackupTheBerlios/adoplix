package de.adoplix.internal.server;
import java.net.Socket;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import de.adoplix.adapter.TaskAdapterToAdmin;
import de.adoplix.adapter.TaskAdapterToClass;
import de.adoplix.adapter.TaskAdapterToPort;
import de.adoplix.internal.configuration.ServerConfiguration;
import de.adoplix.internal.configuration.TaskConfiguration;
import de.adoplix.internal.connection.PortListenerAdmin;
import de.adoplix.internal.connection.PortListenerExternal;
import de.adoplix.internal.runtimeInformation.AdopLog;
import de.adoplix.internal.runtimeInformation.constants.ErrorConstants;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationKeyNotFoundException;
import de.adoplix.internal.runtimeInformation.exceptions.TaskNotFoundException;
import de.adoplix.internal.tasks.Task;
import de.adoplix.internal.telegram.XMLContainer;

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
    /** Max. number of Clients allowed */
    private static int _maxClientThreadNumber = 0;
    /** Counter for active ClientThreads */
    private static int _activeClientThreadsCount = 0;
//    /** Hashmap to find the event adaptors fast */
//    private static Map _adapterEventList = new HashMap();
//    /** Hashmap to find the service adaptors fast */
//    private static Map _adapterServiceList = new HashMap();
//    /** Hashmap to find the admin adaptors fast */
//    private static Map _adapterAdminList = new HashMap();
    private static String vid = "";
    
    
    private static Logger logger = AdopLog.getLogger (AdoplixServer.class);
    
    public static void main (String[] args) {
        logger.info ("adoplix start");
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
                        logger.severe (ErrorConstants.getErrorMsg (ErrorConstants.STARTUP_NO_CONF_FILE_SELECTED));
                        System.out.println (ErrorConstants.getErrorMsg (ErrorConstants.STARTUP_NO_CONF_FILE_SELECTED));
                        System.exit (1);
                    }
                } else {
                    // no configuration file selected - server exit
                    logger.severe (ErrorConstants.getErrorMsg (ErrorConstants.STARTUP_NO_CONF_FILE_SELECTED));
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
        
        readConfigurations ();
        startPortListener ();
    }
    
    private void readConfigurations () {
        long timeToReadConfiguration = System.currentTimeMillis ();
        _serverConfiguration = new ServerConfiguration (_pathConfiguration);
        timeToReadConfiguration = System.currentTimeMillis () - timeToReadConfiguration;
        logger.finest ("Zeitbedarf Lesen der Server-Konfiguration: " + timeToReadConfiguration);
        
//        AdopLog.setLevel(_serverConfiguration.getLogLevel());
        
        timeToReadConfiguration = System.currentTimeMillis();
        String taskConfiguration = _serverConfiguration.getPathTaskConfiguration ();
        if (null != taskConfiguration &&
                taskConfiguration.length () > 0) {
            _taskConfiguration = new TaskConfiguration (taskConfiguration);
        }
        timeToReadConfiguration = System.currentTimeMillis () - timeToReadConfiguration;
        logger.finest ("Zeitbedarf Lesen der Task-Konfiguration: " + timeToReadConfiguration);
    }
    
    /*
     * start the listeners to the admin port and to the external port
     */
    private void startPortListener () {
        PortListenerExternal portListenerExternal = new PortListenerExternal (_serverConfiguration.getPortExternal ());
        PortListenerAdmin portListenerAdmin = new PortListenerAdmin (_serverConfiguration.getPortAdmin ());
        Thread portListenerExternalThread = new Thread (portListenerExternal);
        Thread portListenerAdminThread = new Thread (portListenerAdmin);
        portListenerAdminThread.start ();
        portListenerExternalThread.start ();
    }
    
    /**
     * Starts an TaskAdapter, depending to the TaskID. That means that the
     * AdoplixServer knows what to do.
     * @param xmlContainer Transports the message received from a client-application.
     */
    public static synchronized void startTaskAdapter (Socket clientSocket, XMLContainer xmlContainer) {
        if (_activeClientThreadsCount < _serverConfiguration.getMaxClientThreads ()) {
            try {
                Task task = _taskConfiguration.getTask (xmlContainer.getTaskId ());
                switch (task.getLocalAdapterConnType ()) {
                    case (0):   // connection via port
                        TaskAdapterToPort taskAdapterPort = new TaskAdapterToPort (task, clientSocket, xmlContainer);
                        Thread taskAdapterPortThread = new Thread (taskAdapterPort);
                        taskAdapterPortThread.start ();
                        
                    case (1):
                        TaskAdapterToClass taskAdapterClass = new TaskAdapterToClass (task, clientSocket, xmlContainer);
                        Thread taskAdapterClassThread = new Thread (taskAdapterClass);
                        taskAdapterClassThread.start ();
                        
                    case (2):
                        TaskAdapterToAdmin taskAdapterAdmin = new TaskAdapterToAdmin (task, clientSocket, xmlContainer);
                        Thread taskAdapterAdminThread = new Thread (taskAdapterAdmin);
                        taskAdapterAdminThread.start ();
                        
                }
                _activeClientThreadsCount++;
            } catch (ConfigurationKeyNotFoundException cknfEx) {
                logger.warning (cknfEx.getMessage () + "; TaskId / TaskAdapter");
            } catch (TaskNotFoundException tnfEx) {
                logger.warning (tnfEx.getMessage ());
            }
        }
    }
    
    /**
     * Generate a unique ID for thread. <br>
     * This ID later is used to identify or search a thread.
     * @param obj Is the thread-object for what the id will be generated.
     * @return The generated ThreadId
     */
    public static synchronized String generateThreadId (Object obj) {
        String threadId = "";
        Format dateF = new SimpleDateFormat ("ddMMYYhhmmss");
        Date now = new Date ();
        threadId = dateF.format (now);
        Format decF = new DecimalFormat ("000");
        threadId+= decF.format ( new Integer(_threadCounter++));
        threadId+= obj.hashCode ();
        
        return threadId;
    }
    
    public static synchronized void clientThreadStopped () {
        if (_activeClientThreadsCount-- > _maxClientThreadNumber) {
            _activeClientThreadsCount = _maxClientThreadNumber;
        }
    }
    
    /**
     * Lets the server shut down.
     * @param hardOrSoft Two values are valid here: 'hard' or 'soft' (not case sensitive). <br>
     * If 'hard' the server stops immediately itself without taking care in which state the adaptor-threads are.<br>
     * If 'soft' the server stops the threads (adapors) regulary by using their 'stop'-mechanism.
     */
    public static void shutdown(String hardOrSoft){
    }

    /**
     * Restarts the server.
     * @param hardOrSoft Two values are valid here: 'hard' or 'soft' (not case sensitive). <br>
     * Restart means: Stopping all adaptors, cleaning runtime values, rereading the server-configuration.
     * Rereading the tasks-configuration and start up.
     * If 'hard' the server kills all threads immediately independent to their states. <>
     * If 'soft' the server waits until the adaptors have finished their jobs.
     */
    public static void restart(String hardOrSoft){
    }
    
    /**
     * Sets the local system time.
     * @param timeInMillis The new time to set.
     */
    public static void setTime(String timeInMillis){
    }
    
    /**
     * Retrieves the local system time.
     * @return Time in millis, stored in a String
     */
    public static String getTime() {
        return "";
    }
    
    /**
     * Retrieves the uptime of server (not new initialized by restart).
     * @return The millis since the server was started.
     */
    public static String getLifetimeMillis(){
        return "";
    }
    
    /**
     * Forces the server to read the server configuration again.
     * After this moment the new values are valid.
     */
    public static void reReadServerConf() {
    }
    
    /**
     * Forces the server to read the task configuration again.
     * After this moment the new values are valid.
     */
    public static void reReadTaskConf() {
    }
    
    /**
     * Retrieves the software version of the server.
     * @param A version String e.g. like '1.1.0 Beta 01.01.2006'
     */
    public static String getVersion(){
        return "";
    }
    
    /**
     * Forces the server to 'send' the message to the port listener.
     * The behaviour should nearly the same as if the message was sent by a client to the server.
     * @param cData Is an event as described in the specification and wrapped in XML-format.
     */
    public static String sendEvent(String cData) {
        return "";
    }
    
    /**
     * Forces the server to send cyclic messages which contain the situation of runtime-
     * depending parts.
     */
    public static void startMonitor(){
    }
    
    /**
     * Stops the monitoring.
     */
    public static void stopMonitor(){
    }
    
    /**
     * Sets the debug level. Affects to all software components which are part of the
     * server. Does not affect to external adaptors (AdapterService, AdapterEvent).
     * @param loggingLevel Valid values are the same like them of java.util.logging.Logger. <br>
     * The parameter is not case sensitiv.
     * all    \t - all messages are logged
     * finest \t - high detailed logging
     * finer  \t - fairly detailed logging
     * fine   \t - a kind of debugging; but not showing every detail
     * config \t - shows runtime information, system properties, ...
     * info   \t - important messages which should be recognized asap by user; output to console
     * warning\t - problems which potentially make the system unstable
     * severe \t - failures which should not be ignored by administrators
     * off    \t - no logging, no messages.
     */
    public static void setLoggingLevel(String loggingLevel) {
        
    }  
}
