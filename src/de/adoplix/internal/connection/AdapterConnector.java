/*
 * AdapterConnector.java
 *
 */

package de.adoplix.internal.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

import de.adoplix.adapter.Adapter;
import de.adoplix.internal.runtimeInformation.AdopLog;
import de.adoplix.internal.runtimeInformation.constants.ErrorConstants;
import de.adoplix.internal.runtimeInformation.exceptions.MessageContentException;
import de.adoplix.internal.server.AdoplixServer;
import de.adoplix.internal.telegram.Acknowledge;
import de.adoplix.internal.telegram.LocalConnection;
import de.adoplix.internal.telegram.XMLContainer;
import de.adoplix.internal.tools.LittleHelper;

/**
 * Basic communication functions. <br>
 * Check if communication to client is ok.
 *
 * @author dirk
 */
public class AdapterConnector extends Adapter {
    
    private static Logger _logger = AdopLog.getLogger (AdapterConnector.class);
    
    protected boolean _talkToClient = true;
    protected Socket _clientSocket;
    protected XMLContainer _xmlContainer;
    protected LocalConnection _localConnection;
    protected boolean _run = true;
    
    
    /** Creates a new instance of AdapterConnector */
    public AdapterConnector (Socket clientSocket) {
        _clientSocket =  clientSocket;
    }
    
    public void stop () {
        try {
            _clientSocket.close ();
        } catch (IOException ioEx) {}
        _run = false;
    }
    
    public void run () {
        // change xml-stream into xml-container with getters and setters
        // the xml-container will be the right one for the derived subclass.
        // The subclass will use it later
        try {
//            System.out.println("AdapterConnector " + _clientSocket);
//            String dataInputString = "";
//          BufferedReader in = new BufferedReader(
//                  new InputStreamReader( _clientSocket.getInputStream()) );
//          String x = in.readLine();
//          System.out.println("clientsocket recBufSize: " + _clientSocket.getReceiveBufferSize());
//          while (x != null) {
//              dataInputString+=x;
//              System.out.println(x);
//              System.out.println("aljfsdfsdaj" + dataInputString);
//              try {
//              x = in.readLine();
//              } catch (IOException ioEx) {
//                  break;
//              }
//          }
//          dataInputString.trimToSize();
//          _logger.finest("aaaaaaaaaaaaaaaaaaa\n" + dataInputString);
          
            
          _xmlContainer = LittleHelper.createXMLContainer (_clientSocket.getInputStream ());
//          _xmlContainer = LittleHelper.createXMLContainer (dataInputString);
        } catch (MessageContentException mcEx) {
//            _logger.severe (mcEx.getMessage ());
        } catch (IOException ioEx) {
            _logger.severe("Problem hier... : " + ioEx.getMessage());
            _logger.severe (ErrorConstants.MESSAGE_READ_ERROR + ": " + ErrorConstants.getErrorMsg (ErrorConstants.MESSAGE_READ_ERROR));
        }
        
        if (null != _xmlContainer) {
        
            // let server look for task and start TaskAdapter by server
            // server retrieves TaskAdapter
            // set xml container to TaskAdapter (enables the adapter to handle the data)
            System.out.println("AdapterConnector HHIIIIEEEERRRRR");
            AdoplixServer.startTaskAdapter (_clientSocket, _xmlContainer);
            
            try {
                if (_xmlContainer.acknByServer ()) {
                    Acknowledge ackn = new Acknowledge ();
                    ackn.setResult (0);
                    String msg = ackn.getXMLString ();
                    
                    PrintWriter socketOut = new PrintWriter ( _clientSocket.getOutputStream () );
                    socketOut.println (msg);
                    socketOut.flush ();
                }
                _clientSocket.close ();
            } catch (IOException ioEx) {
                _logger.warning ("aaaaaaaaaaaaaaaaaaaaaaaaaa");
            }
        }
    }
}
