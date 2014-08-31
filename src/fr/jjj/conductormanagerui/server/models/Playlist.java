package fr.jjj.conductormanagerui.server.models;

public class Playlist {
	
	private final String name;

	private final String id;
	
	public Playlist(String name, String id)
	{
		this.name=name;
		this.id=id;
	}
	
	public final String getName() {
		return name;
	}

	public final String getId() {
		return id;
	}
}
