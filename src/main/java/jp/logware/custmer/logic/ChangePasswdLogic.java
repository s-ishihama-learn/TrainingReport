package jp.logware.custmer.logic;

import java.sql.Connection;

import jp.logware.custmer.common.Utility;
import jp.logware.custmer.dao.MUserDao;
import jp.logware.custmer.entity.MUserEntity;
import jp.logware.custmer.form.ChangePasswdActionForm;

/**
 * パスワード変更 ロジッククラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */

public class ChangePasswdLogic {
	/**
	 * データベースコネクション
	 */
	private Connection dbConn = null;

	/**
	 * コンストラクタ
	 *
	 * @param conn
	 */
	public ChangePasswdLogic(Connection conn) {
		dbConn = conn;
	}

	/**
	 * ActionFormクラスへ値を代入
	 *
	 * @param String userId
	 * @return ChangePasswdActionForm
	 */
	public ChangePasswdActionForm getForm(String userId) throws Exception {
		ChangePasswdActionForm form = new ChangePasswdActionForm();

		MUserDao mUserDao = new MUserDao(dbConn);
		MUserEntity mUser = mUserDao.getValue(userId);
		if (mUser != null) {
			form.setUserId(mUser.getUserId());
			form.setNewPass("");
			form.setOldPass(Utility.decrypt(mUser.getUserPass()));
			form.setChkPass("");
		}

		return form;
	}

	/**
	 * ActionFormクラスからＤＢの更新
	 *
	 * @param form ChangePasswdActionForm
	 */
	public void updateData(ChangePasswdActionForm form) throws Exception {
		try {
			MUserDao mUserDao = new MUserDao(dbConn);
			MUserEntity mUser = mUserDao.getValue(form.getUserId());
			mUser.setUserId(form.getUserId());
			mUser.setUserPass(Utility.encrypt(form.getNewPass()));
			mUserDao.setValue(mUser);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
