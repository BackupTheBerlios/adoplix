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
 * Calls the server-functions and waits for the responses.
 * @author dirk
 */
public class AdapterConnectorExternal extends AdapterConnector {
    
    /** Creates a new instance of AdapterConnectorAdmin */
    public AdapterConnectorExternal (Socket clientSocket) {
        super (clientSocket);
    }

    public void registerAdapter() {
        _threadId = AdoplixServer.registerAdapter(this);        
    }
    
    public void run() {

    }
}
