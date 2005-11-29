/*
 * PortListener.java
 *
 */

package de.adoplix.internal.connection;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import de.adoplix.internal.runtimeInformation.AdopLog;
import de.adoplix.internal.runtimeInformation.constants.ErrorConstants;

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
        
//        this.run();
    }
    
    public void interrupt () {
        _waitForConnections=false;
    }
    
    public void run () {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket (_socketNr);
            serverSocket.setReuseAddress(true);
            serverSocket.setReceiveBufferSize(2048);
            
            /*
             * Accept client requests start connector and assign it to the client.
             * Store the connector hashCode for administrate it (stopping etc.)
             */
            while (_waitForConnections) {
                try {
                /* don't start a new client (adapterconnector) when max. number
                 * of clients is reached */
                    Socket clientSocket = null;
                    // accept a new client for communicate with
                    clientSocket = serverSocket.accept();
                    System.out.println("ClientSocket = <null>? " + clientSocket);
                    if (clientSocket.isConnected() &&
                            clientSocket.getReceiveBufferSize() > 0) {
                        System.out.println("ClientSocket ist connected");
                        System.out.println("Grösse + " + clientSocket.getReceiveBufferSize());
                        clientSocket.setKeepAlive(true);
                        startAdapterConnector (clientSocket);
                    }
                    // spend a little time to other processes
                    Thread.sleep (10);
                    // process data coming from client socket
                } catch (InterruptedIOException irioEx) {
                    // nothing to do here... enables to stop this process regulary
                }catch (InterruptedException irEx)  {
                    // Interrupt from external process to stop this thread
                    _waitForConnections = false;
                }
            }
            serverSocket.close ();
        } catch (IOException e) {
            logger.severe (ErrorConstants.COMMUNICATION_SOCKET_ACCEPT + ": " + ErrorConstants.getErrorMsg (ErrorConstants.COMMUNICATION_SOCKET_ACCEPT) + "; Socket = " + _socketNr);
        }
        
        try {this.finalize();} catch (Throwable th) {}
    }
    
    /**
     * Used to start a new AdapterConnector (client) by extended class.
     */
    public void startAdapterConnector (Socket clientSocket) {
        AdapterConnector adapterConnector = new AdapterConnector (clientSocket);
//        Runnable adapterConnector = new AdapterConnector(clientSocket);
        Thread connectorThread = new Thread (adapterConnector);
        connectorThread.start ();
    }
    
}
