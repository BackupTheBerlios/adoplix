/*
 * LocalConnection.java
 *
 */

package de.adoplix.internal.telegram;
import de.adoplix.internal.runtimeInformation.AdopLog;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationKeyNotFoundException;
import de.adoplix.internal.runtimeInformation.exceptions.ConfigurationTypeException;
import de.adoplix.internal.runtimeInformation.exceptions.MessageContentException;
import de.adoplix.internal.tools.xml.XMLRetriever;
import java.util.logging.Logger;

/**
 * Container which stores connection informations from a local adapter
 * (ServiceAdapter, EventAdapter, AdminAdapter) when it contacts to the server
 * (PortAcceptor) first time.
 * @author dirkg
 */
public class ExternalEvent extends XMLContainer {

    private Logger _logger = AdopLog.getLogger (Acknowledge.class);

    private String eventId = "";
    private String taskId = "";
    private String responseTaskId = "";
    private StringBuffer cData = new StringBuffer();
    private String initialAddress = "";
    private long timeOutAcknMillis = 0;
    private long timeOutRespMillis = 0;
    
    public ExternalEvent (XMLRetriever retriever) throws MessageContentException {
        // super reads the header
        super(retriever);
        
        // read body
        try {
            retriever.setXMLObjectByKey (XMLMessageConstants.MSG_BODY, true);
            setTimeOutAcknMillis(retriever.toLong (retriever.getChild (XMLMessageConstants.TIME_OUT_ACKN_MILLIS).getValue ()));
            setTimeOutRespMillis(retriever.toLong (retriever.getChild (XMLMessageConstants.TIME_OUT_RESP_MILLIS).getValue ()));
            setEventId(retriever.getChild (XMLMessageConstants.EVENT_ID).getValue ());
            setTaskId(retriever.getChild (XMLMessageConstants.TASK_ID).getValue ());
            setResponseTaskId(retriever.getChild (XMLMessageConstants.RESPONSE_TASK_ID).getValue ());
            setInitialAddress(retriever.getChild (XMLMessageConstants.INITIAL_ADDRESS).getValue ());
            getCData().append (retriever.getChild (XMLMessageConstants.CDATA).getValue ()).trimToSize ();
        }
        catch (ConfigurationKeyNotFoundException cknfEx) {
            throw new MessageContentException();
        }
        catch (ConfigurationTypeException ctEx) {
            _logger.warning(ctEx.getMessage ());
            throw new MessageContentException();
        }
    }

    public String getEventId () {
        return eventId;
    }

    public void setEventId (String eventId) {
        this.eventId = eventId;
    }

    public String getTaskId () {
        return taskId;
    }

    public void setTaskId (String taskId) {
        this.taskId = taskId;
    }

    public StringBuffer getCData () {
        return cData;
    }

    public void setCData (StringBuffer cData) {
        this.cData = cData;
    }

    public String getInitialAddress () {
        return initialAddress;
    }

    public void setInitialAddress (String initialAddress) {
        this.initialAddress = initialAddress;
    }

    public long getTimeOutAcknMillis () {
        return timeOutAcknMillis;
    }

    public void setTimeOutAcknMillis (long timeOutAcknMillis) {
        this.timeOutAcknMillis = timeOutAcknMillis;
    }

    public long getTimeOutRespMillis () {
        return timeOutRespMillis;
    }

    public void setTimeOutRespMillis (long timeOutRespMillis) {
        this.timeOutRespMillis = timeOutRespMillis;
    }

    public String getResponseTaskId () {
        return responseTaskId;
    }

    public void setResponseTaskId (String responseTaskId) {
        this.responseTaskId = responseTaskId;
    }

}
