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
    protected String _timeStampSend = "";
    protected int _acknInitiator = 0;
    protected int _awaitingResponse = 0;
    private String _newMsg = "";
    private String _newMsgHeader = "";
    private String _newMsgBody = "";
    private String _taskId = null;
    
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
        // Header is for every adoplix-messages the same
        try {
            retriever.setXMLObjectByKey (XMLMessageConstants.MSG_HEADER, true);
            _msgType = retriever.getChild (XMLMessageConstants.MSG_TYPE).getValue ();
            _timeStampSend = retriever.getChild (XMLMessageConstants.TIME_STAMP_SEND).getValue ();
            _acknInitiator = retriever.toInt (retriever.getChild (XMLMessageConstants.ACKN_INITIATOR).getValue ());
            _awaitingResponse = retriever.toInt (retriever.getChild (XMLMessageConstants.AWAITING_RESPONSE).getValue ());
            
        }
        catch (ConfigurationKeyNotFoundException cknfEx) {
            logger.warning (new MessageContentException().getMessage ());
        }
        catch (ConfigurationTypeException ctEx) {
            logger.warning (new MessageValueTypeException().getMessage ());
        }
        
        // TaskId is not set by all types of message.
        // But here it is a good place to look for.
        // If the taskId is not found, the member will be set to <null>
        try {
            retriever.setXMLObjectByKey (XMLMessageConstants.MSG_BODY, true);
            _taskId = retriever.getChild (XMLMessageConstants.TASK_ID).getValue ();
        }
        catch (Throwable th) {}
    }

    /**
     * Delivers the type of this message.
     * @return The type of the message which is stored in this container.
     */
    public String getMsgType () {
        return _msgType;
    }
    
    /**
     * Delivers the TaskId of a message. <br>
     * The TaskId is only set when the message is a kind of event (event or
     * response). <br>
     * When the message is of another kind, the TaskId can be <null> !.
     * @return TaskId or <null>
     */
    public String getTaskId () throws ConfigurationKeyNotFoundException {
        if (null== _taskId) {
            throw new ConfigurationKeyNotFoundException();
        }
        return _taskId;
    }
    
    /**
     * Creates a StringReader which delivers the message content in xml-format.
     * @return XML formatted buffered data
     */
    public StringReader getXMLStringReader() {
        _newMsg="<" + XMLMessageConstants.ADOPLIX_MESSAGE + ">";
        _newMsg+= "\n<" + XMLMessageConstants.MSG_HEADER + ">";
        _newMsg+= "\n<" + XMLMessageConstants.MSG_TYPE + ">" + _msgType + "</" + XMLMessageConstants.MSG_TYPE + ">";
        _newMsg+= "\n<" + XMLMessageConstants.ACKN_INITIATOR + ">" + String.valueOf(_acknInitiator).trim () + "</" + XMLMessageConstants.ACKN_INITIATOR + ">";
        _newMsg+= "\n<" + XMLMessageConstants.AWAITING_RESPONSE + ">" + String.valueOf(_msgType).trim () + "</" + XMLMessageConstants.AWAITING_RESPONSE + ">";
        _newMsg+= _newMsgHeader;
        _newMsg+= "\n</" + XMLMessageConstants.MSG_HEADER + ">";
        _newMsg+= "\n<" + XMLMessageConstants.MSG_BODY + ">";
        _newMsg+= _newMsgBody;
        _newMsg+= "\n</" + XMLMessageConstants.MSG_BODY + ">";
        _newMsg+= "\n</" + XMLMessageConstants.ADOPLIX_MESSAGE + ">";
        
        return new StringReader(_newMsg);
    }
    
    /**
     * The XMLContainers differ by their message types.
     * @param type Contains the type of the message in this container.
     */
    public void setMsgType (String type) {
        _msgType = type;
    }
    
    public int getAcknInitiator() {
        return _acknInitiator;
    }
    
    public int getAwaitingResponse () {
        return _awaitingResponse;
    }
    
    public boolean acknByServer () {
        return (1 == _acknInitiator);
    }
    
    public boolean acknByAdapter () {
        return (2 == _acknInitiator);
    }
    
    public boolean awaitingResponse () {
        return (1 ==_awaitingResponse);
    }
    
    public String getXMLString() {
        return _newMsg;
    }
    
    protected void addToHeader (String elementName, String elementValue){
        _newMsgHeader+= "\n<" + elementName + ">" + elementValue + "</" + elementName + ">";
    }
    
    protected void addToBody (String elementName, String elementValue) {
        _newMsgBody+= "\n<" + elementName + ">" + elementValue + "</" + elementName + ">";
    }
}