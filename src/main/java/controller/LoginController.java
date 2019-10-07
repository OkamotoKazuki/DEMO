/**
 *
 */
package controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import form.LoginForm;
import form.SerchResultForm;

@RestController
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Model login(Model model, LoginForm form) {






    	return model;
    }

    @RequestMapping(value = "/serch", method = RequestMethod.POST)
    public Model serch(Model model, SerchResultForm form) {

    	return model;
    }

}