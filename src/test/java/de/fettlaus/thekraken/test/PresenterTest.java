/**
 * 
 */
package de.fettlaus.thekraken.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.fettlaus.thekraken.Presenter;

/**
 * @author bachelor
 *
 */
public class PresenterTest {
	MockModelTest model;
	MockViewTest view;
	Presenter pres;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		model = new MockModelTest();
		view = new MockViewTest();
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
		
		//fail("Not yet implemented");
	}

}
