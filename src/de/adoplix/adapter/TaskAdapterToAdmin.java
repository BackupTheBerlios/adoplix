/*
 * TaskAdapterToPort.java
 *
 */

package de.adoplix.adapter;
import de.adoplix.internal.server.AdoplixServer;
import de.adoplix.internal.tasks.Task;
import de.adoplix.internal.telegram.Acknowledge;
import de.adoplix.internal.telegram.XMLContainer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 *
 * @author dirk
 */
public class TaskAdapterToAdmin extends TaskAdapter {
    
    /** Creates a new instance of TaskAdapterToPort */
    public TaskAdapterToAdmin () {
    }
    
    public TaskAdapterToAdmin (Task task, Socket clientSocket, XMLContainer xmlContainer) {
        super (task, clientSocket, xmlContainer);
        executeServerAdmin();
    }
    
    private void executeServerAdmin() {
        // XMLContainer contains the Method-Name, Parameters and Return-Results.
        // Task is not configured but fix implemented in console application
        try {
            // use server admin method
            StringBuffer strBuffer = _xmlContainer.get
            String _function = _task.getTaskAlias ();
    Method method = AdoplixServer.getClass().getMethod( _function, null );
    String returnType = method.getReturnType().getName();
    System.out.print( "(" + returnType + ") " );
    Object returnValue = method.invoke( p, null );
    System.out.println( returnValue );
            
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
