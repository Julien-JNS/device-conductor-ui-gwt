package fr.jjj.conductormanagerui.shared;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Jaunais on 31/07/2014.
 */
public class MediaActivity implements Serializable {

    public MediaActivity()
    {

    }

    public void setMediaSources(Set<String> mediaSources) {
        this.mediaSources = mediaSources;
    }

    public void setMediaDevices(Set<String> mediaDevices) {
        this.mediaDevices = mediaDevices;
    }

    public Set<String> getMediaSources() {

        return mediaSources;
    }

    public Set<String> getMediaDevices() {
        return mediaDevices;
    }

    private Set<String> mediaSources;

    private Set<String> mediaDevices;



}
