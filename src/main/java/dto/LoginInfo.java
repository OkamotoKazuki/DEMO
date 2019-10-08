package dto;

import lombok.Data;

/**
 * LoginInfoテーブルDTO
 *
 */
@Data
public class LoginInfo implements Comparable<LoginInfo>{

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
	private String msg;

	@Override
	public int compareTo(LoginInfo o) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}
}
