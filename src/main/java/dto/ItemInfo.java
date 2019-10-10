package dto;

import lombok.Data;

@Data
public class ItemInfo {

	/** 検索名 */
	private String searchName;

	/** 商品Id */
	private String itemId;

	/** 商品名 */
	private String itemName;

	/** 商品価格 */
	private String itemPrice;

	/** 登録日 */
	private String insertDate;

	/** 削除フラグ */
	private String deleteFlg;

	/** 商品選択状況 */
	private String itemCheck;

	/** 結果コード */
	private String resultCode;

	/** メッセージ */
	private String msg;
}
