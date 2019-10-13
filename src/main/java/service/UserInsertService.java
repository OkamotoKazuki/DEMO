package service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import constCode.ConstCode;
import dto.UserInsertInfo;
import mapper.LoginInfoSqlCommon;
import mybatis.sqlsession.MyBatisSqlSessionFactory;

@Service
public class UserInsertService {

	/** ログ準備 */
	Log log = LogFactory.getLog(LoginService.class);

	public UserInsertInfo userInsert(UserInsertInfo info) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();

		try {
			// ログイン情報をDBから取得
			LoginInfoSqlCommon mapper = sqlSession.getMapper(LoginInfoSqlCommon.class);

			// idの最大値を取得し、登録用にid+1を行う
			String userId = mapper.findMaxUserId().replace(" ", "");
			int afterId = Integer.parseInt(userId);
			afterId++;
			userId = String.format("%04d", afterId);
			info.setUserId(String.valueOf(userId));

			log.info("新規ユーザー情報登録を開始します");
			boolean result = mapper.userInsert(info);
			log.info("新規ユーザー情報登録を終了します");

			// コミット
			sqlSession.commit();

			// 登録内容確認
			mapper.findLoginInfo(info.getUserName(), info.getUserId(), "0");

			info.setResultCode(ConstCode.SUCCESS_CODE);
		} finally {
			sqlSession.close();
		}
		return info;
	}
}
