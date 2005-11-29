/*
 * TaskAdapterToPort.java
 *
 */

package de.adoplix.adapter;
import de.adoplix.internal.runtimeInformation.AdopLog;
import de.adoplix.internal.server.*;
import de.adoplix.internal.server.AdoplixServer;
import de.adoplix.internal.tasks.Task;
import de.adoplix.internal.telegram.Acknowledge;
import de.adoplix.internal.telegram.AdminFunction;
import de.adoplix.internal.telegram.XMLContainer;
import java.net.Socket;
import java.util.logging.Logger;

/**
 *
 * @author dirk
 */
public class TaskAdapterToAdmin extends TaskAdapter {
    
    private Logger _logger = AdopLog.getLogger (TaskAdapterToAdmin.class);
    
    /** Creates a new instance of TaskAdapterToPort */
    public TaskAdapterToAdmin (int port, String ip) {
        super (port, ip);
    }
    
    public TaskAdapterToAdmin (Task task, Socket clientSocket, XMLContainer xmlContainer) {
        super (task, clientSocket, xmlContainer);
    }
    
    public void run () {
        // XMLContainer contains the Method-Name and Parameter
        // Task is not configured but fix implemented in console application
        
        // use server admin method
        String _function = ((AdminFunction)_xmlContainer).getMethodName ();
        String _parameterValue = ((AdminFunction)_xmlContainer).getParameterValue ();
        String serverReturnValue = "";
        
        if (_function.equalsIgnoreCase (AdminFunctionConstants.F_SHUTDOWN)) {AdoplixServer.shutdown (_parameterValue);}
        if (_function.equalsIgnoreCase (AdminFunctionConstants.F_RESTART)) {AdoplixServer.restart (_parameterValue);}
        if (_function.equalsIgnoreCase (AdminFunctionConstants.F_SET_TIME)) {AdoplixServer.setTime (_parameterValue);}
        if (_function.equalsIgnoreCase (AdminFunctionConstants.F_GET_TIME)) {serverReturnValue = AdoplixServer.getTime ();}
        if (_function.equalsIgnoreCase (AdminFunctionConstants.F_GET_LIFETIME_MILLIS)) {serverReturnValue = AdoplixServer.getLifetimeMillis ();}
        if (_function.equalsIgnoreCase (AdminFunctionConstants.F_REREAD_SERVER_CONF)) {AdoplixServer.reReadServerConf ();}
        if (_function.equalsIgnoreCase (AdminFunctionConstants.F_REREAD_TASK_CONF)) {AdoplixServer.reReadTaskConf ();}
        if (_function.equalsIgnoreCase (AdminFunctionConstants.F_GET_VERSION)) {serverReturnValue = AdoplixServer.getVersion ();}
        if (_function.equalsIgnoreCase (AdminFunctionConstants.F_SEND_EVENT)) {serverReturnValue = AdoplixServer.sendEvent (_parameterValue);}
        if (_function.equalsIgnoreCase (AdminFunctionConstants.F_START_MONITOR)) {AdoplixServer.startMonitor ();}
        if (_function.equalsIgnoreCase (AdminFunctionConstants.F_STOP_MONITOR)) {AdoplixServer.stopMonitor ();}
        if (_function.equalsIgnoreCase (AdminFunctionConstants.F_SET_LOGGING_LEVEL)) {AdoplixServer.setLoggingLevel (_parameterValue);}
        
        
        // acknowledge must be send for every function call
        // that the client is informed about its call has reached the server...
        Acknowledge acknowledge = new Acknowledge ();
        acknowledge.setResult (0);
        sendAcknowledgeToClient (acknowledge);
        
        closeClientSocket();
    }
}
