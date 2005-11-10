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
public class PortListenerExternal extends PortListener {
    
    /**
     * Creates a new instance of PortListenerAdmin 
     */
    public PortListenerExternal (int socketNr) {
        super (socketNr);
    }
    
    /**
     * Starts a new AdaptorConnector.
     * Here it is an adapter for communicate with a client which uses the
     * administration port.
     */
    public void startAdapterConnector (Socket clientSocket) {
        AdapterConnectorExternal adapterConnector = new AdapterConnectorExternal(clientSocket);
//        Runnable adapterConnector = new AdapterConnectorExternal(clientSocket);
        Thread connectorThread = new Thread(adapterConnector);
        connectorThread.start();
    }
}
