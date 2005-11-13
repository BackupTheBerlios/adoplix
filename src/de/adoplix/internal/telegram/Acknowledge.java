/*
 * LocalConnection.java
 *
 */

package de.adoplix.internal.telegram;
import de.adoplix.internal.runtimeInformation.AdopLog;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationKeyNotFoundException;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationTypeException;
import de.adoplix.internal.runtimeInformation.exceptions.MessageContentException;
import de.adoplix.internal.tools.xml.XMLRetriever;
import java.io.StringReader;
import java.util.logging.Logger;

/**
 * Container which stores connection informations from a local adapter
 * (ServiceAdapter, EventAdapter, AdminAdapter) when it contacts to the server
 * (PortAcceptor) first time.
 * @author dirkg
 */
public class Acknowledge extends XMLContainer {
    
    private Logger _logger = AdopLog.getLogger (Acknowledge.class);
    private int _result = 0;

    public Acknowledge () {
        
    }
    
    public Acknowledge (XMLRetriever retriever) throws MessageContentException {
        // super reads the header
        super(retriever);
        
        // read body
        try {
            retriever.setXMLObjectByKey (XMLMessageConstants.MSG_BODY, true);
            _result = retriever.toInt (retriever.getChild (XMLMessageConstants.RESULT).getValue ());
        }
        catch (ConfigurationKeyNotFoundException cknfEx) {
            throw new MessageContentException();
        }
        catch (ConfigurationTypeException ctEx) {
            _logger.warning(ctEx.getMessage ());
            throw new MessageContentException();
        }
    }
    
    public int getResult () {
        return _result;
    }

    public StringReader getXMLStringReader () {
        addToBody (XMLMessageConstants.RESULT, String.valueOf (_result).trim ());
        StringReader stringReader = super.getXMLStringReader ();
        return stringReader;
    }
}
