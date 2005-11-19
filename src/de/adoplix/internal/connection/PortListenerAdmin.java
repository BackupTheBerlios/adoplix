/*
 * PortListenerAdmin.java
 *
 */

package de.adoplix.internal.connection;
import java.util.logging.Logger;

import de.adoplix.internal.runtimeInformation.AdopLog;

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
        logger.info("Initialisiert...");
    }    
}
