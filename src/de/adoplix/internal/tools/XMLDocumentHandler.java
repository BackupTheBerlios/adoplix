package de.adoplix.tools;

import org.xml.sax.*;



public class XMLDocumentHandler implements ContentHandler {

    public void setDocumentLocator() {}
    public void setDocumentLocator(Locator loc) {}
    
    public void startDocument() {}
    public void endDocument(){}
    
    public void processingInstruction(String target, String data) throws SAXException {
        System.out.print("<?");
        System.out.print(target);
        if (data != null && data.length() > 0)
            System.out.print(" " + data);
        System.out.println("?>");
    }
    
    public void characters(char[] chars, int start, int length) throws SAXException {
        System.out.print(new String(chars, start, length));
    }
    
    public void ignorableWhitespace(char[] chars, int start, int length) throws SAXException {
        characters(chars, start, length);
    }
    
    public void startElement(String uri, String localName, String qName, Attributes attributeList) throws SAXException {
        System.out.print('<');
        System.out.print(uri + ":" + localName + ":" + qName);
        System.out.println();
        if (attributeList != null)
        {
            int length = attributeList.getLength();
            for (int i = 0; i < length; i++)
            {
                System.out.print(attributeList.getQName(i));
                System.out.print("=\"");
                System.out.print(attributeList.getValue(i));
                System.out.print("\" ");
                System.out.println();
            }

        }
        System.out.println(qName + ">");        
    }
        
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("</" + qName + ">");
    }

    public void startPrefixMapping(String a, String b) {}
    public void endPrefixMapping(String a) {}
    public void startElement() {}
    public void skippedEntity(String a) {}
    public void endElement() {}

}
