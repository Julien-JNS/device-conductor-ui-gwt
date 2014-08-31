package fr.jjj.conductormanagerui.server.status;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.jjj.conductormanager.ConductorRegistry;
import fr.jjj.conductormanager.ui.StatusPresenter;
import fr.jjj.conductormanager.ui.StatusView;
import fr.jjj.conductormanagerui.client.widgets.status.StatusService;
import fr.jjj.conductormanagerui.shared.ConductorDesc;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class StatusServiceImpl extends RemoteServiceServlet implements StatusService,StatusView {


    private String globalStatus, detailedStatus;

    private StatusPresenter presenter;

    public StatusServiceImpl()
    {
        presenter=new StatusPresenter(this);
    }

    @Override
    public String getGlobalStatus() throws IllegalArgumentException
    {
        return globalStatus;
    }

    @Override
    public String getDetailedStatus() throws IllegalArgumentException {
        return detailedStatus;
    }

    @Override
    public void setGlobalStatus(String s) {
        globalStatus=s;
    }

    @Override
    public void setDetailedStatus(String s) {
        detailedStatus=s;
    }

}
