/*
 * XMLMessage.java
 *
 */

package de.adoplix.internal.telegram;

import de.adoplix.internal.runtimeInformation.constants.ErrorConstants;
import de.adoplix.internal.runtimeInformation.constants.ErrorConstantsText_Ger;
import de.adoplix.internal.tools.XMLObject;
import de.adoplix.internal.tools.XMLParser;
import de.adoplix.internal.tools.XMLRetriever;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;

/**
 *
 * @author dirk
 */
public class XMLMessage extends XMLRetriever {
    
    /** 
     * Mit diesem Konstruktor wird Configuration veranlasst, eine Datei zu
     * parsen, und den Inhalt strukturiert in ein XMLObject zu schreiben.
     * @param confFileName Die zu parsende Datei
     */
    public XMLMessage (StringReader stringReader) {
        _xmlObject = new XMLObject();
        XMLParser xmlParser = new XMLParser(stringReader, _xmlObject);
        xmlParser.parse();
        _xmlRootObject = _xmlObject;
    }
    
}
