package jp.logware.custmer.logic.schedule;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import beachstone.calendar.ViewCalendar;
import beachstone.date.DateUtility;
import beachstone.date.SqlDateUtility;
import jp.logware.custmer.common.CommonSession;
import jp.logware.custmer.common.CommonUtility;
import jp.logware.custmer.common.Param;
import jp.logware.custmer.dao.MHolidayDao;
import jp.logware.custmer.dao.ReportDao;
import jp.logware.custmer.dao.ScheduleDao;
import jp.logware.custmer.entity.MHolidayEntity;
import jp.logware.custmer.entity.ReportEntity;
import jp.logware.custmer.entity.ScheduleEntity;
import jp.logware.custmer.form.schedule.ScheduleMonthActionForm;
import jp.logware.custmer.subform.DayItemValue;

/**
 * スケジュール月表示 ロジッククラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */

public class ScheduleMonthLogic {
	/**
	 * データベースコネクション
	 */
	private Connection dbConn = null;

	/**
	 * コンストラクタ
	 *
	 * @param conn
	 */
	public ScheduleMonthLogic(Connection conn) {
		dbConn = conn;
	}

	/**
	 * ActionFormクラスへ値を代入
	 *
	 * @param String userId
	 * @param String date
	 * @return ScheduleMonthActionForm
	 */
	public ScheduleMonthActionForm getForm(String userId, String date, CommonSession commonSession) throws Exception {
		ScheduleMonthActionForm form = new ScheduleMonthActionForm();

		ScheduleDao scheduleDao = new ScheduleDao(dbConn);
		ReportDao reportDao = new ReportDao(dbConn);
		String firstDay = DateUtility.getStrFirstDay(date);
		String lastDay = DateUtility.getStrLastDay(date);
		String where = "USER_ID='" + userId + "' AND DATE>='" + firstDay + "' AND DATE<='" + lastDay + "'";
		List<ScheduleEntity> scheduleList = scheduleDao.getListWhereSort(where, "DATE,START_TIME");
		List<ReportEntity> reportList = reportDao.getListWhereSort(where, "DATE,START_TIME");

		ViewCalendar util = new ViewCalendar();
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();

		Set<String> scheduleSet = new HashSet<String>();
		for (ScheduleEntity schedule : scheduleList) {
			DayItemValue item = new DayItemValue();
			String dateStr = CommonUtility.getDateString(schedule.getDate());
			item.setUserId(userId);
			item.setViewDate(dateStr);
			item.setSeq(String.valueOf(schedule.getSeq()));
			item.setStartTime(CommonUtility.getViewTimeZeroString(schedule.getStartTime()));
			item.setTitle(schedule.getTitle());
			item.setKind(Param.KIND_SCHEDULE);
			String dblKey = item.getUserId() + "," + item.getViewDate();
			if (!scheduleSet.contains(dblKey)) {
				map = util.setItemMap(SqlDateUtility.getDate(schedule.getDate()), item, map);
				scheduleSet.add(dblKey);
			}
		}

		Set<String> reportSet = new HashSet<String>();
		for (ReportEntity report : reportList) {
			DayItemValue item = new DayItemValue();
			item.setUserId(userId);
			item.setViewDate(CommonUtility.getDateString(report.getDate()));
			item.setSeq(String.valueOf(report.getSeq()));
			item.setStartTime(CommonUtility.getViewTimeZeroString(report.getStartTime()));
			item.setTitle(report.getTitle());
			item.setKind(Param.KIND_REPORT);
			String dblKey = item.getUserId() + "," + item.getViewDate();
			if (!reportSet.contains(dblKey)) {
				map = util.setItemMap(SqlDateUtility.getDate(report.getDate()), item, map);
				reportSet.add(dblKey);
			}
		}

		List<Object> monthItem = util.getMonthItem(date, map);
		form.setMonthItem(monthItem);
		form.setStartDay(util.getStartDay(date));
		form.setLastDay(util.getLastDay(date));
		form.setHolidayColorMap(getHolidayColorMap(commonSession, firstDay, lastDay));
		form.setViewMonth(date.substring(0, 8));

		return form;
	}

	/**
	 * ActionFormクラスへ値を代入
	 *
	 * @param String userId
	 * @param String date
	 * @return ScheduleMonthActionForm
	 */
	public ScheduleMonthActionForm getListForm(String userId, String date) throws Exception {
		ScheduleMonthActionForm form = new ScheduleMonthActionForm();

		ScheduleDao scheduleDao = new ScheduleDao(dbConn);
		String firstDay = DateUtility.getStrFirstDay(date);
		String lastDay = DateUtility.getStrLastDay(date);
		String where = "USER_ID='" + userId + "' AND DATE>='" + firstDay + "' AND DATE<='" + lastDay + "'";
		List<ScheduleEntity> scheduleList = scheduleDao.getListWhereSort(where, "DATE,START_TIME");

		ViewCalendar util = new ViewCalendar();
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();

		for (ScheduleEntity schedule : scheduleList) {
			DayItemValue daySchedule = new DayItemValue();
			daySchedule.setUserId(userId);
			daySchedule.setViewDate(CommonUtility.getDateString(schedule.getDate()));
			daySchedule.setSeq(String.valueOf(schedule.getSeq()));
			daySchedule.setStartTime(CommonUtility.getViewTimeZeroString(schedule.getStartTime()));
			daySchedule.setTitle(schedule.getTitle());
			map = util.setItemMap(SqlDateUtility.getDate(schedule.getDate()), daySchedule, map);
		}

		List<Object> monthItem = util.getMonthItem(date, map);
		form.setMonthItem(monthItem);
		form.setStartDay(util.getStartDay(date));
		form.setLastDay(util.getLastDay(date));

		return form;
	}

	/**
	 * 休日背景色マップの取得
	 * @param commonSession
	 * @param firstDay
	 * @param lastDay
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	private Map<String, String> getHolidayColorMap(CommonSession commonSession, String firstDay, String lastDay) throws SQLException, Exception {
		MHolidayDao horidayDao = new MHolidayDao(dbConn);
		String where = "DATE>='" + firstDay + "' AND DATE<='" + lastDay + "'";
		List<MHolidayEntity> holidayList = horidayDao.getListWhere(where);
		Map<String, String> holidayMap = new HashMap<String, String>();
		for(MHolidayEntity horiday : holidayList){
			holidayMap.put(horiday.getDate(), commonSession.getHolidayColorMap().get(horiday.getKind()));
		}
		return holidayMap;
	}
}
