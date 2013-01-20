package de.fettlaus.thekraken.events;

import de.fettlaus.thekraken.ModelInterface;
import de.fettlaus.thekraken.ViewInterface;

public abstract class KrakenListener {
	ModelInterface model;
	public KrakenListener(ModelInterface model) {
		this.model = model;
	}
	abstract public void fireEvent(ViewInterface view);

}
