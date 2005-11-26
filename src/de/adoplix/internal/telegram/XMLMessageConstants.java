/*
 * XMLMessageConstants.java
 *
 * Created on 23. Oktober 2005, 22:34
 */

package de.adoplix.internal.telegram;

/**
 *
 * @author  dirk
 */
public abstract class XMLMessageConstants {

    /** Message Type LocalConnection */
    public static final String MSG_TYPE_LOCAL_CONNECTION = "LocalConnection";
    /** Message Type Ping */
    public static final String MSG_TYPE_PING = "Ping";
    /** Message Type Pong */
    public static final String MSG_TYPE_PONG = "Pong";
    /** Message Type ExternalEvent */
    public static final String MSG_TYPE_EVENT = "Event";
    /** Message Type ExternalResponse */
    public static final String MSG_TYPE_RESPONSE = "Response";
    /** Message Type Acknowledge */
    public static final String MSG_TYPE_ACKNOWLEDGE = "Acknowledge";
    /** Message Type AdminFunction */
    public static final String MSG_TYPE_FUNCTION = "AdminFunction";
    
    
    /** AdoplixMessage <br> Zeigt, dass es sich um eine adoplix-Nachricht handelt */
    public static final String ADOPLIX_MESSAGE = "AdoplixMessage";
    /** Header <br> Wurzel des Headers */
    public static final String MSG_HEADER = "Header";
    /** Body <br> Wurzel des Nachrichteninhalts */
    public static final String MSG_BODY = "Body";
    /** MsgType <br> Typ der Nachricht. <br> z.B. Ping, LocalConnection, Event... */
    public static final String MSG_TYPE = "MsgType";
    /** InitialAddress <br> Urspr�nglicher Absender der Nachricht */
    public static final String INITIAL_ADDRESS = "InitialAddress";
    /** TimeStampSend <br> Zeitstempel des Nachrichtenversands */
    public static final String TIME_STAMP_SEND = "TimeStampSend";
    /** EventId <br> ID einer initialen Nachricht */
    public static final String EVENT_ID = "EventId";
    /** TaskId <br> Id der Task */
    public static final String TASK_ID = "TaskId";
    /** ResponseTaskId <br> Local ID of Task when response comes in. */
    public static final String RESPONSE_TASK_ID = "ResponseTaskId";
    /** ![CDATA[ <br> Unformatierter Nachrichtentext */
    public static final String CDATA_BEGIN = "![CDATA[";
    /** ]] <br> Unformatierter Nachrichtentext Ende*/
    public static final String CDATA_END = "]]";
    /** CDataContent <br> Inhalt eines CDATA-Blocks */
    public static final String CDATA_CONTENT = "CDataContent";
    /** ExternalPort <br> Port f�r Kommunikation nach aussen */
    public static final String EXTERNAL_PORT = "ExternalPort";
    /** TimeOutAcknMillis <br> Wartezeit auf Quittung (nicht Response) */
    public static final String TIME_OUT_ACKN_MILLIS = "TimeOutAcknMillis";
    /** TimeOutRespMillis <br> Wartezeit auf Response. */
    public static final String TIME_OUT_RESP_MILLIS = "TimeOutRespMillis";
    /** AcknInitiator <br> Antwort durch Server oder Adapter. */
    public static final String ACKN_INITIATOR = "AcknInitiator";
    /** AwaitingResponse <br> Wird Antwort (Response) erwartet */
    public static final String AWAITING_RESPONSE = "AwaitingResponse";
    
    /** LocalConnection constants <br> PartnerName is communication partner. */
    public static final String PARTNER_NAME = "PartnerName";
    /** LocalConnection constants <br> PartnerType is communication partner. */
    public static final String PARTNER_TYPE = "PartnerType";
    /** LocalConnection constants <br> Aim of partner: Awaits Response or Event */
    public static final String AIM = "Aim";
    
    /** ExternalResponse constants <br> InitialEventId is the event which was
      * send by the requesting client */
    public static final String INITIAL_EVENT_ID = "InitialEventId";
    /** ExternalResponse constants <br> TimeStampSend is the time when message was send */
    public static final String TIME_STAMP_RECEIVED = "TimeStampReceived";
    /** ExternalResponse constants <br> ResponseId is unique identifier for message */ 
    public static final String RESPONSE_ID = "ResponseId";
 
    /** Acknowledge constants <br> Result */
    public static final String RESULT = "Result";

    /** AdminFunction constants <br> MethodName */
    public static final String METHOD_NAME = "MethodName";
    /** AdminFunction constants <br> ParameterValue */
    public static final String PARAMETER_VALUE = "ParameterValue";
    
}
