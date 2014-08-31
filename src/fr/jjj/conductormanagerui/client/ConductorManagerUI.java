package fr.jjj.conductormanagerui.client;

import java.util.*;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import fr.jjj.conductormanagerui.client.widgets.status.StatusPanel;
import fr.jjj.conductormanagerui.client.widgets.status.StatusService;
import fr.jjj.conductormanagerui.client.widgets.status.StatusServiceAsync;
import fr.jjj.conductormanagerui.shared.ConductorDesc;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ConductorManagerUI implements EntryPoint {
    /**
     * The message displayed to the user when the server cannot be reached or
     * returns an error.
     */
    private static final String SERVER_ERROR = "An error occurred while "
            + "attempting to contact the server. Please check your network "
            + "connection and try again.";

    private final ListBox playlists = new ListBox(true);

    private final CellList<String> agentCellList = new CellList<String>(new TextCell());

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        // Status
        final StatusPanel statusPanel = new StatusPanel();
        RootPanel.get("status").add(statusPanel);

        // Devices
        final DevicePanel devicePanel = new DevicePanel();
        RootPanel.get("devices").add(devicePanel);
    }
}