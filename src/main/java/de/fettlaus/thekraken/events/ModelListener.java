package de.fettlaus.thekraken.events;

import de.fettlaus.thekraken.model.Model;
import de.fettlaus.thekraken.view.View;

public abstract class ModelListener {
	View view;
	public ModelListener(View view) {
		this.view = view;
	}
	abstract public void fireEvent(Model model);

}