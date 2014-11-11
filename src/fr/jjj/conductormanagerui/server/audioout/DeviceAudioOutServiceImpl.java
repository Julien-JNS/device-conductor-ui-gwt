package fr.jjj.conductormanagerui.server.audioout;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import fr.jjj.conductor.model.Device;
import fr.jjj.conductor.model.MediaItemDesc;
import fr.jjj.conductormanager.ui.DeviceAudioOutPresenter;
import fr.jjj.conductormanager.ui.DeviceAudioOutView;
import fr.jjj.conductormanagerui.client.widgets.audioout.DeviceAudioOutService;
import fr.jjj.conductormanagerui.shared.DeviceDesc;
import fr.jjj.conductormanagerui.shared.MediaItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Jaunais on 11/08/2014.
 */
public class DeviceAudioOutServiceImpl extends RemoteServiceServlet implements DeviceAudioOutService,DeviceAudioOutView{

    private Log log= LogFactory.getLog(this.getClass());

    private DeviceAudioOutPresenter presenter;

    private List<String> navigationItems;

    public DeviceAudioOutServiceImpl() {
        presenter=new DeviceAudioOutPresenter(this);
    }

    @Override
    public void setDevice(DeviceDesc desc) throws IllegalArgumentException {
        presenter.setDevice(desc.getLabel());
    }

    @Override
    public List<MediaItem> getQueue() throws IllegalArgumentException {
        List<MediaItem> list=new ArrayList<MediaItem>();

        Iterator<MediaItemDesc> itItem=presenter.getQueue().iterator();
        while(itItem.hasNext())
        {
            MediaItemDesc mediaItemDesc=itItem.next();
            MediaItem mediaItem=new MediaItem(mediaItemDesc.getTitle());
            list.add(mediaItem);
        }
        return list;
    }

    @Override
    public String getStatus() throws IllegalArgumentException {
        return null;
    }

    @Override
    public Set<String> getMediaSources() throws IllegalArgumentException {
        return presenter.getMediaSources();
    }

    @Override
    public String getDeviceLabel() {
        return null;
    }

    @Override
    public void setNavigation(List<String> strings) {
        navigationItems=strings;
    }

    @Override
    public List<String> getNavItems(String ref) throws IllegalArgumentException {
        log.info("Requesting nav items for ref " + ref);
        return navigationItems;
    }

    @Override
    public void setMediaSource(String mediaSource) throws IllegalArgumentException {
        presenter.setMediaSource(mediaSource);
    }

    @Override
    public void selectNavItem(String item) throws IllegalArgumentException {
        log.info("Selected nav item " + item);
        presenter.navigationItemSelected(item);
    }

    @Override
    public void moveBackToParent() throws IllegalArgumentException {
        log.info("Move back to parent... ");
        presenter.moveBackToParent();
    }

    @Override
    public void addToQueue(String item) throws IllegalArgumentException {
        presenter.addToQueue(item);

    }

    @Override
    public void setQueue(List<String> strings) {

    }

    public void play(String item)
    {
        log.info("Play "+item);
        presenter.play(item);
    }

    @Override
    public void command(DeviceDesc.Command command) throws IllegalArgumentException {
        log.info("Command "+command);
        DeviceAudioOutPresenter.Command presenterCommand=convertToPresenter(command);
        presenter.command(presenterCommand);
    }

    private DeviceAudioOutPresenter.Command convertToPresenter(DeviceDesc.Command command)
    {
        DeviceAudioOutPresenter.Command PresenterCommand=null;
        switch(command)
        {
            case VOLUP:
                PresenterCommand=DeviceAudioOutPresenter.Command.VOLUP;
            break;
            case VOLDOWN:
                PresenterCommand=DeviceAudioOutPresenter.Command.VOLDOWN;
            break;
            case PAUSE:
                PresenterCommand=DeviceAudioOutPresenter.Command.PAUSE;
            break;
            case NEXT:
                PresenterCommand=DeviceAudioOutPresenter.Command.NEXT;
            break;
            case PREV:
                PresenterCommand=DeviceAudioOutPresenter.Command.PREV;
            break;
            case STOP:
                PresenterCommand=DeviceAudioOutPresenter.Command.STOP;
            break;
        }
        return PresenterCommand;
    }

}
