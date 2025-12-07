package jp.logware.custmer.logic.schedule;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
import jp.logware.custmer.dao.MUserDao;
import jp.logware.custmer.dao.ReportDao;
import jp.logware.custmer.dao.ScheduleDao;
import jp.logware.custmer.dao.ViewGroupDao;
import jp.logware.custmer.entity.MHolidayEntity;
import jp.logware.custmer.entity.ReportEntity;
import jp.logware.custmer.entity.ScheduleEntity;
import jp.logware.custmer.entity.ViewGroupEntity;
import jp.logware.custmer.form.schedule.ScheduleWeekActionForm;
import jp.logware.custmer.subform.DayItemValue;

/**
 * スケジュールグループ表示 ロジッククラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */

public class ScheduleWeekLogic {
	/**
	 * データベースコネクション
	 */
	private Connection dbConn = null;

	/**
	 * コンストラクタ
	 *
	 * @param conn
	 */
	public ScheduleWeekLogic(Connection conn) {
		dbConn = conn;
	}

	/**
	 * ActionFormクラスへ値を代入
	 *
	 * @param String userId
	 * @param String date
	 * @return ScheduleGroupActionForm
	 */
	public ScheduleWeekActionForm getForm(String userId, String date, CommonSession commonSession) throws Exception {
		ScheduleWeekActionForm form = new ScheduleWeekActionForm();

		ScheduleDao scheduleDao = new ScheduleDao(dbConn);
		ReportDao reportDao = new ReportDao(dbConn);
		String week[] = DateUtility.getWeek(date);
		String firstDay = week[0];
		String lastDay = week[week.length - 1];
		String dayNumber[] = DateUtility.getWeekDayNumber(date);
		form.setDays(dayNumber);

		// ユーザ名表示マップ
		MUserDao mUserDao = new MUserDao(dbConn);
		Map<String, String> nameMap = mUserDao.getMap();

		// 表示ユーザ一覧の取得
		List<String> users = new ArrayList<String>();
		users.add(userId);
		ViewGroupDao viewGroupDao = new ViewGroupDao(dbConn);
		List<ViewGroupEntity> group = viewGroupDao.getListSort(userId, "VIEW_USER_ID");
		for (ViewGroupEntity user : group) {
			users.add(user.getViewUserId());
		}

		List<Object> gropuItem = new ArrayList<Object>();
		int i = 0;
		String userName[] = new String[users.size()];
		for (String user : users) {
			String where = "USER_ID='" + user + "' AND DATE>='" + firstDay + "' AND DATE<='" + lastDay + "'";
			List<ScheduleEntity> scheduleList = scheduleDao.getListWhereSort(where, "DATE,START_TIME");
			List<ReportEntity> reportList = reportDao.getListWhereSort(where, "DATE,START_TIME");

			ViewCalendar util = new ViewCalendar();
			Map<String, List<Object>> map = new HashMap<String, List<Object>>();

			Set<String> scheduleSet = new HashSet<String>();
			for (ScheduleEntity schedule : scheduleList) {
				DayItemValue item = new DayItemValue();
				item.setUserId(user);
				item.setViewDate(CommonUtility.getDateString(schedule.getDate()));
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
				item.setUserId(user);
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

			userName[i++] = nameMap.get(user);
			List<Object> weekItem = util.getWeekItem(date, map);
			gropuItem.add(weekItem);
		}

		form.setGroupItem(gropuItem);
		form.setUserName(userName);
		form.setHolidayColorMap(getHolidayColorMap(commonSession, firstDay, lastDay));
		form.setViewMonth(date.substring(0, 8));

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
