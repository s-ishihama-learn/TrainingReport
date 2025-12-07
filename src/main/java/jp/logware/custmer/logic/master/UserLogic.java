package jp.logware.custmer.logic.master;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts.util.MessageResources;

import jp.logware.custmer.common.Param;
import jp.logware.custmer.common.Utility;
import jp.logware.custmer.dao.MUserDao;
import jp.logware.custmer.entity.MUserEntity;
import jp.logware.custmer.form.master.UserEditActionForm;
import jp.logware.custmer.form.master.UserListActionForm;
import jp.logware.custmer.logic.MasterLogic;
import jp.logware.custmer.logic.SelectBoxLogic;
import jp.logware.custmer.subform.UserValue;

/**
 * 社員編集 ロジッククラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */

public class UserLogic {
	/**
	 * データベースコネクション
	 */
	private Connection dbConn = null;

	/**
	 * メッセージリソース
	 */
	private MessageResources resource = null;

	/**
	 * コンストラクタ
	 *
	 * @param conn
	 */
	public UserLogic(Connection conn, MessageResources res) {
		dbConn = conn;
		resource = res;
	}

	/**
	 * ActionFormクラスへ値を代入
	 *
	 * @return UserListActionForm
	 */
	public UserListActionForm getListForm() throws Exception {
		SelectBoxLogic selectLogic = new SelectBoxLogic(dbConn, resource);
		Map<String,String> comMap = selectLogic.getUserComSelectBox();


		UserListActionForm form = new UserListActionForm();

		MUserDao mUserDao = new MUserDao(dbConn);
		List<MUserEntity> mUserList = mUserDao.getListSort("USER_ID");
		List<UserValue> userList = new ArrayList<UserValue>();
		for (MUserEntity mUser : mUserList) {
			UserValue user = new UserValue();
			user.setUserId(mUser.getUserId());
			user.setDeptCode(mUser.getDeptCode());
			user.setDeptName(getDeptName(mUser.getDeptCode()));
			user.setUserName(mUser.getUserName());
			user.setUserCom(comMap.get(mUser.getUserCom()));
			userList.add(user);
		}

		form.setUserList(userList);

		return form;
	}

	/**
	 * ActionFormクラスへ値を代入
	 *
	 * @param String userId
	 * @return UserEditActionForm
	 */
	public UserEditActionForm getEditForm(String userId) throws Exception {
		UserEditActionForm form = new UserEditActionForm();

		MUserDao mUserDao = new MUserDao(dbConn);
		MUserEntity mUser = mUserDao.getValue(userId);
		if (mUser != null) {
			form.setUserId(mUser.getUserId());
			form.setPasswd(Utility.decrypt(mUser.getUserPass()));
			form.setUserName(mUser.getUserName());
			form.setDeptCode(mUser.getDeptCode());
			form.setUserCom(mUser.getUserCom());
		}

		return form;
	}

	/**
	 * 登録件数取得
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int count(String userId) throws Exception {
		MUserDao mUserDao = new MUserDao(dbConn);
		return mUserDao.count(userId);
	}

	/**
	 * ActionFormクラスからＤＢへ値を新規登録
	 *
	 * @param form UserEditActionForm
	 */
	public void registData(UserEditActionForm form) throws Exception {
		try {
			MUserDao mUserDao = new MUserDao(dbConn);
			MUserEntity mUser = new MUserEntity();
			mUser.setUserId(form.getUserId());
			mUser.setUserPass(Utility.encrypt(form.getPasswd()));
			mUser.setUserName(form.getUserName());
			mUser.setDeptCode(form.getDeptCode());
			mUser.setUserCom(form.getUserCom());
			mUser.setIdate(Utility.getTimestampNow());
			mUser.setUdate(Utility.getTimestampNow());
			mUserDao.setValue(mUser);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * ActionFormクラスからＤＢの更新
	 *
	 * @param form UserEditActionForm
	 */
	public void updateData(UserEditActionForm form) throws Exception {
		try {
			MUserDao mUserDao = new MUserDao(dbConn);
			MUserEntity mUser = mUserDao.getValue(form.getUserId());
			mUser.setUserId(form.getUserId());
			mUser.setUserPass(Utility.encrypt(form.getPasswd()));
			mUser.setUserName(form.getUserName());
			mUser.setDeptCode(form.getDeptCode());
			mUser.setUserCom(form.getUserCom());
			mUser.setUdate(Utility.getTimestampNow());
			mUserDao.setValue(mUser);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * ActionFormクラスからＤＢの削除
	 *
	 * @param form UserEditActionForm
	 */
	public void removeData(UserEditActionForm form) throws Exception {
		try {
			MUserDao mUserDao = new MUserDao(dbConn);
			mUserDao.removeValue(form.getUserId());

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * ユーザの権限取得
	 *
	 * @param userId ユーザID
	 * @return 権限 1：利用者　2：承認者　9：管理者
	 */
	public String getUserCom(String userId) throws Exception {
		try {
			MUserDao mUserDao = new MUserDao(dbConn);
			MUserEntity mUser = mUserDao.getValue(userId);
			if (mUser == null) {
				return "0";
			} else {
				return mUser.getUserCom();
			}

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
	private String getDeptName(String deptCode) throws Exception {
		MasterLogic logic = new MasterLogic(dbConn);
		return logic.getMasterDeptName(deptCode);
	}

	/**
	 * ActionFormクラスからＤＢへ値を新規登録
	 * @param userId
	 * @param userName
	 * @param passwd
	 * @throws Exception
	 */
	public void initUser(String userId, String userName, String passwd) throws Exception {
		try {
			MUserDao mUserDao = new MUserDao(dbConn);
			MUserEntity mUser = new MUserEntity();
			mUser.setUserId(userId);
			mUser.setUserName(userName);
			mUser.setUserPass(Utility.encrypt(passwd));
			mUser.setDeptCode("");
			mUser.setUserCom(Param.USER_ADMIN);
			mUser.setIdate(Utility.getTimestampNow());
			mUser.setUdate(Utility.getTimestampNow());
			mUserDao.setValue(mUser);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
