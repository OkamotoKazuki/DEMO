package service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import constCode.ConstCode;
import dto.ItemInfo;
import mapper.ItemInfoSqlCommon;
import mybatis.sqlsession.MyBatisSqlSessionFactory;

@Service
public class BuyService {

	/** ログ準備 */
	Log log = LogFactory.getLog(BuyService.class);

	public ItemInfo buyltem(ItemInfo info) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();

		List<ItemInfo> infoList = null;
		try {
			// ログイン情報をDBから取得
			ItemInfoSqlCommon mapper = sqlSession.getMapper(ItemInfoSqlCommon.class);

			log.info("商品情報検索を開始します");
			infoList = mapper.itemSearch(info);

			String itemId = infoList.get(0).getItemId();
			String stock = infoList.get(0).getStock();

			log.info("商品情報検索を終了します");

			// 在庫数確認
			if (ConstCode.ZERO.equals(infoList.get(0).getStock())) {
				info.setResultCode(ConstCode.FAILE_CODE);
				info.setMsg("在庫がありません。");
			} else {
				log.info("商品情報更新を開始します");
				mapper.itemUpdate(itemId, stock);
				log.info("商品情報更新を終了します");

				sqlSession.commit();
				info.setResultCode(ConstCode.SUCCESS_CODE);
			}
		} finally {
			sqlSession.close();
		}
		return info;
	}
}
