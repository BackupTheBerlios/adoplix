/*
 * AdapterConnector.java
 *
 */

package de.adoplix.internal.connection;

import de.adoplix.internal.runtimeInformation.AdopLog;
import de.adoplix.internal.runtimeInformation.constants.ErrorConstants;
import de.adoplix.internal.runtimeInformation.exceptions.MessageContentException;
import de.adoplix.internal.server.AdoplixServer;
import de.adoplix.internal.telegram.Acknowledge;
import de.adoplix.internal.telegram.LocalConnection;
import de.adoplix.internal.telegram.XMLContainer;
import de.adoplix.internal.tools.LittleHelper;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * Basic communication functions. <br>
 * Check if communication to client is ok.
 *
 * @author dirk
 */
public class AdapterConnector implements I_AdapterConnector {
    
    private static Logger _logger = AdopLog.getLogger (AdapterConnector.class);
    
    protected boolean _talkToClient = true;
    protected Socket _clientSocket;
    protected XMLContainer _xmlContainer;
    protected LocalConnection _localConnection;
    protected boolean _run = true;
    
    
    /** Creates a new instance of AdapterConnector */
    public AdapterConnector (Socket clientSocket) {
        _clientSocket =  clientSocket;
        
        // change xml-stream into xml-container with getters and setters
        // the xml-container will be the right one for the derived subclass.
        // The subclass will use it later in run-method
        try {
            _xmlContainer = LittleHelper.createXMLContainer (_clientSocket.getInputStream ());
        } catch (MessageContentException mcEx) {
            _logger.severe (mcEx.getMessage ());
        } catch (IOException ioEx) {
            _logger.severe (ErrorConstants.MESSAGE_READ_ERROR + ": " + ErrorConstants.getErrorMsg (ErrorConstants.MESSAGE_READ_ERROR));
        }
    }
    
    public void stop () {
        try {
            _clientSocket.close ();
        } catch (IOException ioEx) {}
        _run = false;
    }
    
    public void run () {
        // let server look for task and start TaskAdapter by server
        TaskAdapter taskAdapter = AdoplixServer.startTaskAdapter();
        // server retrieves TaskAdapter
        // set xml container to TaskAdapter (he must be able to handle the data)
        taskAdapter.setXMLContainer(_xmlContainer);
        
        if (_xmlContainer.acknByServer ()) {
            Acknowledge ackn = new Acknowledge();
        }
        
        while (_run) {
            // wait for acknowledge by TaskAdapter... 
            nur wenn so konf.
        }
    }
}
