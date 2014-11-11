package fr.jjj.conductormanagerui.client;

import java.lang.String;
import java.util.*;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
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

        // Menu
        Command cmd = new Command() {
            public void execute() {
                Window.alert("You selected a menu item!");
            }
        };

        // Make some sub-menus that we will cascade from the top menu.
        MenuBar menu = new MenuBar();
        menu.addItem("Statut global", cmd);
        menu.addItem("Périphériques", cmd);
        menu.addItem("Diffuser...", cmd);

        RootPanel.get("menu").add(menu);
        // Status
        final StatusPanel statusPanel = new StatusPanel();
        RootPanel.get("status").add(statusPanel);
//        final CellList<String> cellList=new CellList<String>(new TextCell());
//        List<String> list=new ArrayList<String>();
//        list.add("klkh");
//        list.add("kljlj");
//        cellList.setRowData(list);
//        final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<String>();
//
//        cellList.setSelectionModel(selectionModel);
//        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
//            public void onSelectionChange(SelectionChangeEvent event) {
//                Window.alert("You selected: "+event.getSource());
//                String selectedItem = selectionModel.getSelectedObject();
//                final SingleSelectionModel<String> selectionModel=(SingleSelectionModel<String>)cellList.getSelectionModel();
//                Window.alert("model " + selectionModel.getSelectedObject() + "selected:");// + selectionModel.getSelectedObject());
//                if (selectedItem != null) {
//                    Window.alert("You selected: " + selectedItem);
//                }
//            }
//        });
//
//        RootPanel.get("status").add(cellList);

        // Devices
        final DevicePanel devicePanel = new DevicePanel();
        RootPanel.get("devices").add(devicePanel);
    }
}
