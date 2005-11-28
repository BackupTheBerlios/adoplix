/*
 * TaskConfigurationConstants.java
 *
 * Created on 23. Oktober 2005, 22:34
 */

package de.adoplix.internal.configuration;

/**
 * Constants around the task configuration.
 * @author  dirk
 */
public abstract class TaskConfigurationConstants {

    /** TaskList <br> Liste aller Tasks ohne Wert */
    public static final String TASK_LIST = "TaskList";
    /** TaskDetails <br> Die Details der Tasks */
    public final static String TASK_DETAILS = "TaskDetails";

    /** TaskTypesService <br> Liste der Tasks, die der Server liest */
    public static final String TASK_TYPES_SERVICE = "TaskTypesService";
    /** TaskList.TaskTypesService <br> Liste der Tasks, die der Server liest */
    public static final String X_TASK_TYPES_SERVICE = TASK_LIST + "%" +TASK_TYPES_SERVICE; 
    /** TaskTypesClient <br> Liste der Tasks, die Client liest */
    public static final String TASK_TYPES_CLIENT = "TaskTypesClient";
    /** TaskList.TaskTypesClient <br> Liste der Tasks, die der Client liest */
    public static final String X_TASK_TYPES_CLIENT = TASK_LIST + "%" +TASK_TYPES_CLIENT; 
    
    /** LocalTaskId <br> ID einer lokalen Task */
    public final static String LOCAL_TASK_ID = "LocalTaskId";
    /** TaskName <br> Alias der Task */
    public final static String TASK_ALIAS = "TaskAlias";
    /** TaskType <br> Client oder Server (Service) */
    public final static String TASK_TYPE = "TaskType";
    /** RemoteServerAddress <br> IP-Adresse des Servers */
    public final static String REMOTE_SERVER_ADDRESS = "RemoteServerAddress";
    public final static String REMOTE_SERVER_IP = "RemoteServerIP";
    public final static String REMOTE_SERVER_PORT = "RemoteServerPort";
    /** RemoteTaskId <br> Die TaskID, die auf der Gegenseite die Arbeit macht <br>
      * Kann auch die gleiche sein ;-)
      */
    public final static String REMOTE_TASK_ID = "RemoteTaskId";
    /** LocalAdapterClass <br> Vollständiger Pfad der Adapter-Klasse (de.bla.Klasse) */
    public final static String LOCAL_ADAPTER_CLASS = "LocalAdapterClass";
    /** AcknInitiator <br> Sagt aus, ob Quittung von Server oder Adapter erwartet wird */
    public final static String ACKN_INITIATOR = "AcknInitiator";
    /** TimeOutAcknMillis <br> Max. Wartezeit auf Response */
    public final static String TIME_OUT_ACKN_MILLIS = "TimeOutAcknMillis";
    /** PathAdapterConfig <br> Pfad einer mögl. Konfigurationsdatei des Adapters */
    public final static String PATH_ADAPTER_CONFIG = "PathAdapterConfig";
    /** DefaultData <br> Optionale Standardwerte des Adapters */
    public final static String DEFAULT_DATA = "DefaultData";
    
    /** ResponseTaskId <br> The TaskId that identifies the Adapter which takes
      * the response and forwards it to the initial client */
    public final static String RESPONSE_TASK_ID = "ResponseTaskId";
    /** LocalAdapterConnType <br> Is local Adapter reachable by Port or as
      * a Class which must be instantiated as new object */
    public final static String LOCAL_ADAPTER_CONN_TYPE = "LocalAdapterConnType";
    /** LocalAdapterIP <br> IP-Adress of Local Adapter which waits for a job */
    public final static String LOCAL_ADAPTER_IP = "LocalAdapterIP";
    /** LocalAdapterPort <br> Port of Local Adapter which waits for a job */
    public final static String LOCAL_ADAPTER_PORT = "LocalAdapterPort";
}
