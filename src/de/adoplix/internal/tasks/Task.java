package de.adoplix.internal.tasks;

import de.adoplix.internal.configuration.Configuration;
import de.adoplix.internal.configuration.TaskConfigurationConstants;
import de.adoplix.internal.runtimeInformation.AdopLog;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationKeyNotFoundException;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationTypeException;
import java.util.logging.Logger;

/**
 * Dient als Container für Informationen über eine Task.
 * 
 * @author dirkg
 */
public class Task {

    private Logger _logger = AdopLog.getLogger(Task.class);
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
    private long _timeOutAcknMillis = 0;
    private int _acknInitiator = 0;
    private String _pathAdapterConfig = "";
    private StringBuffer _defaultData = new StringBuffer("");

    public Task(Configuration conf, String taskId) {
        this.setTaskType(conf);
        this.setLocalTaskId(taskId);
        this.setTaskAlias(conf);
        this.setRemoteServerAddress(conf);
        this.setRemoteTaskId(conf);
        this.setLocalAdapterClass(conf);
        this.setAcknInitiator(conf);
        this.setTimeOutAcknMillis(conf);
        this.setPathAdapterConfig(conf);
        this.setDefaultData(conf);
        this.setLocalAdapterConnType(conf);
        this.setLocalAdapterIP(conf);
        this.setLocalAdapterPort(conf);
        this.setResponseTaskId(conf);
    }

    /**
     * @return Returns the _acknInitiator.
     */
    public int getAcknInitiator() {
        return _acknInitiator;
    }

    /**
     * @param initiator
     *            The _acknInitiator to set.
     */
    public void setAcknInitiator(int initiator) {
        _acknInitiator = initiator;
    }
    
    private void setAcknInitiator (Configuration conf) {
        try {
        _acknInitiator = conf.toInt(conf.getChild(TaskConfigurationConstants.ACKN_INITIATOR).getValue());
        } catch (ConfigurationKeyNotFoundException confEx) {
            _logger.info(confEx.getMessage());
        } catch (ConfigurationTypeException ctEx) {
            _logger.warning(ctEx.getMessage());
        }
    }

    /**
     * @return Returns the _defaultData.
     */
    public StringBuffer getDefaultData() {
        return _defaultData;
    }

    /**
     * @param data
     *            The _defaultData to set.
     */
    public void setDefaultData(StringBuffer data) {
        _defaultData = data;
    }
    
    private void setDefaultData(Configuration conf) {
        try {
            _defaultData = new StringBuffer(conf.getChild(TaskConfigurationConstants.DEFAULT_DATA).getValue());
        } catch (ConfigurationKeyNotFoundException confEx) {
            _logger.info(confEx.getMessage());
        }
    }

    /**
     * @return Returns the _localAdapterClass.
     */
    public String getLocalAdapterClass() {
        return _localAdapterClass;
    }

    /**
     * @param adapterClass
     *            The _localAdapterClass to set.
     */
    public void setLocalAdapterClass(String adapterClass) {
        _localAdapterClass = adapterClass;
    }

    private void setLocalAdapterClass(Configuration conf) {
        try {
            _localAdapterClass = conf.getChild(TaskConfigurationConstants.LOCAL_ADAPTER_CLASS)
                    .getValue();
        } catch (ConfigurationKeyNotFoundException confEx) {
            _logger.info(confEx.getMessage());
        }
    }

    /**
     * @return Returns the _localTaskId.
     */
    public String getLocalTaskId() {
        return _localTaskId;
    }

    /**
     * @param taskId
     *            The _localTaskId to set.
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
     * @param adapterConfig
     *            The _pathAdapterConfig to set.
     */
    public void setPathAdapterConfig(String adapterConfig) {
        _pathAdapterConfig = adapterConfig;
    }
    
    private void setPathAdapterConfig (Configuration conf) {
        try {
            _pathAdapterConfig = conf.getChild(TaskConfigurationConstants.PATH_ADAPTER_CONFIG).getValue();
        } catch (ConfigurationKeyNotFoundException confEx) {
            _logger.info(confEx.getMessage());
        }
    }

    /**
     * @return Returns the _remoteServerAddress.
     */
    public String getRemoteServerAddress() {
        return _remoteServerAddress;
    }

    /**
     * @param serverAddress
     *            The _remoteServerAddress to set.
     */
    public void setRemoteServerAddress(String serverAddress) {
        _remoteServerAddress = serverAddress;
    }

    private void setRemoteServerAddress(Configuration conf) {
        try {
            _remoteServerAddress = conf.getChild(
                    TaskConfigurationConstants.REMOTE_SERVER_ADDRESS)
                    .getValue();
        } catch (ConfigurationKeyNotFoundException confEx) {
            _logger.info(confEx.getMessage());
        }
    }

    /**
     * @return Returns the _remoteTaskId.
     */
    public String getRemoteTaskId() {
        return _remoteTaskId;
    }

    /**
     * @param taskId
     *            The _remoteTaskId to set.
     */
    public void setRemoteTaskId(String taskId) {
        _remoteTaskId = taskId;
    }

    private void setRemoteTaskId(Configuration conf) {
        try {
            _remoteTaskId = conf.getChild(TaskConfigurationConstants.REMOTE_TASK_ID).getValue();
        } catch (ConfigurationKeyNotFoundException confEx) {
            _logger.info(confEx.getMessage());
        }
    }

    /**
     * @return Returns the _taskName.
     */
    public String getTaskAlias() {
        return _taskAlias;
    }

    /**
     * @param alias
     *            The _taskName to set.
     */
    public void setTaskAlias(String alias) {
        _taskAlias = alias;
    }

    public void setTaskAlias(Configuration conf) {
        try {
            _taskAlias = conf.getChild(TaskConfigurationConstants.TASK_ALIAS)
                    .getValue();
        } catch (ConfigurationKeyNotFoundException confEx) {
            _logger.info(confEx.getMessage());
        }
    }

    /**
     * @return Returns the _taskType.
     */
    public int getTaskType() {
        return _taskType;
    }

    /**
     * @param type
     *            The _taskType to set.
     */
    public void setTaskType(int type) {
        _taskType = type;
    }

    private void setTaskType(Configuration conf) {
        try {
            _taskType = conf.toInt(conf.getChild(
                    TaskConfigurationConstants.TASK_TYPE).getValue());
        } catch (ConfigurationKeyNotFoundException confEx) {
            _logger.info(confEx.getMessage());
        } catch (ConfigurationTypeException ctEx) {
            _logger.warning(ctEx.getMessage());
        }
    }

    /**
     * @return Returns the _timeOutAcknMillis.
     */
    public long getTimeOutAcknMillis() {
        return _timeOutAcknMillis;
    }

    /**
     * @param outAcknMillis
     *            The _timeOutAcknMillis to set.
     */
    public void setTimeOutAcknMillis(long outAcknMillis) {
        _timeOutAcknMillis = outAcknMillis;
    }
    
    protected void setTimeOutAcknMillis (Configuration conf) {
        try {
        _timeOutAcknMillis = conf.toLong(conf.getChild(TaskConfigurationConstants.TIME_OUT_ACKN_MILLIS).getValue());
        } catch (ConfigurationKeyNotFoundException confEx) {
            _logger.info(confEx.getMessage());
        } catch (ConfigurationTypeException ctEx) {
            _logger.warning(ctEx.getMessage());
        }
    }

    public String getLocalAdapterIP() {
        return _localAdapterIP;
    }

    public void setLocalAdapterIP(String _localAdapterIP) {
        this._localAdapterIP = _localAdapterIP;
    }
    
    private void setLocalAdapterIP(Configuration conf) {
        try {
            _localAdapterIP = conf.getChild(TaskConfigurationConstants.LOCAL_ADAPTER_IP).getValue();
        } catch (ConfigurationKeyNotFoundException confEx) {
            _logger.info(confEx.getMessage());
        }
    }

    public int getLocalAdapterPort() {
        return _localAdapterPort;
    }

    public void setLocalAdapterPort(int _localAdapterPort) {
        this._localAdapterPort = _localAdapterPort;
    }
    
    private void setLocalAdapterPort(Configuration conf) {
        try {
            _localAdapterPort = conf.toInt(conf.getChild(TaskConfigurationConstants.LOCAL_ADAPTER_PORT).getValue());
        } catch (ConfigurationKeyNotFoundException confEx) {
            _logger.info(confEx.getMessage());
        } catch (ConfigurationTypeException ctEx) {
            _logger.warning(ctEx.getMessage());
        }
    }

    public int getLocalAdapterConnType() {
        return _localAdapterConnType;
    }

    public void setLocalAdapterConnType(int _localAdapterConnType) {
        this._localAdapterConnType = _localAdapterConnType;
    }
    
    private void setLocalAdapterConnType(Configuration conf) {
        try {
            _localAdapterConnType = conf.toInt(conf.getChild(TaskConfigurationConstants.LOCAL_ADAPTER_CONN_TYPE).getValue());
        } catch (ConfigurationKeyNotFoundException confEx) {
            _logger.info(confEx.getMessage());
        } catch (ConfigurationTypeException ctEx) {
            _logger.warning(ctEx.getMessage());
        }
    }

    public String getResponseTaskId() {
        return _responseTaskId;
    }

    public void setResponseTaskId(String _responseTaskId) {
        this._responseTaskId = _responseTaskId;
    }

    private void setResponseTaskId(Configuration conf) {
        try {
            _responseTaskId = conf.getChild(TaskConfigurationConstants.RESPONSE_TASK_ID).getValue();
        } catch (ConfigurationKeyNotFoundException confEx) {
            _logger.info(confEx.getMessage());
        }
    }
}
