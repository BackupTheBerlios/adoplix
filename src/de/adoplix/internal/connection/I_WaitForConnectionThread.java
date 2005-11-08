/*
 * I_WaitForConnectionThread.java
 * The three ports server has listen to. For every port there is a delegated
 * Thread. This Threads are implementing this Interface and they extends the
 * Superclass WaitForConnectionThread.
 */

package de.adoplix.internal.connection;

import de.adoplix.internal.telegram.XMLMessage;
import java.net.Socket;

/**
 *
 * @author dirk
 */
public interface I_WaitForConnectionThread extends Runnable {
    public AdapterConnector startAdapterConnector (String threadId, Socket clientSocket);
    public void setMaxClientNumber (int maxClientNumber);
}
