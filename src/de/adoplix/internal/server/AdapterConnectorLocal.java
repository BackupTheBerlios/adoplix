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
public class AdapterConnectorLocal extends AdapterConnector {
    
    /** Creates a new instance of AdapterConnectorLocal */
    public AdapterConnectorLocal (Socket clientSocket) {
        super (clientSocket);
    }
    
    /**
     * tells server that it exists
     */
    public void registerAdapter() {
        _threadId = AdoplixServer.registerAdapter(this);        
    }
    
    public void deregisterAdapter() {
        AdoplixServer.deregisterAdapter (this);
    }
    
    public void run() {
        /* when the process is here, the phase of initialization isn't 
         * completely finished... <br>
         * LocalConnection must be the first message.
         * Let's have a look into the message. */
        
        
        while (_run) {
            
        }
    }
}