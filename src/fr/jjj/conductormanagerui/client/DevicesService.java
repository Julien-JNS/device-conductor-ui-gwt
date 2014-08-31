package fr.jjj.conductormanagerui.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import fr.jjj.conductormanagerui.shared.DeviceDesc;

import java.util.Set;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("devices")
public interface DevicesService extends RemoteService {

    Set<DeviceDesc> getDevices() throws IllegalArgumentException;

}
