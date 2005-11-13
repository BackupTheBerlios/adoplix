/*
 * PortListenerAdmin.java
 *
 */

package de.adoplix.internal.server;
import de.adoplix.internal.connection.PortListener;
import de.adoplix.internal.runtimeInformation.AdopLog;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * PortListener which starts AdaptorConnectorAdmin. <br>
 * Nothing more nothing less.
 * @author dirk
 */
public class PortListenerAdmin extends PortListener {
    
    private Logger logger = AdopLog.getLogger (PortListener.class);

    /**
     * Creates a new instance of PortListenerAdmin 
     */
    public PortListenerAdmin (int socketNr) {
        super (socketNr);
    }
    
    public void run() {
        // TODO
        // Admin Listener benutzt keine weiteren Adapter, sondern ruft
        // direkt die Methoden des Servers auf...
    }
}
