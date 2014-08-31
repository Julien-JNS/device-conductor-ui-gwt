package fr.jjj.conductormanagerui.client.widgets.audioout;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.VerticalPanel;
import fr.jjj.conductormanagerui.shared.MediaItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jaunais on 10/08/2014.
 */
public class QueuePanel extends VerticalPanel{

    private CellList listComp;

    private static class MediaItemCell extends AbstractCell<MediaItem> {
        @Override
        public void render(Context context, MediaItem item, SafeHtmlBuilder sb) {
            if (item != null) {
                sb.appendEscaped(item.getTitle());
            }
            else
            {
                sb.appendEscaped("NULL!!!JJA");
            }
        }
    }


    public QueuePanel() {
        listComp=new CellList(new MediaItemCell());

//        List<String> test=new ArrayList<String>();
//        test.add("test");
//        test.add("test2");
//        listComp.setRowData(test);
//        listComp.setRowCount(2);
        add(listComp);
    }

    public void update(List<MediaItem> list)
    {
        list.add(new MediaItem("test"));
        listComp.setRowData(list);
        listComp.setRowCount(list.size());
    }

}
