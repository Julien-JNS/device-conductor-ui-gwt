package fr.jjj.conductormanagerui.client;


import com.google.gwt.user.client.rpc.AsyncCallback;
import fr.jjj.conductormanagerui.shared.DeviceDesc;

import java.util.Set;

/**
 * The async counterpart of <code>StatusService</code>.
 */
public interface DevicesServiceAsync {

    void getDevices(AsyncCallback<Set<DeviceDesc>> callback) throws IllegalArgumentException;
}
