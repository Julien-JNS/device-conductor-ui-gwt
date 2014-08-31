package fr.jjj.conductormanagerui.client.widgets.audioout;

import com.google.gwt.user.client.rpc.AsyncCallback;
import fr.jjj.conductormanagerui.shared.ConductorDesc;
import fr.jjj.conductormanagerui.shared.DeviceDesc;
import fr.jjj.conductormanagerui.shared.MediaItem;

import java.util.List;
import java.util.Set;

/**
 * Created by Jaunais on 11/08/2014.
 */
public interface DeviceAudioOutServiceAsync {

    void setDevice(DeviceDesc desc, AsyncCallback<Void> callback) throws IllegalArgumentException;

    void getQueue(AsyncCallback<List<MediaItem>> callback) throws IllegalArgumentException;

    void getMediaSources(AsyncCallback<Set<String>> callback) throws IllegalArgumentException;

    void getStatus(AsyncCallback<Set<ConductorDesc>> callback) throws IllegalArgumentException;

    void setMediaSource(String mediaSource, AsyncCallback<Void> callback) throws IllegalArgumentException;

    void getNavItems(String ref,AsyncCallback<List<String>> callback) throws IllegalArgumentException;

    void selectNavItem(String item,AsyncCallback<List<String>> callback) throws IllegalArgumentException;

    void addToQueue(String item,AsyncCallback<Void> callback) throws IllegalArgumentException;
}
