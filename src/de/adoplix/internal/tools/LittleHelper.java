/*
 * LittleHelper.java
 *
 *
 */

package de.adoplix.internal.tools;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationKeyNotFoundException;
import de.adoplix.internal.runtimeInformation.exceptions.MessageContentException;
import de.adoplix.internal.telegram.Acknowledge;
import de.adoplix.internal.telegram.ExternalEvent;
import de.adoplix.internal.telegram.ExternalResponse;
import de.adoplix.internal.telegram.Ping;
import de.adoplix.internal.telegram.Pong;
import de.adoplix.internal.telegram.XMLContainer;
import de.adoplix.internal.telegram.XMLMessageConstants;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.logging.Logger;
import de.adoplix.internal.runtimeInformation.AdopLog;
import de.adoplix.internal.runtimeInformation.constants.ErrorConstants;
import de.adoplix.internal.tools.xml.XMLObject;
import de.adoplix.internal.tools.xml.XMLRetriever;

/**
 *
 * @author dirk
 */
public class LittleHelper {
    
    private static Logger _logger = AdopLog.getLogger (LittleHelper.class);
    
    /** Creates a new instance of LittleHelper */
    public LittleHelper () {
    }

    /**
     * Takes a Byte-Stream (InputStream) and copies the content to a StringReader
     */
    public static synchronized StringReader streamToStringReader (InputStream dataInputStream) {
        int b;
        String dataInputString = "";
        
        try {
            BufferedInputStream bufferedStream = new BufferedInputStream (dataInputStream);
            while((b = bufferedStream.read ()) != -1 ) {
                dataInputString+= b;
            }
        } catch (IOException ioEx){
            dataInputString = "";
        }
        return new StringReader (dataInputString);
    }
    
    /**
     * Creates an Class which is derived from superclass XMLContainer. <br>
     * The container retrieves the properties by getters and setters. <br>
     * Simplifies the same method createXMLContainer with signature StringReader.
     * @param dataInputStream Is a InputStream which holds the XML-data
     * @return A subclass from XMLContainer, depending to the MsgType which is
     * named within the XML-code.
     */
    public static synchronized XMLContainer createXMLContainer (InputStream dataInputStream) throws MessageContentException {
        return createXMLContainer (streamToStringReader(dataInputStream));
    }
    
    /**
     * Creates an Class which is derived from superclass XMLContainer. <br>
     * The container retrieves the properties by getters and setters.
     * @param stringReader Is a buffer which holds the XML-data
     * @return A subclass from XMLContainer, depending to the MsgType which is
     * named within the XML-code.
     */
    public static synchronized XMLContainer createXMLContainer (StringReader stringReader) throws MessageContentException {
        String msgType = "";
        XMLObject xmlObject = null;
        XMLContainer xmlContainer = null;
        XMLRetriever retriever = new XMLRetriever (stringReader);
        try {
            retriever.setXMLObjectByKey (XMLMessageConstants.MSG_HEADER);
            msgType = retriever.getChild (XMLMessageConstants.MSG_TYPE).getValue ();
            
        } catch (ConfigurationKeyNotFoundException cknfEx) {
            throw new MessageContentException();
        }
        
        // the returned object depends to the msgtype.
        // The type was read above
        
//        if (msgType.equalsIgnoreCase (XMLMessageConstants.MSG_TYPE_LOCAL_CONNECTION)) {
//            xmlContainer = new LocalConnection (retriever);
//        }
        
        if (msgType.equalsIgnoreCase (XMLMessageConstants.MSG_TYPE_EVENT)) {
            xmlContainer = new ExternalEvent (retriever);
        }
        
        if (msgType.equalsIgnoreCase (XMLMessageConstants.MSG_TYPE_RESPONSE)) {
            xmlContainer = new ExternalResponse (retriever);
        }
        
        if (msgType.equalsIgnoreCase (XMLMessageConstants.MSG_TYPE_ACKNOWLEDGE)) {
            xmlContainer = new Acknowledge (retriever);
        }
        
        if (msgType.equalsIgnoreCase (XMLMessageConstants.MSG_TYPE_PING)) {
            xmlContainer = new Ping (retriever);
        }
        
        if (msgType.equalsIgnoreCase (XMLMessageConstants.MSG_TYPE_PONG)) {
            xmlContainer = new Pong (retriever);
        }
        
        return xmlContainer;
    }
    
}
