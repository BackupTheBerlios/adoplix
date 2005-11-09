/*
 * I_AdapterConnector.java
 *
 */

package de.adoplix.internal.connection;

/**
 *
 * @author dirk
 */
public interface I_AdapterConnector extends Runnable {
    /**
     * tells server that it exists
     */
    public void registerAdapter();
    
    /**
     * tells server that this object will not longer exist
     */
    public void deregisterAdapter();
    
    /**
     * returns the threadId of adaptor
     */
    public String getThreadId();
    
    /**
     * sets the threadId
     */
    public void setThreadId(String threadId);
}
