/*
 * TaskAdapter.java
 *
 */

package de.adoplix.adapter;

import de.adoplix.internal.tasks.Task;
import de.adoplix.internal.telegram.XMLContainer;


/**
 *
 * @author dirk
 */
public class TaskAdapter extends Adapter {
    
    protected Task _task;
    
    /** Creates a new instance of TaskAdapter */
    public TaskAdapter () {
    }
    
    public TaskAdapter (Task task, XMLContainer xmlContainer) {
        super(xmlContainer);
        _task = task;
    }   
}
