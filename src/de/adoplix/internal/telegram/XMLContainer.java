package de.adoplix.internal.telegram;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

import de.adoplix.internal.runtimeInformation.AdopLog;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationKeyNotFoundException;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationTypeException;
import de.adoplix.internal.runtimeInformation.exceptions.MessageContentException;
import de.adoplix.internal.runtimeInformation.exceptions.MessageValueTypeException;
import de.adoplix.internal.tools.xml.XMLRetriever;

public class XMLContainer implements I_XMLContainer {
    private Logger logger = AdopLog.getLogger(XMLContainer.class);
    protected String _msgType = "";
    private String _timeStampSend = "DD-MM-YYYY.hh:mm:ss:SSS";
    protected int _acknInitiator = 0;
    protected int _awaitingResponse = 0;
    private String _newMsg = "";
    private String _newMsgHeader = "";
    private String _newMsgBody = "";
    private String _newCData = "";
    private String _taskId = null;
    private Map _cDataList = new HashMap();

    /**
     * Constructor for creating a blank container which is later used to write
     * XML-data-format.
     */
    public XMLContainer() {

    }

    /**
     * Constructor with an XML-object-retriever. Takes the xml-formatted data
     * and stores it to container members.
     * 
     * @param retriever
     *            Is the class which contains prepared xml-data.
     */
    public XMLContainer(XMLRetriever retriever) {
        // Header is for every adoplix-messages the same
        try {
            retriever.setXMLObjectByKey(XMLMessageConstants.MSG_HEADER, true);
            _msgType = retriever.getChild(XMLMessageConstants.MSG_TYPE).getValue();
            setTimeStampSend(retriever.getChild(XMLMessageConstants.TIME_STAMP_SEND).getValue());
            _acknInitiator = retriever.toInt(retriever.getChild(XMLMessageConstants.ACKN_INITIATOR).getValue());
            _awaitingResponse = retriever.toInt(retriever.getChild(XMLMessageConstants.AWAITING_RESPONSE).getValue());

        } catch (ConfigurationKeyNotFoundException cknfEx) {
            logger.warning(new MessageContentException().getMessage());
        } catch (ConfigurationTypeException ctEx) {
            logger.warning(new MessageValueTypeException().getMessage());
        }

        // TaskId is not set by all types of message.
        // But here it is a good place to look for.
        // If the taskId is not found, the member will be set to <null>
        try {
            retriever.setXMLObjectByKey(XMLMessageConstants.MSG_BODY, true);
            _taskId = retriever.getChild(XMLMessageConstants.TASK_ID).getValue();
        } catch (Throwable th) {
            logger.fine(th.getMessage());
        }

        try {
            // CDATA
            retriever.setXMLObjectByKey(XMLMessageConstants.MSG_BODY, true);
            // setCData(retriever.getChild
            // (XMLMessageConstants.CDATA_BEGIN).getValue ());

            // XML part of cdata in new 'container'
            XMLRetriever cDataRetriever = new XMLRetriever(new StringReader(retriever.getElementValue()));
            cDataRetriever.setXMLObjectByKey(XMLMessageConstants.CDATA_CONTENT, true);
            for (int i = 0; i < cDataRetriever.countChildren(); i++) {
                String[] cDataEntry = new String[2];
                cDataEntry[0] = cDataRetriever.getChild(i).getLName();
                cDataEntry[1] = cDataRetriever.getChild(i).getValue();
                _cDataList.put(cDataEntry[0], cDataEntry);
            }
        } catch (Throwable th) {
            logger.fine(th.getMessage());
        }
    }

    /**
     * Delivers the type of this message.
     * 
     * @return The type of the message which is stored in this container.
     */
    public String getMsgType() {
        return _msgType;
    }

    /**
     * Delivers the TaskId of a message. <br>
     * The TaskId is only set when the message is a kind of event (event or
     * response). <br>
     * When the message is of another kind, the TaskId can be <null> !.
     * 
     * @return TaskId or <null>
     */
    public String getTaskId() throws ConfigurationKeyNotFoundException {
        if (null == _taskId) {
            throw new ConfigurationKeyNotFoundException();
        }
        return _taskId;
    }

    private void prepareXMLString() {
        _newMsg="";
        _newMsgBody="";
        _newCData="";
        
        // header always has the same structure
        _newMsg = "<" + XMLMessageConstants.ADOPLIX_MESSAGE + ">";
        _newMsg += "\n<" + XMLMessageConstants.MSG_HEADER + ">";
        _newMsg += "\n<" + XMLMessageConstants.MSG_TYPE + ">" + _msgType + "</" + XMLMessageConstants.MSG_TYPE + ">";
        _newMsg += "\n<" + XMLMessageConstants.TIME_STAMP_SEND + ">" + String.valueOf(getTimeStampSend()).trim() + "</" + XMLMessageConstants.TIME_STAMP_SEND + ">";
        _newMsg += "\n<" + XMLMessageConstants.ACKN_INITIATOR + ">" + String.valueOf(_acknInitiator).trim() + "</" + XMLMessageConstants.ACKN_INITIATOR + ">";
        _newMsg += "\n<" + XMLMessageConstants.AWAITING_RESPONSE + ">" + String.valueOf(_awaitingResponse).trim() + "</" + XMLMessageConstants.AWAITING_RESPONSE + ">";
        _newMsg += _newMsgHeader;
        _newMsg += "\n</" + XMLMessageConstants.MSG_HEADER + ">";

        // CData always is part of body
        if (_cDataList.size() > 0) {
            Collection col = _cDataList.values();
            Object[] cDataArray = col.toArray();
            _newMsgBody += "\n<" + XMLMessageConstants.CDATA_BEGIN + ">";
            _newMsgBody += "\n<" + XMLMessageConstants.CDATA_CONTENT + ">";
            for (int i = 0; i < cDataArray.length; i++) {
                String[] cDataEntry = (String[]) cDataArray[i];
                _newCData += "\n<" + cDataEntry[0] + ">" + cDataEntry[1] + "</" + cDataEntry[0] + ">";
            }
            _newMsgBody += _newCData;
            _newMsgBody += "\n</" + XMLMessageConstants.CDATA_CONTENT + ">";
            _newMsgBody += "\n</" + XMLMessageConstants.CDATA_END + ">";
        }

        // add body to msg
        _newMsg += "\n<" + XMLMessageConstants.MSG_BODY + ">";
        _newMsg += _newMsgBody;
        _newMsg += "\n</" + XMLMessageConstants.MSG_BODY + ">";

        // close complete structure
        _newMsg += "\n</" + XMLMessageConstants.ADOPLIX_MESSAGE + ">";
    }

    /**
     * Creates a StringReader which delivers the message content in xml-format.
     * 
     * @return XML formatted buffered data
     */
    public StringReader getXMLStringReader() {
        prepareXMLString();
        return new StringReader(_newMsg);
    }

    /**
     * Retrieves a String which contains the XML-formatted data
     * 
     * @return XML formatted String
     */
    public String getXMLString() {
        prepareXMLString();
        return _newMsg;
    }

    /**
     * The XMLContainers differ by their message types.
     * 
     * @param type
     *            Contains the type of the message in this container.
     */
    public void setMsgType(String type) {
        _msgType = type;
    }

    public int getAcknInitiator() {
        return _acknInitiator;
    }

    public int getAwaitingResponse() {
        return _awaitingResponse;
    }

    public boolean acknByServer() {
        return (1 == _acknInitiator);
    }

    public boolean acknByAdapter() {
        return (2 == _acknInitiator);
    }

    public boolean awaitingResponse() {
        return (1 == _awaitingResponse);
    }

    protected void addToHeader(String elementName, String elementValue) {
        _newMsgHeader += "\n<" + elementName + ">" + elementValue + "</"
                + elementName + ">";
    }

    protected void addToBody(String elementName, String elementValue) {
        _newMsgBody += "\n<" + elementName + ">" + elementValue + "</"
                + elementName + ">";
    }

    protected void setCDataEntry(String elementName, String elementValue) {
        String[] cDataEntry = new String[2];
        cDataEntry[0] = elementName;
        cDataEntry[1] = elementValue;
        _cDataList.put(elementName, cDataEntry);
    }

    protected void addToCData(String content) {
        _newCData += content;
    }

    public String getCDataEntry(String elementName) {
        String[] cDataEntry = (String[]) _cDataList.get(elementName);
        try {
            return cDataEntry[1];
        } catch (NullPointerException npEx) {
            return "";
        }
    }
    
    public void setTimeStampSend() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy.hh:mm:ss:SSS", Locale.GERMAN);
        _timeStampSend = dateFormatter.format(new Date());
        
    }

    public void setTimeStampSend(String _timeStampSend) {
        this._timeStampSend = _timeStampSend;
    }

    public String getTimeStampSend() {
        return _timeStampSend;
    }
}