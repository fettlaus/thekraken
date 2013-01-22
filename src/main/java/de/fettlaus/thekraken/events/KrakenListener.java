package de.fettlaus.thekraken.events;

import de.fettlaus.thekraken.model.Model;
import de.fettlaus.thekraken.view.View;

public abstract class KrakenListener {
	Model model;
	public KrakenListener(Model model) {
		this.model = model;
	}
	abstract public void fireEvent(View view);

}
