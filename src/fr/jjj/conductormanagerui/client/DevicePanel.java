package fr.jjj.conductormanagerui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import fr.jjj.conductormanagerui.client.widgets.DeviceDetailsPanel;
import fr.jjj.conductormanagerui.shared.DeviceDesc;

import java.util.*;

/**
 * Created by Jaunais on 03/08/2014.
 */
public class DevicePanel extends VerticalPanel {

    private CellTable<DeviceDesc> table;

    private DeviceDetailsPanel detailsPanel;

    private List<DeviceDesc> devices;

    private final DevicesServiceAsync devicesServiceAsync = GWT
            .create(DevicesService.class);

    public DevicePanel()
    {
        devices=new ArrayList<DeviceDesc>();

        // Create a CellTable.
        table = new CellTable<DeviceDesc>();
        table.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.DISABLED);

        // Add a text column to show the name.
        TextColumn<DeviceDesc> nameColumn = new TextColumn<DeviceDesc>() {
            @Override
            public String getValue(DeviceDesc object) {
                return object.getLabel();
            }
        };
        table.addColumn(nameColumn, "Device");

        // Add a text column to show the type.
        TextColumn<DeviceDesc> typeColumn = new TextColumn<DeviceDesc>() {
            @Override
            public String getValue(DeviceDesc object) {
                return object.getType();
            }
        };
        table.addColumn(typeColumn, "Type");

        // Add a text column to show the related conductor.
        TextColumn<DeviceDesc> conductorColumn = new TextColumn<DeviceDesc>() {
            @Override
            public String getValue(DeviceDesc object) {
                return object.getConductor();
            }
        };
        table.addColumn(conductorColumn, "Conductor");

        // Add a text column to show the related conductor.
        TextColumn<DeviceDesc> statusColumn = new TextColumn<DeviceDesc>() {
            @Override
            public String getValue(DeviceDesc object) {
                return object.getStatus();
            }
        };
        table.addColumn(statusColumn, "Status");

        // Add a selection model to handle user selection.
        final SingleSelectionModel<DeviceDesc> selectionModel = new SingleSelectionModel<DeviceDesc>();
        table.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            public void onSelectionChange(SelectionChangeEvent event) {
                DeviceDesc selected = selectionModel.getSelectedObject();
                if (selected != null) {
                    //Window.alert("You selected: " + selected.getLabel());
                    detailsPanel.setDevice(selected);
                }
            }
        });


        add(table);

        detailsPanel=new DeviceDetailsPanel();
        add(detailsPanel);

        // Devices
        devicesServiceAsync.getDevices(new AsyncCallback<Set<DeviceDesc>>() {
            public void onFailure(Throwable caught) {
                // Show the RPC error message to the user
                System.out.println("FAILURE!!!");
            }

            public void onSuccess(Set<DeviceDesc> result) {
                System.out.println("RESPONSE=" + result);
                update(result);
            }
        });
    }

    public void update(Set<DeviceDesc> deviceDescs)
    {

        Iterator<DeviceDesc> it = deviceDescs.iterator();
        //Window.alert("result devices: " + deviceDescs + " (size=" + deviceDescs.size() + ")");
        while (it.hasNext()) {
            DeviceDesc dc = it.next();
            //Window.alert("label: " + dc.getLabel()+" / type: "+dc.getType()+" / conductor: "+dc.getConductor());
        }

        devices.clear();
        devices.addAll(deviceDescs);
        table.setRowCount(deviceDescs.size());
        table.setRowData(devices);
    }

}
