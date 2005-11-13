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
 * PortListener which starts AdaptorConnector. <br>
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
}
