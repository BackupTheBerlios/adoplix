/*
 * LittleHelper.java
 *
 * Created on 9. November 2005, 22:53
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package de.adoplix.internal.tools;

import de.adoplix.internal.telegram.XMLMessage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

/**
 *
 * @author dirk
 */
public class LittleHelper {
    
    /** Creates a new instance of LittleHelper */
    public LittleHelper () {
    }
    
    public static synchronized StringReader streamToStringReader (InputStream dataInputStream) {
        int b;
        String dataInputString = "";
        
        try {
            BufferedInputStream bufferedStream = new BufferedInputStream (dataInputStream);
            while((b = bufferedStream.read ()) != -1 ) {
                dataInputString+= b;
            }
        } catch (IOException ioEx){
            dataInputString = "";
        }
        return new StringReader(dataInputString);
    }
    
}
