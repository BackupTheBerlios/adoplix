package de.adoplix.internal.tools;

import java.util.ArrayList;
import org.xml.sax.Attributes;

/**
 * XMLObject ist ein XML-Element, das weitere Elemente beinhalten kann. <br>
 * Kann ein Kind von einem XMLObject sein.  <br>
 * Kann auch Werte annehmen.                <br>
 * Die Verschachtelungstiefe ist beliebig.  <br>
 * Bsp.: XMLObject A                        <br>
 *            XMLObject AA                  <br>
 *            XMLObject AB                  <br>
 *                  XMLObject ABA           <br>
 *            XMLObject AC                  <br>
 *       XMLObject B                        <br>
 * @author  dirkg
 */
public class XMLObject extends XMLElement {
    
    private String _xmlStyle = null;
    private ArrayList _xmlObjectList = new ArrayList();
    private String _target = null;
    private String _data = null;
    private XMLObject _parentObject = null;
    
    /** Creates a new instance of XMLObject */
    public XMLObject () {
    }
    
    /**
     * Konstruktor mit allen wichtigen Eigenschaften des XMLObjekts in seiner
     * Funktion als XML-Element.
     * @param uri ???
     * @param localName ???
     * @param qName ???
     * @param attributeList ???
     * @param parentObject Ist das Eltern-Objekt, das dieses hier instanziiert hat
     */
    public XMLObject (String uri, String localName, String qName, Attributes attributeList, XMLObject parentObject) {
        super(uri, localName, qName, attributeList);
        _parentObject = parentObject;
    }
    
    /**
     * Belegt den Wert des Attributs xmlStyle.
     * @param xmlStyle Der Style ???
     */
    public void setXMLStyle(String xmlStyle) {
        _xmlStyle = xmlStyle;
    }
    
    /**
     * Belegt den Wert des Attributs target.
     * @param target Ziel
     */
    public void setTarget(String target) {
        _target = target;
    }
    
    /**
     * Sind Daten, die von der Anwendung interpretiert werden.
     * Entsprechen also nicht zwangslaeufig den syntaktischen Vorgaben eines
     * XML-Schemas.
     * @param data 'Beliebige' Daten, die z.B. von einem Adapter interpretiert
     * werden
     */
    public void setData (String data) {
        _data = data;
    }
    
    /**
     * Fügt diesem XMLOject ein weiteres Kind-Element hinzu
     * @param newObject Ist das Kind-Objekt
     */
    public void addXMLSubObject(XMLObject newObject) {
        _xmlObjectList.add(newObject);
    }

    /**
     * Liefert ein Kind-XMLObject aus der internen Liste.
     * Wird vermutlich nicht von 'aussen' benoetigt.
     * @param index Ist der Index innerhalb der Liste
     * @return Das n-te Element oder null
     */
    public XMLObject getXMLSubObject(int index) {
        try {
            return (XMLObject)_xmlObjectList.get (index);
        }
        catch (IndexOutOfBoundsException ioobEx) {}
        return null;
    }

    /**
     * Liefert ein Kind-XMLObject aus der internen Liste über den QNamen.
     * @param objectName Der Name (qName) des gesuchten Objekts
     * @return Das n-te Element oder null
     */
    public XMLObject getXMLSubObject (String objectName) {
        for (int i = 0; i < _xmlObjectList.size (); i++) {
            XMLObject anObject = getXMLSubObject(i);
            if (anObject.getQName ().equalsIgnoreCase (objectName)) {
                return anObject;
            }
        }
        return null;
    }
    
    /**
     * Durchsucht rekursiv den gesamten Baum aller XMLObjects nach einem
     * bestimmten XMLObject. Dazu zaehlt auch das auf der obersten Ebene.
     * Referenz fuer die Suche ist der Name (qName).
     */
    public XMLObject searchXMLSubObject (String objectName) {
        if (this.getQName ().equalsIgnoreCase (objectName)) {
            return this;
        }
        for (int i = 0; i < _xmlObjectList.size (); i++) {
            XMLObject anObject = getXMLSubObject(i).searchXMLSubObject (objectName);
            if (null != anObject) {
                return anObject;
            }
        }
        return null;
    }
    
    /**
     * Liefert die Liste aller direkt untergeordneten Kind-XML-Objekte.
     * Diese Funktion ist nicht rekursiv.
     */
    public ArrayList getXMLObjectList() {
        return _xmlObjectList;
    }
    
    /**
     * Dient dem Testen.
     * Schreibt rekursiv die eigenen Daten und die von moeglichen Kindern
     * auf System.out.
     * @param tab Sind fuehrende Leerzeichen, die fuer die Kinder erweitert 
     * werden, damit eingerueckt 'formatiert' werden kann
     */
    public void rollOut(String tab)
    {
        System.out.println(tab + "XMLObject: " + _elementLName + "=" + _elementValue + "{");

        for (int i=0; i<_xmlObjectList.size (); i++) {
            XMLObject anObject = (XMLObject)_xmlObjectList.get (i);
            anObject.rollOut (tab + "    ");
        }
        System.out.println(tab + "}");
    }
    
    /**
     * Liefert das Vater/Mutter/Eltern-Objekt.
     * @return Das Objekt, das dieses hier instanziiert hat
     */
    public XMLObject getParent() {
        return _parentObject;
    }
}
