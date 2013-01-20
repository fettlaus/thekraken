package de.fettlaus.thekraken;

import java.util.Locale;

import de.fettlaus.thekraken.view.View;
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
		Presenter pres = new Presenter(new Model());
		pres.addView(new View());
		
		//EventQueue.invokeLater( );

	}

}
