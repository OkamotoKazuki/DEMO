package service;

import java.util.ArrayList;
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
public class SearchService {

	/** ログ準備 */
	Log log = LogFactory.getLog(SearchService.class);

	public List<ItemInfo> ltemSearch(ItemInfo info) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();

		List<ItemInfo> infoList = null;
		try {
			// ログイン情報をDBから取得
			ItemInfoSqlCommon mapper = sqlSession.getMapper(ItemInfoSqlCommon.class);

			log.info("ログイン情報検索を開始します");
			infoList = mapper.itemSearch(info);
			log.info("ログイン情報検索を終了します");

			// DBに登録あればログイン可能
			if (infoList == null) {
				infoList = new ArrayList<ItemInfo>();
				infoList.get(0).setResultCode(ConstCode.FAILE_CODE);
				infoList.get(0).setMsg("検索結果がありません。");
			} else {
				infoList.get(0).setResultCode(ConstCode.SUCCESS_CODE);
			}
		} finally {
			sqlSession.close();
		}
		return infoList;
	}
}
