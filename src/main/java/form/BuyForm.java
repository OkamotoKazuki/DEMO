package form;

public class BuyForm {

	// No
	private String no;

	// 商品名
	private String itemName;

	// 商品価格
	private String itemPrice;

	// 商品選択状況
	private String itemCheck;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getItemCheck() {
		return itemCheck;
	}

	public void setItemCheck(String itemCheck) {
		this.itemCheck = itemCheck;
	}

	public String getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}
