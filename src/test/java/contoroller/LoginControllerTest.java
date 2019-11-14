package contoroller;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import common.StandaloneMvcTestViewResolver;
import constCode.ConstCode;
import controller.LoginController;
import dto.LoginInfo;
import form.LoginForm;
import form.SearchForm;
import service.LoginService;

/**
 * @author 81802
 * ログインテスト
 *
 */
@ContextConfiguration
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class LoginControllerTest {

	@InjectMocks
	LoginController loginController;

	@Mock
    private LoginService loginService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		// ログイン実行初期設定
		mockMvc = MockMvcBuilders.standaloneSetup(loginController)
				.setViewResolvers(new StandaloneMvcTestViewResolver())
				.build();
	}

	/** ログイン初期表示
	 *
	 *
	 */
	@Test
	public void test001() throws Exception {
		// ログイン初期表示実行
		MvcResult  result =
				mockMvc.perform(get("/login"))
				.andExpect(status().isOk()) // loginIndex()を実行するよう「get("/login")とする」
				.andExpect(view().name("/login")) // 表示するビューをnameに設定する
				.andReturn();

		// modelに詰められたformの値を取得
		LoginForm resultForm = (LoginForm) result.getModelAndView().getModel().get("form");

		// form内容確認
		assertEquals(resultForm.getResultCode(), ConstCode.FAILE_CODE);

		// 遷移先URL確認
		assertEquals(result.getResponse().getForwardedUrl(), "/login");
	}

	/** ログインボタン押下
	 * エラーパターン
	 *
	 */
	@Test
	public void test002() throws Exception {
		// テスト対象メソッド実行
		MvcResult  result =
				mockMvc.perform(post("/login"))
				.andExpect(status().isOk())
				.andExpect(view().name("/login"))
				.andReturn();

		// 想定出力メッセージ
		List<String> msgList = Arrays.asList("ユーザー名が入力されていません。", "パスワードが入力されていません。");

		LoginForm resultForm = (LoginForm) result.getModelAndView().getModel().get("form");

		// メッセージ比較
		// ArrayListを回しながら比較
		// ラムダ内の{}は省略可能→その場合;は不要
		resultForm.getMsgList().forEach(s -> {
			assertTrue(s, (msgList.contains(s)));
		});

		assertEquals(result.getResponse().getForwardedUrl(), "/login");
	}

	/** ログインボタン押下
	 * 正常パターン
	 *
	 */
	@Test
	public void test003() throws Exception {
		LoginForm form = new LoginForm();
		form.setUserId("a");
		form.setPassWord("a");

		// serviceの返却結果作成
		LoginInfo info = new LoginInfo();
		info.setResultCode(ConstCode.SUCCESS_CODE);

		// サービスメソッドをモック化
		when(loginService.loginInfoSearch(anyString(), anyString(), anyString())).thenReturn(info);

		// テスト対象メソッド実行
		MvcResult  result =
				mockMvc.perform(post("/login").param("userName", "a").param("passWord", "a")) // paramで引数を設定
				.andExpect(status().isOk())
				.andExpect(view().name("/search"))
				.andReturn();

		// modelに詰められたformの値を取得
		SearchForm resultForm = (SearchForm) result.getModelAndView().getModel().get("searchForm");

		// 実行結果検証
		assertEquals(resultForm.getSearchName(), "");
		assertEquals(result.getResponse().getForwardedUrl(), "/search");
	}

}
