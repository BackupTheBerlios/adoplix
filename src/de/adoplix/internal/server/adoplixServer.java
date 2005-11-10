package de.adoplix.internal.server;
import de.adoplix.internal.connection.AdapterConnector;
import de.adoplix.internal.runtimeInformation.constants.ErrorConstants;
import de.adoplix.internal.configuration.TaskConfiguration;
import de.adoplix.internal.runtimeInformation.AdopLog;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import de.adoplix.internal.configuration.ServerConfiguration;

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
    /** Counter for generating ThreadID's */
    private static int _threadCounter = 0;
    /** Counter for max. number of Clients */
    private static int _maxClientThreadNumber = 0;
    /** Counter for active ClientThreads */
    private static int _activeClientThreadsCount = 0;
    /** Hashmap to find the event adaptors fast */
    private static Map _adapterEventList = new HashMap();
    /** Hashmap to find the service adaptors fast */
    private static Map _adapterServiceList = new HashMap();
    /** Hashmap to find the admin adaptors fast */
    private static Map _adapterAdminList = new HashMap();

    
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
        PortListenerAdmin listenerAdmin = new PortListenerAdmin(_serverConfiguration.getPortAdmin());
        PortListenerLocal listenerLocal = new PortListenerLocal(_serverConfiguration.getPortLocal());
        PortListenerExternal listenerExternal = new PortListenerExternal(_serverConfiguration.getPortExternal());

//        Runnable listenerAdmin = new PortListenerLocal(_serverConfiguration.getPortAdmin());
//        Runnable listenerLocal = new PortListenerLocal(_serverConfiguration.getPortLocal());
//        Runnable listenerExternal = new PortListenerLocal(_serverConfiguration.getPortExternal());

        Thread listenerAdminThread = new Thread(listenerAdmin);
        Thread listenerLocalThread = new Thread(listenerLocal);
        Thread listenerExternalThread = new Thread(listenerExternal);

        listenerAdminThread.start();
        listenerLocalThread.start();
        listenerExternalThread.start();
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
    
    /**
     * The system-adapters hare registered in lists. <br>
     * Before they stop, they have to deregister here. This function removes them from the lists.
     * @param adaptor Is the adapter to remove from internal lists.
     */
    public static synchronized void deregisterAdapter(AdapterConnector adaptor) {
        String threadId = adaptor.getThreadId();
        if (adaptor.getClass().equals (AdapterConnectorAdmin.class)) {
            _adapterAdminList.remove (threadId);
        }
        if (adaptor.getClass ().equals (AdapterConnectorLocal.class)) {
            _adapterServiceList.remove (threadId);
        }
        if (adaptor.getClass ().equals (AdapterConnectorExternal.class)) {
            _adapterEventList.remove (threadId);
        }
        _activeClientThreadsCount--;
    }
    
    /**
     * The system-adapters have to register here after they started. <br>
     * Their ID is stored to lists and so they can later returned if recommended.
     * @param adaptor Is the new adaptor which will be added to the lists.
     * @return A generated unique ThreadId.
     */
    public static synchronized String registerAdapter(AdapterConnector adaptor) {
        String threadId = generateThreadId (adaptor);
        adaptor.setThreadId(threadId);
        
        if (adaptor.getClass().equals (AdapterConnectorAdmin.class)) {
            _adapterAdminList.put (threadId, adaptor);
        }
        if (adaptor.getClass ().equals (AdapterConnectorLocal.class)) {
            _adapterServiceList.put (threadId, adaptor);
        }
        if (adaptor.getClass ().equals (AdapterConnectorExternal.class)) {
            _adapterEventList.put (threadId, adaptor);
        }
        _activeClientThreadsCount++;
        return threadId;
    }
}
