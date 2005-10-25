package de.adoplix.internal.tools;

import org.xml.sax.Attributes;
import java.util.*;

/**
 * Hilfsklasse zur Verwaltung von Werten, die zu XMLObject gehoeren.
 * Koennte auch in XMLObject 'integriert' werden.
 * Ist ein Kandidat fuer Aufraeumaktionen.
 * @author dirkg
 */
public class XMLElement {
    
    protected String _elementQName = "";
    protected String _elementLName = "";
    protected String _elementUri = "";
    protected String _elementValue = "";
    protected Attributes _attributeList = null;
    
    /** Creates a new instance of XMLElement */
    public XMLElement () {
    }
   
    /**
     * Konstruktor mit allen wichtigen Bestandteilen des Elements
     * @param uri ???
     * @param localName ???
     * @param qName ???
     * @param attributeList Alle Attribute dieses Elements
     */
    public XMLElement (String uri, String localName, String qName, Attributes attributeList) {
        _elementQName = qName;
        _elementLName = localName;
        _elementUri = uri;
        _attributeList = attributeList;
    }
    
    
    /**
     * Liefert den Wert des Attributs qName.
     * @return ???
     */
    public String getQName() {
        return _elementQName;
    }
    
    /**
     * Liefert den Wert des Attributs lName
     * @return ???
     */
    public String getLName() {
        return _elementLName;
    }
    
    /**
     * Liefert die Attributliste
     * @return Die Attributliste des Elements
     */
    public Attributes getAttributes() {
        return _attributeList;
    }
    
    /**
     * Belegt den Wert des Attributs qName
     * @param qName Der neue Wert des Attributs
     */
    public void setQName(String qName) {
        _elementQName = qName;
    }
    
    /**
     * Belegt den Wert des Attributs lName
     * @param lName Der neue Wert des Attributs
     */
    public void setLName(String lName) {
        _elementLName = lName;
    }
    
    /**
     * Belegt den Wert des Attributs elementValue.
     * @param elementValue Ist der Wert, den ein XML-Element annehmen kann.
     */
    public void setValue(String elementValue) {
        _elementValue = elementValue;
    }
    
    /**
     * Liefert den Wert des Attributs elementValue
     * @return Der Wert des Attributs
     */
    public String getValue () {
        return _elementValue;
    }
}
