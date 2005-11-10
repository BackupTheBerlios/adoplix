/*
 * ServerConfigurationConstants.java
 *
 * Created on 23. Oktober 2005, 22:34
 */

package de.adoplix.internal.configuration;

/**
 *
 * @author  dirk
 */
public abstract class ServerConfigurationConstants {

    /** ServerHandling <br> Wurzel ohne Wert */
    public static final String SERVER_HANDLING = "ServerHandling";
    /** Communication <br> Wurzel ohne Wert */
    public static final String SERVER_COMMUNICATION = "Communication";
    /** ClientHandling <br> Wurzel ohne Wert */
    public static final String CLIENT_HANDLING = "ClientHandling";
    /** TaskConfiguration <br> Wurzel ohne Wert */
    public static final String TASK_CONFIGURATION = "TaskConfiguration";
    /** Security <br> Wurzel ohne Wert */
    public static final String SECURITY = "Security";
    /** ActiveConfiguration <br> Wurzel ohne Wert */
    public static final String ACTIVE_CONFIGURATION = "ActiveConfiguration";

    /** ServerHandling.ServerId <br> Eigene lokale ID */
    public static final String X_SERVER_ID = SERVER_HANDLING + "%ServerId";
    /** ServerHandling.Pwd <br> Passwort für ??? */
    public static final String X_SERVER_PWD = SERVER_HANDLING + "%Pwd";
    
    /** Communication.ExternalPort <br> Port für Kommunikation nach aussen */
    public static final String X_EXTERNAL_PORT = SERVER_COMMUNICATION + "%ExternalPort";
    /** ExternalPort <br> Port für Kommunikation nach aussen */
    public static final String EXTERNAL_PORT = "ExternalPort";
    /** Communication.ServicePort <br> Port für Administration */
    public static final String X_ADMIN_PORT = SERVER_COMMUNICATION + "%AdminPort";
    /** AdminPort <br> Port für Administration */
    public static final String ADMIN_PORT = "AdminPort";
    /** Communication.LocalPort <br> Port für Kommunikation mit lokalen Clients */
    public static final String X_LOCAL_PORT = SERVER_COMMUNICATION + "%LocalPort";
    /** LocalPort <br> Port für Kommunikation mit lokalen Clients */
    public static final String LOCAL_PORT = "LocalPort";
    
    /** ClientHandling.MaxClientThreads <br> Max. Anzahl externer Client-Threads insgesamt */
    public static final String X_MAX_CLIENT_THREADS = CLIENT_HANDLING + "%MaxClientThreads";
    /** MaxClientThreads <br> Max. Anzahl externer Client-Threads insgesamt */
    public static final String MAX_CLIENT_THREADS = "MaxClientThreads";
    /** ClientHandling.TimeoutClientMillis <br>
     * Adapter unterbricht nach Ablauf Kommunikation zum Client */
    public static final String X_TIMEOUT_CLIENT_MILLIS = CLIENT_HANDLING + "%TimeoutClientMillis";
    /** TimeoutClientMillis <br>
     * Adapter unterbricht nach Ablauf Kommunikation zum Client */
    public static final String TIMEOUT_CLIENT_MILLIS = "TimeoutClientMillis";
     
    /** TaskConfiguration.PathTaskConfiguration <br> Pfad der XML-Datei für Tasks */
    public static final String X_PATH_TASK_CONFIGURATION = TASK_CONFIGURATION + "%PathTaskConfiguration";      
            
    /** Security.PwdAdministration <br> Passwort für Service-Schnittstelle */
    public static final String X_PWD_ADMINISTRATION = SECURITY + "%PwdAdministration";

    /** Security.ServerValidation <br> Element ohne Wert */
    public static final String X_SERVER_VALIDATION = SECURITY + "%ServerValidation";
    /** Security.ServerValidation.ValidServer <br> Element ohne Wert */
    public static final String X_VALIDATION_VALID_SERVER = X_SERVER_VALIDATION + "%ValidServer";
    /** Security.ServerValidation.ValidServer.ServerId <br> Id eines berechtigten Rechners */
    public static final String X_VALIDATION_VALID_SERVER_ID = X_VALIDATION_VALID_SERVER + "%ServerId";
    /** ValidServer <br> Ohne Pfad aber mit Wert */
    public static final String VALID_SERVER = "ValidServer";
    /** ServerId  <br> Ohne Pfad aber mit Wert <br> ein validierter Rechner */
    public static final String VALID_SERVER_ID = "ServerId";
    
    /** ActiveConfiguration.IntervalGetProjectSec <br> Abstand, in dem Konfiguration gelesen wird. <br>
     * Future Use */
    public static final String X_INTERVAL_GET_PROJECT_SEC = ACTIVE_CONFIGURATION + "%IntervalGetProjectSec";
}
