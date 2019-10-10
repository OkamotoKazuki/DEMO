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


}
