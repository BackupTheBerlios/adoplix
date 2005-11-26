/*
 * LittleHelper.java
 *
 *
 */

package de.adoplix.internal.tools;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.logging.Logger;

import de.adoplix.internal.runtimeInformation.AdopLog;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationKeyNotFoundException;
import de.adoplix.internal.runtimeInformation.exceptions.MessageContentException;
import de.adoplix.internal.telegram.Acknowledge;
import de.adoplix.internal.telegram.AdminFunction;
import de.adoplix.internal.telegram.ExternalEvent;
import de.adoplix.internal.telegram.ExternalResponse;
import de.adoplix.internal.telegram.Ping;
import de.adoplix.internal.telegram.Pong;
import de.adoplix.internal.telegram.XMLContainer;
import de.adoplix.internal.telegram.XMLMessageConstants;
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
    
    public static synchronized StringReader streamBufferToStringReader(BufferedReader dataInputBuffer) {
        String dataInputString = "";
        try {
            dataInputString = dataInputBuffer.readLine ();
        } catch (IOException ioEx){
            dataInputString = "";
        }
        return new StringReader(dataInputString);
    }

    public static synchronized String streamToString (InputStream dataInputStream) {
        String dataInputString = "";
        try {
        BufferedReader in = new BufferedReader(new InputStreamReader( dataInputStream) );
        String x = "";
        while ((x = in.readLine()).length() > 0) {
            dataInputString+=x;
//            System.out.println(x);
//            System.out.println(dataInputString);
        }
        } catch (IOException ioEx){
            System.out.println(ioEx.getMessage());
            dataInputString = "";
        }
        return dataInputString;
    }
    
    /**
     * Takes a Byte-Stream (InputStream) and copies the content to a StringReader
     */
    public static synchronized StringReader streamToStringReader (InputStream dataInputStream) {
        String x="";
        String dataInputString = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(dataInputStream));
        BufferedInputStream bi = new BufferedInputStream(dataInputStream);
        
        try {
            int available = bi.available ();
            char[] characters = new char[available];
            int count = br.read (characters);
            System.out.println("Anzahl Zeichen "+ count);
            dataInputString = new String(characters);
//            while( (x= br.readLine()) != null ) {
//                dataInputString+=x;
//            }
        } catch (IOException ioEx){
            int a = 1;
        }
        return new StringReader (dataInputString);
    }
    
//    public static synchronized XMLContainer createXMLContainer (String dataInputString) throws MessageContentException {
//        String msgType = "";
//        XMLContainer xmlContainer = null;
//        XMLRetriever retriever = new XMLRetriever (dataInputString);
//        try {
//            _logger.finest("Versuche Zugriff auf Header...");
//            retriever.setXMLObjectByKey (XMLMessageConstants.MSG_HEADER);
//            msgType = retriever.getChild (XMLMessageConstants.MSG_TYPE).getValue ();
//            
//        } catch (ConfigurationKeyNotFoundException cknfEx) {
//            _logger.warning("Zugriff auf Logger fehlgeschlagen...");
//            throw new MessageContentException();
//        }
//        
//        // the returned object depends to the msgtype.
//        // The type was read above
//        
////        if (msgType.equalsIgnoreCase (XMLMessageConstants.MSG_TYPE_LOCAL_CONNECTION)) {
////            xmlContainer = new LocalConnection (retriever);
////        }
//        
//        if (msgType.equalsIgnoreCase (XMLMessageConstants.MSG_TYPE_EVENT)) {
//            xmlContainer = new ExternalEvent (retriever);
//        }
//        
//        if (msgType.equalsIgnoreCase (XMLMessageConstants.MSG_TYPE_RESPONSE)) {
//            xmlContainer = new ExternalResponse (retriever);
//        }
//        
//        if (msgType.equalsIgnoreCase (XMLMessageConstants.MSG_TYPE_ACKNOWLEDGE)) {
//            xmlContainer = new Acknowledge (retriever);
//        }
//        
//        if (msgType.equalsIgnoreCase (XMLMessageConstants.MSG_TYPE_PING)) {
//            xmlContainer = new Ping (retriever);
//        }
//        
//        if (msgType.equalsIgnoreCase (XMLMessageConstants.MSG_TYPE_PONG)) {
//            xmlContainer = new Pong (retriever);
//        }
//        
//        return xmlContainer;
//    }
    
    public static synchronized XMLContainer createXMLContainer (BufferedReader dataInputBuffer) throws MessageContentException {
        return createXMLContainer (streamBufferToStringReader(dataInputBuffer));
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
//        return createXMLContainer (new StringReader(streamToString(dataInputStream)));
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
        XMLContainer xmlContainer = null;
        XMLRetriever retriever = new XMLRetriever (stringReader);
//        XMLRetriever retriever = new XMLRetriever (stringReader.toString());
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
        
        if (msgType.equalsIgnoreCase(XMLMessageConstants.MSG_TYPE_FUNCTION)) {
            xmlContainer = new AdminFunction(retriever);
        }
        
        return xmlContainer;
    }
    
}
