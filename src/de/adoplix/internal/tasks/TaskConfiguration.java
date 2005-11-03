package de.adoplix.internal.tasks;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.adoplix.internal.runtimeInformation.exceptions.*;
import de.adoplix.internal.tools.Configuration;
import de.adoplix.internal.tools.XMLObject;
import de.adoplix.internal.tools.XMLObjectList;

/**
 * Veranlasst das Auslesen der Serverkonfiguration und stellt diese
 * zur Laufzeit zur Verfuegung.
 * @author dirkg
 */
public class TaskConfiguration {

    private Map    _taskListByAlias = null;
    private Map    _taskListById = null;
    
    
    
    /** Creates a new instance of TaskConfiguration */
    public TaskConfiguration (String configurationFile) {
        ArrayList taskArray = new ArrayList();
        _taskListByAlias = new HashMap();
        _taskListById = new HashMap();

        try {
            Configuration conf = new Configuration(configurationFile);
            
            // TaskListe
            // Typ Service
            conf.setXMLObjectByKey(TaskConfigurationConstants.X_TASK_TYPES_SERVICE);
            XMLObjectList xmlObjectList = conf.getChildren ();
            for (int i = 0; i < xmlObjectList.size (); i++) {
                taskArray.add(xmlObjectList.get(i));
            }
            
            // Typ Client
            conf.setXMLParentObject();
            conf.setXMLObjectByKey(TaskConfigurationConstants.TASK_TYPES_CLIENT);
            xmlObjectList = conf.getChildren ();
            for (int i = 0; i < xmlObjectList.size (); i++) {
                taskArray.add(xmlObjectList.get(i));
            }
            
            // Jetzt für jede Task die Details
            conf.setXMLRootObject();
            conf.setXMLObjectByKey(TaskConfigurationConstants.TASK_DETAILS);
            for (int i = 0; i < taskArray.size(); i++) {
                Task task = new Task();
                conf.setXMLParentObject();
                String taskId = ((XMLObject)taskArray.get(i)).getValue();
                conf.setXMLObjectByKey(taskId);
                task.setLocalTaskId(taskId);
                task.setTaskAlias(conf.getChild(TaskConfigurationConstants.TASK_ALIAS).getValue());
                task.setTaskType(conf.toInt(conf.getChild(TaskConfigurationConstants.TASK_TYPE).getValue()));
                task.setRemoteServerAddress(conf.getChild(TaskConfigurationConstants.REMOTE_SERVER_ADDRESS).getValue());
                task.setRemoteTaskId(conf.getChild(TaskConfigurationConstants.REMOTE_TASK_ID).getValue());
                task.setLocalAdapterClass(conf.getChild(TaskConfigurationConstants.LOCAL_ADAPTER_CLASS).getValue());
                task.setAcknInitiator(conf.toInt(conf.getChild(TaskConfigurationConstants.ACKN_INITIATOR).getValue()));
                task.setTimeOutAcknMillis(conf.toInt(conf.getChild(TaskConfigurationConstants.TIME_OUT_ACKN_MILLIS).getValue()));
                task.setPathAdapterConfig(conf.getChild(TaskConfigurationConstants.PATH_ADAPTER_CONFIG).getValue());
                task.setDefaultData(new StringBuffer(conf.getChild(TaskConfigurationConstants.DEFAULT_DATA).getValue()));
                
                // Zwei Hashmaps, um sowohl über den Index, als auch über den Alias die Tasks schnell zu finden
                _taskListById.put(taskId, task);
                _taskListByAlias.put(task.getTaskAlias (), task);
            }
        }
        catch (ConfigurationKeyNotFoundException confEx){
        	System.out.println(confEx.getMessage());
        }
//        catch (ConfigurationTypeException typeEx) {
//        	System.out.println(typeEx.getMessage());
//        }
        catch (Exception ex) {
            System.out.println("ERROR " + ": " + ex.getMessage ());
        }
    }
    
    public Task getTaskById (String key) throws TaskNotFoundException {
        try {
            Task aTask = (Task)_taskListById.get (key);
            if (null == aTask) {
                throw new TaskNotFoundException();
            }
            return aTask;
        }
        catch (Exception ex) {
            throw new TaskNotFoundException();
        }
    }

    public Task getTaskByAlias (String key) throws TaskNotFoundException {
        try {
            Task aTask = (Task)_taskListByAlias.get (key);
            if (null == aTask) {
                throw new TaskNotFoundException();
            }
            return aTask;
        }
        catch (Exception ex) {
            throw new TaskNotFoundException();
        }
    }
}
