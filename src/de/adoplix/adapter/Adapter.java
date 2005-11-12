/*
 * Adapter.java
 *
 */

package de.adoplix.adapter;
import de.adoplix.internal.runtimeInformation.AdopLog;
import de.adoplix.internal.runtimeInformation.exceptions.MessageContentException;
import de.adoplix.internal.runtimeInformation.exceptions.MessageSendException;
import de.adoplix.internal.telegram.XMLContainer;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Logger;
import de.adoplix.internal.adapter.I_Adapter;
import de.adoplix.internal.runtimeInformation.exceptions.MessageNotAvailableException;

/**
 *
 * @author dirk
 */
public class Adapter implements I_Adapter {
    
    private Logger _logger = AdopLog.getLogger (Adapter.class);
    protected XMLContainer _xmlContainer;
    
    /** Creates a new instance of Adapter */
    public Adapter () {
    }
    
    public Adapter (XMLContainer xmlContainer) {
        _xmlContainer = xmlContainer;
    }
    
    protected void sendAdoplixMsg (Socket socket, XMLContainer msg) {
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
