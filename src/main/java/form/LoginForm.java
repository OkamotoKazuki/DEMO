package form;

import java.util.List;

import lombok.Data;

@Data
public class LoginForm {

	/** ユーザーId */
	private String userId;

	/** ユーザー名 */
	private String userName;

	/** ユーザー名 */
	private String passWord;

	/** 登録日 */
	private String insertDate;

	/** ユーザー名 */
	private String deleteFlg;

	/** 認証コード */
	private String resultCode;

	/** メッセージ */
	private List<String> msgList;
}
