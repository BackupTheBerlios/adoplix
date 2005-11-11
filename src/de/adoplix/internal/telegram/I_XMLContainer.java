package de.adoplix.internal.telegram;

import java.io.StringReader;

public interface I_XMLContainer {
    public StringReader createStringReader();
    public String getMsgType();
    public int getAcknInitiator();
    public int getAwaitingResponse ();
    public boolean acknByAdapter();
    public boolean acknByServer();
    public boolean awaitingResponse();
}