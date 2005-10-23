/*
 * XMLObject.java
 *
 * Created on 20. Oktober 2005, 22:19
 */

package de.adoplix.internal.tools;

import java.util.ArrayList;

/**
 *
 * @author  dirk
 */
public class XMLObject {
    
    private String _xmlStyle = null;
    private ArrayList _xmlElementList = new ArrayList();
    private String _target = null;
    private String _data = null;
    
    /** Creates a new instance of XMLObject */
    public XMLObject () {
    }
    
    public void add(XMLElement xmlElement) {
        _xmlElementList.add (xmlElement);
    }
    
    public void setXMLStyle(String xmlStyle) {
        _xmlStyle = xmlStyle;
    }
    
    public void setTarget(String target) {
        _target = target;
    }
    
    public void setData (String data) {
        _data = data;
    }
    
    public ArrayList getXMLElementList() {
        return _xmlElementList;
    }
    
    public XMLElement getXMLElement(int index) {
        try {
            return (XMLElement)_xmlElementList.get(index);
        }
        catch (Exception ex) {}
        return null;
    }
    
    public XMLElement getXMLElement(String elementName){
        for (int i=0; i<_xmlElementList.size (); i++) {
            XMLElement anElement = (XMLElement)_xmlElementList.get (i);
            if (anElement.getQName ().equalsIgnoreCase (elementName)) {
                return anElement;
            }
            if (anElement.getLName ().equalsIgnoreCase (elementName)) {
                return anElement;
            }
        }
        return null;
    }
}
