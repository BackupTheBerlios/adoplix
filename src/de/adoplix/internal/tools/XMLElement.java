package de.adoplix.internal.tools;

import org.xml.sax.Attributes;
import java.util.*;

public class XMLElement {
    
    protected String _elementQName = "";
    protected String _elementLName = "";
    protected String _elementUri = "";
    protected String _elementValue = "";
    protected Attributes _attributeList = null;
    
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
    
    public void setQName(String qName) {
        _elementQName = qName;
    }
    
    public void setLName(String lName) {
        _elementLName = lName;
    }
    
    public void setValue(String elementValue) {
        _elementValue = elementValue;
    }
    
    public String getValue () {
        return _elementValue;
    }
}
