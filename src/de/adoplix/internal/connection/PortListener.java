/*
 * PortListener.java
 *
 */

package de.adoplix.internal.connection;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    
    public void stop () {
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
//                    serverSocket.setSoTimeout (1000);
                /* don't start a new client (adapterconnector) when max. number
                 * of clients is reached */
                    Socket clientSocket = null;
                    // accept a new client for communicate with
                    clientSocket = serverSocket.accept();
                    System.out.println("ClientSocket = <null>? " + clientSocket);
//                    clientSocket.setReceiveBufferSize (serverSocket.getReceiveBufferSize ());
                    if (clientSocket.isConnected()) {
                        System.out.println("ClientSocket ist connected");
                        System.out.println("Grösse + " + clientSocket.getReceiveBufferSize());
                        clientSocket.setKeepAlive(true);
                        StringBuffer dataInputString = new StringBuffer("");
//                        try {
//                            System.out.println("Kurz vor Lesen...");
//                            BufferedReader in = new BufferedReader(
//                                    new InputStreamReader( clientSocket.getInputStream()) );
//                            String x = "";
//                            while ((x = in.readLine()).length() > 0) {
//                                dataInputString.append(x);
//                                System.out.println(x);
//                                System.out.println(dataInputString);
//                            }
//                            dataInputString.trimToSize();
                            System.out.println(dataInputString);
                            
//                        } catch (IOException ioEx){
//                            System.out.println(ioEx.getMessage());
//                            dataInputString.setLength(0);
//                        }
//                        logger.finest(clientSocket.toString());
//                        logger.finest(dataInputString.toString());
                        startAdapterConnector (clientSocket);
                    }
                    // spend a little time to other processes
                    Thread.sleep (10);
                    // process data coming from client socket
                } catch (InterruptedIOException irioEx) {
                    // nothing to do here... enables to stop this process regulary
                }catch (InterruptedException irEx)  {
                }
            }
            serverSocket.close ();
        } catch (IOException e) {
            logger.severe (ErrorConstants.COMMUNICATION_SOCKET_ACCEPT + ": " + ErrorConstants.getErrorMsg (ErrorConstants.COMMUNICATION_SOCKET_ACCEPT) + "; Socket = " + _socketNr);
        }
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
