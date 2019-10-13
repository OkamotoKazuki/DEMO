package mapper;

import java.util.ArrayList;

import org.mybatis.spring.annotation.MapperScan;

import dto.ItemInfo;

/**
 * ItemInfoテーブルアクセス共通インターフェース
 *
 */
@MapperScan
public interface ItemInfoSqlCommon {

	/** 商品情報検索 */
	ArrayList<ItemInfo> itemSearch(ItemInfo info);

	/** 商品購入更新 */
	void itemUpdate(String stock, String itemId);

	/** itemId取得 */
	String findItemId(String ItemName);
}
