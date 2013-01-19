package de.fettlaus.thekraken;

import de.fettlaus.thekraken.tests.mocks.MockModel;
import de.fettlaus.thekraken.tests.mocks.MockView;
import de.fettlaus.thekraken.view.View;

@SuppressWarnings("unused")
public class TheKraken {

	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		Presenter pres = new Presenter(new MockModel());
		pres.addView(new View());
		//EventQueue.invokeLater( );

	}

}
