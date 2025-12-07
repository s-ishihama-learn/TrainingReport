package jp.logware.custmer.logic.schedule;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import jp.logware.custmer.common.CommonUtility;
import jp.logware.custmer.common.Param;
import jp.logware.custmer.common.Utility;
import jp.logware.custmer.dao.MUserDao;
import jp.logware.custmer.dao.ScheduleDao;
import jp.logware.custmer.entity.ScheduleEntity;
import jp.logware.custmer.form.schedule.EditScheduleActionForm;
import jp.logware.custmer.form.schedule.ListScheduleActionForm;
import jp.logware.custmer.subform.DayItemValue;

/**
 * スケジュール編集 ロジッククラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */

public class EditScheduleLogic {
	/**
	 * データベースコネクション
	 */
	private Connection dbConn = null;

	/**
	 * コンストラクタ
	 *
	 * @param conn
	 */
	public EditScheduleLogic(Connection conn) {
		dbConn = conn;
	}

	/**
	 * ActionFormクラスへ値を代入
	 *
	 * @param String userId
	 * @param String date
	 * @param String seq
	 * @return EditScheduleActionForm
	 */
	public ListScheduleActionForm getListForm(String userId, String date) throws Exception {
		ListScheduleActionForm form = new ListScheduleActionForm();

		MUserDao mUserDao = new MUserDao(dbConn);
		Map<String,String> userNameMap = mUserDao.getMap();

		ScheduleDao scheduleDao = new ScheduleDao(dbConn);
		List<ScheduleEntity> scheduleList = scheduleDao.getList(userId, Utility.toDate(date));
		for (ScheduleEntity entity : scheduleList) {
			DayItemValue item = new DayItemValue();
			item.setUserId(entity.getUserId());
			item.setUserName(userNameMap.get(entity.getUserId()));
			item.setViewDate(Utility.getDate(entity.getDate()));
			item.setSeq(String.valueOf(entity.getSeq()));
			item.setStartTime(CommonUtility.getViewTimeZeroString(entity.getStartTime()));
			item.setEndTime(CommonUtility.getViewTimeZeroString(entity.getEndTime()));
			item.setTitle(entity.getTitle());
			item.setBody(entity.getBody());
			item.setKind(Param.KIND_SCHEDULE);
			form.getScheduleList().add(item);
		}

		return form;
	}

	/**
	 * ActionFormクラスへ値を代入
	 *
	 * @param String userId
	 * @param String date
	 * @param String seq
	 * @return EditScheduleActionForm
	 */
	public EditScheduleActionForm getEditForm(String userId, String date, String seq) throws Exception {
		EditScheduleActionForm form = new EditScheduleActionForm();

		MUserDao mUserDao = new MUserDao(dbConn);
		Map<String,String> userNameMap = mUserDao.getMap();

		ScheduleDao scheduleDao = new ScheduleDao(dbConn);
		ScheduleEntity entity = scheduleDao.getValue(userId, Utility.toDate(date), Integer.parseInt(seq));
		if (entity != null) {
			form.setUserId(entity.getUserId());
			form.setUserName(userNameMap.get(entity.getUserId()));
			form.setViewDate(getDateString(entity.getDate()));
			form.setSeq(String.valueOf(entity.getSeq()));
			form.setStartTime(entity.getStartTime());
			form.setEndTime(entity.getEndTime());
			form.setStartTimeView(CommonUtility.getViewTimeString(entity.getStartTime()));
			form.setEndTimeView(CommonUtility.getViewTimeString(entity.getEndTime()));
			form.setTitle(entity.getTitle());
			form.setBody(entity.getBody());
		}

		return form;
	}

	/**
	 * ActionFormクラスからＤＢへ値を新規登録
	 *
	 * @param form EditScheduleActionForm
	 */
	public void registData(EditScheduleActionForm form) throws Exception {
		try {
			ScheduleDao scheduleDao = new ScheduleDao(dbConn);
			ScheduleEntity entity = new ScheduleEntity();
			entity.setUserId(form.getUserId());
			entity.setDate(Utility.toDate(form.getViewDate()));
			entity.setSeq(getNewScheduleSeq(form.getUserId(), Utility.toDate(form.getViewDate())));
			entity.setStartTime(form.getStartTime());
			entity.setEndTime(form.getEndTime());
			entity.setTitle(form.getTitle());
			entity.setBody(form.getBody());
			entity.setIdate(Utility.getTimestampNow());
			entity.setUdate(Utility.getTimestampNow());
			scheduleDao.setValue(entity);
			form.setSeq(String.valueOf(entity.getSeq()));

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * ActionFormクラスからＤＢの更新
	 *
	 * @param form EditScheduleActionForm
	 */
	public void updateData(EditScheduleActionForm form) throws Exception {
		try {
			ScheduleDao scheduleDao = new ScheduleDao(dbConn);
			ScheduleEntity entity = scheduleDao.getValue(form.getUserId(), Utility.toDate(form.getViewDate()), Integer.parseInt(form.getSeq()));
			entity.setUserId(form.getUserId());
			entity.setDate(Utility.toDate(form.getViewDate()));
			entity.setSeq(Utility.toInt(form.getSeq()));
			entity.setStartTime(form.getStartTime());
			entity.setEndTime(form.getEndTime());
			entity.setTitle(form.getTitle());
			entity.setBody(form.getBody());
			entity.setUdate(Utility.getTimestampNow());
			scheduleDao.setValue(entity);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * ActionFormクラスからＤＢの削除
	 *
	 * @param form EditScheduleActionForm
	 */
	public void removeData(EditScheduleActionForm form) throws Exception {
		try {
			ScheduleDao scheduleDao = new ScheduleDao(dbConn);
			scheduleDao.removeValue(form.getUserId(), Utility.toDate(form.getViewDate()), Integer.parseInt(form.getSeq()));

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
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
	 * 新規変換処理
	 *
	 * @param seq int
	 * @return String
	 */
	private int getNewScheduleSeq(String userId, Date date) throws Exception {
		ScheduleDao scheduleDao = new ScheduleDao(dbConn);
		List<ScheduleEntity> list = scheduleDao.getListSort(userId, date, "SEQ DESC LIMIT(1)");
		int n = 1;
		if (list.size() > 0) {
			ScheduleEntity info = list.get(0);
			n = info.getSeq() + 1;
		}
		return n;
	}
}
