package de.adoplix.internal.runtimeInformation.exceptions;

import de.adoplix.internal.runtimeInformation.constants.ErrorConstants;
import de.adoplix.internal.runtimeInformation.constants.ErrorConstantsText_Ger;

/**
 *
 * @author dirk
 */
  public class ConfigurationTypeException extends Exception {
    public static final long serialVersionUID = 0; 
    /** Creates a new instance of ConfigurationKeyNotFoundException */
    public ConfigurationTypeException () {
        super (ErrorConstants.CONFIGURATION_TYPE_FALSE + ": " + //
               ErrorConstantsText_Ger.CONFIGURATION_TYPE_FALSE);
    }
    
    public ConfigurationTypeException (String msg) {
        super(msg);
    }
    
}
