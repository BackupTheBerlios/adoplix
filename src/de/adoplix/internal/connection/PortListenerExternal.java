/*
 * PortListenerAdmin.java
 *
 */

package de.adoplix.internal.connection;

import java.util.logging.Logger;

import de.adoplix.internal.runtimeInformation.AdopLog;


/**
 * PortListener which starts AdaptorConnector. <br>
 * Nothing more nothing less.
 * @author dirk
 */
public class PortListenerExternal extends PortListener {
    
    private Logger logger = AdopLog.getLogger(PortListenerExternal.class);
    
    /**
     * Creates a new instance of PortListenerExternal 
     */
    public PortListenerExternal (int socketNr) {
        super (socketNr);
        logger.info("Initialisiert...");
    }
}
