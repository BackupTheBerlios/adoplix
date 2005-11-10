/*
 * ErrorConstants.java
 *
 * Created on 23. Oktober 2005, 22:41
 */

package de.adoplix.internal.runtimeInformation.constants;

import de.adoplix.internal.server.AdoplixServer;
import de.adoplix.internal.runtimeInformation.AdopLog;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * @author  dirk
 */
public abstract class ErrorConstants {
    
    private static ErrorConstants errorConstants;
    private static Map errorMap = new HashMap();
    private static Logger logger = AdopLog.getLogger (AdoplixServer.class);
    
    /*
     * Errors caused by main and starting up
     * 0 - 99
     */
    public static final int STARTUP_NO_CONF_FILE_SELECTED = 10;

    /*
     * Basic Configuration - Errors
     * 100 - 199
     */
    public static final int CONFIGURATION_FILE_NOT_FOUND = 110;
    public static final int CONFIGURATION_KEY_NOT_FOUND = 120;
    public static final int CONFIGURATION_TYPE_FALSE = 122;
    
    /*
     * TaskConfiguration
     * 200 - 299
     */
    public static final int CONFIGURATION_TASK_NOT_CONFIGURED = 200;
    
    /*
     * Communication (Sockets etc.)
     * 300 - 399
     */
    public static final int COMMUNICATION_SOCKET_IO = 300;
    public static final int COMMUNICATION_SOCKET_ACCEPT = 301;
    
    /*
     * Communication message faults
     * 400 - 499
     */
    public static final int MESSAGE_CONTENT_MISSED = 400;
    public static final int MESSAGE_READ_ERROR = 401;
    public static final int MESSAGE_VALUE_TYPE_ERROR = 402;
            
    
    private ErrorConstants() {
        errorMap.put (new Integer (STARTUP_NO_CONF_FILE_SELECTED), "Start ohne Angabe einer Konfigurationsdatei.");
        
        errorMap.put (new Integer(CONFIGURATION_FILE_NOT_FOUND), "Konfigurationsdatei nicht gefunden.");
        errorMap.put (new Integer(CONFIGURATION_KEY_NOT_FOUND), "Schluessel nicht gefunden. Evtl. fehlerhafte XML-Struktur.");
        errorMap.put (new Integer(CONFIGURATION_TYPE_FALSE), "Konfigurierter Wert ist vom falschen Typ.");

        errorMap.put (new Integer(CONFIGURATION_TASK_NOT_CONFIGURED), "Task in interner Task-Liste nicht gefunden.");
        
        errorMap.put (new Integer(COMMUNICATION_SOCKET_IO), "Socket konnte nicht belauscht werden.");
        errorMap.put (new Integer(COMMUNICATION_SOCKET_ACCEPT), "Annahme einer Nachricht über Socket ist fehlgeschlagen.");
        
        errorMap.put (new Integer(MESSAGE_CONTENT_MISSED), "Eine erforderliche Information innerhalb einer Nachricht fehlt.");
        errorMap.put (new Integer(MESSAGE_READ_ERROR), "Eine Nachricht konnte nicht fehlerfrei gelesen werden.");
        errorMap.put (new Integer(MESSAGE_VALUE_TYPE_ERROR), "Wert eines Nachrichtenelements ist vom falschen Typ.");
    }
    
    public static String getErrorMsg (int errorNr) {
        try {
            return (String)errorMap.get(new Integer(errorNr));
        }
        catch (Exception ex) {
            logger.severe ("Couldn't get error message by error number: " + errorNr);
        }
        
        return "";
    }
}
