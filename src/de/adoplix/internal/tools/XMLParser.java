package de.adoplix.tools;

import java.io.*;

import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;

public class XMLParser {

    private StringReader _inputStreamString = null;
    private FileReader _inputStreamFile = null;

    public static void main(String[] args) {
        try {
            String fileName = "/home/dgo01/workspace/uebungen/testxml.xml";
            XMLParser myTest = new XMLParser();
            FileReader fReader = new FileReader(fileName);
            myTest.setInputStream(fReader);
            myTest.parse();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setInputStream(StringReader inputStreamString) {
        _inputStreamString = inputStreamString;
    }

    public void setInputStream(FileReader inputStreamFile) {
        _inputStreamFile = inputStreamFile;
    }

    public void parse() {
        InputSource inputSource = null;
        XMLDocumentHandler xmlDocumentHandler = new XMLDocumentHandler();

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
            reader.parse(inputSource);
            reader.setContentHandler(xmlDocumentHandler);

        } catch (IOException ioEx) {
            System.err.println("IO Fehler beim parsen: ");
            System.err.println(ioEx);
        } catch (SAXException saxEx) {
            System.err.println("SAX Fehler beim parsen: ");
            System.err.println(saxEx);
        }
    }
}
