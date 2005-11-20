/*
 * MiniServer.java
 *
 */

package de.adoplix.internal.connection;

import de.adoplix.internal.runtimeInformation.AdopLog;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Logger;

/**
 *
 * @author dirk
 */
public class MiniServer extends SocketConnection {
    
    private Logger logger = AdopLog.getLogger (MiniServer.class);
    
    /** Creates a new instance of MiniServer */
    public MiniServer (Socket socket) {
        super (socket);
    }
    
    public MiniServer (int port, String ip) {
        super (port, ip);
    }
    
    public void acceptClientRequest() {
        
    }

}
