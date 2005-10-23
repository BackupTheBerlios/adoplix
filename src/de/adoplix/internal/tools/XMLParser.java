package de.adoplix.internal.tools;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


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
            
            for (int i = 0; i < xmlObject.getXMLElementList ().size (); i++) {
                XMLElement xmlElement = xmlObject.getXMLElement (i);
                System.out.println("QName: " + xmlElement.getQName ());
                for (int y = 0; y < xmlElement.getAttributes ().getLength (); y++) {
                    System.out.println("Element: " + xmlElement.getAttributes ().getQName (y));
                    System.out.println("Wert:    " + xmlElement.getAttributes ().getValue (y));
                }
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public XMLParser() {
      System.out.println("a�jjfsdkjfa�klj");
    }
    
    public XMLParser (StringReader inputStreamString, XMLObject xmlObject) {
        _inputStreamString = inputStreamString;
        _xmlObject = xmlObject;
    }
    
    public XMLParser (FileReader inputStreamFile, XMLObject xmlObject) {
        _inputStreamFile = inputStreamFile;
        _xmlObject = xmlObject;
    }
    
    public void parse() {
        InputSource inputSource = null;
        XMLDocumentHandler xmlDocumentHandler = new XMLDocumentHandler(_xmlObject);
        xmlDocumentHandler.setDocumentLocator ();

        try {
            // org.xml.sax.Parser
            // XMLReader reader = XMLReaderFactory.createXMLReader("org.xml.sax.Parser");
            XMLReader reader = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
            // XMLReader reader = XMLReaderFactory.createXMLReader();
            
            if (_inputStreamString != null) {
                inputSource = new InputSource(_inputStreamString);
            } else {
                inputSource = new InputSource(_inputStreamFile);
            }
            reader.setContentHandler(xmlDocumentHandler);
            reader.parse(inputSource);
//            reader.setContentHandler(xmlDocumentHandler);

        } catch (IOException ioEx) {
            System.err.println("IO Fehler beim parsen: ");
            System.err.println(ioEx);
        } catch (SAXException saxEx) {
            System.err.println("SAX Fehler beim parsen: ");
            System.err.println(saxEx);
        }
        
    }
}
