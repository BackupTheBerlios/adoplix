package de.adoplix.internal.tasks;

import de.adoplix.internal.configuration.Configuration;
import de.adoplix.internal.configuration.TaskConfigurationConstants;
import de.adoplix.internal.runtimeInformation.AdopLog;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationKeyNotFoundException;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationTypeException;
import java.util.logging.Logger;

/**
 * Dient als Container für Informationen über eine Task.
 * @author dirkg
 */
public class Task {
    
    private Logger _logger = AdopLog.getLogger (Task.class);
    private String _localTaskId = "";
    private int _taskType = 0;
    private String _taskAlias = "";
    private String _remoteServerAddress = "";
    private String _remoteTaskId = "";
    private String _responseTaskId = "";
    private String _localAdapterIP = "";
    private int _localAdapterPort = 0;
    private String _localAdapterClass = "";
    private int _localAdapterConnType = 0;
    private int _timeOutAcknMillis = 0;
    private int _acknInitiator = 0;
    private String _pathAdapterConfig = "";
    private StringBuffer _defaultData = new StringBuffer ("");
    
    public Task (Configuration conf, String taskId){
        try {
            this.setLocalTaskId (taskId);
            this.setTaskAlias (conf.getChild (TaskConfigurationConstants.TASK_ALIAS).getValue ());
            this.setTaskType (conf.toInt (conf.getChild (TaskConfigurationConstants.TASK_TYPE).getValue ()));
            this.setRemoteServerAddress (conf.getChild (TaskConfigurationConstants.REMOTE_SERVER_ADDRESS).getValue ());
            this.setRemoteTaskId (conf.getChild (TaskConfigurationConstants.REMOTE_TASK_ID).getValue ());
            this.setLocalAdapterClass (conf.getChild (TaskConfigurationConstants.LOCAL_ADAPTER_CLASS).getValue ());
            this.setAcknInitiator (conf.toInt (conf.getChild (TaskConfigurationConstants.ACKN_INITIATOR).getValue ()));
            this.setTimeOutAcknMillis (conf.toInt (conf.getChild (TaskConfigurationConstants.TIME_OUT_ACKN_MILLIS).getValue ()));
            this.setPathAdapterConfig (conf.getChild (TaskConfigurationConstants.PATH_ADAPTER_CONFIG).getValue ());
            this.setDefaultData (new StringBuffer (conf.getChild (TaskConfigurationConstants.DEFAULT_DATA).getValue ()));
            this.setLocalAdapterConnType (conf.toInt (conf.getChild (TaskConfigurationConstants.LOCAL_ADAPTER_CONN_TYPE).getValue ()));
            this.setLocalAdapterIP (conf.getChild (TaskConfigurationConstants.LOCAL_ADAPTER_IP).getValue ());
            this.setLocalAdapterPort (conf.toInt(conf.getChild (TaskConfigurationConstants.LOCAL_ADAPTER_PORT).getValue ()));
            this.setResponseTaskId (conf.getChild (TaskConfigurationConstants.RESPONSE_TASK_ID).getValue ());
            
        } catch (ConfigurationKeyNotFoundException confEx){
            _logger.warning (confEx.getMessage ());
        } catch (ConfigurationTypeException ctEx) {
            _logger.warning (ctEx.getMessage ());
        }
    }
    
    /**
     * @return Returns the _acknInitiator.
     */
    public int getAcknInitiator () {
        return _acknInitiator;
    }
    
    /**
     * @param initiator The _acknInitiator to set.
     */
    public void setAcknInitiator (int initiator) {
        _acknInitiator = initiator;
    }
    
    /**
     * @return Returns the _defaultData.
     */
    public StringBuffer getDefaultData () {
        return _defaultData;
    }
    
    /**
     * @param data The _defaultData to set.
     */
    public void setDefaultData (StringBuffer data) {
        _defaultData = data;
    }
    
    /**
     * @return Returns the _localAdapterClass.
     */
    public String getLocalAdapterClass () {
        return _localAdapterClass;
    }
    
    /**
     * @param adapterClass The _localAdapterClass to set.
     */
    public void setLocalAdapterClass (String adapterClass) {
        _localAdapterClass = adapterClass;
    }
    
    /**
     * @return Returns the _localTaskId.
     */
    public String getLocalTaskId () {
        return _localTaskId;
    }
    
    /**
     * @param taskId The _localTaskId to set.
     */
    public void setLocalTaskId (String taskId) {
        _localTaskId = taskId;
    }
    
    /**
     * @return Returns the _pathAdapterConfig.
     */
    public String getPathAdapterConfig () {
        return _pathAdapterConfig;
    }
    
    /**
     * @param adapterConfig The _pathAdapterConfig to set.
     */
    public void setPathAdapterConfig (String adapterConfig) {
        _pathAdapterConfig = adapterConfig;
    }
    
    /**
     * @return Returns the _remoteServerAddress.
     */
    public String getRemoteServerAddress () {
        return _remoteServerAddress;
    }
    
    /**
     * @param serverAddress The _remoteServerAddress to set.
     */
    public void setRemoteServerAddress (String serverAddress) {
        _remoteServerAddress = serverAddress;
    }
    
    /**
     * @return Returns the _remoteTaskId.
     */
    public String getRemoteTaskId () {
        return _remoteTaskId;
    }
    
    /**
     * @param taskId The _remoteTaskId to set.
     */
    public void setRemoteTaskId (String taskId) {
        _remoteTaskId = taskId;
    }
    
    /**
     * @return Returns the _taskName.
     */
    public String getTaskAlias () {
        return _taskAlias;
    }
    
    /**
     * @param alias The _taskName to set.
     */
    public void setTaskAlias (String alias) {
        _taskAlias = alias;
    }
    
    /**
     * @return Returns the _taskType.
     */
    public int getTaskType () {
        return _taskType;
    }
    
    /**
     * @param type The _taskType to set.
     */
    public void setTaskType (int type) {
        _taskType = type;
    }
    
    /**
     * @return Returns the _timeOutAcknMillis.
     */
    public int getTimeOutAcknMillis () {
        return _timeOutAcknMillis;
    }
    
    /**
     * @param outAcknMillis The _timeOutAcknMillis to set.
     */
    public void setTimeOutAcknMillis (int outAcknMillis) {
        _timeOutAcknMillis = outAcknMillis;
    }
    
    public String getLocalAdapterIP () {
        return _localAdapterIP;
    }
    
    public void setLocalAdapterIP (String _localAdapterIP) {
        this._localAdapterIP = _localAdapterIP;
    }
    
    public int getLocalAdapterPort () {
        return _localAdapterPort;
    }
    
    public void setLocalAdapterPort (int _localAdapterPort) {
        this._localAdapterPort = _localAdapterPort;
    }
    
    public int getLocalAdapterConnType () {
        return _localAdapterConnType;
    }
    
    public void setLocalAdapterConnType (int _localAdapterConnType) {
        this._localAdapterConnType = _localAdapterConnType;
    }

    public String getResponseTaskId () {
        return _responseTaskId;
    }

    public void setResponseTaskId (String _responseTaskId) {
        this._responseTaskId = _responseTaskId;
    }
    
}
