package service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import dto.ItemInfo;

/**
 * @author 81802
 * 検索サービステスト
 *
 */
@ContextConfiguration
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class SearchServiceTest {

	@InjectMocks
	private SearchService searchService;


	/** 検索サービス
	 * 正常パターン
	 *
	 */
	@Test
	public void test001() throws Exception {
		// 検索条件
		ItemInfo info = new ItemInfo();
		info.setSearchName("いろはす");

		List<ItemInfo> infoList2 = searchService.itemSearch(info);

		// 検証
		infoList2.forEach(s -> assertTrue(s.getItemName().contains("いろはす")));
		assertEquals(infoList2.size(), 2);
	}

}
