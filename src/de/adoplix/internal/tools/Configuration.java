package de.adoplix.internal.tools;

import de.adoplix.internal.runtimeInformation.constants.ErrorConstants;
import de.adoplix.internal.runtimeInformation.constants.ErrorConstantsText_Ger;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationKeyNotFoundException;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationTypeException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Auslesen von Konfigurationsdaten mit Hilfe des XMLObject. <br>
 * Funktionsweise: <br>
 * Ein Parameter wird im gesamten Baum der XMLObjects gesucht und dann der 
 * passende Wert ausgelesen.
 * @author dirkg
 */
public class Configuration extends XMLRetriever {
    
    /** 
     * Mit diesem Konstruktor wird Configuration veranlasst, eine Datei zu
     * parsen, und den Inhalt strukturiert in ein XMLObject zu schreiben.
     * @param xmlObject Ein (hoffentlich) noch leeres XMLObject
     * @param confFileName Die zu parsende Datei
     */
    public Configuration (String confFileName) {
        _xmlObject = new XMLObject();
        try {
            FileReader fReader = new FileReader(confFileName);
            XMLParser xmlParser = new XMLParser(fReader, _xmlObject);
            xmlParser.parse();
            fReader.close ();
            _xmlRootObject = _xmlObject;
        }
        catch (FileNotFoundException fnfEx) {
            System.out.println("ERROR " + ErrorConstants.CONFIGURATION_FILE_NOT_FOUND + //
                               ": " + ErrorConstantsText_Ger.CONFIGURATION_FILE_NOT_FOUND);
        }
        catch (IOException ioEx) {
            System.out.println("Problem bei Auslesen der Konfigurationsdatei");
        }
    }
    
    /**
     * Dieser Konstruktor unterstützt das Auslesen von Werten aus einem bereits
     * existierenden XMLObject.
     * Wird z.B. benutzt, wenn nach Substrukturen gesucht werden soll, die 
     * innerhalb eines Zweiges mehrfach vorkommen (Arrays).
     * @param xmlObject Das Objekt, das die Substruktur beinhaltet.
     */
    public Configuration (XMLObject xmlObject){
        super(xmlObject);
    }
    
    public int toInt (String intValue) throws ConfigurationTypeException {
    	try {
    		return Integer.parseInt(intValue);
    	}
    	catch(Exception ex) {
    		throw new ConfigurationTypeException();
    	}
    }
}
