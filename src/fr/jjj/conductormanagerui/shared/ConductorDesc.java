package fr.jjj.conductormanagerui.shared;

import java.io.Serializable;

/**
 * Created by Jaunais on 31/07/2014.
 */
public class ConductorDesc implements Serializable {

    public void setLabel(String label) {
        this.label = label;
    }

    private String label;

    public void setMediaActivity(MediaActivity mediaActivity) {
        this.mediaActivity = mediaActivity;
    }

    public MediaActivity getMediaActivity() {
        return mediaActivity;
    }

    private MediaActivity mediaActivity;

    public ConductorDesc() {
        // Needed for serialization ?
    }

//    public DeviceConductor(String label) {
//        this();
//        this.label = label;
//    }

    public String getLabel() {
        return label;
    }
}
