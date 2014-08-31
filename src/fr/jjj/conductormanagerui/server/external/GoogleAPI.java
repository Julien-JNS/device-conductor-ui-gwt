package fr.jjj.conductormanagerui.server.external;

import java.util.ArrayList;
import java.util.Collection;

//import gmusic.api.impl.GoogleMusicAPI;
//import gmusic.api.impl.InvalidCredentialsException;
//import gmusic.api.model.Playlist;
//import gmusic.api.model.Playlists;

public class GoogleAPI {

	private final String EMAIL = "majulou.jaunais@gmail.com";
	private final String PASSWORD = "L29M17!M18J15";

	//private GoogleMusicAPI api;

	public GoogleAPI() {
//		api = new GoogleMusicAPI();
//
//		try {
//			api.login(EMAIL, PASSWORD);
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (URISyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvalidCredentialsException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	//	System.out.println("SERVER:api="+api);

	}

	public Collection<fr.jjj.conductormanagerui.server.models.Playlist> getPlaylists() {
		Collection<fr.jjj.conductormanagerui.server.models.Playlist> list=new ArrayList<fr.jjj.conductormanagerui.server.models.Playlist>();
//		try {
//			Playlists playlists=api.getAllPlaylists();
//
//			for(Playlist playlist:playlists.getPlaylists())
//			{
//				list.add(new fr.jjj.conductormanagerui.server.models.Playlist(playlist.getTitle(),playlist.getPlaylistId()));
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (URISyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}catch(IllegalStateException e)
//		{
//			e.printStackTrace();
//		}
return list;
	}
}
