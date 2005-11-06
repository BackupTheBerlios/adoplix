/*
 * PortAcceptorAdmin.java
 *
 */

package de.adoplix.internal.server;

import de.adoplix.internal.connection.AdapterConnector;
import de.adoplix.internal.connection.WaitForConnectionThread;
import java.net.Socket;

/**
 * Takes ports (implementation in superclass) and starts client 
 * @author dirk
 */
public class PortAcceptorAdmin extends WaitForConnectionThread {
    
    /** Creates a new instance of PortAcceptorAdmin */
    public PortAcceptorAdmin (int socketNr) {
        super (socketNr);
    }
    
    /**
     * Starts a new AdapterConnector.
     * Here it is an adapter for communicate with a client which uses the
     * administration port.
     */
    public AdapterConnector startAdapterConnector (Socket clientSocket) {
        AdapterConnectorAdmin adapterConnector = null;
        adapterConnector = new AdapterConnectorAdmin(clientSocket);
        return (AdapterConnector)adapterConnector;
    }
}
