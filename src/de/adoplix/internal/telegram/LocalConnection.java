/*
 * LocalConnection.java
 *
 */

package de.adoplix.internal.telegram;

/**
 * Container which stores connection informations from a local adapter
 * (ServiceAdapter, EventAdapter, AdminAdapter) when it contacts to the server
 * (PortAcceptor) first time.
 * @author dirk
 */
public class LocalConnection extends XMLMessage {
    
    private String _type = "";
    private String _adapterName = "";
    private int _aim = 0;
    private String _eventId = "";
    
    
    /** Creates a new instance of LocalConnection */
    public LocalConnection (String type, String adapterName, int aim,  String eventId) {
        setType(type);
        setName(adapterName);
        setAim(aim);
        setEventId(eventId);
    }

    public String getType () {
        return _type;
    }

    public void setType (String _type) {
        this._type = _type;
    }

    public String getName () {
        return _adapterName;
    }

    public void setName (String _adapterName) {
        this._adapterName = _adapterName;
    }

    public int getAim () {
        return _aim;
    }

    public void setAim (int _aim) {
        this._aim = _aim;
    }

    public String getEventId () {
        return _eventId;
    }

    public void setEventId (String _eventId) {
        this._eventId = _eventId;
    }
    
    
}
