package jp.logware.custmer.logic.schedule;

import java.sql.Connection;
import java.util.List;

import jp.logware.custmer.dao.MUserDao;
import jp.logware.custmer.entity.MUserEntity;
import jp.logware.custmer.form.schedule.ViewUserActionForm;
import jp.logware.custmer.logic.MasterLogic;
import jp.logware.custmer.logic.ValueBean;
import jp.logware.custmer.subform.UserValue;

/**
 * 表示ユーザ設定 ロジッククラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */

public class ViewUserLogic {
	/**
	 * データベースコネクション
	 */
	private Connection dbConn = null;

	/**
	 * コンストラクタ
	 *
	 * @param conn
	 */
	public ViewUserLogic(Connection conn) {
		dbConn = conn;
	}

	/**
	 * ActionFormクラスへ値を代入
	 *
	 * @return ViewUserActionForm
	 */
	public ViewUserActionForm getForm() throws Exception {
		ViewUserActionForm form = new ViewUserActionForm();

		MUserDao mUserDao = new MUserDao(dbConn);
		List<MUserEntity> mUserList = mUserDao.getListSort("USER_ID");
		ValueBean userIdMap = new ValueBean();
		for (MUserEntity mUser : mUserList) {
			UserValue user = new UserValue();
			user.setUserId(mUser.getUserId());
			user.setUserName(mUser.getUserName());
			user.setDeptCode(mUser.getDeptCode());
			user.setDeptName(getDeptName(mUser.getDeptCode()));
			userIdMap.setMapData(user.getUserId(), user);
		}

		form.setUserIdMap(userIdMap);

		return form;
	}

	/**
	 * 処理メソッド
	 *
	 * @return String
	 */
	public String getDeptName(String deptCode) throws Exception {
		MasterLogic logic = new MasterLogic(dbConn);
		return logic.getMasterDeptName(deptCode);
	}
}
