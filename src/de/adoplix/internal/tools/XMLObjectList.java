package de.adoplix.internal.tools;

import de.adoplix.internal.runtimeInformation.constants.ErrorConstants;
import de.adoplix.internal.runtimeInformation.constants.ErrorConstantsText_Ger;
import java.util.ArrayList;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationKeyNotFoundException;


public class XMLObjectList {
    
    private ArrayList _xmlObjects = null;

    public XMLObjectList () {
        _xmlObjects = new ArrayList();
    }

    public add(XMLObject xmlObject) {
        _xmlObjects.add(xmlObject);
    }

    public get(int index) {
        return _xmlObjects.get(index);
    }

    public get (String xmlObjectElementName) {
        for (int i = 0; i < size(); i++) {
            if (get(i).getLName().equalsIgnoreCase(xmlElementName)) {
                return get(i);
            }
        }
        return null;
    }

    public size() {
        return _xmlObjects.size();
    }
}