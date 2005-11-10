package de.adoplix.internal.runtimeInformation.exceptions;

import de.adoplix.internal.runtimeInformation.constants.*;

/**
 *
 * @author dirk
 */
  public class MessageContentException extends Exception {
    public static final long serialVersionUID = 0; 
    /** Creates a new instance of ConfigurationKeyNotFoundException */
    public MessageContentException () {
        super (ErrorConstants.MESSAGE_CONTENT_MISSED + ": " +  ErrorConstants.getErrorMsg (ErrorConstants.MESSAGE_CONTENT_MISSED));
    }
    
    public MessageContentException (String msg) {
        super(msg);
    }
    
    public MessageContentException (int errNr, String msg) {
        super(errNr + ": " + ErrorConstants.getErrorMsg(errNr) + " " + msg);
    }
    
}
