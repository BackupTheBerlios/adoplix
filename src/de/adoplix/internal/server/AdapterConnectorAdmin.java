/*
 * AdapterConnectorAdmin.java
 *
 */

package de.adoplix.internal.server;

import de.adoplix.internal.connection.AdapterConnector;
import de.adoplix.internal.telegram.XMLMessage;
import java.net.Socket;

/**
 * Gets a LocalRequest by the PortAcceptorAdmin. Issue is to be communication
 * partner of an AdapterAdmin.
 * Cyclic check if communication is stable and other typical aims of the
 * AdapterConnector are done by the superclass.
 * @author dirk
 */
public class AdapterConnectorAdmin extends AdapterConnector {
    
    /** Creates a new instance of AdapterConnectorAdmin */
    public AdapterConnectorAdmin (Socket clientSocket, XMLMessage xmlMessage) {
        super (clientSocket, xmlMessage);
    }
    
}
