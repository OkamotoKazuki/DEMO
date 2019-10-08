package service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import constCode.ConstCode;
import dto.LoginInfo;
import impl.LoginInfoImpl;
import impl.MyBatisSqlSessionFactory;
import mapper.LoginInfoSqlCommon;

@Service
public class LoginService {

	LoginInfoImpl loginInfoImpl = new LoginInfoImpl();

	// ログ準備
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

			// DBに登録あればログイン可能
			if (info == null) {
				info = new LoginInfo();
				info.setResultCode(ConstCode.FAILE_CODE);
				info.setMsg("新規登録してください。");
			} else {
				info.setResultCode(ConstCode.SUCCESS_CODE);
			}
		} finally {
			sqlSession.close();
		}
		return info;
	}
}
