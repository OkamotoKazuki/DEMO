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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import constCode.ConstCode;
import dto.ItemInfo;
import form.BuyForm;
import form.SearchForm;
import form.SearchResultForm;
import service.SearchService;

@Controller
public class SearchController {

	// ログ準備
	Log log = LogFactory.getLog(SearchController.class);

	SearchService searchService = new SearchService();

	/** 検索ボタン押下
	 *
	 * @param model
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/searchResult", method = RequestMethod.POST)
	public String search(Model model, SearchResultForm form) {
		ItemInfo info = new ItemInfo();

		BeanUtils.copyProperties(form, info);

		String view = null;

		log.info("商品検索サービスを開始します");
		ArrayList<ItemInfo> infoList = searchService.ltemSerch(info);
		log.info("商品検索サービスを終了します");

		List<SearchResultForm> searchFormList = new ArrayList<SearchResultForm>();

		// 検索結果をformに設定
		for (ItemInfo item : infoList) {
			SearchResultForm serchForm = new SearchResultForm();
			BeanUtils.copyProperties(item, serchForm);
			searchFormList.add(serchForm);
		}

		SearchForm searchForm = new SearchForm();
		searchForm.setSearchName("");
		model.addAttribute("searchForm", searchForm);

		// DB登録有無によって遷移先指定
		if (ConstCode.SUCCESS_CODE.equals(infoList.get(0).getResultCode())) {
			view = "/searchResult";
			model.addAttribute("searchFormList", searchFormList);
		} else {
			model.addAttribute("msg", infoList.get(0).getMsg());
			view = "/search";
		}
		return view;
	}


	/** 購入ボタン押下
	 *
	 * @param model
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	public Model buy(Model model, BuyForm form) {




		return model;
	}











}