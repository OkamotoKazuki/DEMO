package form;

import lombok.Data;

@Data
public class BuyForm {

	/** No */
	private String no;

	/** 商品名 */
	private String itemName;

	/** 商品価格 */
	private String itemPrice;

	/** 商品選択状況 */
	private String itemCheck;
}
