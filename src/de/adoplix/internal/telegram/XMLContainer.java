package de.adoplix.internal.telegram;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationKeyNotFoundException;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationTypeException;
import de.adoplix.internal.runtimeInformation.exceptions.MessageContentException;
import de.adoplix.internal.runtimeInformation.exceptions.MessageValueTypeException;
import de.adoplix.internal.runtimeInformation.AdopLog;
import de.adoplix.internal.tools.xml.XMLRetriever;
import java.io.StringReader;
import java.util.logging.Logger;

public class XMLContainer implements I_XMLContainer {
    private Logger logger = AdopLog.getLogger (XMLContainer.class);
    protected String _msgType = "";
    
    /**
     * Constructor for creating a blank container which is later used to
     * write XML-data-format.
     */
    public XMLContainer () {
        
    }

    /**
     * Constructor with an XML-object-retriever. Takes the xml-formatted data
     * and stores it to container members.
     * @param retriever Is the class which contains prepared xml-data.
     */
    public XMLContainer (XMLRetriever retriever) {
        try {
            retriever.setXMLRootObject ();
            retriever.setXMLObjectByKey (XMLMessageConstants.MSG_HEADER);
            _msgType = retriever.getChild (XMLMessageConstants.MSG_TYPE).getValue ();
        }
        catch (ConfigurationKeyNotFoundException cknfEx) {
            logger.warning (new MessageContentException().getMessage ());
        }
    }

    /**
     * Delivers the type of this message.
     * @return The type of the message which is stored in this container.
     */
    public String getMsgType () {
        return _msgType;
    }
    
    /**
     * Creates a StringReader which delivers the message content in xml-format.
     * @return XML formatted buffered data
     */
    public StringReader createStringReader() {
        return new StringReader("");
    }
    
    /**
     * The XMLContainers differ by their message types.
     * @param type Contains the type of the message in this container.
     */
    public void setMsgType (String type) {
        _msgType = type;
    }
}