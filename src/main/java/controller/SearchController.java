/**
 *
 */
package controller;

import java.util.ArrayList;
import java.util.List;

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
import form.SearchForm;
import form.SearchResultForm;
import service.SearchService;

@Controller
public class SearchController {

	// ログ準備
	Log log = LogFactory.getLog(SearchController.class);

	@Autowired
	SearchService searchService;

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
		List<ItemInfo> infoList = searchService.itemSearch(info);
		log.info("商品検索サービスを終了します");

		List<SearchResultForm> searchFormList = new ArrayList<SearchResultForm>();

		// 検索結果をformに設定
		for (ItemInfo item : infoList) {
			SearchResultForm searchForm = new SearchResultForm();
			BeanUtils.copyProperties(item, searchForm);
			searchFormList.add(searchForm);
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









}