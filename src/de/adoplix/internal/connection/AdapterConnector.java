/*
 * AdapterConnector.java
 *
 */

package de.adoplix.internal.connection;

import de.adoplix.internal.telegram.LocalConnection;
import de.adoplix.internal.telegram.XMLMessage;
import de.adoplix.internal.tools.LittleHelper;
import java.io.IOException;
import java.io.StringReader;
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
    protected String _threadId;
    protected boolean _run = true;

    
    /** Creates a new instance of AdapterConnector */
    public AdapterConnector (Socket clientSocket) {
        _clientSocket =  clientSocket;
        StringReader dataInputReader;
        registerAdapter();
        try {
            dataInputReader = LittleHelper.streamToStringReader(_clientSocket.getInputStream ());
            _xmlMessage = new XMLMessage(dataInputReader);
            run();
        }
        catch (IOException ioEx) {
            
        }
    }
    
    public String getThreadId() {
        return _threadId;
    }
    
    public void setThreadId(String threadId) {
        _threadId = threadId;
    }

    public void stop() {
        try {
            _clientSocket.close ();
        }
        catch (IOException ioEx) {}
        deregisterAdapter ();
        _run = false;
    }
    
    public void run() {}
    public void registerAdapter() {}
    public void deregisterAdapter() {}
}
