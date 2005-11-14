/*
 * Adapter.java
 *
 */

package de.adoplix.adapter;
import de.adoplix.internal.runtimeInformation.AdopLog;
import de.adoplix.internal.runtimeInformation.exceptions.MessageSendException;
import de.adoplix.internal.telegram.XMLContainer;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Logger;
import de.adoplix.internal.adapter.I_Adapter;
import de.adoplix.internal.runtimeInformation.exceptions.MessageNotAvailableException;
import de.adoplix.internal.server.AdoplixServer;

/**
 *
 * @author dirk
 */
public class Adapter implements I_Adapter {
    
    private Logger _logger = AdopLog.getLogger (Adapter.class);
    protected boolean _run = true;
    protected Socket _clientSocket;
    protected XMLContainer _xmlContainer;
    
    /** Creates a new instance of Adapter */
    public Adapter () {
    }
    
    public void stop() {
        _run = false;
        AdoplixServer.clientThreadStopped ();
    }
    
    public void run() {}
    
    public Adapter (Socket clientSocket, XMLContainer xmlContainer) {
        _clientSocket = clientSocket;
        _xmlContainer = xmlContainer;
    }
    
    public void sendAdoplixMsg (Object adapterInterface, XMLContainer msg) {
        if (adapterInterface instanceof Adapter){
            // use methods of class to exchange data
        }
        
        if (adapterInterface instanceof Socket) {
            sendAdoplixMsg ((Socket)adapterInterface, msg);
        }
    }
   
    private void sendAdoplixMsg (Socket socket, XMLContainer msg) {
        try {
            PrintStream outputStream = new PrintStream ( socket.getOutputStream () );
            outputStream.println ( msg.getXMLString ());
        } catch (IOException ioEx) {
            _logger.warning (new MessageSendException ().getMessage ());
        }
    }
    
    protected XMLContainer getXMLContainer () {
        return _xmlContainer;
    }
    
    protected XMLContainer getAdoplixMsg () throws MessageNotAvailableException {
        throw new MessageNotAvailableException();
    }
}
