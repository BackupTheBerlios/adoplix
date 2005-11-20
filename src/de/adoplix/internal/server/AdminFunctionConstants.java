/*
 * AdminFunctionConstants.java
 */
 
package de.adoplix.internal.server;
 
/**
 * Constants around the administration functions.
 * @author dirk
 */
public abstract class AdminFunctionConstants {
    
    /** Shutdown <br> */
    public static final String F_SHUTDOWN = "Shutdown";
    /** Restart <br> */
    public static final String F_RESTART = "Restart";
    /** SetTime <br> */
    public static final String F_SET_TIME = "SetTime";
    /** GetTime <br> */
    public static final String F_GET_TIME = "GetTime";
    /** GetLifeTimeMillis <br> */
    public static final String F_GET_LIFETIME_MILLIS = "GetLifeTimeMillis";
    /** ReReadServerConf <br> */
    public static final String F_REREAD_SERVER_CONF = "ReReadServerConf";
    /** ReReadTaskConf <br> */
    public static final String F_REREAD_TASK_CONF = "ReReadTaskConf";
    /** GetVersion <br> */
    public static final String F_GET_VERSION = "GetVersion";
    /** SendEvent <br> */
    public static final String F_SEND_EVENT = "SendEvent";
    /** StartMonitor <br> */
    public static final String F_START_MONITOR = "StartMonitor";
    /** StopMonitor <br> */
    public static final String F_STOP_MONITOR = "StopMonitor";
    /** SetLoggingLevel <br> */
    public static final String F_SET_LOGGING_LEVEL = "SetLoggingLevel";
    
    public static final String P_SOFT = "Soft";
    public static final String P_HARD = "Hard";
}
