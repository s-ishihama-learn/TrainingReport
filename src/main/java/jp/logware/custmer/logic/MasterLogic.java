package jp.logware.custmer.logic;

import java.sql.Connection;

import jp.logware.custmer.dao.MDeptDao;
import jp.logware.custmer.dao.MUserDao;
import jp.logware.custmer.entity.MDeptEntity;
import jp.logware.custmer.entity.MUserEntity;

/**
 * 部署マスタ ロジッククラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */

public class MasterLogic {
	/**
	 * データベースコネクション
	 */
	private Connection dbConn = null;

	/**
	 * コンストラクタ
	 *
	 * @param conn
	 */
	public MasterLogic(Connection conn) {
		dbConn = conn;
	}

	/**
	 * 参照変換処理
	 *
	 * @param deptCode String
	 * @return String
	 */
	public String getMasterDeptName(String deptCode) throws Exception {
		MDeptDao mDeptDao = new MDeptDao(dbConn);
		MDeptEntity mDept = mDeptDao.getValue(deptCode);
		String deptName = "";
		if (mDept != null) {
			deptName = mDept.getDeptName();
		}
		return deptName;
	}

	/**
	 * 参照変換処理
	 *
	 * @param userId String
	 * @return String
	 */
	public String getMasterUserName(String userId) throws Exception {
		MUserDao mUserDao = new MUserDao(dbConn);
		MUserEntity mUser = mUserDao.getValue(userId);
		String userName = "";
		if (mUser != null) {
			userName = mUser.getUserName();
		}
		return userName;
	}
}
