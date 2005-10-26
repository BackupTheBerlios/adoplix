package de.adoplix.internal.server;

/**
 *
 * @author dirk
 */
public class AdoplixServer {
    
    public static void main(String[] args) {
        try {
            String fileName = "C:/Dokumente und Einstellungen/dirk/Eigene Dateien/Adoplix/Test/Configuration/testxml.xml";
            
            ServerConfiguration serverConf = new ServerConfiguration(fileName);
            String myId = serverConf.getServerId ();
            String serverPwd = serverConf.getPwd ();
            String validServerId = serverConf.getValidServerServerId (0);
            validServerId = serverConf.getValidServerServerId (1);
            validServerId = serverConf.getValidServerServerId (2);
            System.out.println("MyId ist..." + myId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Creates a new instance of AdoplixServer 
     */
    public AdoplixServer () {
    }
    
}
