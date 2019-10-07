/**
 *
 */
package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import form.BuyForm;
import form.SerchResultForm;

@RestController
public class SerchResultController {

    @RequestMapping(value = "/serchResult", method = RequestMethod.POST)
    public Model serch(Model model, SerchResultForm form) {

    	List<SerchResultForm> serchFormList = new ArrayList<SerchResultForm>();
    	SerchResultForm serchForm = new SerchResultForm();
    	SerchResultForm serchForm2 = new SerchResultForm();

    	serchForm.setItemName("えんぴつ");
    	serchForm.setItemPrice("120");

    	serchForm2.setItemName("シャーペン");
    	serchForm2.setItemPrice("300");

    	serchFormList.add(serchForm);
    	serchFormList.add(serchForm2);

    	model.addAttribute("serchFormList", serchFormList);

    	return model;
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public Model buy(Model model, BuyForm form) {




    	return model;
    }
}