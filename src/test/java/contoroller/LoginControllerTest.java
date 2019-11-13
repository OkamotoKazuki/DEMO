package contoroller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;

import controller.LoginController;
import form.LoginForm;

/**
 * @author 81802
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
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
