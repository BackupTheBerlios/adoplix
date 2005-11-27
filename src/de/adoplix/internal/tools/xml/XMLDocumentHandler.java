package de.adoplix.internal.tools.xml;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 * Ist die Klasse, die vom Parser verwendet wird, um XML-Inhalte abzulegen.
 * Hier wird die Hierarchie der XMLObjects aufgebaut.
 * Der Parser verwendet die von ContentHandler abgeleiteten Methoden als CallBack
 * Funktionen, die er bei Auftreten eines Ereignisses aufruft.
 * @author dirkg
 */
public class XMLDocumentHandler implements ContentHandler {
    
    private XMLObject _xmlObject = null;
    
    /**
     * Konstruktor.
     * @param xmlObject ist das 'oberste' XMLObject in der Hierarchie aller
     * XMLObjects (innerhalb einer Anwendung)
     */
    public XMLDocumentHandler(XMLObject xmlObject) {
        super();
//        System.out.println("Konstruktor");
        _xmlObject = xmlObject;
    }
    
    public void setDocumentLocator () {}
    public void setDocumentLocator (Locator loc) {}
    
    /**
     * Das Parsen des Dokuments beginnt.
     */
    public void startDocument () {
//        System.out.println("startDocument");
    }
    
    /**
     * Parsen ist abgeschlossen.
     */
    public void endDocument () {}
    
    /**
     * Eine Instruktion wurde gefunden.
     * Instruktionen dienen dem Aufruf von entferntem Code ;-)
     * @param target Ist die Ziel-Anwendung
     * @param data Sind die Daten der Zielanwendung (unformatiert).
     */
    public void processingInstruction (String target, String data) throws SAXException {
        _xmlObject.setTarget(target);
//        System.out.println("XMLDoc processingInstruction: " + target);
        if (data != null && data.length () > 0) {
            _xmlObject.setData(data);
        }
    }
    
    /**
     * Wird vom Parser aufgerufen, wenn eine Zeichenkette gefunden wurde.
     * Das passiert i.d.R. zwischen startElement() und endElement().
     * @param chars Ist die gesamte Datei (glaube ich)
     * @param start Zeigt an, wo die fuer das aktuelle Element interessanten
     * Character innerhalb des char-Arrays beginnen
     * @param length Gibt die Anzahl Zeichen ab start an, die interessant sind
     */
    public void characters (char[] chars, int start, int length) throws SAXException {
        _xmlObject.setValue(new String (chars, start, length).trim());
//        System.out.println ("XMLDoc characters: " + new String (chars, start, length));
    }
    
    public void ignorableWhitespace (char[] chars, int start, int length) throws SAXException {
        characters (chars, start, length);
    }
    
    /**
     * Ein neues Element wurde gefunden.
     * Die Methode legt ein neues XMLObject an und fuegt es dem aktuellen
     * (zuletzt instanziiertes) hinzu.
     * @param uri ???
     * @param localName ???
     * @param qName ???
     * @param attributeList Liste aller Attribute des gerade gefundenen Elements
     */
    public void startElement (String uri, String localName, String qName, Attributes attributeList) throws SAXException {
//        XMLElement xmlElement = new XMLElement(uri, qName, localName, attributeList);
        XMLObject xmlObject = new XMLObject(uri, qName, localName, attributeList, _xmlObject);
        _xmlObject.addXMLSubObject(xmlObject);
        _xmlObject = xmlObject;
//        System.out.println ("XMLDoc StartElement: <" + qName + ">");
    }
    
    /**
     * Zeigt den Abschluss eines Elements an.
     * Nun wird das Eltern-Objekt rausgefischt, um die Bearbeitung dort
     * fortzusetzen.
     * @param uri ???
     * @param localName ???
     * @param qName ???
     */
    public void endElement (String uri, String localName, String qName) throws SAXException {
//        System.out.println ("XMLDoc EndElement: </" + qName + ">");
        _xmlObject = _xmlObject.getParent();
    }
    
    public void startPrefixMapping (String a, String b) {
//        System.out.println("startPrefixMapping");
    }
    public void endPrefixMapping (String a) {
//        System.out.println("endPrefixMapping");
    }
    public void startElement () {
//        System.out.println("startElement");
    }
    public void skippedEntity (String a) {}
    public void endElement () {
//        System.out.println("endElement");
    }
}
