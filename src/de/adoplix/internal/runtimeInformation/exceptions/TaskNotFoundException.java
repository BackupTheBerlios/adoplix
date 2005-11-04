package de.adoplix.internal.runtimeInformation.exceptions;

import de.adoplix.internal.runtimeInformation.constants.*;
import de.adoplix.internal.runtimeInformation.constants.*;

/**
 *
 * @author dirk
 */
  public class TaskNotFoundException extends Exception {
    public static final long serialVersionUID = 0; 
    /** Creates a new instance of ConfigurationKeyNotFoundException */
    public TaskNotFoundException () {
        super (ErrorConstants.CONFIGURATION_TASK_NOT_CONFIGURED + ": " + //
               ErrorConstantsText_Ger.CONFIGURATION_TASK_NOT_CONFIGURED);
    }
    
    public TaskNotFoundException (String msg) {
        super(msg);
    }
    
}
