package de.fettlaus.thekraken;

import java.net.SocketException;

import de.fettlaus.thekraken.model.KrakenModel;
import de.fettlaus.thekraken.view.GuiView;

@SuppressWarnings("unused")
public class TheKraken {

	/**
	 * Launch the application.
	 * 
	 * @throws InterruptedException
	 * @throws SocketException 
	 */
	public static void main(String[] args) throws InterruptedException, SocketException {

		final Presenter pres = new Presenter(new KrakenModel(), new GuiView());

	}

}
