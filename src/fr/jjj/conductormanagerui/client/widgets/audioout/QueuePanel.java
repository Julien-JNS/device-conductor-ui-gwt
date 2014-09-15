package fr.jjj.conductormanagerui.client.widgets.audioout;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import fr.jjj.conductormanagerui.shared.MediaItem;

import java.lang.Override;
import java.lang.String;
import java.lang.Throwable;
import java.lang.Void;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Jaunais on 10/08/2014.
 */
public class QueuePanel extends VerticalPanel {

    private CellList listComp;

    private DeviceAudioOutServiceAsync service;

    private static class MediaItemCell extends AbstractCell<MediaItem> {
        @Override
        public void render(Context context, MediaItem item, SafeHtmlBuilder sb) {
            if (item != null) {
                sb.appendEscaped(item.getTitle());
            } else {
                sb.appendEscaped("NULL!!!JJA");
            }
        }
    }

    public QueuePanel(final DeviceAudioOutServiceAsync service) {

        this.service = service;

        ProvidesKey<MediaItem> keyProvider = new ProvidesKey<MediaItem>() {
            public Object getKey(MediaItem item) {
                // Always do a null check.
                return (item == null) ? null : item.getTitle();
            }
        };


        listComp = new CellList<String>(new TextCell()/*new MediaItemCell(),keyProvider*/);
        // Add a selection model to handle user selection.
        //final SingleSelectionModel<MediaItem> selectionModel = new SingleSelectionModel<MediaItem>();
        final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<String>();

        listComp.setSelectionModel(selectionModel);
//        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
//            public void onSelectionChange(SelectionChangeEvent event) {
//                Window.alert("You selected: "+event.getSource());
//                //MediaItem selectedItem = selectionModel.getSelectedObject();
//                String selectedItem = selectionModel.getSelectedObject();
//                final SingleSelectionModel<String> selectionModel=(SingleSelectionModel<String>)listComp.getSelectionModel();
//                Window.alert("model " + selectionModel.getSelectedObject() + "selected:");// + selectionModel.getSelectedObject());
//                if (selectedItem != null) {
//                    Window.alert("You selected: " + selectedItem);
//                }
//            }
//        });

        add(listComp);

        HorizontalPanel buttonPanel = new HorizontalPanel();
        Button play = new Button("Play");
        play.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                final SingleSelectionModel<String> selectionModel=(SingleSelectionModel<String>)listComp.getSelectionModel();
                Window.alert("model " + selectionModel + "selected:");// + selectionModel.getSelectedObject());
                service.play(selectionModel.getSelectedObject(), new AsyncCallback<Void>() {
                    @Override
                    public void onFailure(Throwable throwable) {

                    }

                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
            }
        });
        buttonPanel.add(play);
        add(buttonPanel);
    }

    public void update(List<MediaItem> list) {
        //list.add(new MediaItem("test"));
        List<String> newList=new ArrayList<String>();

        Iterator<MediaItem> itItem=list.iterator();
        while(itItem.hasNext()) {
            newList.add(itItem.next().getTitle());
        }
        listComp.setRowData(newList);
        listComp.setRowCount(newList.size());
    }

}
