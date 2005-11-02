package de.adoplix.internal.tasks;

/**
 * Dient als Container für Informationen über eine Task.
 * @author dirkg
 */
public class Task {
    
    private String _localTaskId = "";
    private int _taskType = 0;
    private String _taskAlias = "";
    private String _remoteServerAddress = "";
    private String _remoteTaskId = "";
    private String _localAdapterClass = "";
    private int _timeOutAcknMillis = 0;
    private int _acknInitiator = 0;
    private String _pathAdapterConfig = "";
    private StringBuffer _defaultData = new StringBuffer("");

    public Task(){
        
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
    public StringBuffer getDefaultData() {
        return _defaultData;
    }

    /**
     * @param data The _defaultData to set.
     */
    public void setDefaultData(StringBuffer data) {
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
     * @return Returns the _localTaskId.
     */
    public String getLocalTaskId() {
        return _localTaskId;
    }

    /**
     * @param taskId The _localTaskId to set.
     */
    public void setLocalTaskId(String taskId) {
        _localTaskId = taskId;
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
     * @return Returns the _taskName.
     */
    public String getTaskAlias() {
        return _taskAlias;
    }

    /**
     * @param name The _taskName to set.
     */
    public void setTaskAlias(String alias) {
        _taskAlias = alias;
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
