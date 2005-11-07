/*
 * WaitForConnectionThread.java
 *
 */

package de.adoplix.internal.connection;

import de.adoplix.internal.runtimeInformation.constants.ErrorConstants;
import de.adoplix.internal.tools.AdopLog;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * @author dirk
 */
public class WaitForConnectionThread implements I_WaitForConnectionThread {
    
    protected int _socketNr = 0;
    protected int _maxClientNumber = 0;
    protected boolean _waitForConnections = true;
    protected Map _adapterConnectors = new HashMap();
    private Logger logger = AdopLog.getLogger (WaitForConnectionThread.class);
    
    /** Creates a new instance of WaitForConnectionThread */
    public WaitForConnectionThread (int socketNr) {
        _socketNr = socketNr;
        
        this.run();
    }
    
    public void stop() {
        _waitForConnections=false;
    }
    
    public void run () {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket (_socketNr);
        } catch (IOException ioEx) {
            _waitForConnections = false;
            logger.severe (ErrorConstants.COMMUNICATION_SOCKET_IO + ": " + ErrorConstants.getErrorMsg (ErrorConstants.COMMUNICATION_SOCKET_IO) + "; Socket = " + _socketNr);
            System.out.println ("adoplix Error" + ErrorConstants.COMMUNICATION_SOCKET_IO + ": " + ErrorConstants.getErrorMsg (ErrorConstants.COMMUNICATION_SOCKET_IO) + "; Socket = " + _socketNr);
        }
        
        while (_waitForConnections) {
            // don't start a new client (adapterconnector) when max. number
            // of clients is reached
            if (_adapterConnectors.size () < _maxClientNumber) {
                Socket clientSocket = null;
                try {
                    clientSocket = serverSocket.accept ();
                    AdapterConnector adapter = startAdapterConnector (clientSocket);
                    if (null != adapter) {
                        _adapterConnectors.put (new Integer(adapter.hashCode ()), adapter);
                    }

                    this.wait (50);
                    // process data coming from client socket
                } catch (IOException e) {
                logger.severe (ErrorConstants.COMMUNICATION_SOCKET_ACCEPT + ": " + ErrorConstants.getErrorMsg (ErrorConstants.COMMUNICATION_SOCKET_ACCEPT) + "; Socket = " + _socketNr);
                } catch (InterruptedException irEx) {
    //                _waitForConnections = false;
                }
            }
        }
    }
    
    /**
     * Remove client thread from list.
     * This function is called by the stopping client thread itself.
     * Needed for starting new clients, because there is a maximum number of
     * clients allowed to start.
     */
    public void clientThreadStopsWork(AdapterConnector adapter) {
        _adapterConnectors.remove (new Integer(adapter.hashCode ()));
    }
    
    /**
     * Setting the max. number of clients.
     */
    public void setMaxClientNumber (int maxClientNumber) {
        _maxClientNumber = maxClientNumber;
    }
    
    /**
     * Used to start a new AdapterConnector (client) by extended class.
     */
    public AdapterConnector startAdapterConnector (Socket clientSocket) {
        return null;
    }
}
