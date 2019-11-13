/**
 *
 */
package controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import constCode.ConstCode;
import dto.ItemInfo;
import form.BuyForm;
import service.BuyService;

@Controller
public class BuyController {

	// ログ準備
	Log log = LogFactory.getLog(BuyController.class);

	@Autowired
	BuyService buyService;

	/** 購入ボタン押下
	 *
	 * @param model
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	public String buy(Model model, BuyForm form) {
		ItemInfo info = new ItemInfo();

		BeanUtils.copyProperties(form, info);

		String view = null;

		log.info("商品検索サービスを開始します");
		info = buyService.buyItem(info);
		log.info("商品検索サービスを終了します");

		// DB更新有無によって遷移先指定
		if (ConstCode.SUCCESS_CODE.equals(info.getResultCode())) {
			view = "/buyComplete";
		} else {
			view = "/seachResult";
			model.addAttribute("msg", info.getMsg());
		}
		return view;
	}
}