/*
 * ServerConfiguration.java
 *
 * Created on 23. Oktober 2005, 22:24
 */

package de.adoplix.internal.server;

import java.io.*;
import de.adoplix.internal.tools.*;
import de.adoplix.internal.ErrorHandling.*;

/**
 *
 * @author  dirk
 */
public class ServerConfiguration {
    
    private XMLObject _xmlObject = null;
    
    /** Creates a new instance of ServerConfiguration */
    public ServerConfiguration (String configurationFile) {

        try {
        FileReader fReader = new FileReader(configurationFile);
        _xmlObject = new XMLObject();
        XMLParser serverParser = new XMLParser(fReader, _xmlObject);
        }
        catch (IOException ioEx) {
            System.out.println("ERROR " + ErrorConstants.CONFIGURATION_FILE_NOT_FOUND + ": " + ErrorConstantsText_Ger.CONFIGURATION_FILE_NOT_FOUND);
            System.out.println(ioEx.getMessage ());
        }
    }
    
    public String getServerId() {
        try {
        XMLObject xmlServerHandling = _xmlObject.getXMLSubObject (ServerConfigurationConstants.SERVER_HANDLING);
        XMLObject xmlServerId = xmlServerHandling.getXMLSubObject (ServerConfigurationConstants.SERVER_ID);
        return xmlServerId.getValue();
        }            
        catch (Exception ex) {}
        
        
        return "";
    }
    
}
