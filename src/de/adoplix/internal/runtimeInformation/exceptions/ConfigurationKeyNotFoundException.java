package de.adoplix.internal.runtimeInformation.exceptions;

import de.adoplix.internal.runtimeInformation.constants.ErrorConstants;
import de.adoplix.internal.runtimeInformation.constants.ErrorConstantsText_Ger;

/**
 *
 * @author dirk
 */
  public class ConfigurationKeyNotFoundException extends Exception {
    public static final long serialVersionUID = 0; 
    /** Creates a new instance of ConfigurationKeyNotFoundException */
    public ConfigurationKeyNotFoundException () {
        super (ErrorConstants.CONFIGURATION_KEY_NOT_FOUND + ": " + //
               ErrorConstantsText_Ger.CONFIGURATION_KEY_NOT_FOUND);
    }
    
    public ConfigurationKeyNotFoundException (String msg) {
        super(msg);
    }
    
}
