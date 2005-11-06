/*
 * I_WaitForConnectionThread.java
 * The three ports server has listen to. For every port there is a delegated
 * Thread. This Threads are implementing this Interface and they extends the
 * Superclass WaitForConnectionThread.
 */

package de.adoplix.internal.connection;

import java.net.Socket;

/**
 *
 * @author dirk
 */
public interface I_WaitForConnectionThread extends Runnable {
    public AdapterConnector startAdapterConnector (Socket clientSocket);
    public void setMaxClientNumber (int maxClientNumber);
}
