package de.adoplix.internal.runtimeInformation.exceptions;

import de.adoplix.internal.runtimeInformation.constants.*;

/**
 *
 * @author dirk
 */
  public class ConfigurationKeyNotFoundException extends Exception {
    public static final long serialVersionUID = 0; 
    /** Creates a new instance of ConfigurationKeyNotFoundException */
    public ConfigurationKeyNotFoundException () {
        super (ErrorConstants.CONFIGURATION_KEY_NOT_FOUND + ": " +  ErrorConstants.getErrorMsg (ErrorConstants.CONFIGURATION_KEY_NOT_FOUND));
    }
    
    public ConfigurationKeyNotFoundException (String msg) {
        super(msg);
    }
    
    public ConfigurationKeyNotFoundException (int errNr, String msg) {
        super(errNr + ": " + ErrorConstants.getErrorMsg(errNr) + " " + msg);
    }
    
}
