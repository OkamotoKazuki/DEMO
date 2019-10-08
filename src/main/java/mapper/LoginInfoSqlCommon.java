package mapper;

import org.mybatis.spring.annotation.MapperScan;

import dto.LoginInfo;
import dto.UserInsertInfo;

/**
 * LoginInfoテーブルアクセス共通インターフェース
 *
 */
// @MapperScanをかかないとマッパーとして登録されないのでかく
@MapperScan
public interface LoginInfoSqlCommon {

	/** 新規ユーザー登録 */
	void userInsert(UserInsertInfo info);

	/** ログインユーザー情報検索 */
	LoginInfo findLoginInfo(String userName, String passWord, String deleteFlg);
}
