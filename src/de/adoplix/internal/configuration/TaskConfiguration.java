package de.adoplix.internal.configuration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.adoplix.internal.runtimeInformation.exceptions.*;
import de.adoplix.internal.runtimeInformation.AdopLog;
import de.adoplix.internal.configuration.Configuration;
import de.adoplix.internal.tools.xml.XMLObject;
import de.adoplix.internal.tools.xml.XMLObjectList;
import java.util.logging.Logger;
import de.adoplix.internal.tasks.*;

/**
 * Reads and holds the server configuration. <p>
 * The tasks are stored in two different hash maps. One for the Client-Tasks,
 * the other one for the Service-Tasks.
 * @author dirkg
 */
public class TaskConfiguration {

    /** HashMap which contains the Client-Tasks, key is ID */
    private Map    _mapTasksClientObjectsById = new HashMap();
    // HashMap which contains the Client-TasksId's, key is Alias */
    private Map    _mapTasksClientIdsByAlias = new HashMap();
    // HashMap which contains the Service-Tasks, key is ID */
    private Map    _mapTasksServiceObjectsById = new HashMap();
    // HashMap which contains the Service-TaskId's, key is Alias */
    private Map    _mapTasksServiceIdsByAlias = new HashMap();
    
    private static Logger logger = AdopLog.getLogger (TaskConfiguration.class);
    
    /** Creates a new instance of TaskConfiguration */
    public TaskConfiguration (String configurationFile) {
        /** List which contains Client-Id's */
        ArrayList _listClientIds = new ArrayList();
        /** List which contains Service-Id's */
        ArrayList _listServiceIds = new ArrayList();

        try {
            Configuration conf = new Configuration(configurationFile);
            
            // TaskListe
            // Typ Service
            conf.setXMLObjectByKey(TaskConfigurationConstants.X_TASK_TYPES_SERVICE);
            XMLObjectList xmlObjectList = conf.getChildren ();
            for (int i = 0; i < xmlObjectList.size (); i++) {
                _listServiceIds.add(xmlObjectList.get(i));
            }
            
            // Typ Client
            conf.setXMLParentObject();
            conf.setXMLObjectByKey(TaskConfigurationConstants.TASK_TYPES_CLIENT);
            xmlObjectList = conf.getChildren ();
            for (int i = 0; i < xmlObjectList.size (); i++) {
                _listClientIds.add(xmlObjectList.get(i));
            }
            
            conf.setXMLRootObject();
            conf.setXMLObjectByKey(TaskConfigurationConstants.TASK_DETAILS);
            // fill hashmaps with client-tasks
            fillHashMaps (conf, _listClientIds, _mapTasksClientObjectsById, _mapTasksClientIdsByAlias);
            // fill hashmap with service-tasks
            fillHashMaps (conf, _listServiceIds, _mapTasksServiceObjectsById, _mapTasksServiceIdsByAlias);
        }
        catch (ConfigurationKeyNotFoundException confEx){
        	logger.severe(confEx.getMessage ());
        	System.out.println(confEx.getMessage());
        }
        catch (Exception ex) {
            logger.severe(ex.getMessage ());
            System.out.println("ERROR " + ": " + ex.getLocalizedMessage() + ";" +  ex.getMessage ());
        }
    }
    
    /*
     * Filling up the hashmaps for storing tasks grouped by client/service and
     * distributed to id-key-map and alias-key-map
     */
    private void fillHashMaps (Configuration conf, ArrayList idList, Map objects, Map ids) {
        try {
            for (int i = 0; i < idList.size(); i++) {
                conf.setXMLParentObject();
                String taskId = ((XMLObject)idList.get(i)).getValue();
                conf.setXMLObjectByKey(taskId);
                Task task = new Task(conf, taskId);

                // append to hashmap for finding id's by alias
                ids.put(task.getTaskAlias (), taskId);
                // append to hashmap for finding task-objects by id's
                objects.put(taskId, task);
            }
        }
        catch (ConfigurationKeyNotFoundException confEx){
        	logger.warning(confEx.getMessage ());
        	System.out.println(confEx.getMessage());
        }
        catch (Exception ex) {
            logger.warning(ex.getMessage ());
            System.out.println("ERROR " + ": " + ex.getMessage ());
        }
    }
    
    public Task getTask(String idOrAlias)throws TaskNotFoundException {
        try {
            return getServiceTaskById (idOrAlias);
        }
        catch (TaskNotFoundException tnfEx) {}
        
        try {
            return getClientTaskById (idOrAlias);
        }
        catch (TaskNotFoundException tnfEx) {}
            
        try {
            return getServiceTaskByAlias (idOrAlias);
        }
        catch (TaskNotFoundException tnfEx) {}
        
        try {
            return getClientTaskByAlias (idOrAlias);
        }
        catch (TaskNotFoundException tnfEx) {}
        
        throw new TaskNotFoundException();
    }
    
    /**
     * Returns a Service Task-Object by its ID. <p>
     * @param id Is unique within the service-task-list
     */
    public Task getServiceTaskById (String id) throws TaskNotFoundException {
        try {
            Task aTask = (Task)_mapTasksServiceObjectsById.get (id);
            if (null == aTask) {
                throw new TaskNotFoundException();
            }
            return aTask;
        }
        catch (Exception ex) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Returns a Service Task-Object by its Alias. <p>
     * @param alias Is unique within the service-task-list
     */
    public Task getServiceTaskByAlias (String alias) throws TaskNotFoundException {
        try {
            String id = (String)_mapTasksServiceIdsByAlias.get(alias);
            if (null != id) {
                return getServiceTaskById (id);
            }
            else {
                throw new TaskNotFoundException();
            }
        }
        catch (Exception ex) {
            throw new TaskNotFoundException();
        }
    }
    /**
     * Returns a Client Task-Object by its ID. <p>
     * @param id Is unique within the client-task-list
     */
    public Task getClientTaskById (String id) throws TaskNotFoundException {
        try {
            Task aTask = (Task)_mapTasksClientObjectsById.get (id);
            if (null == aTask) {
                throw new TaskNotFoundException();
            }
            return aTask;
        }
        catch (Exception ex) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Returns a Client Task-Object by its Alias. <p>
     * @param alias Is unique within the client-task-list
     */
    public Task getClientTaskByAlias (String alias) throws TaskNotFoundException {
        try {
            String id = (String)_mapTasksClientIdsByAlias.get(alias);
            if (null != id) {
                return getClientTaskById (id);
            }
            else {
                throw new TaskNotFoundException();
            }
        }
        catch (Exception ex) {
            throw new TaskNotFoundException();
        }
    }
}
