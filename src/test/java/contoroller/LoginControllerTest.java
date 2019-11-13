package contoroller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import common.StandaloneMvcTestViewResolver;
import constCode.ConstCode;
import controller.LoginController;
import form.LoginForm;

/**
 * @author 81802
 *
 */
@ContextConfiguration
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class LoginControllerTest {

	LoginController loginController = new LoginController();

	private MockMvc mockMvc;

	@Before
	public void setup() {
		// リクエスト情報設定
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

		MvcResult  result =
				mockMvc.perform(get("/login"))
				.andExpect(status().isOk()) // loginIndex()を実行するよう「get("/login")とする」
				.andExpect(view().name("/login")) // 表示するビューをnameに設定する
				.andReturn();

		// ここでmodelに詰められたformの値を取得
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

		LoginForm form = new LoginForm();

		MvcResult  result =
				mockMvc.perform(post("/login").flashAttr("form", form)) // flashAttrで引数を設定
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

			// 遷移先URL確認
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

		MvcResult  result =
				mockMvc.perform(post("/login").flashAttr("form", form))
				.andExpect(status().isOk())
				.andExpect(view().name("/login"))
				.andReturn();

		// 遷移先URL確認
		assertEquals(result.getResponse().getForwardedUrl(), "/search");
	}

}
