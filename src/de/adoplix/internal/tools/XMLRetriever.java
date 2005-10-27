package de.adoplix.internal.tools;

import de.adoplix.internal.runtimeInformation.constants.ErrorConstants;
import de.adoplix.internal.runtimeInformation.constants.ErrorConstantsText_Ger;
import java.util.ArrayList;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationKeyNotFoundException;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Bietet den gezielten Zugriff auf Elemente, Attribute, etc. einer XML-Struktur.
 * @author dirk
 */
public class XMLRetriever {
    protected XMLObject _xmlRootObject = null;
    protected XMLObject _xmlObject = null;
    
    /**
     * Konstruktor, da diese Klasse als Basisklasse verwendet wird.
     */
    public XMLRetriever () {
    }
    
    /** 
     * Standard-Konstruktor.
     * @param xmlObject Ein XMLObject-Baum
     */
    public XMLRetriever (XMLObject xmlObject) {
        _xmlObject = xmlObject;
	_xmlRootObject = xmlObject;
    }

    public XMLObject setXMLRootObject () throws ConfigurationKeyNotFoundException {
        _xmlObject = _xmlRootObject;
	return _xmlObject;
    }
    
    public XMLObject setXMLParentObject () throws ConfigurationKeyNotFoundException {
        if (_xmlObject != _xmlRootObject) {
            _xmlObject = _xmlObject.getParent ();
        }
        return _xmlObject;
    }

    public XMLObject setXMLObjectByKey (String key, boolean startAtRoot)  throws ConfigurationKeyNotFoundException {
	if (startAtRoot) {
	     _xmlObject = _xmlRootObject;
	}
	return setXMLObjectByKey (key);
    }
    
    public XMLObject setXMLObjectByKey (String key) throws ConfigurationKeyNotFoundException {
        /*
         * Solange . ein XMLObject mit search...() suchen.
         * Wenn kein . mehr, den Wert mit get...() lesen.
         * Bsp.: ServerHandling.ServerId
         */
        try {
            while (key.indexOf (".") > -1) {
                int dotPos = key.indexOf (".");
                String preKey = key.substring (0, dotPos);
                _xmlObject = _xmlObject.searchXMLSubObject(preKey);
                key = key.substring(dotPos + 1);
            }
            _xmlObject = _xmlObject.getXMLSubObject (key);
            return _xmlObject;
        }            
        catch (Exception ex) {
            throw new ConfigurationKeyNotFoundException();
        }
    }

    /**
     * Liefert die Anzahl aller untergeordneten Objekte.
     */
    public int countChildren() {
        return _xmlObject.getXMLSubObjectList().size();
    }

    /**
     * Liefert die Anzahl aller untergeordneten Objekte abhaengig vom Schluessel
     */
    public int countChildren(String key) {
        return _xmlObject.getXMLSubObjectList(key).size();
    }

    /**
     * Liefert das Kind-Objekt eines Elements zurück.
     * Dabei wird davon ausgegangen, dass es nur eines gibt.
     * Gibt's mehrere, wird das erste zurückgeliefert.
     */
    public XMLObject getChild ()  throws ConfigurationKeyNotFoundException {
        try {
            return getChildren().get(0);
        }
        catch (Exception ex) {
            throw new ConfigurationKeyNotFoundException();
        }
    }

    public XMLObject getChild (int index) throws ConfigurationKeyNotFoundException {
        try {
            return getChildren().get(index);
        }
        catch (Exception ex) {
            throw new ConfigurationKeyNotFoundException();
        }
    }

    /**
     * Liefert alle Kinder des aktuellen Objekts.
     */
    public XMLObjectList getChildren() throws ConfigurationKeyNotFoundException {
        return _xmlObject.getXMLSubObjectList();
    }
    
    /**
     * Liefert über einen Schlüssel ein Kind-Objekt. Dabei wird davon ausgegangen,
     * dass es nur ein Kind gibt. Bei mehreren Kindern wird das erste zurückgeliefert.
     */
    public XMLObject getChild (String key) throws ConfigurationKeyNotFoundException {
        return getChildren (key).get(0);
    }
    
    /**
     * Liefert alle Kinder des aktuellen Objekts mit einem bestimmten Namen
     */
    public XMLObjectList getChildren(String key) throws ConfigurationKeyNotFoundException {
        XMLObjectList filterList = new XMLObjectList();
        XMLObjectList childrenList = _xmlObject.getXMLSubObjectList();
        for (int i = 0; i < childrenList.size(); i ++) {
            XMLObject anObject = childrenList.get(i);
            if (anObject.getLName().equalsIgnoreCase(key)) {
                filterList.add(anObject);
            }
        }
        return filterList;
    }
    
    public String getElementValue() {
        try {
            return _xmlObject.getValue ();
        }
        catch (Exception ex) {
            return null;
        }
    }
}
