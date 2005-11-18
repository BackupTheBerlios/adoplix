/*
 * Adapter.java
 *
 */

package de.adoplix.adapter;
import de.adoplix.internal.connection.MiniClient;
import de.adoplix.internal.runtimeInformation.AdopLog;
import de.adoplix.internal.runtimeInformation.exceptions.NotImplementedHereException;
import de.adoplix.internal.telegram.XMLContainer;
import java.net.Socket;
import java.util.logging.Logger;
import de.adoplix.internal.adapter.I_Adapter;
import de.adoplix.internal.connection.MiniServer;
import de.adoplix.internal.runtimeInformation.exceptions.MessageNotAvailableException;
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
