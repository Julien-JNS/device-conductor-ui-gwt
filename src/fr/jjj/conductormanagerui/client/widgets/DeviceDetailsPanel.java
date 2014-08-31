package fr.jjj.conductormanagerui.client.widgets;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import fr.jjj.conductormanagerui.client.widgets.audioout.DeviceAudioOutPanel;
import fr.jjj.conductormanagerui.client.widgets.common.Attribute;
import fr.jjj.conductormanagerui.shared.DeviceDesc;

/**
 * Created by Jaunais on 10/08/2014.
 */
public class DeviceDetailsPanel extends VerticalPanel {

    private DeviceDesc deviceDesc;

    private VerticalPanel overviewPanel;

    private Panel specificPanel;

    private Attribute label;

    public DeviceDetailsPanel() {

        overviewPanel =new VerticalPanel();

        label=new Attribute("label");
        overviewPanel.add(label);

        add(overviewPanel);

    }

    public void setDevice(DeviceDesc deviceDesc)
    {
        this.deviceDesc=deviceDesc;

        label.setValue(deviceDesc.getLabel());

        if(deviceDesc.getType().equals("audio-out"))
        {
            if(specificPanel==null) {
                specificPanel = new DeviceAudioOutPanel();
                overviewPanel.add(specificPanel);
            }
            else
            {
                //TODO Re-activate / show
            }
            ((DeviceAudioOutPanel)specificPanel).setDevice(deviceDesc);
        }
    }
}
