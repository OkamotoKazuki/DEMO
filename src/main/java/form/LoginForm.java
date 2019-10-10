package form;

import java.util.List;

import lombok.Data;

@Data
public class LoginForm {

	/** ユーザーId */
	private String userId;

	/** ユーザー名 */
	private String userName;

	/** パスワード */
	private String passWord;

	/** 登録日 */
	private String insertDate;

	/** 削除フラグ */
	private String deleteFlg;

	/** 認証結果コード */
	private String resultCode;

	/** メッセージ */
	private List<String> msgList;
}
