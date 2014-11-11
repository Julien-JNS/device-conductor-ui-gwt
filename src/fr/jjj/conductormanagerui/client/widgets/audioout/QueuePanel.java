package fr.jjj.conductormanagerui.client.widgets.audioout;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.*;
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
    private final ListDataProvider<String> dataProvider;

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
//        listComp.setPageSize(30);
//        listComp.setKeyboardPagingPolicy(HasKeyboardPagingPolicy.KeyboardPagingPolicy.INCREASE_RANGE);
//        listComp.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.BOUND_TO_SELECTION);


        dataProvider = new ListDataProvider<String>();
        dataProvider.addDataDisplay(listComp);

//        List<String> list=dataProvider.getList();
//        for(int i=1;i<100;i++)
//        {
//            list.add("test"+i);
//        }

//        ScrollPager scrollPager=new ScrollPager(listComp);

//        AbstractPager pager=new SimplePager();
//        AbstractPager pager=new ShowMorePagerPanel();
//        AbstractPager pager=new ScrollPager(listComp);
//        pager.setDisplay(listComp);


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
        ScrollPanel scrollPanel=new ScrollPanel(listComp);
        scrollPanel.setHeight("100%");
//        add(pager);
//        add(listComp);
        add(scrollPanel);

    }

    public void update(List<MediaItem> list) {
        //list.add(new MediaItem("test"));
        List<String> newList=new ArrayList<String>();

        Iterator<MediaItem> itItem=list.iterator();
        while(itItem.hasNext()) {
            newList.add(itItem.next().getTitle());
        }

        dataProvider.setList(newList);
        listComp.setVisibleRange(0,newList.size());
//        listComp.setRowData(0,newList);
//        listComp.setRowCount(newList.size(),true);
    }

    public String getSelectedItem()
    {
        final SingleSelectionModel<String> selectionModel=(SingleSelectionModel<String>)listComp.getSelectionModel();
        return selectionModel.getSelectedObject();
    }

    private class ScrollPager extends AbstractPager
    {
        private final ScrollPanel scrollable = new ScrollPanel();

        private int lastScrollPos = 0;

        public ScrollPager(CellList display)
        {
            initWidget(scrollable);

            scrollable.addScrollHandler(new ScrollHandler() {
                public void onScroll(ScrollEvent event) {
                    // If scrolling up, ignore the event.
                    int oldScrollPos = lastScrollPos;
                    lastScrollPos = scrollable.getVerticalScrollPosition();
                    if (oldScrollPos >= lastScrollPos) {
                        return;
                    }

                    HasRows display = getDisplay();
                    if (display == null) {
                        return;
                    }
                    int maxScrollTop = scrollable.getWidget().getOffsetHeight()
                            - scrollable.getOffsetHeight();
                    if (lastScrollPos >= maxScrollTop) {
                        // We are near the end, so increase the page size.
                        int newPageSize = Math.min(
                                display.getVisibleRange().getLength() + 20,
                                display.getRowCount());
                        display.setVisibleRange(0, newPageSize);
                    }
                }
            });

            scrollable.setWidget(display);
            super.setDisplay(display);
        }

        @Override
        protected void onRangeOrRowCountChanged() {

        }
    }

    public class ShowMorePagerPanel extends AbstractPager {

        /**
         * The default increment size.
         */
        private static final int DEFAULT_INCREMENT = 20;

        /**
         * The increment size.
         */
        private int incrementSize = DEFAULT_INCREMENT;

        /**
         * The last scroll position.
         */
        private int lastScrollPos = 0;

        /**
         * The scrollable panel.
         */
        private final ScrollPanel scrollable = new ScrollPanel();

        /**
         * Construct a new {@link ShowMorePagerPanel}.
         */
        public ShowMorePagerPanel() {
            initWidget(scrollable);

            // Do not let the scrollable take tab focus.
            scrollable.getElement().setTabIndex(-1);

            // Handle scroll events.
            scrollable.addScrollHandler(new ScrollHandler() {
                public void onScroll(ScrollEvent event) {
                    // If scrolling up, ignore the event.
                    int oldScrollPos = lastScrollPos;
                    lastScrollPos = scrollable.getVerticalScrollPosition();
                    if (oldScrollPos >= lastScrollPos) {
                        return;
                    }

                    HasRows display = getDisplay();
                    if (display == null) {
                        return;
                    }
                    int maxScrollTop = scrollable.getWidget().getOffsetHeight()
                            - scrollable.getOffsetHeight();
                    if (lastScrollPos >= maxScrollTop) {
                        // We are near the end, so increase the page size.
                        int newPageSize = Math.min(
                                display.getVisibleRange().getLength() + incrementSize,
                                display.getRowCount());
                        display.setVisibleRange(0, newPageSize);
                    }
                }
            });
        }

        /**
         * Get the number of rows by which the range is increased when the scrollbar
         * reaches the bottom.
         *
         * @return the increment size
         */
        public int getIncrementSize() {
            return incrementSize;
        }

        @Override
        public void setDisplay(HasRows display) {
            assert display instanceof Widget : "display must extend Widget";
            scrollable.setWidget((Widget) display);
            super.setDisplay(display);
        }

        /**
         * Set the number of rows by which the range is increased when the scrollbar
         * reaches the bottom.
         *
         * @param incrementSize the incremental number of rows
         */
        public void setIncrementSize(int incrementSize) {
            this.incrementSize = incrementSize;
        }

        @Override
        protected void onRangeOrRowCountChanged() {
        }
    }
}
