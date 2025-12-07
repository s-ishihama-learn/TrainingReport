package jp.logware.custmer.logic.master;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import jp.logware.custmer.common.Utility;
import jp.logware.custmer.dao.MDeptDao;
import jp.logware.custmer.entity.MDeptEntity;
import jp.logware.custmer.form.master.DeptEditActionForm;
import jp.logware.custmer.form.master.DeptListActionForm;
import jp.logware.custmer.subform.DeptValue;

/**
 * 所属編集 ロジッククラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */

public class DeptLogic {
	/**
	 * データベースコネクション
	 */
	private Connection dbConn = null;

	/**
	 * コンストラクタ
	 *
	 * @param conn
	 */
	public DeptLogic(Connection conn) {
		dbConn = conn;
	}

	/**
	 * ActionFormクラスへ値を代入
	 *
	 * @return DeptListActionForm
	 */
	public DeptListActionForm getListForm() throws Exception {
		DeptListActionForm form = new DeptListActionForm();

		MDeptDao mDeptDao = new MDeptDao(dbConn);
		List<MDeptEntity> mDeptList = mDeptDao.getListSort("DEPT_CODE");
		List<DeptValue> deptList = new ArrayList<DeptValue>();
		for (MDeptEntity mDept : mDeptList) {
			DeptValue dept = new DeptValue();
			dept.setDeptCode(mDept.getDeptCode());
			dept.setDeptName(mDept.getDeptName());
			deptList.add(dept);
		}

		form.setDeptList(deptList);

		return form;
	}

	/**
	 * ActionFormクラスへ値を代入
	 *
	 * @param String deptCode
	 * @return DeptEditActionForm
	 */
	public DeptEditActionForm getEditForm(String deptCode) throws Exception {
		DeptEditActionForm form = new DeptEditActionForm();

		MDeptDao mDeptDao = new MDeptDao(dbConn);
		MDeptEntity mDept = mDeptDao.getValue(deptCode);
		if (mDept != null) {
			form.setDeptCode(mDept.getDeptCode());
			form.setDeptName(mDept.getDeptName());
		}

		return form;
	}

	/**
	 * 登録件数取得
	 * @param deptCode
	 * @return
	 * @throws Exception
	 */
	public int count(String deptCode) throws Exception {
		MDeptDao mDeptDao = new MDeptDao(dbConn);
		return mDeptDao.count(deptCode);
	}

	/**
	 * ActionFormクラスからＤＢへ値を新規登録
	 *
	 * @param form DeptEditActionForm
	 */
	public void registData(DeptEditActionForm form) throws Exception {
		try {
			MDeptDao mDeptDao = new MDeptDao(dbConn);
			MDeptEntity mDept = new MDeptEntity();
			mDept.setDeptCode(form.getDeptCode());
			mDept.setDeptName(form.getDeptName());
			mDept.setIdate(Utility.getTimestampNow());
			mDept.setUdate(Utility.getTimestampNow());
			mDeptDao.setValue(mDept);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * ActionFormクラスからＤＢの更新
	 *
	 * @param form DeptEditActionForm
	 */
	public void updateData(DeptEditActionForm form) throws Exception {
		try {
			MDeptDao mDeptDao = new MDeptDao(dbConn);
			MDeptEntity mDept = mDeptDao.getValue(form.getDeptCode());
			mDept.setDeptCode(form.getDeptCode());
			mDept.setDeptName(form.getDeptName());
			mDept.setUdate(Utility.getTimestampNow());
			mDeptDao.setValue(mDept);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * ActionFormクラスからＤＢの削除
	 *
	 * @param form DeptEditActionForm
	 */
	public void removeData(DeptEditActionForm form) throws Exception {
		try {
			MDeptDao mDeptDao = new MDeptDao(dbConn);
			mDeptDao.removeValue(form.getDeptCode());

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
