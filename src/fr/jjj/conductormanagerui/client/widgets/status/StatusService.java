package fr.jjj.conductormanagerui.client.widgets.status;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import fr.jjj.conductormanagerui.shared.ConductorDesc;
import fr.jjj.conductormanagerui.shared.DeviceDesc;

import java.util.Set;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("status")
public interface StatusService extends RemoteService {

    String getGlobalStatus() throws IllegalArgumentException;

    String getDetailedStatus() throws IllegalArgumentException;


}
