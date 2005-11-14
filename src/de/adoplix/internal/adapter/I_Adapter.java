package de.adoplix.internal.adapter;

import de.adoplix.internal.tasks.Task;
import de.adoplix.internal.telegram.XMLContainer;

public interface I_Adapter extends Runnable {
    public void sendAdoplixMsg (Object adapterInterface, XMLContainer msg);
}
