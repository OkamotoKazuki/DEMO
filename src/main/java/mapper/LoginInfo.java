package mapper;

/**
 * LoginInfoテーブルDTO
 *
 */
public class LoginInfo {

	// ユーザーId
	private String userId;

	// ユーザー名
	private String userName;

	// ユーザー名
	private String passsWord;

	// 登録日
	private String insertDate;

	// ユーザー名
	private String deleteFlg;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasssWord() {
		return passsWord;
	}

	public void setPasssWord(String passsWord) {
		this.passsWord = passsWord;
	}

	public String getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	public String getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
}
