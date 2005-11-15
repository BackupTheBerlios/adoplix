/*
 * TaskAdapterToPort.java
 *
 * Created on 13. November 2005, 00:18
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package de.adoplix.adapter;
import de.adoplix.internal.runtimeInformation.AdopLog;
import de.adoplix.internal.tasks.Task;
import de.adoplix.internal.telegram.Acknowledge;
import de.adoplix.internal.telegram.XMLContainer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

/**
 *
 * @author dirk
 */
public class TaskAdapterToPort extends TaskAdapter {
    
    private Logger _logger = AdopLog.getLogger (TaskAdapterToPort.class);
    
    /** Creates a new instance of TaskAdapterToPort */
    public TaskAdapterToPort () {
    }
    
    public TaskAdapterToPort (Task task, Socket clientSocket, XMLContainer xmlContainer) {
        super (task, clientSocket, xmlContainer);
        sendMessageToAdapterServerAndAckn ();
    }
    
    public void sendMessageToAdapterServerAndAckn () {
        // in taskid there is the ip and the port for connection...
        String ip = _task.getLocalAdapterIP ();
        int port = _task.getLocalAdapterPort ();
        try {
            Socket serverSocket = new Socket ( ip, port );
            super.sendAdoplixMsg (serverSocket, _xmlContainer);
            
            // if client awaits acknowledge from adapter
            // wait here for ackn
            if (1 == _task.getAcknInitiator ()) {
                Acknowledge acknowledge = null;
                long millisTimeOut = System.currentTimeMillis () + _task.getTimeOutAcknMillis ();
                
                // wait for ackn
                boolean acknSent = false;
                while (System.currentTimeMillis () < millisTimeOut) {
                    BufferedReader socketIn = new BufferedReader (new InputStreamReader ( serverSocket.getInputStream ()) );
                    if (socketIn.ready ()) {
                        String responseOfServer = socketIn.readLine ();
                        writeToClientSocket (_clientSocket, responseOfServer);
                        acknSent = true;
                    }
                    // spend a little time for whole system
                    try {wait (10);} catch (Throwable th){}
                }
                
                if (! acknSent) {
                    Acknowledge ackn = new Acknowledge ();
                    ackn.setResult (1);
                    writeToClientSocket (_clientSocket, ackn.getXMLString ());
                }
            }
            _clientSocket.close ();
            serverSocket.close ();
            
        } catch (IOException ioEx) {
            _logger.warning ("aaaaaaaaaaaaaaaaaaaaaaaaaa");
        }
    }
    
    private void writeToClientSocket (Socket socket, String msg) {
        try {
            PrintWriter socketOut = new PrintWriter ( _clientSocket.getOutputStream () );
            socketOut.println (msg);
            socketOut.flush ();
        } catch (IOException ioEx) {
            _logger.warning ("aaaaaaaaaaaaaaaaaaaaaaaaaa");
        }
    }
}
