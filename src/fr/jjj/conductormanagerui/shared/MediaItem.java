package fr.jjj.conductormanagerui.shared;

import java.io.Serializable;

/**
 * Created by Jaunais on 10/08/2014.
 */
public class MediaItem implements Serializable{

    private String title;

    public MediaItem()
    {

    }

    public MediaItem(String title) {
        this();
        this.title=title;
    }

    public String getTitle()
    {
        return title;
    }
}
