/**
 * 
 */
package de.fettlaus.thekraken.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.fettlaus.thekraken.Presenter;
import de.fettlaus.thekraken.tests.mocks.MockModel;
import de.fettlaus.thekraken.tests.mocks.MockView;

/**
 * @author bachelor
 *
 */
public class PresenterTest {
	MockModel model;
	MockView view;
	Presenter pres;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		model = new MockModel();
		view = new MockView();
		pres = new Presenter(model);
		pres.addView(view);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		fail("Not yet implemented");
	}

}
