package de.adoplix.internal.tasks;
import java.util.ArrayList;
import java.util.Map;

import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationKeyNotFoundException;
import de.adoplix.internal.tools.Configuration;
import de.adoplix.internal.tools.XMLObject;
import de.adoplix.internal.tools.XMLObjectList;

/**
 * Veranlasst das Auslesen der Serverkonfiguration und stellt diese
 * zur Laufzeit zur Verfuegung.
 * @author dirkg
 */
public class TaskConfiguration {

    private Map    _taskList = null;
    private String _taskAlias = "";
    private int _taskType = 0;
    private String _remoteServerAddress = "";
    private String _remoteTaskId = "";
    private String _localAdapterClass = "";
    private int _acknInitiator = 0;
    private int _timeOutAcknMillis = 0;
    private String _pathAdapterConfig = "";
    private String _defaultData = "";
    
    
    
    /** Creates a new instance of TaskConfiguration */
    public TaskConfiguration (String configurationFile) {
        ArrayList taskArray = new ArrayList();

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
            
            // Jetzt f�r jede Task die Details
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
                
                _taskList.put(task, taskId);
            }
        }
        catch (ConfigurationKeyNotFoundException confEx){
        	System.out.println(confEx.getMessage());
        }
//        catch (ConfigurationTypeException typeEx) {
//        	System.out.println(typeEx.getMessage());
//        }
        catch (Exception ex) {
            System.out.println("ERROR " + "---: " + ex.getMessage ());
        }
    }



    /**
     * @return Returns the _acknInitiator.
     */
    public int getAcknInitiator() {
        return _acknInitiator;
    }

    /**
     * @param initiator The _acknInitiator to set.
     */
    public void setAcknInitiator(int initiator) {
        _acknInitiator = initiator;
    }

    /**
     * @return Returns the _defaultData.
     */
    public String getDefaultData() {
        return _defaultData;
    }

    /**
     * @param data The _defaultData to set.
     */
    public void setDefaultData(String data) {
        _defaultData = data;
    }

    /**
     * @return Returns the _localAdapterClass.
     */
    public String getLocalAdapterClass() {
        return _localAdapterClass;
    }

    /**
     * @param adapterClass The _localAdapterClass to set.
     */
    public void setLocalAdapterClass(String adapterClass) {
        _localAdapterClass = adapterClass;
    }

    /**
     * @return Returns the _pathAdapterConfig.
     */
    public String getPathAdapterConfig() {
        return _pathAdapterConfig;
    }

    /**
     * @param adapterConfig The _pathAdapterConfig to set.
     */
    public void setPathAdapterConfig(String adapterConfig) {
        _pathAdapterConfig = adapterConfig;
    }

    /**
     * @return Returns the _remoteServerAddress.
     */
    public String getRemoteServerAddress() {
        return _remoteServerAddress;
    }

    /**
     * @param serverAddress The _remoteServerAddress to set.
     */
    public void setRemoteServerAddress(String serverAddress) {
        _remoteServerAddress = serverAddress;
    }

    /**
     * @return Returns the _remoteTaskId.
     */
    public String getRemoteTaskId() {
        return _remoteTaskId;
    }

    /**
     * @param taskId The _remoteTaskId to set.
     */
    public void setRemoteTaskId(String taskId) {
        _remoteTaskId = taskId;
    }

    /**
     * @return Returns the _taskAlias.
     */
    public String getTaskAlias() {
        return _taskAlias;
    }

    /**
     * @param alias The _taskAlias to set.
     */
    public void setTaskAlias(String alias) {
        _taskAlias = alias;
    }

    /**
     * @return Returns the _taskList.
     */
    public Map getTaskList() {
        return _taskList;
    }

    /**
     * @return Returns the _taskType.
     */
    public int getTaskType() {
        return _taskType;
    }

    /**
     * @param type The _taskType to set.
     */
    public void setTaskType(int type) {
        _taskType = type;
    }

    /**
     * @return Returns the _timeOutAcknMillis.
     */
    public int getTimeOutAcknMillis() {
        return _timeOutAcknMillis;
    }

    /**
     * @param outAcknMillis The _timeOutAcknMillis to set.
     */
    public void setTimeOutAcknMillis(int outAcknMillis) {
        _timeOutAcknMillis = outAcknMillis;
    }
}
