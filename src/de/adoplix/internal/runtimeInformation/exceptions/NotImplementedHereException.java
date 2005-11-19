package de.adoplix.internal.runtimeInformation.exceptions;

import de.adoplix.internal.runtimeInformation.constants.ErrorConstants;

/**
 *
 * @author dirk
 */
  public class NotImplementedHereException extends Exception {
    public static final long serialVersionUID = 0; 
    /** Creates a new instance of ConfigurationKeyNotFoundException */
    public NotImplementedHereException () {
        super (ErrorConstants.NOT_IMPLEMENTED_IN_CLASS + ": " + ErrorConstants.getErrorMsg(ErrorConstants.NOT_IMPLEMENTED_IN_CLASS));
    }
    
    public NotImplementedHereException (String msg) {
        super(msg);
    }
    
    public NotImplementedHereException (int errNr, String msg) {
        super(errNr + ": " + ErrorConstants.getErrorMsg(errNr) + " " + msg);
    }
}
