package de.adoplix.internal.tools;

import de.adoplix.internal.runtimeInformation.constants.ErrorConstants;
import de.adoplix.internal.runtimeInformation.constants.ErrorConstantsText_Ger;
import java.util.ArrayList;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationKeyNotFoundException;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Allgemeines Auslesen von Konfigurationsdaten mit Hilfe des XMLObject. <br>
 * Funktionsweise: <br>
 * Ein Parameter wird im gesamten Baum der XMLObjects gesucht und dann der 
 * passende Wert ausgelesen.
 * @author dirk
 */
public class Configuration {
    private XMLObject _xmlObject = null;
    
    /** Creates a new instance of Configuration */
    public Configuration (XMLObject xmlObject, String confFileName) {
        _xmlObject = xmlObject;
        
        try {
            FileReader fReader = new FileReader(confFileName);
            XMLParser xmlParser = new XMLParser(fReader, _xmlObject);
            xmlParser.parse();
        }
        catch (FileNotFoundException fnfEx) {
            System.out.println("ERROR " + ErrorConstants.CONFIGURATION_FILE_NOT_FOUND + //
                               ": " + ErrorConstantsText_Ger.CONFIGURATION_FILE_NOT_FOUND);
        }
        
    }
    
    public String readParam(String key) throws ConfigurationKeyNotFoundException {
    // ServerHandling.ServerId
        
        /* Beim naechsten Aufruf soll wieder ganz oben begonnen werden.
         * Daher das Original XMLObject sichern.
         */
        XMLObject xmlObject = _xmlObject;
        
        /*
         * Solange . ein XMLObject mit search...() suchen.
         * Wenn kein . mehr, den Wert mit get...() lesen.
         * Bsp.: ServerHandling.ServerId
         */
        try {
            while (key.indexOf (".") > -1) {
                int dotPos = key.indexOf (".");
                String preKey = key.substring (0, dotPos);
                key = key.substring(dotPos + 1);
                xmlObject = xmlObject.searchXMLSubObject(preKey);
            }
            xmlObject = xmlObject.getXMLSubObject (key);
            return xmlObject.getValue ();
        }            
        catch (Exception ex) {
            throw new ConfigurationKeyNotFoundException();
        }
    }
    
    public ArrayList readParamList(String key) {
        return new ArrayList();
    }
    
}
