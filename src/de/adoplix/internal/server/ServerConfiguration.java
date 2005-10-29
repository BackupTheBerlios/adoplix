package de.adoplix.internal.server;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationKeyNotFoundException;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationTypeException;
import de.adoplix.internal.tools.*;
import java.util.ArrayList;

/**
 * Veranlasst das Auslesen der Serverkonfiguration und stellt diese
 * zur Laufzeit zur Verfuegung.
 * @author dirkg
 */
public class ServerConfiguration {

    private String _serverId = null;
    private String _serverPwd = null;
    private String _pathTaskConfiguration = null;
    private String _securityPwdAdmin = null;
    private int _portExternal = 0;
    private int _portService = 0;
    private int _portLocal = 0;
    private int _maxClientThreads = 0;
    private int _timeoutClientMillis = 0;
    private int _intervalGetProjectSec = 0;
    private ArrayList _validServerIds = new ArrayList();
    
    
    /** Creates a new instance of ServerConfiguration */
    public ServerConfiguration (String configurationFile) {

        try {
            Configuration conf = new Configuration(configurationFile);
            
            // ServerId
            conf.setXMLObjectByKey (ServerConfigurationConstants.X_SERVER_ID);
            _serverId = conf.getElementValue ();
            
            // ServerPwd
            conf.setXMLParentObject ();
            conf.setXMLObjectByKey (ServerConfigurationConstants.X_SERVER_PWD);
            _serverPwd = conf.getElementValue ();
            
            // Ports
            conf.setXMLRootObject ();
            conf.setXMLObjectByKey(ServerConfigurationConstants.SERVER_COMMUNICATION);
            _portExternal = conf.toInt(conf.getChild (ServerConfigurationConstants.EXTERNAL_PORT).getValue());
            _portService = conf.toInt(conf.getChild (ServerConfigurationConstants.SERVICE_PORT).getValue());
            _portLocal = conf.toInt(conf.getChild (ServerConfigurationConstants.LOCAL_PORT).getValue());
            
            // MaxClientThreads
            conf.setXMLRootObject ();
            conf.setXMLObjectByKey (ServerConfigurationConstants.CLIENT_HANDLING);
            _maxClientThreads = conf.toInt (conf.getChild (ServerConfigurationConstants.MAX_CLIENT_THREADS).getValue());
            // TimeoutClientMillis
            _timeoutClientMillis = conf.toInt (conf.getChild (ServerConfigurationConstants.TIMEOUT_CLIENT_MILLIS).getValue());
            
            // PathTaskConfiguration
            conf.setXMLRootObject ();
            _pathTaskConfiguration = conf.setXMLObjectByKey (ServerConfigurationConstants.X_PATH_TASK_CONFIGURATION).getValue();
            
            // Active Configuration
            conf.setXMLRootObject ();
            _intervalGetProjectSec = conf.toInt (conf.setXMLObjectByKey (ServerConfigurationConstants.X_INTERVAL_GET_PROJECT_SEC).getValue());
            
            // ValidServers
            conf.setXMLRootObject ();
            conf.setXMLObjectByKey (ServerConfigurationConstants.X_SERVER_VALIDATION);
            XMLObjectList xmlObjectList = conf.getChildren (ServerConfigurationConstants.VALID_SERVER);
            for (int i = 0; i < xmlObjectList.size (); i++) {
                XMLRetriever retriever = new XMLRetriever(xmlObjectList.get(i));
                retriever.setXMLObjectByKey(ServerConfigurationConstants.VALID_SERVER_ID);
                _validServerIds.add(retriever.getElementValue ());
            }
        }
        catch (ConfigurationKeyNotFoundException confEx){
        	System.out.println(confEx.getMessage());
        }
        catch (ConfigurationTypeException typeEx) {
        	System.out.println(typeEx.getMessage());
        }
        catch (Exception ex) {
            System.out.println("ERROR " + "---: " + ex.getMessage ());
        }
    }
    
    public String getServerId() {
        return _serverId;
    }
    
    public String getPwd() {
        return _serverPwd;
    }

    public String getPathTaskConfiguration() {
        return _pathTaskConfiguration;
    }
    public String getSecurityPwdAdmin() {
        return _securityPwdAdmin;
    }
    public int getPortExternal() {
        return _portExternal;
    }
    public int getPortService() {
        return _portService;
    }
    public int getPortLocal() {
        return _portLocal;
    }
    public int getMaxClientThreads() {
        return _maxClientThreads;
    }
    public int getTimeoutClientMillis() {
        return _timeoutClientMillis;
    }
    public int getIntervalGetProjectSec() {
        return _intervalGetProjectSec;
    }
    
    public int countValidServerIds() {
        return _validServerIds.size ();
    }
    
    public String getValidServerId(int index) {
        return (String)_validServerIds.get(index);
    }
}
