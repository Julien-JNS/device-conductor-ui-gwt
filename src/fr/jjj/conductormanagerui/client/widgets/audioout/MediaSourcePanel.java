package fr.jjj.conductormanagerui.client.widgets.audioout;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import fr.jjj.conductormanagerui.shared.DeviceDesc;
import fr.jjj.conductormanagerui.shared.MediaItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by Jaunais on 11/08/2014.
 */
public class MediaSourcePanel extends VerticalPanel {


    private ListBox source;
    private ListBox navigation;
    private Button open;
    private Button add;
    private Button back;
    private final MediaSourcePanelListener listener;

    private DeviceDesc device;

    private final DeviceAudioOutServiceAsync service;

    public MediaSourcePanel(final DeviceAudioOutServiceAsync service, final MediaSourcePanelListener listener) {
        this.service = service;
        this.listener=listener;

        source = new ListBox(false);
        source.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                int selectedIndex = source.getSelectedIndex();

                if (selectedIndex >= 0)
                    service.setMediaSource(source.getItemText(selectedIndex), new AsyncCallback<Void>() {
                        @Override
                        public void onFailure(Throwable caught) {

                        }

                        @Override
                        public void onSuccess(Void result) {
                            updateNavItem();
                        }
                    });


            }
        });
        add(source);
        navigation = new ListBox(true);
        add(navigation);

        add = new Button("Add to queue");
        add.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                service.addToQueue(getSelectedNavItem(),new AsyncCallback<Void>() {
                    @Override
                    public void onFailure(Throwable caught) {

                    }

                    @Override
                    public void onSuccess(Void result) {
                        listener.itemAddedToQueue();
                    }
                });
            }
        });

        open = new Button("Open");
        open.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                selectNavItem(getSelectedNavItem());
            }
        });

        back = new Button("Back");
        back.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                moveBackToParent();
            }
        });

        HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.add(add);
        buttonPanel.add(open);
        buttonPanel.add(back);
        add(buttonPanel);

    }

    public void setDevice(DeviceDesc desc) {
        this.device = desc;
        service.getMediaSources(new AsyncCallback<Set<String>>() {
            public void onFailure(Throwable caught) {
                // Show the RPC error message to the user
                System.out.println("FAILURE!!!");
            }

            public void onSuccess(Set<String> result) {
                System.out.println("RESPONSE=" + result);
                Iterator<String> it = result.iterator();
                while (it.hasNext()) {
                    source.addItem(it.next());
                }
                DomEvent.fireNativeEvent(Document.get().createChangeEvent(), source);
            }
        });
    }

    private void updateNavItem() {
        service.getNavItems(null, new AsyncCallback<List<String>>() {
            @Override
            public void onFailure(Throwable caught) {
                System.out.println("Could not retrieve nav items");
            }

            @Override
            public void onSuccess(List<String> result) {
                System.out.println("Received nav " + result.size() + " items");
                Iterator<String> it = result.iterator();
                navigation.clear();
                while (it.hasNext()) {
                    navigation.addItem(it.next());
                }
            }
        });
    }

    private void selectNavItem(String item) {
        service.selectNavItem(item, new AsyncCallback<List<String>>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(List<String> result) {
                updateNavItem();
            }
        });
    }

    private void moveBackToParent() {
        service.moveBackToParent(new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(Void result) {
                updateNavItem();
            }
        });
    }

    private String getSelectedNavItem()
    {
        return navigation.getItemText(navigation.getSelectedIndex());
    }

}
