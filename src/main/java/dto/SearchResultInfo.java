package dto;

import java.util.List;

import lombok.Data;

@Data
public class SearchResultInfo {

	/** 商品名 */
	private String itemName;

	/** 商品価格 */
	private String itemPrice;

	/** 商品選択状況 */
	private String itemCheck;

	/** 結果コード */
	private String resultCode;

	/** メッセージ */
	private List<String> msgList;
}
