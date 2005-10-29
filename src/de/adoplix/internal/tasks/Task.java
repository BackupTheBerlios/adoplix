package de.adoplix.internal.tasks;

/**
 * Dient als Container für Informationen über eine Task.
 * @author dirkg
 */
public class Task {
    
    private String _localTaskId = "";
    private String _taskType = "";
    private String _taskName = "";
    private String _remoteServerAddress = "";
    private String _remoteTaskId = "";
    private String _localAdapterClass = "";
    private int _timeOutAcknMillis = 0;
    private int _acknInitiator = 0;
    private String _pathAdapterConfig = "";
    private StringBuffer _defaultData = new StringBuffer("");

    public Task(){
        
    }

    private int getAcknInitiator() {
        return _acknInitiator;
    }

    private StringBuffer getDefaultData() {
        return _defaultData;
    }

    private String getlocalAdapterClass() {
        return _localAdapterClass;
    }

    private String getLocalTaskId() {
        return _localTaskId;
    }

    private String getPathAdapterConfig() {
        return _pathAdapterConfig;
    }

    private String getRemoteServerAddress() {
        return _remoteServerAddress;
    }

    private String getRemoteTaskId() {
        return _remoteTaskId;
    }

    private String getTaskName() {
        return _taskName;
    }

    private String getTaskType() {
        return _taskType;
    }

    private int getTimeOutAcknMillis() {
        return _timeOutAcknMillis;
    }

}
