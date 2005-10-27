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
    private XMLObject _xmlRootObject = null;
    private XMLObject _xmlObject = null;
    
    /** 
     * Standard-Konstruktor.
     * @param xmlObject Ein XMLObject-Baum
     */
    public XMLRetriever (XMLObject xmlObject) {
        _xmlObject = xmlObject;
	_xmlRootObject = xmlObject;
    }

    public XMLObject setXMLRootObject () throws ConfigurationKeyNotFoundException {
	return _xmlRootObject;
    }

    public XMLObject setXMLObjectByKey (String key, boolean startAtRoot)  throws ConfigurationKeyNotFoundException {
	if (startAtRoot) {
	     _xmlObject = _xmlRootObject;
	}
	return getXMLObjectByKey (key);
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
                key = key.substring (0, dotPos);
                _xmlObject = _xmlObject.searchXMLSubObject(key);
                key = key.substring(dotPos + 1);
            }
            key = key.substring(
            _xmlObject = _xmlObject.getXMLSubObject (getKeySuffix(key));
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
    public int countChildren() {
        return _xmlObject.getXMLSubObjectList().size();
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
}
