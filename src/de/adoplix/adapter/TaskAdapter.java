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
public class TaskAdapter extends Adapter {
    
    protected Task _task;
    
    /** Creates a new instance of TaskAdapter */
    public TaskAdapter () {
    }
    
    public TaskAdapter (Task task, Socket clientSocket, XMLContainer xmlContainer) {
        super(clientSocket, xmlContainer);
        _task = task;
    }   
}
