package de.adoplix.internal.runtimeInformation.exceptions;

import de.adoplix.internal.runtimeInformation.constants.ErrorConstants;
import de.adoplix.internal.runtimeInformation.constants.ErrorConstantsText_Ger;

/**
 *
 * @author dirk
 */
  public class MessageSendException extends Exception {
    public static final long serialVersionUID = 0; 
    /** Creates a new instance of ConfigurationKeyNotFoundException */
    public MessageSendException () {
        super (ErrorConstants.COMMUNICATION_SEND_ERROR + ": " + ErrorConstants.getErrorMsg(ErrorConstants.COMMUNICATION_SEND_ERROR));
    }
    
    public MessageSendException (String msg) {
        super(msg);
    }
    
    public MessageSendException (int errNr, String msg) {
        super(errNr + ": " + ErrorConstants.getErrorMsg(errNr) + " " + msg);
    }
}
