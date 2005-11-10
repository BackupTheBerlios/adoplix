/*
 * XMLMessage.java
 *
 */

package de.adoplix.internal.telegram;

import de.adoplix.internal.runtimeInformation.constants.ErrorConstants;
import de.adoplix.internal.runtimeInformation.constants.ErrorConstantsText_Ger;
import de.adoplix.internal.tools.xml.XMLObject;
import de.adoplix.internal.tools.xml.XMLParser;
import de.adoplix.internal.tools.xml.XMLRetriever;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;

/**
 *
 * @author dirk
 */
public class XMLMessage extends XMLRetriever {
    private I_XMLContainer _xmlContainer;
    
    /** 
     * Mit diesem Konstruktor wird XMLMessage veranlasst, die XML-Daten zu parsen.
     * @param stringReader Stream mit den zu parsenden Daten
     */
    public XMLMessage (StringReader stringReader) {
//        super(stringReader);        
        // next step: look what kind of adoplix-Telegram it is
        // and wrap it into a container
    }

}
