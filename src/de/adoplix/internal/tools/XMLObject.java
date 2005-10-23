/**
 * XMLObject ist ein XML-Element, das weitere Elemente beinhalten kann.
 * Kann auch Werte annehmen.
 * Die Verschachtelungstiefe ist theoretisch beliebig.
 * Bsp.: XMLObject A
 *            XMLObject AA
 *            XMLObject AB
 *                  XMLObject ABA
 *            XMLObject AC
 *       XMLObject B
 * @author  dirk
 */

package de.adoplix.internal.tools;

import java.util.ArrayList;
import org.xml.sax.Attributes;

/**
 *
 * @author  dirk
 */
public class XMLObject extends XMLElement {
    
    private String _xmlStyle = null;
//    private ArrayList _xmlElementList = new ArrayList();
    private ArrayList _xmlObjectList = new ArrayList();
    private String _target = null;
    private String _data = null;
    private XMLObject _parentObject = null;
    
    /** Creates a new instance of XMLObject */
    public XMLObject () {
    }
    
    public XMLObject (String uri, String localName, String qName, Attributes attributeList, XMLObject parentObject) {
        super(uri, localName, qName, attributeList);
        _parentObject = parentObject;
    }
    
//    public void add(XMLElement xmlElement) {
//        _xmlElementList.add (xmlElement);
//    }
    
    public void setXMLStyle(String xmlStyle) {
        _xmlStyle = xmlStyle;
    }
    
    public void setTarget(String target) {
        _target = target;
    }
    
    public void setData (String data) {
        _data = data;
    }
    
//    public ArrayList getXMLElementList() {
//        return _xmlElementList;
//    }
//    
//    public XMLElement getXMLElement(int index) {
//        try {
//            return (XMLElement)_xmlElementList.get(index);
//        }
//        catch (Exception ex) {}
//        return null;
//    }
//    
//    public XMLElement getXMLElement(String elementName){
//        for (int i=0; i<_xmlElementList.size (); i++) {
//            XMLElement anElement = (XMLElement)_xmlElementList.get (i);
//            if (anElement.getQName ().equalsIgnoreCase (elementName)) {
//                return anElement;
//            }
//            if (anElement.getLName ().equalsIgnoreCase (elementName)) {
//                return anElement;
//            }
//        }
//        return null;
//    }

    public void addXMLSubObject(XMLObject newObject) {
        _xmlObjectList.add(newObject);
    }
    
    public XMLObject getXMLSubObject(int index) {
        return (XMLObject)_xmlObjectList.get (index);
    }
    
    public XMLObject getXMLSubObject (String objectName) {
        for (int i = 0; i < _xmlObjectList.size (); i++) {
            XMLObject anObject = (XMLObject)getXMLSubObject(i);
            if (anObject.getQName ().equalsIgnoreCase (objectName)) {
                return anObject;
            }
        }
        return null;
    }
    
    public ArrayList getXMLObjectList() {
        return _xmlObjectList;
    }
    
    public void rollOut(String tab)
    {
        System.out.println(tab + "XMLObject: " + _elementLName + "=" + _elementValue + "{");

        for (int i=0; i<_xmlObjectList.size (); i++) {
            XMLObject anObject = (XMLObject)_xmlObjectList.get (i);
            anObject.rollOut (tab + "    ");
        }
        System.out.println(tab + "}");
    }
    
    public XMLObject getParent() {
        return _parentObject;
    }
}
