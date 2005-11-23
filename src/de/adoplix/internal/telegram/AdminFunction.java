/*
 * LocalConnection.java
 *
 */

package de.adoplix.internal.telegram;
import java.io.StringReader;
import java.util.logging.Logger;

import de.adoplix.internal.runtimeInformation.AdopLog;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationKeyNotFoundException;
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
    private String _cData;
    private String _methodName = "";
    private String _parameterValue = "";

    public AdminFunction () {
        _msgType = XMLMessageConstants.MSG_TYPE_FUNCTION;
        
    }
    
    public AdminFunction (XMLRetriever retriever) throws MessageContentException {
        // super reads the header
        super(retriever);
        
        // read body
        // here are two steps: at first getting CDATA, then using CDATA as
        // one more XML-Container.
        try {
            // CDATA
            retriever.setXMLObjectByKey (XMLMessageConstants.MSG_BODY, true);
            setCData(retriever.getChild (XMLMessageConstants.CDATA_BEGIN).getValue ());
            
            // XML part of cdata in new 'container'
            XMLRetriever cDataRetriever = new XMLRetriever(new StringReader(getCData()));
            cDataRetriever.setXMLObjectByKey (XMLMessageConstants.MSG_BODY, true);
            setMethodName(cDataRetriever.getChild (XMLMessageConstants.METHOD_NAME).getValue ());
            setParameterValue(cDataRetriever.getChild(XMLMessageConstants.PARAMETER_VALUE).getValue ());
        }
        catch (ConfigurationKeyNotFoundException cknfEx) {
            throw new MessageContentException();
        }
    }
    

    /**
     * Returns an XML-formatted StringReader which contains the values of the members
     * of this class (AdminFunction).
     * @return XML-formatted values
     */
    public StringReader getXMLStringReader () {
        prepareCData();
        addCDataToBody (getCData());
        return super.getXMLStringReader ();
    }
    
    public String getXMLString () {
        prepareCData();
        addCDataToBody(getCData());
        return super.getXMLString();
    }
    
    private void prepareCData() {
        setCData("\n<" + XMLMessageConstants.MSG_BODY + ">");
        setCData(getCData() + ("\n<" + XMLMessageConstants.METHOD_NAME + ">" + getMethodName() + "</" + XMLMessageConstants.METHOD_NAME + ">"));
        setCData(getCData() + ("\n<" + XMLMessageConstants.PARAMETER_VALUE + ">" + getParameterValue() + "</" + XMLMessageConstants.PARAMETER_VALUE + ">"));
        setCData(getCData() + ("\n</" + XMLMessageConstants.MSG_BODY + ">"));
    }

    public String getCData () {
        return _cData;
    }

    public void setCData (String _cData) {
        this._cData = _cData;
    }

    public String getMethodName () {
        return _methodName;
    }

    public void setMethodName (String _methodName) {
        this._methodName = _methodName;
    }

    public String getParameterValue () {
        return _parameterValue;
    }

    public void setParameterValue (String _parameterValue) {
        this._parameterValue = _parameterValue;
    }
}
