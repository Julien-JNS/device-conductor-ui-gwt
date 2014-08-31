package fr.jjj.conductormanagerui.client.widgets.common;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * Created by Jaunais on 10/08/2014.
 */
public class Attribute extends HorizontalPanel {

    private Label label;

    private Label value;

    public Attribute(String text) {
        label = new Label(text + ":");

        value = new Label();
        add(label);
        add(value);
    }

    public void setValue(String value) {
        this.value.setText(value);
    }
}
