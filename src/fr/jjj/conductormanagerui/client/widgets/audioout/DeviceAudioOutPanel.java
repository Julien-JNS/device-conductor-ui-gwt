package fr.jjj.conductormanagerui.client.widgets.audioout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import fr.jjj.conductormanagerui.shared.DeviceDesc;
import fr.jjj.conductormanagerui.shared.MediaItem;

import java.util.List;

/**
 * Created by Jaunais on 10/08/2014.
 */
public class DeviceAudioOutPanel extends HorizontalPanel implements MediaSourcePanelListener {

    private QueuePanel queuePanel;

    private MediaSourcePanel mediaSourcePanel;

    private final DeviceAudioOutServiceAsync deviceAudioOutService = GWT
            .create(DeviceAudioOutService.class);

    public DeviceAudioOutPanel() {

        queuePanel = new QueuePanel();

        add(queuePanel);

        mediaSourcePanel = new MediaSourcePanel(deviceAudioOutService, this);

        add(mediaSourcePanel);
    }

    public void setDevice(final DeviceDesc deviceDesc) {
        deviceAudioOutService.setDevice(deviceDesc, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(Void result) {
                mediaSourcePanel.setDevice(deviceDesc);
                updateQueue();
            }
        });


    }

    @Override
    public void itemAddedToQueue() {
        updateQueue();
    }

    private void updateQueue() {

        deviceAudioOutService.getQueue(new AsyncCallback<List<MediaItem>>()

        {
            public void onFailure(Throwable caught) {
                // Show the RPC error message to the user
                System.out.println("FAILURE!!!");
            }

            public void onSuccess(List<MediaItem> result) {
                System.out.println("RESPONSE=" + result);
                queuePanel.update(result);
            }
        });
    }
}
