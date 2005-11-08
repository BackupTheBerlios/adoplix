/*
 * AdapterConnector.java
 *
 */

package de.adoplix.internal.connection;

import de.adoplix.internal.telegram.LocalConnection;
import de.adoplix.internal.telegram.XMLMessage;
import java.net.Socket;

/**
 * Basic communication functions. <br>
 * Check if communication to client is ok.
 * 
 * @author dirk
 */
public class AdapterConnector implements I_AdapterConnector {
    
    protected boolean _talkToClient = true;
    protected Socket _clientSocket;
    protected XMLMessage _xmlMessage;
    protected LocalConnection _localConnection;
    
    /** Creates a new instance of AdapterConnector */
    public AdapterConnector (Socket clientSocket, XMLMessage xmlMessage) {
        _clientSocket =  clientSocket;
        
        this.run();
    }
    
    public void run() {
        
//        while (_talkToClient)
//                lesen auf inputstream
//                abhängig von telegramm an eine methode weiterleiten
//                methode sendet über outputstream
//        Buffered...
//                
//        InputStream in = _clientSocket.getInputStream();
//        OutputStream out = _clientSocket.getOutputStream();
                
//       am ende der run method nicht vergessen, 
//       bei PortAcceptor abzumelden.  (clientThreadStopsWork)
    }
}
