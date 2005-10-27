package de.adoplix.internal.server;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationKeyNotFoundException;
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
    private int _portRequest = 0;
    private int _portService = 0;
    private int _portClient = 0;
    private int _maxClientThreads = 0;
    private int _timeoutClientMillis = 0;
    private ArrayList _validServerIds = new ArrayList();
    
    
    /** Creates a new instance of ServerConfiguration */
    public ServerConfiguration (String configurationFile) {

        try {
            Configuration conf = new Configuration(configurationFile);
            
            // ServerId
            conf.setXMLObjectByKey (ServerConfigurationConstants.SERVER_ID);
            _serverId = conf.getElementValue ();
            
            // ServerPwd
            conf.setXMLParentObject ();
            conf.setXMLObjectByKey (ServerConfigurationConstants.SERVER_PWD);
            _serverPwd = conf.getElementValue ();
            
            // ValidServers
            conf.setXMLRootObject ();
            conf.setXMLObjectByKey (ServerConfigurationConstants.SERVER_VALIDATION);
            XMLObjectList xmlObjectList = conf.getChildren (ServerConfigurationConstants.VALID_SERVER);
            for (int i = 0; i < xmlObjectList.size (); i++) {
                XMLRetriever retriever = new XMLRetriever(xmlObjectList.get(i));
                retriever.setXMLObjectByKey(ServerConfigurationConstants.VALID_SERVER_ID);
                _validServerIds.add(retriever.getElementValue ());
            }
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
    
    public int countValidServerIds() {
        return _validServerIds.size ();
    }
    
    public String getValidServerId(int index) {
        return (String)_validServerIds.get(index);
    }
}
