/**
 * 
 */
package de.fettlaus.thekraken.test;

import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.fettlaus.thekraken.Presenter;
import de.fettlaus.thekraken.model.Model;
import de.fettlaus.thekraken.view.View;

/**
 * @author bachelor
 *
 */
public class PresenterTest {
	Model model;
	View view;
	Presenter pres;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		model = mock(Model.class);
		view = mock(View.class);
		pres = new Presenter(model,view);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		model.getConnection(0);
		verify(model).getConnection(0);
		//fail("Not yet implemented");
	}

}
