package de.adoplix.internal.tools.xml;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


/**
 * Dient dem generellen Parsen einfacher XML-Dateien.
 * Wird vorlaeufig nur fuer das Einlesen der Konfigurationsdateien verwendet.
 * @author dirkg
 */
public class XMLParser {

    private StringReader _inputStreamString = null;
    private FileReader _inputStreamFile = null;
    private XMLObject _xmlObject = null;

    public static void main(String[] args) {
        try {
            String fileName = "C:/Dokumente und Einstellungen/dirk/Eigene Dateien/Adoplix/Test/Configuration/testxml.xml";
            FileReader fReader = new FileReader(fileName);
            XMLObject  xmlObject = new XMLObject();
            XMLParser myTest = new XMLParser(fReader, xmlObject);
            myTest.parse();
            
//            xmlObject.rollOut ("");
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Dieser Konstruktor macht bisher keinen Sinn
     */
    public XMLParser() {
      System.out.println("aöjjfsdkjfaöklj");
    }
    
    /**
     * Konstruktor fuer die Bearbeitung von Daten, die nicht aus einer
     * Datei kommen.
     * @param inputStreamString XML-Daten z.B. von einer Anwendung
     * @param xmlObject Wird waehrend des Parsens mit Daten gefuellt und spaeter
     * vom aufrufenden Objekt verwendet
     */
    public XMLParser (StringReader inputStreamString, XMLObject xmlObject) {
        _inputStreamString = inputStreamString;
        _xmlObject = xmlObject;
    }
    
    /**
     * Konstruktor fuer die Bearbeitung von Daten, die aus einer Datei kommen.
     * @param inputStreamFile XML-Daten aus einer Datei
     * @param xmlObject Wird waehrend des Parsens mit Daten gefuellt
     * @see XMLObject
     */
    public XMLParser (FileReader inputStreamFile, XMLObject xmlObject) {
        _inputStreamFile = inputStreamFile;
        _xmlObject = xmlObject;
    }
    
    /**
     * Veranlasst das Parsen der XML-Daten.
     * Verwendet die Klasse XMLDocumentHandler zur 'Verwaltung' der geparsten
     * Element.
     * @see XMLDocumentHandler
     */
    public void parse() {
        InputSource inputSource = null;
        XMLDocumentHandler xmlDocumentHandler = new XMLDocumentHandler(_xmlObject);
        xmlDocumentHandler.setDocumentLocator ();

        try {
            XMLReader reader = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
            
            if (_inputStreamString != null) {
                inputSource = new InputSource(_inputStreamString);
            } else {
                inputSource = new InputSource(_inputStreamFile);
            }
            reader.setContentHandler(xmlDocumentHandler);
            reader.parse(inputSource);

        } catch (IOException ioEx) {
            System.err.println("IO Fehler beim parsen: ");
            System.err.println(ioEx);
        } catch (SAXException saxEx) {
            System.err.println("SAX Fehler beim parsen: ");
            System.err.println(saxEx);
        }
    }
}
