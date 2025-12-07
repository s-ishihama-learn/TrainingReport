package jp.logware.custmer.logic.master;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import jp.logware.custmer.common.Utility;
import jp.logware.custmer.dao.MTypeDao;
import jp.logware.custmer.entity.MTypeEntity;
import jp.logware.custmer.form.master.TypeEditActionForm;
import jp.logware.custmer.form.master.TypeListActionForm;
import jp.logware.custmer.subform.TypeValue;

/**
 * 分類編集 ロジッククラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */

public class TypeLogic {
	/**
	 * データベースコネクション
	 */
	private Connection dbConn = null;

	/**
	 * コンストラクタ
	 *
	 * @param conn
	 */
	public TypeLogic(Connection conn) {
		dbConn = conn;
	}

	/**
	 * ActionFormクラスへ値を代入
	 *
	 * @return TypeListActionForm
	 */
	public TypeListActionForm getListForm() throws Exception {
		TypeListActionForm form = new TypeListActionForm();

		MTypeDao mTypeDao = new MTypeDao(dbConn);
		List<MTypeEntity> mTypeList = mTypeDao.getListSort("TYPE_CODE");
		List<TypeValue> typeTypeList = new ArrayList<TypeValue>();
		for (MTypeEntity mType : mTypeList) {
			TypeValue typeType = new TypeValue();
			typeType.setTypeCode(mType.getTypeCode());
			typeType.setTypeName(mType.getTypeName());
			typeTypeList.add(typeType);
		}

		form.setTypeList(typeTypeList);

		return form;
	}

	/**
	 * ActionFormクラスへ値を代入
	 *
	 * @param String typeCode
	 * @return TypeEditActionForm
	 */
	public TypeEditActionForm getEditForm(String typeCode) throws Exception {
		TypeEditActionForm form = new TypeEditActionForm();

		MTypeDao mTypeDao = new MTypeDao(dbConn);
		MTypeEntity mType = mTypeDao.getValue(typeCode);
		if (mType != null) {
			form.setTypeCode(mType.getTypeCode());
			form.setTypeName(mType.getTypeName());
		}

		return form;
	}

	/**
	 * 登録件数取得
	 * @param typeCode
	 * @return
	 * @throws Exception
	 */
	public int count(String typeCode) throws Exception {
		MTypeDao mTypeDao = new MTypeDao(dbConn);
		return mTypeDao.count(typeCode);
	}

	/**
	 * ActionFormクラスからＤＢへ値を新規登録
	 *
	 * @param form TypeEditActionForm
	 */
	public void registData(TypeEditActionForm form) throws Exception {
		try {
			MTypeDao mTypeDao = new MTypeDao(dbConn);
			MTypeEntity mType = new MTypeEntity();
			mType.setTypeCode(form.getTypeCode());
			mType.setTypeName(form.getTypeName());
			mType.setIdate(Utility.getTimestampNow());
			mType.setUdate(Utility.getTimestampNow());
			mTypeDao.setValue(mType);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * ActionFormクラスからＤＢの更新
	 *
	 * @param form TypeEditActionForm
	 */
	public void updateData(TypeEditActionForm form) throws Exception {
		try {
			MTypeDao mTypeDao = new MTypeDao(dbConn);
			MTypeEntity mType = mTypeDao.getValue(form.getTypeCode());
			mType.setTypeCode(form.getTypeCode());
			mType.setTypeName(form.getTypeName());
			mType.setUdate(Utility.getTimestampNow());
			mTypeDao.setValue(mType);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * ActionFormクラスからＤＢの削除
	 *
	 * @param form TypeEditActionForm
	 */
	public void removeData(TypeEditActionForm form) throws Exception {
		try {
			MTypeDao mTypeDao = new MTypeDao(dbConn);
			mTypeDao.removeValue(form.getTypeCode());

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
