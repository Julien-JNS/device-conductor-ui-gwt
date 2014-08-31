package fr.jjj.conductormanagerui.client.widgets.status;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.StackPanel;
import fr.jjj.conductormanagerui.shared.ConductorDesc;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Jaunais on 03/08/2014.
 */
public class StatusPanel extends StackPanel {

    private final StatusServiceAsync statusService = GWT
            .create(StatusService.class);

    private Label global;

    private Label detailed;

    public StatusPanel() {
        global = new Label("Initializing...");
        add(global);

        detailed = new Label("Initializing...");
        add(detailed);

        update();
    }

    public void update() {
        statusService.getGlobalStatus(new AsyncCallback<String>() {
            public void onFailure(Throwable caught) {
                // Show the RPC error message to the user
                System.out.println("FAILURE WHEN RETRIEVING GLOBAL STATUS !");
            }

            public void onSuccess(String result) {
                System.out.println("RESPONSE FOR GLOBAL STATUS=" + result);

                global.setText(result);

            }
        });

        statusService.getDetailedStatus(new AsyncCallback<String>() {
            public void onFailure(Throwable caught) {
                // Show the RPC error message to the user
                System.out.println("FAILURE WHEN RETRIEVING DETAILED STATUS !");
            }

            public void onSuccess(String result) {
                System.out.println("RESPONSE FOR DETAILED STATUS=" + result);

                detailed.setText(result);
            }
        });
    }
}
