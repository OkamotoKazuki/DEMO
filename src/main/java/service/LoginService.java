package service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import constCode.ConstCode;
import dto.LoginInfo;
import mapper.LoginInfoSqlCommon;
import mybatis.sqlsession.MyBatisSqlSessionFactory;

@Service
public class LoginService {

	/** ログ準備 */
	Log log = LogFactory.getLog(LoginService.class);

	public LoginInfo loginInfoSerch(String userName, String passWord, String deleteFlg) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();

		LoginInfo info = null;
		try {
			// ログイン情報をDBから取得
			LoginInfoSqlCommon mapper = sqlSession.getMapper(LoginInfoSqlCommon.class);

			log.info("ログイン情報検索を開始します");
			info = mapper.findLoginInfo(userName, passWord, deleteFlg);
			log.info("ログイン情報検索を終了します");

			List<String> msgList = new ArrayList<String>();

			// DBに登録あればログイン可能
			if (info == null) {
				info = new LoginInfo();
				info.setResultCode(ConstCode.FAILE_CODE);
				msgList.add("新規登録してください。");
				info.setMsgList(msgList);
			} else {
				info.setResultCode(ConstCode.SUCCESS_CODE);
			}
		} finally {
			sqlSession.close();
		}
		return info;
	}
}
