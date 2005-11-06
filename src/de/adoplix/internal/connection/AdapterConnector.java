/*
 * AdapterConnector.java
 *
 */

package de.adoplix.internal.connection;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author dirk
 */
public class AdapterConnector implements I_AdapterConnector {
    
    private Socket _clientSocket;
    
    /** Creates a new instance of AdapterConnector */
    public AdapterConnector (Socket clientSocket) {
        _clientSocket =  clientSocket;
        
        this.run();
    }
    
    public void run() {
        
        while ...
                lesen auf inputstream
                abhängig von telegramm an eine methode weiterleiten
                methode sendet über outputstream
        Buffered...
                
//        InputStream in = _clientSocket.getInputStream();
//        OutputStream out = _clientSocket.getOutputStream();
                
       am ende der run method nicht vergessen, 
       bei PortAcceptor abzumelden.  (clientThreadStopsWork)
    }
}
