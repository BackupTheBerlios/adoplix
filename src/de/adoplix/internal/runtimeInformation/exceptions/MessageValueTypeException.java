package de.adoplix.internal.runtimeInformation.exceptions;

import de.adoplix.internal.runtimeInformation.constants.ErrorConstants;
import de.adoplix.internal.runtimeInformation.constants.ErrorConstantsText_Ger;

/**
 *
 * @author dirk
 */
  public class MessageValueTypeException extends Exception {
    public static final long serialVersionUID = 0; 
    /** Creates a new instance of ConfigurationKeyNotFoundException */
    public MessageValueTypeException () {
        super (ErrorConstants.CONFIGURATION_TYPE_FALSE + ": " + ErrorConstants.getErrorMsg(ErrorConstants.CONFIGURATION_TYPE_FALSE));
    }
    
    public MessageValueTypeException (String msg) {
        super(msg);
    }
    
    public MessageValueTypeException (int errNr, String msg) {
        super(errNr + ": " + ErrorConstants.getErrorMsg(errNr) + " " + msg);
    }
}
