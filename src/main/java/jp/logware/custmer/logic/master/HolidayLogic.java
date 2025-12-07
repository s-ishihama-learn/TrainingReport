package jp.logware.custmer.logic.master;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.logware.custmer.common.Utility;
import jp.logware.custmer.dao.MHolidayDao;
import jp.logware.custmer.entity.MHolidayEntity;
import jp.logware.custmer.form.master.HolidayEditActionForm;
import jp.logware.custmer.form.master.HolidayListActionForm;
import jp.logware.custmer.subform.HolidayValue;

/**
 * 社員編集 ロジッククラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */

public class HolidayLogic {
	/**
	 * データベースコネクション
	 */
	private Connection dbConn = null;

	/**
	 * コンストラクタ
	 *
	 * @param conn
	 */
	public HolidayLogic(Connection conn) {
		dbConn = conn;
	}

	/**
	 * ActionFormクラスへ値を代入
	 *
	 * @return HolidayListActionForm
	 */
	public HolidayListActionForm getListForm(Map<String,Object> nameMap) throws Exception {
		HolidayListActionForm form = new HolidayListActionForm();

		MHolidayDao mHolidayDao = new MHolidayDao(dbConn);
		List<MHolidayEntity> mHolidayList = mHolidayDao.getListSort("DATE");
		List<HolidayValue> holidayList = new ArrayList<HolidayValue>();
		for (MHolidayEntity entity : mHolidayList) {
			HolidayValue holiday = new HolidayValue();
			holiday.setDate(entity.getDate());
			holiday.setNote(entity.getNote());
			holiday.setKind(entity.getKind());
			holiday.setKindName((String)nameMap.get(entity.getKind()));
			holidayList.add(holiday);
		}

		form.setHolidayList(holidayList);

		return form;
	}

	/**
	 * ActionFormクラスへ値を代入
	 *
	 * @param String holidayId
	 * @return HolidayEditActionForm
	 */
	public HolidayEditActionForm getEditForm(String holidayId) throws Exception {
		HolidayEditActionForm form = new HolidayEditActionForm();

		MHolidayDao mHolidayDao = new MHolidayDao(dbConn);
		MHolidayEntity mHoliday = mHolidayDao.getValue(holidayId);
		if (mHoliday != null) {
			form.setDate(mHoliday.getDate());
			form.setNote(mHoliday.getNote());
			form.setKind(mHoliday.getKind());
		}

		return form;
	}

	/**
	 * 登録件数取得
	 * @param holidayId
	 * @return
	 * @throws Exception
	 */
	public int count(String holidayId) throws Exception {
		MHolidayDao mHolidayDao = new MHolidayDao(dbConn);
		return mHolidayDao.count(holidayId);
	}

	/**
	 * ActionFormクラスからＤＢへ値を新規登録
	 *
	 * @param form HolidayEditActionForm
	 */
	public void registData(HolidayEditActionForm form) throws Exception {
		try {
			MHolidayDao mHolidayDao = new MHolidayDao(dbConn);
			MHolidayEntity mHoliday = new MHolidayEntity();
			mHoliday.setDate(form.getDate());
			mHoliday.setNote(form.getNote());
			mHoliday.setKind(form.getKind());
			mHoliday.setIdate(Utility.getTimestampNow());
			mHoliday.setUdate(Utility.getTimestampNow());
			mHolidayDao.setValue(mHoliday);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * ActionFormクラスからＤＢの更新
	 *
	 * @param form HolidayEditActionForm
	 */
	public void updateData(HolidayEditActionForm form) throws Exception {
		try {
			MHolidayDao mHolidayDao = new MHolidayDao(dbConn);
			MHolidayEntity mHoliday = mHolidayDao.getValue(form.getDate());
			mHoliday.setDate(form.getDate());
			mHoliday.setNote(form.getNote());
			mHoliday.setKind(form.getKind());
			mHoliday.setUdate(Utility.getTimestampNow());
			mHolidayDao.setValue(mHoliday);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * ActionFormクラスからＤＢの削除
	 *
	 * @param form HolidayEditActionForm
	 */
	public void removeData(HolidayEditActionForm form) throws Exception {
		try {
			MHolidayDao mHolidayDao = new MHolidayDao(dbConn);
			mHolidayDao.removeValue(form.getDate());

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * ユーザの権限取得
	 *
	 * @param date ユーザID
	 */
	public String getHolidayColor(String date) throws Exception {
		try {
			MHolidayDao mHolidayDao = new MHolidayDao(dbConn);
			MHolidayEntity mHoliday = mHolidayDao.getValue(date);
			if (mHoliday == null) {
				return "0";
			} else {
				return mHoliday.getKind();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
