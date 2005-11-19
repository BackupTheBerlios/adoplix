/*
 * Adapter.java
 *
 */

package de.adoplix.adapter;
import java.util.logging.Logger;

import de.adoplix.internal.adapter.I_Adapter;
import de.adoplix.internal.runtimeInformation.AdopLog;
import de.adoplix.internal.server.AdoplixServer;

/**
 *
 * @author dirk
 */
public class Adapter implements I_Adapter {
    
    private Logger _logger = AdopLog.getLogger (Adapter.class);
    protected boolean _run = true;
    
    public Adapter () {
        
    }
    
    public void stop() {
        _run = false;
        AdoplixServer.clientThreadStopped ();
    }
    
    public void run() {}
    
}
