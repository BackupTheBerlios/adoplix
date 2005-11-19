/*
 * PortListenerAdmin.java
 * @deprecated
 */

package de.adoplix.internal.connection;


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
