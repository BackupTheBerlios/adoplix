/*
 * LocalConnection.java
 *
 */

package de.adoplix.internal.telegram;
import de.adoplix.internal.runtimeInformation.AdopLog;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationKeyNotFoundException;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationTypeException;
import de.adoplix.internal.runtimeInformation.exceptions.MessageContentException;
import de.adoplix.internal.runtimeInformation.exceptions.MessageValueTypeException;
import de.adoplix.internal.tools.xml.XMLRetriever;
import java.util.logging.Logger;

/**
 * Container which stores connection informations from a local adapter
 * (ServiceAdapter, EventAdapter, AdminAdapter) when it contacts to the server
 * (PortAcceptor) first time.
 * @author dirkg
 */
public class ExternalResponse extends XMLContainer {

    private Logger _logger = AdopLog.getLogger (LocalConnection.class);
    
    private String _initialEventId = "";
    private String _initialAddress = "";
    private String _timeStampSend = "";
    private String _timeStampReceived = "";
    private String _responseId = "";
    private StringBuffer _cData = new StringBuffer();
    
    
    public ExternalResponse (XMLRetriever retriever) throws MessageContentException {
        super(retriever);
        try {
            // HEADER was selected in super-constructor
            setInitialAddress(retriever.getChild (XMLMessageConstants.INITIAL_ADDRESS).getValue ());
            setInitialEventId(retriever.getChild (XMLMessageConstants.INITIAL_EVENT_ID).getValue ());
            setTimeStampSend(retriever.getChild (XMLMessageConstants.TIME_STAMP_SEND).getValue ());
            setTimeStampReceived(retriever.getChild (XMLMessageConstants.TIME_STAMP_RECEIVED).getValue ());
            setResponseId(retriever.getChild (XMLMessageConstants.RESPONSE_ID).getValue ());
            retriever.setXMLRootObject ();
            retriever.setXMLObjectByKey (XMLMessageConstants.MSG_BODY);
            getCData().append (retriever.getChild (XMLMessageConstants.CDATA).getValue ());
        }
        catch (ConfigurationKeyNotFoundException cknfEx) {
            _logger.warning (new MessageContentException().getMessage ());
        }
    }

    public String getInitialEventId () {
        return _initialEventId;
    }

    public void setInitialEventId (String _initialEventId) {
        this._initialEventId = _initialEventId;
    }

    public String getInitialAddress () {
        return _initialAddress;
    }

    public void setInitialAddress (String _initialAddress) {
        this._initialAddress = _initialAddress;
    }

    public String getTimeStampSend () {
        return _timeStampSend;
    }

    public void setTimeStampSend (String _timeStampSend) {
        this._timeStampSend = _timeStampSend;
    }

    public String getTimeStampReceived () {
        return _timeStampReceived;
    }

    public void setTimeStampReceived (String _timeStampReceived) {
        this._timeStampReceived = _timeStampReceived;
    }

    public String getResponseId () {
        return _responseId;
    }

    public void setResponseId (String _responseId) {
        this._responseId = _responseId;
    }

    public StringBuffer getCData () {
        return _cData;
    }

    public void setCData (StringBuffer _cData) {
        this._cData = _cData;
    }
}
