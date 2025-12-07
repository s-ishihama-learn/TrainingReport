package jp.logware.custmer.logic;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jp.logware.custmer.common.Utility;
import jp.logware.custmer.dao.InfoDao;
import jp.logware.custmer.dao.MUserDao;
import jp.logware.custmer.entity.InfoEntity;
import jp.logware.custmer.entity.MUserEntity;
import jp.logware.custmer.form.LoginActionForm;
import jp.logware.custmer.subform.InfoValue;

/**
 * ログイン ロジッククラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */

public class LoginLogic {
	/**
	 * データベースコネクション
	 */
	private Connection dbConn = null;

	/**
	 * コンストラクタ
	 *
	 * @param conn
	 */
	public LoginLogic(Connection conn) {
		dbConn = conn;
	}

	/**
	 * ActionFormクラスへ値を代入
	 * @return LoginActionForm
	 */
	public LoginActionForm getForm() throws Exception {
		InfoDao infoDao = new InfoDao(dbConn);
		String limitDate = Utility.getNowDate();

		LoginActionForm form = new LoginActionForm();
		String where = "LIMIT_DATE>='" + Utility.toDate(limitDate) + "'";
		List<InfoEntity> infoList = infoDao.getListWhereSort(where, "DATE DESC");
		List<InfoValue> infomList = new ArrayList<InfoValue>();
		for (InfoEntity info : infoList) {
			InfoValue infom = new InfoValue();
			infom.setInfoDate(getDateString(info.getDate()));
			infom.setInfoBody(changeCrToBr(info.getBody()));
			infomList.add(infom);
		}
		form.setInfoList(infomList);

		return form;
	}

	/**
	 * 参照変換処理
	 *
	 * @param date Date
	 * @return String
	 */
	public String getDateString(Date date) throws Exception {
		return Utility.getDate(date);
	}

	/**
	 * 処理メソッド
	 *
	 * @return boolean
	 */
	public boolean authUser(String userId, String passwd) throws Exception {
		MUserDao mUserDao = new MUserDao(dbConn);
		MUserEntity mUser = mUserDao.getValue(userId);
		if (mUser != null && Utility.decrypt(mUser.getUserPass()).equals(passwd)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ユーザ情報未登録チェック
	 * @return boolean
	 */
	public boolean initUser(String userId) throws Exception {
		MUserDao mUserDao = new MUserDao(dbConn);
		int n = mUserDao.count();
		if (n == 0 && userId.equals("admin")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 改行文字列を改行表示に変換
	 * @param str
	 * @return
	 */
	private String changeCrToBr(String str){
		return str.replaceAll("\r\n", "<br>").replaceAll("\n", "<br>");
	}
}
