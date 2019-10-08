/**
 *
 */
package controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import constCode.ConstCode;
import dto.LoginInfo;
import form.LoginForm;
import form.SerchResultForm;
import service.LoginService;

@Controller
public class LoginController {

	//@Autowired
	LoginService loginService = new LoginService();

	// ログ準備
	Log log = LogFactory.getLog(LoginController.class);

	// ログイン初期表示
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginIndex(Model model, LoginForm form) {

		return "/login";
	}

	// ログインボタン押下
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, LoginForm form) {

		log.info("サービス呼出を開始します");
		LoginInfo info =loginService.loginInfoSerch(form.getUserName(), form.getPassWord(), "0"); // "aaaa", "1111", "0"
		log.info("サービス呼出を終了します");

		BeanUtils.copyProperties(info, form);

		model.addAttribute("form", form);
		model.addAttribute("msg", form.getMsg());

		String view = null;

		// DB登録有無によって遷移先指定
		if (ConstCode.SUCCESS_CODE.equals(form.getResultCode())) {
			view = "/serch";
		} else {
			view = "/login";
		}

		return view;
	}

	@RequestMapping(value = "/serch", method = RequestMethod.POST)
	public Model serch(Model model, SerchResultForm form) {

		return model;
	}

}