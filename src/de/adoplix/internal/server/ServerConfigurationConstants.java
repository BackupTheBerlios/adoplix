/*
 * ServerConfigurationConstants.java
 *
 * Created on 23. Oktober 2005, 22:34
 */

package de.adoplix.internal.server;

/**
 *
 * @author  dirk
 */
public abstract class ServerConfigurationConstants {

    public static final String SERVER_HANDLING = "ServerHandling";
    public static final String SERVER_COMMUNICATION = "Communication";
    public static final String CLIENT_HANDLING = "ClientHandling";
    public static final String TASK_CONFIGURATION = "TaskConfiguration";
    public static final String SECURITY = "Security";
    public static final String ACTIVE_CONFIGURATION = "ActiveConfiguration";

    public static final String SERVER_ID = SERVER_HANDLING + ".ServerId";
    public static final String SERVER_PWD = SERVER_HANDLING + ".Pwd";
    
    public static final String REQUEST_PORT = SERVER_COMMUNICATION + ".RequestPort";
    public static final String SERVICE_PORT = SERVER_COMMUNICATION + ".ServicePort";
    public static final String CLIENT_PORT = SERVER_COMMUNICATION + ".ClientPort";
    
    public static final String MAX_CLIENT_THREADS = CLIENT_HANDLING + ".MaxClientThreads";
    public static final String TIMEOUT_CLIENT_MILLIS = CLIENT_HANDLING + ".TimeoutClientMillis";
            
    public static final String PATH_TASK_CONFIGURATION = TASK_CONFIGURATION + ".PathTaskConfiguration";      
            
    public static final String PWD_ADMINISTRATION = SECURITY + ".PwdAdministration";

    /** Security.ServerValidation */
    public static final String SERVER_VALIDATION = SECURITY + ".ServerValidation";
    /** Security.ServerValidation.ValidServer */
    public static final String VALIDATION_VALID_SERVER = SERVER_VALIDATION + ".ValidServer";
    public static final String VALIDATION_VALID_SERVER_ID = VALIDATION_VALID_SERVER + ".ServerId";
    public static final String VALID_SERVER = "ValidServer";
    
    /** ValidServerId */
    public static final String VALID_SERVER_ID = "ServerId";
    
    public static final String INTERVAL_GET_PROJECT_SEC = ACTIVE_CONFIGURATION + ".IntervalGetProjectSec";
}
