package de.adoplix.internal.ErrorHandling.exceptions;

import de.adoplix.internal.ErrorHandling.constants.ErrorConstants;
import de.adoplix.internal.ErrorHandling.constants.ErrorConstantsText_Ger;
import javax.naming.ConfigurationException;

/**
 *
 * @author dirk
 */
public class ConfigurationKeyNotFoundException extends Exception {
    
    /** Creates a new instance of ConfigurationKeyNotFoundException */
    public ConfigurationKeyNotFoundException () {
        super (ErrorConstants.CONFIGURATION_KEY_NOT_FOUND + ": " + //
               ErrorConstantsText_Ger.CONFIGURATION_KEY_NOT_FOUND);
    }
    
    public ConfigurationKeyNotFoundException (String msg) {
        super(msg);
    }
    
}
