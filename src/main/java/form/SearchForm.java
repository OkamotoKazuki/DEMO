package form;

import lombok.Data;

@Data
public class SearchForm {

	/** 検索名 */
	private String searchName;

	/** 商品名 */
	private String itemName;

	/** 結果コード */
	private String resultCode;

	/** メッセージ */
	private String msg;
}
