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
    
    public AdapterSocketCommunication (int port, String ip) {
        miniClient = new MiniClient(port, ip);
    }
    
    public AdapterSocketCommunication (Socket clientSocket, XMLContainer xmlContainer) {
        miniClient = new MiniClient(clientSocket);
        miniClient.setClientSocket(clientSocket);
        _xmlContainer = xmlContainer;
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
