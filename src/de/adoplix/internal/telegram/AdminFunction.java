/*
 * LocalConnection.java
 *
 */

package de.adoplix.internal.telegram;
import java.util.logging.Logger;

import de.adoplix.internal.runtimeInformation.AdopLog;
import de.adoplix.internal.runtimeInformation.exceptions.MessageContentException;
import de.adoplix.internal.tools.xml.XMLRetriever;

/**
 * Container which stores connection informations from a local adapter
 * (ServiceAdapter, EventAdapter, AdminAdapter) when it contacts to the server
 * (PortAcceptor) first time.
 * @author dirkg
 */
public class AdminFunction extends XMLContainer {
    
    private Logger _logger = AdopLog.getLogger (Acknowledge.class);

    public AdminFunction () {
        _msgType = XMLMessageConstants.MSG_TYPE_FUNCTION;
    }
    
    public AdminFunction (XMLRetriever retriever) throws MessageContentException {
        // super reads the header
        super(retriever);
    }
    
    public String getMethodName () {
        return super.getCDataEntry(XMLMessageConstants.METHOD_NAME);
    }

    public void setMethodName (String _methodName) {
        setCDataEntry(XMLMessageConstants.METHOD_NAME, _methodName);
//        this._methodName = _methodName;
    }

    public String getParameterValue () {
        return super.getCDataEntry(XMLMessageConstants.PARAMETER_VALUE);
    }

    public void setParameterValue (String _parameterValue) {
        setCDataEntry(XMLMessageConstants.PARAMETER_VALUE, _parameterValue);
//        this._parameterValue = _parameterValue;
    }
}
