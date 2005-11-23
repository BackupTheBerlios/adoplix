/*
 * SocketConnection.java
 *
 */

package de.adoplix.internal.connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import de.adoplix.internal.runtimeInformation.AdopLog;
import de.adoplix.internal.runtimeInformation.exceptions.MessageContentException;
import de.adoplix.internal.runtimeInformation.exceptions.MessageSendException;
import de.adoplix.internal.telegram.Acknowledge;
import de.adoplix.internal.telegram.XMLContainer;
import de.adoplix.internal.tools.LittleHelper;

/**
 *
 * @author dirk
 */
public class SocketConnection {
    
    private Logger logger = AdopLog.getLogger (SocketConnection.class);
    private Acknowledge acknowledge = null;
    private Socket socket;

    /**
     * Creates a new instance of SocketConnection 
     */
    public SocketConnection (int port, String ip) {
        try {
            socket = new Socket ( ip, port );
        } catch (UnknownHostException uhEx) {
            logger.warning (uhEx.getMessage ());
        } catch (IOException ioEx) {
            logger.warning (ioEx.getMessage ());
        }
    }
    
    public SocketConnection(Socket socket) {
        this.socket = socket;
    }
    
    public void closeSocket () {
        if (null != socket &&
                ! socket.isClosed ()) {
            try {
                socket.close ();
            } catch (IOException ioEx) {
                logger.warning (new MessageSendException ().getMessage ());
            }
        }
    }
    
    public void sendMsg (String msg) {
        sendMsg (socket, msg);  
    }
    
    public void sendMsg (Socket socket, String msg) {
        acknowledge = null;
        if (! socket.isConnected ()) logger.warning ("Socket ist nicht verbunden." + socket.toString ());
        try {
            System.out.println("sendMsg");
            System.out.println("MSG: " + msg);
            System.out.flush();
            logger.finest ("Socket.getSendBufferSize " + socket.getSendBufferSize ());
            PrintStream outputStream = new PrintStream ( socket.getOutputStream () );
            logger.finest("Socket: " + socket.toString());
            logger.finest("OutputStream msg: " + msg);
            outputStream.println ( msg);
            outputStream.flush ();
            outputStream.notifyAll();
        } catch (IOException ioEx) {
            logger.warning (new MessageSendException ().getMessage ());
        }
    }
    
    public void sendAdoplixMsg (Socket socket, XMLContainer msg) {
        acknowledge = null;
        if (! socket.isConnected ()) logger.warning ("Socket ist nicht verbunden." + socket.toString ());
        try {
            System.out.println("sendAdoplixMsg");
            System.out.println("MSG: " + msg.getXMLString());
            System.out.flush();
            logger.finest ("Socket.getSendBufferSize " + socket.getSendBufferSize ());
            PrintStream outputStream = new PrintStream (socket.getOutputStream () );
            logger.finest("Socket: " + socket.toString());
            logger.finest("OutputStream msg: " + msg);
            outputStream.println ( msg.getXMLString ());
            outputStream.flush ();
            outputStream.notifyAll();
        } catch (IOException ioEx) {
            logger.warning (new MessageSendException ().getMessage ());
        }
    }
    
    public void sendAdoplixMsg (String msg) {
        sendMsg (socket, msg);
    }
    
    public void sendAdoplixMsg (XMLContainer msg) {
        sendAdoplixMsg (socket, msg);
    }
    
    public Socket getSocket () {
        return socket;
    }
    
    public void setSocket (Socket socket) {
        this.socket = socket;
    }
    
    public Acknowledge awaitAcknowledge (long timeOutMillis) {
        // if response has reached, return immediately       
        if (null != acknowledge) return acknowledge;
        
        long millisTimeOut = System.currentTimeMillis () + timeOutMillis;
        try {
            if (!socket.isConnected ()) logger.warning ("Socket ist nicht verbunden: " + socket.toString ());
            while (System.currentTimeMillis () < millisTimeOut) {
                BufferedReader socketIn = new BufferedReader (new InputStreamReader (socket.getInputStream ()));
                if (socketIn.ready ()) {
                    acknowledge = (Acknowledge)LittleHelper.createXMLContainer  (socketIn);
                }
                // spend a little time for whole system
                try {wait (10);} catch (Throwable th){}
            }
        } catch (IOException ioEx) {
            logger.warning (ioEx.getMessage ());
        } catch (MessageContentException mcEx) {
            logger.warning (mcEx.getMessage ());
        }
        return getAcknowledge ();
    }
    
    public Acknowledge getAcknowledge () {
        return acknowledge;
    }
    
    private void setResponse (String response) {
        this.acknowledge = acknowledge;
    }
}
