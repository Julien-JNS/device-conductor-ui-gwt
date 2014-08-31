package fr.jjj.conductormanagerui.client.widgets.status;

import com.google.gwt.user.client.rpc.AsyncCallback;
import fr.jjj.conductormanagerui.shared.ConductorDesc;
import fr.jjj.conductormanagerui.shared.DeviceDesc;

import java.util.Set;

/**
 * The async counterpart of <code>StatusService</code>.
 */
public interface StatusServiceAsync {

    void getGlobalStatus(AsyncCallback<String> callback) throws IllegalArgumentException;

    void getDetailedStatus(AsyncCallback<String> callback) throws IllegalArgumentException;


}
