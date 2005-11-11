/*
 * AdapterConnectorAdmin.java
 *
 */

package de.adoplix.internal.server;

import de.adoplix.internal.connection.AdapterConnector;
import de.adoplix.internal.telegram.XMLMessage;
import java.net.Socket;

/**
 * Receives XML-Messages. <br>
 * Decodes the Message and looks what to do <br>
 * Calls the server-functions and waits for the responses. <br>
 * Doesn't use the Task-Configuration.
 * @author dirk
 */
public class AdapterConnectorAdmin extends AdapterConnector {


    /** Creates a new instance of AdapterConnectorAdmin */
    public AdapterConnectorAdmin (Socket clientSocket) {
        // after super-call the xml-data is prepared to read
        // from _xmlContainer
        super (clientSocket);
    }
    
    /**
     * tells server that it exists
     */
    public void registerAdapter() {
    }
    
    public void deregisterAdapter() {
    }
    
    public void run() {
        while (_run) {
        
        }
    }
}
