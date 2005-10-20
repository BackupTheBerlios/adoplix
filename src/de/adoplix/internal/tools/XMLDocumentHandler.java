package de.adoplix.internal.tools;

import org.xml.sax.*;


public class XMLDocumentHandler implements ContentHandler {
    private XMLObject _xmlObject = null;
    
    public XMLDocumentHandler(XMLObject xmlObject) {
        super();
        _xmlObject = xmlObject;
    }
    
    public void setDocumentLocator () {}
    public void setDocumentLocator (Locator loc) {}
    
    public void startDocument () {}
    public void endDocument () {}
    
    public void processingInstruction (String target, String data) throws SAXException {
        _xmlObject.setTarget(target);
        if (data != null && data.length () > 0) {
            _xmlObject.setData(data);
        }
    }
    
    public void characters (char[] chars, int start, int length) throws SAXException {
        System.out.print (new String (chars, start, length));
    }
    
    public void ignorableWhitespace (char[] chars, int start, int length) throws SAXException {
        characters (chars, start, length);
    }
    
    public void startElement (String uri, String localName, String qName, Attributes attributeList) throws SAXException {
        XMLElement xmlElement = new XMLElement(uri, qName, localName, attributeList);
        _xmlObject.add(xmlElement);
    }
    
    public void endElement (String uri, String localName, String qName) throws SAXException {
        System.out.println ("</" + qName + ">");
    }
    
    public void startPrefixMapping (String a, String b)
    {}
    public void endPrefixMapping (String a)
    {}
    public void startElement ()
    {}
    public void skippedEntity (String a)
    {}
    public void endElement ()
    {}
}
