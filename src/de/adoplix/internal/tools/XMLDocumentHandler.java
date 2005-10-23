package de.adoplix.internal.tools;

import org.xml.sax.*;


public class XMLDocumentHandler implements ContentHandler {
    private XMLObject _xmlObject = null;
    
    public XMLDocumentHandler(XMLObject xmlObject) {
        super();
        System.out.println("Konstruktor");
        _xmlObject = xmlObject;
    }
    
    public void setDocumentLocator () {}
    public void setDocumentLocator (Locator loc) {}
    
    public void startDocument () {
        System.out.println("startDocument");
    }
    public void endDocument () {}
    
    public void processingInstruction (String target, String data) throws SAXException {
        _xmlObject.setTarget(target);
        if (data != null && data.length () > 0) {
            _xmlObject.setData(data);
        }
    }
    
    public void characters (char[] chars, int start, int length) throws SAXException {
        _xmlObject.setValue(new String (chars, start, length).trim());
        System.out.print (new String (chars, start, length));
    }
    
    public void ignorableWhitespace (char[] chars, int start, int length) throws SAXException {
        characters (chars, start, length);
    }
    
    public void startElement (String uri, String localName, String qName, Attributes attributeList) throws SAXException {
//        XMLElement xmlElement = new XMLElement(uri, qName, localName, attributeList);
        XMLObject xmlObject = new XMLObject(uri, qName, localName, attributeList, _xmlObject);
        _xmlObject.addXMLSubObject(xmlObject);
        _xmlObject = xmlObject;
        System.out.print ("</" + qName + ">");
    }
    
    public void endElement (String uri, String localName, String qName) throws SAXException {
        System.out.print ("</" + qName + ">");
        _xmlObject = _xmlObject.getParent();
    }
    
    public void startPrefixMapping (String a, String b) {
        System.out.println("startPrefixMapping");
    }
    public void endPrefixMapping (String a) {
        System.out.println("endPrefixMapping");
    }
    public void startElement () {
        System.out.println("startElement");
    }
    public void skippedEntity (String a)
    {}
    public void endElement () {
        System.out.println("endElement");
    }
}
