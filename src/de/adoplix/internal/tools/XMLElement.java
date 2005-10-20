package de.adoplix.internal.tools;

import org.xml.sax.Attributes;

/**
 *
 * @author  dirk
 */
public class XMLElement {
    
    private String _elementQName = null;
    private String _elementLName = null;
    private String _elementUri = null;
    private Attributes _attributeList = null;
    
    /** Creates a new instance of XMLElement */
    public XMLElement () {
    }
    
    public XMLElement (String uri, String localName, String qName, Attributes attributeList) {
        _elementQName = qName;
        _elementLName = localName;
        _elementUri = uri;
        _attributeList = attributeList;
    }
    
    public String getQName() {
        return _elementQName;
    }
    
    public String getLName() {
        return _elementLName;
    }
    
    public Attributes getAttributes() {
        return _attributeList;
    }
}
