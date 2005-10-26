package de.adoplix.internal.server;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationKeyNotFoundException;
import de.adoplix.internal.tools.*;

/**
 * Veranlasst das Auslesen der Serverkonfiguration und stellt diese
 * zur Laufzeit zur Verfuegung.
 * @author dirkg
 */
public class ServerConfiguration {
    private XMLObject _xmlObject = null;
    private Configuration conf = null;
    
    /** Creates a new instance of ServerConfiguration */
    public ServerConfiguration (String configurationFile) {

        try {
            _xmlObject = new XMLObject();
            conf = new Configuration(_xmlObject, configurationFile);
        }
        catch (Exception ex) {
            System.out.println("ERROR " + "---: " + ex.getMessage ());
        }
    }
    
    public String getServerId() {
        try {
            return conf.readParam (ServerConfigurationConstants.SERVER_ID);
        }            
        catch (ConfigurationKeyNotFoundException confEx) {
            System.out.println(confEx.getMessage ());
        }
        
        return null;
    }
    
    public String getPwd() {
        try {
            return conf.readParam (ServerConfigurationConstants.SERVER_PWD);
        }            
        catch (ConfigurationKeyNotFoundException confEx) {
            System.out.println(confEx.getMessage ());
        }
        
        return null;
    }
    
    public String getValidServerServerId(int index) {
        try {
            return conf.readParamFromList (ServerConfigurationConstants.VALID_SERVER_ID, index);
        }            
        catch (ConfigurationKeyNotFoundException confEx) {
            System.out.println(confEx.getMessage ());
        }
        
        return null;
    }
    
}
