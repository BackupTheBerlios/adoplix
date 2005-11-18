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
    public TaskAdapterToPort (int port, String ip) {
        super (port, ip);
    }
    
    public TaskAdapterToPort (Task task, Socket clientSocket, XMLContainer xmlContainer) {
        super (task, clientSocket, xmlContainer);
        sendMessageToAdapterServerAndAckn ();
    }
    
    public void sendMessageToAdapterServerAndAckn () {
        // in taskid there is the ip and the port for connection...
        TaskAdapter adapterToServer = new TaskAdapter (_task.getLocalAdapterPort (), _task.getLocalAdapterIP ());
        adapterToServer.sendAdoplixMsgToClient (_xmlContainer);
        
        // if client awaits acknowledge from adapter
        // wait here for ackn
        if (1 == _task.getAcknInitiator ()) {
            Acknowledge acknowledge = adapterToServer.awaitAcknowledge (_task.getTimeOutAcknMillis ());
            if (null == acknowledge) {
                // server has send response
                acknowledge = new Acknowledge();
                acknowledge.setResult (1);
            }
            sendAdoplixMsgToClient (acknowledge);
        }
        closeClientSocket ();
    }
}
