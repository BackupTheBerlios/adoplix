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
            System.out.println( serverConf.getServerId ());
            System.out.println( serverConf.getPwd ());
            System.out.println( serverConf.getIntervalGetProjectSec ());
            System.out.println( serverConf.getMaxClientThreads ());
            System.out.println( serverConf.getPathTaskConfiguration ());
            System.out.println( serverConf.getPortExternal ());
            System.out.println( serverConf.getPortService ());
            System.out.println( serverConf.getPortLocal ());
            System.out.println( serverConf.getTimeoutClientMillis ());
            System.out.println( serverConf.getValidServerId (0));
            System.out.println( serverConf.getValidServerId (1));
//            validServerId = serverConf.getValidServerServerId (2);
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
