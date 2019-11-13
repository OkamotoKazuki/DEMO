package contoroller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import controller.LoginController;
import form.LoginForm;

/**
 * @author 81802
 *
 */
public class LoginControllerTest {

	LoginController loginController = new LoginController();

	@Autowired
	Model model;


	@Test
	public void test001() {

		LoginForm form = new LoginForm();

		String result = loginController.loginIndex(model, form);

		assertEquals("/login", result);
	}

}
