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
import de.adoplix.internal.tasks.Task;
import de.adoplix.internal.telegram.Acknowledge;
import de.adoplix.internal.telegram.ExternalEvent;
import de.adoplix.internal.telegram.XMLContainer;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author dirk
 */
public class TaskAdapterToPort extends TaskAdapter {
    
    /** Creates a new instance of TaskAdapterToPort */
    public TaskAdapterToPort () {
    }
    
    public TaskAdapterToPort (Task task, XMLContainer xmlContainer) {
        super (task, xmlContainer);
    }
    
    public Acknowledge sendAdoplixMsg () {
        // in taskid there is the ip and the port for connection...
        String ip = _task.getLocalAdapterIP ();
        int port = _task.getLocalAdapterPort ();
        try {
            Socket clientSocket = new Socket ( ip, port );
            super.sendAdoplixMsg (clientSocket, _xmlContainer);
            
            if (1 == _task.getAcknInitiator ()) {
                Acknowledge acknowledge = null;
//                long millisNow = System.currentTimeMillis () + ((ExternalEvent)_xmlContainer).g;
                
                // Adapter has to acknolewdge
                while (1<2) {
//                wait for quit...
                    break;
                }
                
                if (null != acknowledge) {
                    
                }
            }
        } catch (IOException ioEx) {
            
        }
        
        return null;
    }
    
}
