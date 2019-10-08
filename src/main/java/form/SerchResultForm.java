package form;

import lombok.Data;

@Data
public class SerchResultForm {

	/** 商品名 */
	private String itemName;

	/** 商品価格 */
	private String itemPrice;

	/** 商品選択状況 */
	private String itemCheck;
}
