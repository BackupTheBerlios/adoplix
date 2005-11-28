/*
 * AdapterSocketCommunication.java
 *
 */

package de.adoplix.adapter;

import de.adoplix.internal.connection.MiniClient;
import de.adoplix.internal.connection.MiniServer;
import de.adoplix.internal.connection.SocketConnection;
import de.adoplix.internal.runtimeInformation.AdopLog;
import de.adoplix.internal.runtimeInformation.exceptions.MessageNotAvailableException;
import de.adoplix.internal.telegram.Acknowledge;
import de.adoplix.internal.telegram.XMLContainer;
import java.net.Socket;
import java.util.logging.Logger;

/**
 *
 * @author dirk
 */
public class AdapterSocketCommunication extends Adapter {
    
    private Logger _logger = AdopLog.getLogger (AdapterSocketCommunication.class);
    private MiniClient miniClient;
    private MiniServer miniServer;
    protected boolean _run = true;
    protected XMLContainer _xmlContainer;
    
    public AdapterSocketCommunication() {
        super();
    }
    
    public AdapterSocketCommunication (int portServerConnectTo, String ipServerConnectTo, int portServerListens) {
        miniClient = new MiniClient(portServerConnectTo, ipServerConnectTo);
        if (portServerListens != 0) {
            miniServer = new MiniServer(portServerListens);
        }
    }
    
    public AdapterSocketCommunication (Socket clientSocket, XMLContainer xmlContainer, int portServerListens) {
        miniClient = new MiniClient(clientSocket);
        _xmlContainer = xmlContainer;
        if (portServerListens != 0 ) {
            miniServer = new MiniServer(portServerListens);
        }
    }
    
    protected void createMiniClient(int portServerConnectTo, String ipServerConnectTo) {
        miniClient = new MiniClient(portServerConnectTo, ipServerConnectTo);
    }
    
    protected void createMiniServer(int portServerListens) {
        miniServer = new MiniServer(portServerListens);
    }
    
    public void stop() {
        miniClient.closeSocket();
        super.stop();
    }
    
    public void run() {}
    
    protected void closeClientSocket() {
        miniClient.closeSocket ();
    }
    
    protected void closeServerSocket () {
        miniServer.closeSocket();
    }
    
    protected void sendAcknowledgeToClient (XMLContainer xmlContainer) {
        miniClient.sendAdoplixMsg(xmlContainer);
    }
    
    protected void sendAcknowledgeToServer (XMLContainer xmlContainer) {
        miniServer.sendAdoplixMsg(xmlContainer);
    }
    
    public void sendMsgToClient(Object adapterInterface, String msg) {
        sendMsg(adapterInterface, msg, miniClient);
    }
    
    public void sendMsgToServer (Object adapterInterface, String msg) {
        sendMsg(adapterInterface, msg, miniServer);
    }

    private void sendMsg (Object adapterInterface, String msg, SocketConnection communicationPart ) {
        if (adapterInterface instanceof Adapter) {
            
        }
        
        if (adapterInterface instanceof Socket) {
            communicationPart.sendMsg((Socket)adapterInterface, msg);
        }
    }
    
    public void sendAdoplixMsgToClient (XMLContainer msg) {
        miniClient.sendAdoplixMsg(msg);
    }
    
    public void sendAdoplixMsgToServer (XMLContainer msg) {
        miniServer.sendAdoplixMsg(msg);
    }
    
    public void sendAdoplixMsgToClient (Object adapterInterface, XMLContainer msg) {
        sendAdoplixMsg(adapterInterface, msg, miniClient);
    }
    
    public void sendAdoplixMsgToServer (Object adapterInterface, XMLContainer msg) {
        sendAdoplixMsg(adapterInterface, msg, miniServer);
    }
    
    private void sendAdoplixMsg (Object adapterInterface, XMLContainer msg, SocketConnection communicationPart ) {
        if (adapterInterface instanceof Adapter){
            // use methods of class to exchange data
        }
        
        if (adapterInterface instanceof Socket) {
            communicationPart.sendAdoplixMsg ((Socket)adapterInterface, msg);
        }
    }
   
    protected XMLContainer getXMLContainer () {
        return _xmlContainer;
    }
    
    protected XMLContainer getAdoplixMsg () throws MessageNotAvailableException {
        throw new MessageNotAvailableException();
    }
    
    public Acknowledge awaitAcknowledge(long timeOutMillis) {
        return miniClient.awaitAcknowledge (timeOutMillis);
    }
}
