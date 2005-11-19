package de.adoplix.internal.tools.xml;

import java.util.ArrayList;

import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationKeyNotFoundException;

public class XMLObjectList {

    private ArrayList _xmlObjects = null;

    public XMLObjectList() {
        _xmlObjects = new ArrayList();
    }

    public void add(XMLObject xmlObject) {
        _xmlObjects.add(xmlObject);
    }

    public XMLObject get(int index) throws ConfigurationKeyNotFoundException {
        try {
            return (XMLObject) _xmlObjects.get(index);
        } catch (Exception ex) {
            throw new ConfigurationKeyNotFoundException();
        }
    }

    public XMLObject get(String key) throws ConfigurationKeyNotFoundException {
        for (int i = 0; i < size(); i++) {
            if (get(i).getLName().equalsIgnoreCase(key)) {
                return get(i);
            }
        }
        return null;
    }

    public int size() {
        return _xmlObjects.size();
    }
}