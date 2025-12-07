package jp.logware.custmer.logic.schedule;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import jp.logware.custmer.common.Utility;
import jp.logware.custmer.dao.MUserDao;
import jp.logware.custmer.dao.ViewGroupDao;
import jp.logware.custmer.entity.MUserEntity;
import jp.logware.custmer.entity.ViewGroupEntity;
import jp.logware.custmer.form.schedule.ViewGroupUserActionForm;
import jp.logware.custmer.logic.MasterLogic;
import jp.logware.custmer.subform.UserValue;

/**
 * 表示グループ設定 ロジッククラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */

public class ViewGroupUserLogic {
	/**
	 * データベースコネクション
	 */
	private Connection dbConn = null;

	/**
	 * コンストラクタ
	 *
	 * @param conn
	 */
	public ViewGroupUserLogic(Connection conn) {
		dbConn = conn;
	}

	/**
	 * ActionFormクラスへ値を代入
	 *
	 * @param String userId
	 * @return ViewGroupUserActionForm
	 */
	public ViewGroupUserActionForm getForm(String userId) throws Exception {
		ViewGroupUserActionForm form = new ViewGroupUserActionForm();

		MUserDao mUserDao = new MUserDao(dbConn);
		List<MUserEntity> mUserList = mUserDao.getListSort("USER_ID");
		List<UserValue> userList = new ArrayList<UserValue>();
		for (MUserEntity mUser : mUserList) {
			if (!userId.equals(mUser.getUserId())) {
				UserValue user = new UserValue();
				user.setUserId(mUser.getUserId());
				user.setUserName(mUser.getUserName());
				user.setDeptCode(mUser.getDeptCode());
				user.setDeptName(getDeptName(mUser.getDeptCode()));
				userList.add(user);
			}
		}
		form.setChecked(getViewGroupUserChecked(userId));

		form.setUserList(userList);

		return form;
	}

	/**
	 * ActionFormクラスからＤＢへ値を新規登録
	 *
	 * @param form ViewGroupUserActionForm
	 */
	public void registData(ViewGroupUserActionForm form) throws Exception {
		try {
			ViewGroupDao viewGroupDao = new ViewGroupDao(dbConn);
			List<ViewGroupEntity> viewGroupList = new ArrayList<ViewGroupEntity>();
			if (form.getChecked() != null) {
				for (String user : form.getChecked()) {
					ViewGroupEntity viewGroup = new ViewGroupEntity();
					viewGroup.setUserId(form.getUserId());
					viewGroup.setViewUserId(user);
					viewGroup.setIdate(Utility.getTimestampNow());
					viewGroupList.add(viewGroup);
				}
			}
			viewGroupDao.setList(viewGroupList);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * ActionFormクラスからＤＢの削除
	 *
	 * @param form ViewGroupUserActionForm
	 */
	public void removeData(ViewGroupUserActionForm form) throws Exception {
		try {
			ViewGroupDao viewGroupDao = new ViewGroupDao(dbConn);
			viewGroupDao.removeValue(form.getUserId());

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
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

	/**
	 * 処理メソッド
	 *
	 * @return String[]
	 */
	public String[] getViewGroupUserChecked(String userId) throws Exception {
		try {
			ViewGroupDao viewGroupDao = new ViewGroupDao(dbConn);
			List<ViewGroupEntity> viewGroupList = viewGroupDao.getListSort(userId, "");
			String checked[] = new String[viewGroupList.size()];
			int i = 0;
			for (ViewGroupEntity viewGroup : viewGroupList) {
				checked[i++] = viewGroup.getViewUserId();
			}

			return checked;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
