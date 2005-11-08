/*
 * WaitForConnectionThread.java
 *
 */

package de.adoplix.internal.connection;

import de.adoplix.internal.runtimeInformation.constants.ErrorConstants;
import de.adoplix.internal.server.AdoplixServer;
import de.adoplix.internal.tools.AdopLog;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    protected List _adapterConnectorNames = new ArrayList();
    private Logger logger = AdopLog.getLogger (WaitForConnectionThread.class);
    
    /** Creates a new instance of WaitForConnectionThread */
    public WaitForConnectionThread (int socketNr) {
        _socketNr = socketNr;
        
        this.run();
    }
    
    public void stop() {
        for (int i = 0; i < _adapterConnectorNames.size (); i++) {
            Integer adapterHashCode = (Integer)_adapterConnectorNames.get (i);
            
        }
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
        
        /*
         * Accept client requests start connector and assign it to the client.
         * Store the connector hashCode for administrate it (stopping etc.)
         */
        while (_waitForConnections) {
            /* don't start a new client (adapterconnector) when max. number
             * of clients is reached */ 
            if (_adapterConnectors.size () < _maxClientNumber) {
                Socket clientSocket = null;
                try {
                    // accept a new client for communicate with
                    clientSocket = serverSocket.accept ();
                    // unique threadId for adaptor
                    String threadId = AdoplixServer.generateThreadId(this);
                    AdapterConnector adapter = startAdapterConnector (threadId, clientSocket);
                    // store hashCode of adaptor for later use
                    if (null != adapter) {
                        Integer adapterHashCode = new Integer(adapter.hashCode ());
                        _adapterConnectors.put (adapterHashCode, adapter);
                        _adapterConnectorNames.add(adapterHashCode);
                    }

                    // spend a little time to other processes
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
        Integer adapterHashCode = new Integer(adapter.hashCode ());
        _adapterConnectors.remove (adapterHashCode);
        _adapterConnectorNames.remove (adapterHashCode);
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
    public AdapterConnector startAdapterConnector (String threadId, Socket clientSocket) {
        return null;
    }
    
}
