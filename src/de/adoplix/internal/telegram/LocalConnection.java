/*
 * LocalConnection.java
 *
 */

package de.adoplix.internal.telegram;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationKeyNotFoundException;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationTypeException;
import de.adoplix.internal.runtimeInformation.exceptions.MessageContentException;
import de.adoplix.internal.runtimeInformation.exceptions.MessageValueTypeException;
import de.adoplix.internal.runtimeInformation.AdopLog;
import de.adoplix.internal.tools.xml.XMLRetriever;
import java.util.logging.Logger;

/**
 * Container which stores connection informations from a local adapter
 * (ServiceAdapter, EventAdapter, AdminAdapter) when it contacts to the server
 * (PortAcceptor) first time.
 * @author dirkg
 */
public class LocalConnection extends XMLContainer {

    private Logger logger = AdopLog.getLogger (LocalConnection.class);
    
    /* communication partner */
    private String _partnerName = "";
    private String _partnerType = "";
    private int _aim = 0;
    private String _eventId = "";
    
    /**
     * Constructor for blank container which is be used to create an XML-Format.
     */
    public LocalConnection () {
        super();
    }
    
    public LocalConnection (XMLRetriever retriever) {
        super(retriever);
        try {
            // HEADER was selected in super-constructor
            retriever.setXMLRootObject ();
            retriever.setXMLObjectByKey (XMLMessageConstants.MSG_BODY);
            setPartnerName(retriever.getChild (XMLMessageConstants.PARTNER_NAME).getValue ());
            setPartnerType(retriever.getChild (XMLMessageConstants.PARTNER_TYPE).getValue ());
            setAim (retriever.toInt(retriever.getChild (XMLMessageConstants.AIM).getValue ()));
            setEventId (retriever.getChild (XMLMessageConstants.EVENT_ID).getValue ());
        }
        catch (ConfigurationKeyNotFoundException cknfEx) {
            logger.warning (new MessageContentException().getMessage ());
        }
        catch (ConfigurationTypeException ctEx) {
            logger.warning (new MessageValueTypeException().getMessage ());
        }
    }

    public String getPartnerName () {
        return _partnerName;
    }

    public void setPartnerName (String _adapterName) {
        this._partnerName = _partnerName;
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

    public String getPartnerType () {
        return _partnerType;
    }

    public void setPartnerType (String _partnerType) {
        this._partnerType = _partnerType;
    }
    
    
}
