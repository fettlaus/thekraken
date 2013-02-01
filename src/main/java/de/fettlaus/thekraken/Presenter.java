package de.fettlaus.thekraken;

import java.util.ArrayList;

import com.google.common.eventbus.EventBus;

import de.fettlaus.thekraken.events.ConnectClientEvent;
import de.fettlaus.thekraken.model.Model;
import de.fettlaus.thekraken.view.View;

public class Presenter {
	EventBus event;
	public Presenter(Model model) {
		super();
		this.model = model;
		this.views = new ArrayList<View>();
		event = new EventBus();
		
		// TODO concrete model
	}
	Model model;
	ArrayList<View> views;
	
	private void connectEvents(View view){
		view.subscribeConnectButtonClicked(new ConnectClientEvent(model,view));
		model.subscribeNewMessage(null);
	}
	
	public void addView(View view){
		views.add(view);
		connectEvents(view);
	}

}
