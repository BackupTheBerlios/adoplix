/*
 * PortListenerAdmin.java
 *
 */

package de.adoplix.internal.server;

import de.adoplix.internal.connection.AdapterConnector;
import de.adoplix.internal.connection.PortListener;
import de.adoplix.internal.telegram.XMLMessage;
import java.net.Socket;

/**
 * PortListener which starts AdaptorConnectorAdmin. <br>
 * Nothing more nothing less.
 * @author dirk
 */
public class PortListenerLocal extends PortListener {
    
    /**
     * Creates a new instance of PortListenerAdmin 
     */
    public PortListenerLocal (int socketNr) {
        super (socketNr);
    }
    
    /**
     * Starts a new AdaptorConnector.
     * Here it is an adapter for communicate with a client which uses the
     * administration port.
     */
    public void startAdapterConnector (Socket clientSocket) {
        AdapterConnectorLocal adapterConnector = new AdapterConnectorLocal(clientSocket);
    }
}
