/*
 * TaskAdapterToPort.java
 *
 */

package de.adoplix.adapter;
import de.adoplix.internal.server.*;
import de.adoplix.internal.server.AdoplixServer;
import de.adoplix.internal.tasks.Task;
import de.adoplix.internal.telegram.Acknowledge;
import de.adoplix.internal.telegram.AdminFunction;
import de.adoplix.internal.telegram.XMLContainer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
        // XMLContainer contains the Method-Name and Parameter
        // Task is not configured but fix implemented in console application
        try {
            // use server admin method
            String _function = ((AdminFunction)_xmlContainer).getMethodName ().toUpperCase ();
            String _parameterValue = ((AdminFunction)_xmlContainer).getParameterValue ().toUpperCase ();
            String serverReturnValue = "";
            
            if (_function.equals(AdminFunctionConstants.F_SHUTDOWN)) {AdoplixServer.shutdown(_parameterValue);}
            if (_function.equals(AdminFunctionConstants.F_RESTART)) {AdoplixServer.restart(_parameterValue);}
            if (_function.equals(AdminFunctionConstants.F_SET_TIME)) {AdoplixServer.setTime(_parameterValue);}
            if (_function.equals(AdminFunctionConstants.F_GET_TIME)) {serverReturnValue = AdoplixServer.getTime();}
            if (_function.equals(AdminFunctionConstants.F_GET_LIFETIME_MILLIS)) {serverReturnValue = AdoplixServer.getLifetimeMillis();}
            if (_function.equals(AdminFunctionConstants.F_REREAD_SERVER_CONF)) {AdoplixServer.reReadServerConf();}
            if (_function.equals(AdminFunctionConstants.F_REREAD_TASK_CONF)) {AdoplixServer.reReadTaskConf();}
            if (_function.equals(AdminFunctionConstants.F_GET_VERSION)) {serverReturnValue = AdoplixServer.getVersion();}
            if (_function.equals(AdminFunctionConstants.F_SEND_EVENT)) {serverReturnValue = AdoplixServer.sendEvent(_parameterValue);}
            if (_function.equals(AdminFunctionConstants.F_START_MONITOR)) {AdoplixServer.startMonitor();}
            if (_function.equals(AdminFunctionConstants.F_STOP_MONITOR)) {AdoplixServer.stopMonitor();}
            if (_function.equals(AdminFunctionConstants.F_SET_LOGGING_LEVEL)) {AdoplixServer.setLoggingLevel(_parameterValue);}
            
            
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
