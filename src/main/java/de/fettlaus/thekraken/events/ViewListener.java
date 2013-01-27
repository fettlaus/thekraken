package de.fettlaus.thekraken.events;

import de.fettlaus.thekraken.model.Model;
import de.fettlaus.thekraken.view.View;

public abstract class ViewListener {
	Model model;
	View view;
	public ViewListener(Model model, View view) {
		this.model = model;
		this.view = view;
	}

}
