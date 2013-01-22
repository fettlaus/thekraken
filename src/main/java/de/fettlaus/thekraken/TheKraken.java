package de.fettlaus.thekraken;

import java.util.Locale;

import de.fettlaus.thekraken.model.KrakenModel;
import de.fettlaus.thekraken.view.GuiView;
import de.fettlaus.thekraken.Presenter;




@SuppressWarnings("unused")
public class TheKraken {

	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		//Locale.setDefault(Locale.GERMAN);
		//Locale.setDefault(Locale.GERMANY);
		Presenter pres = new Presenter(new KrakenModel());
		pres.addView(new GuiView());
		
		//EventQueue.invokeLater( );

	}

}
