package impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dto.LoginInfo;
import mapper.LoginInfoSqlCommon;

@Transactional
@Service
public class LoginInfoImpl implements LoginInfoSqlCommon{

	@Inject
	LoginInfoSqlCommon loginInfoSqlCommon;

	@Override
	public void insert(LoginInfo info) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public LoginInfo findLoginInfo(String userName, String passWord, String deleteFlg) {
		return loginInfoSqlCommon.findLoginInfo(userName, passWord, deleteFlg);
	}

}
