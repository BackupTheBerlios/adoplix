/*
 * PortListener.java
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
public class PortListener implements I_PortListener {
    
    protected int _socketNr = 0;
    protected boolean _waitForConnections = true;
    private Logger logger = AdopLog.getLogger (PortListener.class);
    
    /**
     * Creates a new instance of PortListener 
     */
    public PortListener (int socketNr) {
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
        
        /*
         * Accept client requests start connector and assign it to the client.
         * Store the connector hashCode for administrate it (stopping etc.)
         */
        while (_waitForConnections) {
            /* don't start a new client (adapterconnector) when max. number
             * of clients is reached */
            if (AdoplixServer.startClientThreadAllowed()) {
                Socket clientSocket = null;
                try {
                    // accept a new client for communicate with
                    clientSocket = serverSocket.accept ();
                    // unique threadId for adaptor
//                    String threadId = AdoplixServer.generateThreadId(this);
                    startAdapterConnector (clientSocket);
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
     * Used to start a new AdapterConnector (client) by extended class.
     */
    public void startAdapterConnector (Socket clientSocket) {}
    
}
