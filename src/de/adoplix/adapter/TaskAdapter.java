/*
 * TaskAdapter.java
 *
 */

package de.adoplix.adapter;

import de.adoplix.internal.tasks.Task;
import de.adoplix.internal.telegram.XMLContainer;
import java.net.Socket;


/**
 *
 * @author dirk
 */
public class TaskAdapter extends AdapterSocketCommunication {
    
    protected Task _task;
    
    public TaskAdapter (int port, String ip) {
        super (port, ip, 0, null);
    }
    
    public TaskAdapter (Task task, Socket clientSocket, XMLContainer xmlContainer) {
        super(clientSocket, xmlContainer, 0 , null);
        _task = task;
    }   
}
