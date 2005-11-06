/*
 * AdapterConnectorAdmin.java
 *
 */

package de.adoplix.internal.server;

import de.adoplix.internal.connection.AdapterConnector;
import java.net.Socket;

/**
 *
 * @author dirk
 */
public class AdapterConnectorAdmin extends AdapterConnector {
    
    /** Creates a new instance of AdapterConnectorAdmin */
    public AdapterConnectorAdmin (Socket clientSocket) {
        super (clientSocket);
    }
    
}
