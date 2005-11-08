/*
 * PortAcceptorAdmin.java
 *
 */

package de.adoplix.internal.server;

import de.adoplix.internal.connection.AdapterConnector;
import de.adoplix.internal.connection.WaitForConnectionThread;
import de.adoplix.internal.telegram.XMLMessage;
import java.net.Socket;

/**
 * Supports ports (implementation in superclass) and starts clients. 
 * This PortAcceptor waits for requests (events) from AdaptorAdmin-Clients.
 * New client-requests are initialized by LocalRequests. LocalRequests in XML
 * format at first are parsed by this class (PortAcceptor) and wrapped into
 * a container called LocalRequest.
 * Later the container will be given to a new Instance of an AdaptorConnectorAdmin.
 * @author dirk
 */
public class PortAcceptorAdmin extends WaitForConnectionThread {
    
    /** Creates a new instance of PortAcceptorAdmin */
    public PortAcceptorAdmin (int socketNr) {
        super (socketNr);
    }
    
    /**
     * Starts a new AdaptorConnector.
     * Here it is an adapter for communicate with a client which uses the
     * administration port.
     */
    public AdapterConnector startAdapterConnector (Socket clientSocket, XMLMessage xmlMessage) {
        AdapterConnectorAdmin adapterConnector = null;
        adapterConnector = new AdapterConnectorAdmin(clientSocket, xmlMessage);
        return (AdapterConnector)adapterConnector;
    }
}
