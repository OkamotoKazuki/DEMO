package mapper;

import org.mybatis.spring.annotation.MapperScan;

import dto.LoginInfo;

/**
 * LoginInfoテーブルアクセス共通インターフェース
 *
 */
// @MapperScanをかかないとマッパーとして登録されないのでかく
@MapperScan
public interface LoginInfoSqlCommon {

	/** 新規ユーザー登録 */
	void insert(LoginInfo info);

	/** ログインユーザー情報検索 */
	LoginInfo findLoginInfo(String userName, String passWord, String deleteFlg);
}
