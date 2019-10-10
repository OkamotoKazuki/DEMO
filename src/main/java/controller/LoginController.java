/**
 *
 */
package controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import constCode.ConstCode;
import dto.LoginInfo;
import dto.UserInsertInfo;
import form.LoginForm;
import form.SearchForm;
import form.UserInsertForm;
import service.LoginService;
import service.UserInsertService;

/**
 * @author PC
 *
 */
@Controller
public class LoginController {

	//@Autowired
	LoginService loginService = new LoginService();

	UserInsertService userInsertService = new UserInsertService();

	// ログ準備
	Log log = LogFactory.getLog(LoginController.class);

	/** ログイン初期表示
	 *
	 * @param model
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginIndex(Model model, LoginForm form) {
		form.setUserName("");
		form.setPassWord("");
		form.setMsgList(new ArrayList<String>());

		model.addAttribute("form", form);

		return "/login";
	}

	/** ログインボタン押下
	 *
	 * @param model
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, LoginForm form) {

		String view = null;
		// 単項目の結果が以上の場合エラーを表示して画面表示
		if (this.checkVal(form.getUserName(), form.getPassWord(), form)) {

			log.info("ログインサービスを開始します");
			LoginInfo info = loginService.loginInfoSerch(form.getUserName(), form.getPassWord(), "0");
			log.info("ログインサービスを終了します");

			BeanUtils.copyProperties(info, form);

			// DB登録有無によって遷移先指定
			if (ConstCode.SUCCESS_CODE.equals(form.getResultCode())) {
				// search.html初期表示処理
				view = "/search";
				SearchForm searchForm = new SearchForm();
				searchForm.setSearchName("");
				model.addAttribute("searchForm", searchForm);
			} else {
				view = "/login";
				form.setUserName("");
				form.setPassWord("");
				model.addAttribute("form", form);
				model.addAttribute("msgList", form.getMsgList());
			}
		}
		return view;
	}

	/**
	 * 登録画面初期表示
	 * @param model
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/insertIndex", method = RequestMethod.POST)
	public String insertIndex(Model model, UserInsertForm form) {
		form.setUserName("");
		form.setPassWord("");
		form.setMsgList(new ArrayList<String>());

		model.addAttribute("form", form);

		return "/userInsert";
	}

	/** 新規登録押下
	 *
	 * @param model
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/userInsert", method = RequestMethod.POST)
	public String userInsert(Model model, UserInsertForm form) {
		UserInsertInfo info = new UserInsertInfo();

		BeanUtils.copyProperties(form, info);

		String view = "/userInsert";

		// 単項目の結果が以上の場合エラーを表示して画面表示
		if (this.checkVal(form.getUserName(), form.getPassWord(), form)) {

			log.info("新規ユーザー登録サービスを開始します");
			UserInsertInfo info2 =userInsertService.userInsert(info);
			log.info("新規ユーザー登録サービスを終了します");

			BeanUtils.copyProperties(info2, form);

			// DB登録有無によって遷移先指定
			if (ConstCode.SUCCESS_CODE.equals(form.getResultCode())) {
				view = "/login";
			} else {
				view = "/userInsert";
			}
		}
		form.setUserName("");
		form.setPassWord("");
		model.addAttribute("form", form);
		model.addAttribute("msgList", form.getMsgList());
		return view;
	}


	/** 単項目チェックを行います
	 *
	 * @param name
	 * @param password
	 * @param form
	 * @return
	 */
	public boolean checkVal(String name, String password, UserInsertForm form) {
		boolean judge = true;

		List<String> msgList = new ArrayList<String>();
		if (StringUtils.isEmpty(name)) {
			msgList.add("名前が入力されていません。");
			judge = false;
		}
		if (StringUtils.isEmpty(password)) {
			msgList.add("パスワードが入力されていません。");
			judge = false;
		}
		form.setMsgList(msgList);
		return judge;
	}

	/**単項目チェックを行います
	 *
	 * @param name
	 * @param password
	 * @param form
	 * @return
	 */
	public boolean checkVal(String name, String password, LoginForm form) {
		boolean judge = true;

		List<String> msgList = new ArrayList<String>();
		if (StringUtils.isEmpty(name)) {
			msgList.add("名前が入力されていません。");
			judge = false;
		}
		if (StringUtils.isEmpty(password)) {
			msgList.add("パスワードが入力されていません。");
			judge = false;
		}
		form.setMsgList(msgList);
		return judge;
	}













}