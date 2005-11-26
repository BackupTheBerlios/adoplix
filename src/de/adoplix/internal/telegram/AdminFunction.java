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
//    private String _methodName = "";
//    private String _parameterValue = "";

    public AdminFunction () {
        _msgType = XMLMessageConstants.MSG_TYPE_FUNCTION;
        
    }
    
    public AdminFunction (XMLRetriever retriever) throws MessageContentException {
        // super reads the header
        super(retriever);
        
        // read body
        // here are two steps: at first getting CDATA, then using CDATA as
        // one more XML-Container.
//        try {
//            // CDATA
//            retriever.setXMLObjectByKey (XMLMessageConstants.MSG_BODY, true);
//            setCData(retriever.getChild (XMLMessageConstants.CDATA_BEGIN).getValue ());
            
            // XML part of cdata in new 'container'
//            XMLRetriever cDataRetriever = new XMLRetriever(new StringReader(retriever.getElementValue()));
//            cDataRetriever.setXMLObjectByKey (XMLMessageConstants.CDATA_CONTENT, true);
//            cDataRetriever.setXMLObjectByKey (XMLMessageConstants.MSG_BODY, true);
//            setMethodName(cDataRetriever.getChild (XMLMessageConstants.METHOD_NAME).getValue ());
//            setParameterValue(cDataRetriever.getChild(XMLMessageConstants.PARAMETER_VALUE).getValue ());
//        }
//        catch (ConfigurationKeyNotFoundException cknfEx) {
//            throw new MessageContentException();
//        }
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
