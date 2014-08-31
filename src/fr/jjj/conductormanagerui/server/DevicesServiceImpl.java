package fr.jjj.conductormanagerui.server;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import fr.jjj.conductormanager.ConductorRegistry;
import fr.jjj.conductormanagerui.client.DevicesService;
import fr.jjj.conductormanagerui.shared.DeviceDesc;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class DevicesServiceImpl extends RemoteServiceServlet implements
        DevicesService {

    @Override
    public Set<DeviceDesc> getDevices() throws IllegalArgumentException {

        Set<DeviceDesc> deviceDescs=new HashSet<DeviceDesc>();

        Iterator<fr.jjj.conductor.model.DeviceDesc> itDeviceDesc =  ConductorRegistry.INSTANCE.getDevices().iterator();

        while (itDeviceDesc.hasNext()) {
            fr.jjj.conductor.model.DeviceDesc dd = itDeviceDesc.next();
            DeviceDesc deviceDesc=new DeviceDesc();
            deviceDesc.setLabel(dd.getLabel());
            deviceDesc.setConductor(dd.getConductor());
            deviceDesc.setType(dd.getType());
            deviceDesc.setStatus(dd.getStatus());
            deviceDescs.add(deviceDesc);
        }
        return deviceDescs;
    }


}
