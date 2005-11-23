package de.adoplix.internal.tools.xml;

import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationKeyNotFoundException;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationTypeException;
import java.io.StringReader;


/**
 * Bietet den gezielten Zugriff auf Elemente, Attribute, etc. einer
 * XML-Struktur.
 * 
 * @author dirk
 */
public class XMLRetriever {
    protected XMLObject _xmlRootObject = null;
    protected XMLObject _xmlObject = null;

    /**
     * Konstruktor, da diese Klasse als Basisklasse verwendet wird.
     */
    public XMLRetriever() {
    }

    /**
     * Standard-Konstruktor.
     * 
     * @param xmlObject Ein XMLObject-Baum
     */
    public XMLRetriever(XMLObject xmlObject) {
        _xmlObject = xmlObject;
        _xmlRootObject = xmlObject;
    }
    
    public XMLRetriever(String string) {
        _xmlObject = new XMLObject();
        XMLParser xmlParser = new XMLParser(string, _xmlObject);
        xmlParser.parse();
        _xmlRootObject = _xmlObject;
    }

    /**
     *
     */
    public XMLRetriever(StringReader stringReader) {
        _xmlObject = new XMLObject();
        XMLParser xmlParser = new XMLParser(stringReader, _xmlObject);
        xmlParser.parse();
        _xmlRootObject = _xmlObject;
    }

    public XMLObject setXMLRootObject()
            throws ConfigurationKeyNotFoundException {
        _xmlObject = _xmlRootObject;
        return _xmlObject;
    }

    public XMLObject setXMLParentObject()
            throws ConfigurationKeyNotFoundException {
        if (_xmlObject != _xmlRootObject) {
            _xmlObject = _xmlObject.getParent();
        }
        return _xmlObject;
    }

    public XMLObject setXMLObject(XMLObject selectedXMLObject) throws ConfigurationKeyNotFoundException {
        _xmlObject = selectedXMLObject;
        return _xmlObject;
    }

    public XMLObject setXMLObjectByKey(String key, boolean startAtRoot) throws ConfigurationKeyNotFoundException {
        if (startAtRoot) {
            _xmlObject = _xmlRootObject;
        }
        return setXMLObjectByKey(key);
    }

    public XMLObject setXMLObjectByKey(String key) throws ConfigurationKeyNotFoundException {
        /*
         * Solange . ein XMLObject mit search...() suchen. Wenn kein . mehr, den
         * Wert mit get...() lesen. Bsp.: ServerHandling.ServerId
         */
        try {
            while (key.indexOf("%") > -1) {
                int dotPos = key.indexOf("%");
                String preKey = key.substring(0, dotPos);
                _xmlObject = _xmlObject.searchXMLSubObject(preKey);
                key = key.substring(dotPos + 1);
            }
            _xmlObject = _xmlObject.searchXMLSubObject(key);
            return _xmlObject;
        } catch (Exception ex) {
            throw new ConfigurationKeyNotFoundException(key);
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
        try {
        return _xmlObject.getXMLSubObjectList(key).size();
        } catch (Throwable th) {
            return 0;
        }
    }

    /**
     * Liefert das Kind-Objekt eines Elements zur�ck. Dabei wird davon
     * ausgegangen, dass es nur eines gibt. Gibt's mehrere, wird das erste
     * zur�ckgeliefert.
     */
    public XMLObject getChild() throws ConfigurationKeyNotFoundException {
        try {
            return getChildren().get(0);
        } catch (ConfigurationKeyNotFoundException confEx) {
            throw new ConfigurationKeyNotFoundException(
                    "Kein Kind-Element konnte gefunden werden: "
                            + _xmlObject.getLName());
        } catch (Exception ex) {
            throw new ConfigurationKeyNotFoundException(ex.getMessage());
        }
    }

    public XMLObject getChild(int index)
            throws ConfigurationKeyNotFoundException {
        try {
            return getChildren().get(index);
        } catch (Exception ex) {
            throw new ConfigurationKeyNotFoundException(
                    "Kind-Element konnte in interner Liste nicht gefunden werden: Index="
                            + index);
        }
    }

    /**
     * Liefert alle Kinder des aktuellen Objekts.
     */
    public XMLObjectList getChildren() throws ConfigurationKeyNotFoundException {
        return _xmlObject.getXMLSubObjectList();
    }

    /**
     * Liefert �ber einen Schl�ssel ein Kind-Objekt. Dabei wird davon
     * ausgegangen, dass es nur ein Kind gibt. Bei mehreren Kindern wird das
     * erste zur�ckgeliefert.
     */
    public XMLObject getChild(String key) throws ConfigurationKeyNotFoundException {
        try {
        return getChildren(key).get(0);
        }
        catch (ConfigurationKeyNotFoundException cknfEx) {
            throw new ConfigurationKeyNotFoundException(key);
        }
    }

    /**
     * Liefert alle Kinder des aktuellen Objekts mit einem bestimmten Namen
     */
    public XMLObjectList getChildren(String key)
            throws ConfigurationKeyNotFoundException {
        try {
            XMLObjectList filterList = new XMLObjectList();
            XMLObjectList childrenList = _xmlObject.getXMLSubObjectList();
            for (int i = 0; i < childrenList.size(); i++) {
                XMLObject anObject = childrenList.get(i);
                if (anObject.getLName().equalsIgnoreCase(key)) {
                    filterList.add(anObject);
                }
            }
            return filterList;
        } catch (Exception ex) {
            throw (new ConfigurationKeyNotFoundException("Kind-Element konnte in interner Liste nicht gefunden werden: Key="
                    + key));
        }
    }

    /**
     * Returns the selected xml-object value.
     * @return Value of the actual selected XML-Tag.
     * @throws ConfigurationKeyNotFoundExcepion
     */
    public String getElementValue() throws ConfigurationKeyNotFoundException {
        try {
            return _xmlObject.getValue();
        } catch (Exception ex) {
            throw new ConfigurationKeyNotFoundException(
                    "ElementValue ist <null>");
        }
    }

    /**
     * Returns the numeric (int) value of an element.
     * @return int value of xml-object value.
     * @throws ConfigurationTypeException, ConfigurationKeyNotFoundException
     */
    public int getElementIntValue() throws ConfigurationTypeException, ConfigurationKeyNotFoundException {
        return toInt(getElementValue());
    }
    
    public int toInt (String intValue) throws ConfigurationTypeException {
    	try {
    		return Integer.parseInt(intValue);
    	}
    	catch(Exception ex) {
    		throw new ConfigurationTypeException();
    	}
    }
    
    /**
     * Returns the numeric (long) value of an element.
     * @return long value of xml-object value.
     * @throws ConfigurationTypeException, ConfigurationKeyNotFoundException
     */
    public long getElementLongValue() throws ConfigurationTypeException, ConfigurationKeyNotFoundException {
        return toLong(getElementValue());
    }
    
    public long toLong (String longValue) throws ConfigurationTypeException {
    	try {
    		return Long.parseLong(longValue);
    	}
    	catch(Exception ex) {
    		throw new ConfigurationTypeException();
    	}
    }
}
