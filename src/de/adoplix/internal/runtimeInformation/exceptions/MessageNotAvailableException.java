package de.adoplix.internal.runtimeInformation.exceptions;

import de.adoplix.internal.runtimeInformation.constants.*;

/**
 *
 * @author dirk
 */
  public class MessageNotAvailableException extends Exception {
    public static final long serialVersionUID = 0; 
    /** Creates a new instance of ConfigurationKeyNotFoundException */
    public MessageNotAvailableException () {
        super (ErrorConstants.MESSAGE_NOT_AVAILABLE + ": " +  ErrorConstants.getErrorMsg (ErrorConstants.MESSAGE_NOT_AVAILABLE));
    }
    
    public MessageNotAvailableException (String msg) {
        super(msg);
    }
    
    public MessageNotAvailableException (int errNr, String msg) {
        super(errNr + ": " + ErrorConstants.getErrorMsg(errNr) + " " + msg);
    }
    
}
