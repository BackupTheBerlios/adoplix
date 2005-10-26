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
    
    /** 
     * Mit diesem Konstruktor wird Configuration veranlasst, eine Datei zu
     * parsen, und den Inhalt strukturiert in ein XMLObject zu schreiben.
     * @param xmlObject Ein (hoffentlich) noch leeres XMLObject
     * @param confFileName Die zu parsende Datei
     */
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
    
    /**
     * Dieser Konstruktor unterstützt das Auslesen von Werten aus einem bereits
     * existierenden XMLObject.
     * Wird z.B. benutzt, wenn nach Substrukturen gesucht werden soll, die 
     * innerhalb eines Zweiges mehrfach vorkommen (Arrays).
     * @param xmlObject Das Objekt, das die Substruktur beinhaltet.
     */
    public Configuration (XMLObject xmlObject) {
        _xmlObject = xmlObject;
    }
    
    private XMLObject getParentXMLObject (String key) throws ConfigurationKeyNotFoundException {
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
            return xmlObject;
        }            
        catch (Exception ex) {
            throw new ConfigurationKeyNotFoundException();
        }
    }
    
    private String getKeySuffix(String key) throws ConfigurationKeyNotFoundException {
        try {
            while (key.indexOf (".") > -1) {
                int dotPos = key.indexOf (".");
                key = key.substring(dotPos + 1);
            }
        }            
        catch (Exception ex) {
            throw new ConfigurationKeyNotFoundException();
        }
        return key;
    }
    
    public String readParam(String key) throws ConfigurationKeyNotFoundException {
        try {
            XMLObject xmlObject = getParentXMLObject(key);
            xmlObject = xmlObject.getXMLSubObject (getKeySuffix(key));
            return xmlObject.getValue ();
        }            
        catch (Exception ex) {
            throw new ConfigurationKeyNotFoundException();
        }
    }
    
    public String readParamFromList(String key, int index) throws ConfigurationKeyNotFoundException {
        try {
            XMLObject xmlObject = getParentXMLObject(key);
            ArrayList xmlObjectList = xmlObject.getXMLSubObjectList (getKeySuffix(key));
            
            xmlObject = (XMLObject)xmlObjectList.get (index);
            return xmlObject.getValue ();
        }            
        catch (Exception ex) {
            throw new ConfigurationKeyNotFoundException();
        }
    }
    
}
