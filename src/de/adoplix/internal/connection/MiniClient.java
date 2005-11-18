/*
 * MiniClient.java
 *
 * Created on 18. November 2005, 17:40
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package de.adoplix.internal.connection;

import de.adoplix.internal.runtimeInformation.AdopLog;
import de.adoplix.internal.telegram.Acknowledge;
import java.net.Socket;
import java.util.logging.Logger;

/**
 *
 * @author dirk
 */
public class MiniClient extends SocketConnection {
    
    private Logger logger = AdopLog.getLogger (MiniClient.class);
    
    public MiniClient (int port, String ip) {
        super (port, ip);
    }
    
    /** Creates a new instance of MiniClient */
    public MiniClient (Socket socket) {
        super(socket);
    }
    
    public Socket getClientSocket () {
        return super.getSocket();
    }
    
    public void setClientSocket (Socket clientSocket) {
        super.setSocket(clientSocket);
    }
    
    public Acknowledge getResponseOfClient () {
        return super.getAcknowledge();
    }
}
