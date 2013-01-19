package de.fettlaus.thekraken;

import java.util.ArrayList;
import de.fettlaus.thekraken.events.ConnectClientEvent;

public class Presenter {
	public Presenter(ModelInterface model) {
		super();
		this.model = model;
		this.views = new ArrayList<ViewInterface>();
		
		// TODO concrete model
	}
	ModelInterface model;
	ArrayList<ViewInterface> views;
	
	private void connectEvents(ViewInterface view){
		view.subscribeConnectButtonClicked(new ConnectClientEvent(model));
	}
	
	public void addView(ViewInterface view){
		views.add(view);
		connectEvents(view);
		view.run();
	}

}
