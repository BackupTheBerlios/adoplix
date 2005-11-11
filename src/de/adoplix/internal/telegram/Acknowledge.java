/*
 * LocalConnection.java
 *
 */

package de.adoplix.internal.telegram;
import de.adoplix.internal.runtimeInformation.exceptions.MessageContentException;
import de.adoplix.internal.tools.xml.XMLRetriever;
import java.io.StringReader;

/**
 * Container which stores connection informations from a local adapter
 * (ServiceAdapter, EventAdapter, AdminAdapter) when it contacts to the server
 * (PortAcceptor) first time.
 * @author dirkg
 */
public class Acknowledge extends XMLContainer {
    
    private int _result = 0;

    public Acknowledge () {
        
    }
    
    public Acknowledge (XMLRetriever retriever) throws MessageContentException {
        super(retriever);
    }
    
    public int getResult () {
        return _result;
    }

    public StringReader createStringReader() {
        addToHeader (XMLMessageConstants.RESULT, String.valueOf (_result));
        StringReader stringReader = super.createStringReader ();
        return stringReader;
    }
}
