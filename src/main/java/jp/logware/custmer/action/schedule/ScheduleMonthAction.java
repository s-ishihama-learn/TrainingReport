package jp.logware.custmer.action.schedule;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import beachstone.date.DateUtility;
import beachstone.date.SqlDateUtility;
import jp.logware.custmer.common.AuthException;
import jp.logware.custmer.common.CommonSession;
import jp.logware.custmer.common.CommonUtility;
import jp.logware.custmer.common.ConnectionPool;
import jp.logware.custmer.common.Param;
import jp.logware.custmer.common.Utility;
import jp.logware.custmer.form.report.EditReportActionForm;
import jp.logware.custmer.form.report.ListReportActionForm;
import jp.logware.custmer.form.schedule.ListScheduleActionForm;
import jp.logware.custmer.form.schedule.ScheduleMonthActionForm;
import jp.logware.custmer.form.schedule.SelectNewActionForm;
import jp.logware.custmer.logic.report.EditReportLogic;
import jp.logware.custmer.logic.schedule.EditScheduleLogic;
import jp.logware.custmer.logic.schedule.ScheduleMonthLogic;

/**
 * スケジュール月表示 アクションクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class ScheduleMonthAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConnectionPool connectionPool = ConnectionPool.getInstance(request);
		Connection dbConn = connectionPool.getConnection();
		System.out.println("URI: " + request.getRequestURI());
		System.out.println("Method: " + request.getMethod()); // GET か POST か
		System.out.println("Referer: " + request.getHeader("referer")); // どこから来たか		
		try {
			HttpSession session = request.getSession(true);
			ScheduleMonthActionForm frm = (ScheduleMonthActionForm) form;
			System.out.println("ScheduleMonthAction:" + frm.getButton());

			ActionMessages messages = new ActionMessages();
			ActionErrors errors = new ActionErrors();
			CommonSession commonSession = (CommonSession) session.getAttribute("CommonSession");

			String forward = "error";
			request.setAttribute("ScheduleMonthForm", frm);

			if (commonSession == null) {
				throw new AuthException();

			} else if (frm.getButton() == null || frm.getButton().length() == 0) {
				throw new AuthException();

			} else if (frm.getButton().equals("lastMonth")) {
				ScheduleMonthActionForm f = doLast(dbConn, commonSession);
				request.setAttribute("ScheduleMonthForm", f);
				forward = "lastMonth";

			} else if (frm.getButton().equals("nextMonth")) {
				ScheduleMonthActionForm f = doNext(dbConn, commonSession);
				request.setAttribute("ScheduleMonthForm", f);
				forward = "nextMonth";

			} else if (frm.getButton().equals("new")) {
				SelectNewActionForm f = doNew(commonSession, frm);
				request.setAttribute("SelectNewForm", f);
				forward = "new";

			} else if (frm.getButton().equals("schedule")) {
				ListScheduleActionForm f = doSchedule(dbConn, commonSession, frm);
				request.setAttribute("ListScheduleForm", f);
				forward = "schedule";

			} else if (frm.getButton().equals("report")) {
				ListReportActionForm f = doReport(dbConn, commonSession, frm);
				request.setAttribute("ListReportForm", f);
				forward = "report";

			} else if (frm.getButton().equals("seq")) {
				EditReportActionForm f = doSeq(dbConn, commonSession, frm);
				session.setAttribute("EditReportForm", f);
				forward = "seq";

			} else {
				throw new AuthException();
			}

			saveMessages(request, messages);
			request.setAttribute(Globals.ERROR_KEY, errors);

			return mapping.findForward(forward);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		} finally {
			connectionPool.releaseConnection(dbConn);
		}
	}

	/**
	 * 前月
	 * @param dbConn
	 * @param commonSession
	 * @return
	 * @throws Exception
	 */
	private ScheduleMonthActionForm doLast(Connection dbConn, CommonSession commonSession) throws Exception {
		ScheduleMonthLogic logic = new ScheduleMonthLogic(dbConn);
		String userId = commonSession.getViewUserId();
		String viewDate = DateUtility.addMonth(Utility.getDate(commonSession.getViewDate()), -1);
		commonSession.setViewDate(SqlDateUtility.getSqlDate(viewDate));
		commonSession.setViewMonthDisp(CommonUtility.getViewDate(viewDate));
		commonSession.setViewDateDisp(CommonUtility.getViewDay(viewDate));
		commonSession.setNewLinkString(CommonUtility.getNewLinkStringMonthSchedule(userId, viewDate));
		ScheduleMonthActionForm f = logic.getForm(userId, viewDate, commonSession);
		return f;
	}

	/**
	 * 次月
	 * @param dbConn
	 * @param commonSession
	 * @return
	 * @throws Exception
	 */
	private ScheduleMonthActionForm doNext(Connection dbConn, CommonSession commonSession) throws Exception {
		ScheduleMonthLogic logic = new ScheduleMonthLogic(dbConn);
		String userId = commonSession.getViewUserId();
		String viewDate = DateUtility.addMonth(Utility.getDate(commonSession.getViewDate()), 1);
		commonSession.setViewDate(SqlDateUtility.getSqlDate(viewDate));
		commonSession.setViewMonthDisp(CommonUtility.getViewDate(viewDate));
		commonSession.setViewDateDisp(CommonUtility.getViewDay(viewDate));
		commonSession.setNewLinkString(CommonUtility.getNewLinkStringMonthSchedule(userId, viewDate));
		ScheduleMonthActionForm f = logic.getForm(userId, viewDate, commonSession);
		return f;
	}

	/**
	 * 新規選択
	 * @param dbConn
	 * @param frm
	 * @param commonSession
	 * @return
	 * @throws Exception
	 */
	private SelectNewActionForm doNew(CommonSession commonSession, ScheduleMonthActionForm frm) throws Exception {
		String userId = frm.getUserId();
		String viewDate = frm.getViewDate();
		commonSession.setViewUserId(userId);
		commonSession.setViewDate(SqlDateUtility.getSqlDate(viewDate));
		commonSession.setViewMonthDisp(CommonUtility.getViewDate(viewDate));
		commonSession.setViewDateDisp(CommonUtility.getViewDay(viewDate));
		commonSession.setEditMode(true);
		SelectNewActionForm f = new SelectNewActionForm();
		return f;
	}

	/**
	 * スケジュール一覧
	 * @param dbConn
	 * @param frm
	 * @param commonSession
	 * @return
	 * @throws Exception
	 */
	private ListScheduleActionForm doSchedule(Connection dbConn, CommonSession commonSession, ScheduleMonthActionForm frm) throws Exception {
		String userId = frm.getUserId();
		String viewDate = frm.getViewDate();
		commonSession.setViewDate(SqlDateUtility.getSqlDate(viewDate));
		commonSession.setViewMonthDisp(CommonUtility.getViewDate(viewDate));
		commonSession.setViewDateDisp(CommonUtility.getViewDay(viewDate));
		EditScheduleLogic logic = new EditScheduleLogic(dbConn);
		ListScheduleActionForm f = logic.getListForm(userId, viewDate);
		return f;
	}

	/**
	 * 日報一覧
	 * @param dbConn
	 * @param frm
	 * @param commonSession
	 * @return
	 * @throws Exception
	 */
	private ListReportActionForm doReport(Connection dbConn, CommonSession commonSession, ScheduleMonthActionForm frm) throws Exception {
		String userId = frm.getUserId();
		String viewDate = frm.getViewDate();
		commonSession.setViewDate(SqlDateUtility.getSqlDate(viewDate));
		commonSession.setViewMonthDisp(CommonUtility.getViewDate(viewDate));
		commonSession.setViewDateDisp(CommonUtility.getViewDay(viewDate));
		EditReportLogic logic = new EditReportLogic(dbConn);
		ListReportActionForm f = logic.getListForm(userId, viewDate);
		return f;
	}

	/**
	 * 編集画面へ
	 * @param dbConn
	 * @param frm
	 * @return
	 * @throws Exception
	 */
	private EditReportActionForm doSeq(Connection dbConn, CommonSession commonSession, ScheduleMonthActionForm frm) throws Exception {
		String userId = frm.getUserId();
		String viewDate = frm.getViewDate();

		EditReportLogic logic = new EditReportLogic(dbConn);
		EditReportActionForm f = logic.getEditForm(userId, viewDate, frm.getSeq());
		if (f.getApplyFlag() == null || f.getApplyFlag().isEmpty() || f.getApplyFlag().equals(Param.MODE_OFF)) {
			if (commonSession.getUserId().equals(frm.getUserId())) {
				commonSession.setEditMode(true);
				f.setViewMode(Param.MODE_EDIT);
			} else {
				commonSession.setEditMode(false);
				f.setViewMode(Param.MODE_COMMENT);
			}
		} else {
			commonSession.setEditMode(false);
			f.setViewMode(Param.MODE_COMMENT);
		}

		return f;
	}
}
