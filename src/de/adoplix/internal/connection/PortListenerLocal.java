/*
 * PortListenerAdmin.java
 * @deprecated
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
 * @deprecated No more reasons to use this class because the concept has changed...
 */
public class PortListenerLocal extends PortListener {
    
    /**
     * Creates a new instance of PortListenerAdmin 
     */
    public PortListenerLocal (int socketNr) {
        super (socketNr);
    }
    
}
