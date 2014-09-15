package fr.jjj.conductormanagerui.client.widgets.audioout;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import fr.jjj.conductormanagerui.shared.DeviceDesc;
import fr.jjj.conductormanagerui.shared.MediaItem;

import java.util.List;
import java.util.Set;

/**
 * Created by Jaunais on 11/08/2014.
 */
@RemoteServiceRelativePath("audioout")
public interface DeviceAudioOutService extends RemoteService {

    void setDevice(DeviceDesc desc) throws IllegalArgumentException;

    List<MediaItem> getQueue() throws IllegalArgumentException;

    Set<String> getMediaSources() throws IllegalArgumentException;

    String getStatus() throws IllegalArgumentException;

    void setMediaSource(String mediaSource) throws IllegalArgumentException;

    List<String> getNavItems(String ref) throws IllegalArgumentException;

    void selectNavItem(String item) throws IllegalArgumentException;

    void addToQueue(String item) throws IllegalArgumentException;

    void play(String item) throws IllegalArgumentException;
}
