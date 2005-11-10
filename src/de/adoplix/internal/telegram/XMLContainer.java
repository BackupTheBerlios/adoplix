package de.adoplix.internal.telegram;

public class XMLContainer extends I_XMLContainer {
    protected String _type = "";

    public String getMsgType () {
        return _type;
    }

    public void setMsgType (String type) {
        _type = type;
    }
    public XMLContainer (XMLObject xmlObject) {
        zerpflücken und passende Tags suchen
    }
}