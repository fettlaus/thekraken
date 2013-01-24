package de.fettlaus.thekraken.events;

import de.fettlaus.thekraken.model.Model;
import de.fettlaus.thekraken.view.View;

public abstract class ViewListener {
	Model model;
	public ViewListener(Model model) {
		this.model = model;
	}
	abstract public void fireEvent(View view);

}
