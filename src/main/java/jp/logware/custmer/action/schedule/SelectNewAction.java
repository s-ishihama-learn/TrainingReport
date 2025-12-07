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

import jp.logware.custmer.common.AuthException;
import jp.logware.custmer.common.CommonSession;
import jp.logware.custmer.common.CommonUtility;
import jp.logware.custmer.common.ConnectionPool;
import jp.logware.custmer.common.Param;
import jp.logware.custmer.common.Utility;
import jp.logware.custmer.form.report.EditReportActionForm;
import jp.logware.custmer.form.schedule.EditScheduleActionForm;
import jp.logware.custmer.form.schedule.ScheduleMonthActionForm;
import jp.logware.custmer.form.schedule.ScheduleWeekActionForm;
import jp.logware.custmer.form.schedule.SelectNewActionForm;
import jp.logware.custmer.logic.schedule.ScheduleMonthLogic;
import jp.logware.custmer.logic.schedule.ScheduleWeekLogic;

public class SelectNewAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("URI: " + request.getRequestURI());
		System.out.println("Method: " + request.getMethod()); // GET か POST か
		System.out.println("Referer: " + request.getHeader("referer")); // どこから来たか		
		ConnectionPool connectionPool = ConnectionPool.getInstance(request);
		Connection dbConn = connectionPool.getConnection();
		try {
			HttpSession session = request.getSession(true);
			SelectNewActionForm frm = (SelectNewActionForm) form;
			System.out.println("SelectNewAction:" + frm.getButton());

			ActionMessages messages = new ActionMessages();
			ActionErrors errors = new ActionErrors();
			CommonSession commonSession = (CommonSession) session.getAttribute("CommonSession");

			String forward = "error";
			request.setAttribute("SelectNewForm", frm);

			if (commonSession == null) {
				throw new AuthException();

			} else if (frm.getButton() == null || frm.getButton().length() == 0) {
				throw new AuthException();

			} else if (frm.getButton().equals("schedule")) {
				EditScheduleActionForm f = doSchedule(commonSession, frm);
				request.setAttribute("EditScheduleForm", f);
				forward = "schedule";

			} else if (frm.getButton().equals("report")) {
				EditReportActionForm f = doReport(commonSession, frm);
				session.setAttribute("EditReportForm", f);
				forward = "report";

			} else if (frm.getButton().equals("cancel")) {
				if (commonSession.getViewDisp().equals(Param.DISP_MONTH)) {
					ScheduleMonthActionForm f = doCancelMonth(dbConn, commonSession);
					request.setAttribute("ScheduleMonthForm", f);
					forward = "cancelMonth";

				} else if (commonSession.getViewDisp().equals(Param.DISP_WEEK)) {
					ScheduleWeekActionForm f = doCancelWeek(dbConn, commonSession);
					request.setAttribute("ScheduleWeekForm", f);
					forward = "cancelWeek";

				} else {
					throw new AuthException();
				}

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
	 * 新規
	 * @param dbConn
	 * @param frm
	 * @param commonSession
	 * @return
	 * @throws Exception
	 */
	private EditScheduleActionForm doSchedule(CommonSession commonSession, SelectNewActionForm frm) throws Exception {
		EditScheduleActionForm f = new EditScheduleActionForm();
		f.setUserId(commonSession.getViewUserId());
		f.setUserName(commonSession.getViewUserName());
		f.setViewDate(CommonUtility.getDateString(commonSession.getViewDate()));
		f.setViewMode(Param.MODE_NEW);
		return f;
	}

	/**
	 * 新規
	 * @param dbConn
	 * @param frm
	 * @param commonSession
	 * @return
	 * @throws Exception
	 */
	private EditReportActionForm doReport(CommonSession commonSession, SelectNewActionForm frm) throws Exception {
		EditReportActionForm f = new EditReportActionForm();
		f.setUserId(commonSession.getViewUserId());
		f.setUserName(commonSession.getViewUserName());
		f.setViewDate(CommonUtility.getDateString(commonSession.getViewDate()));
		f.setViewMode(Param.MODE_NEW);
		return f;
	}

	/**
	 * 月表示
	 * @param dbConn
	 * @param commonSession
	 * @return
	 * @throws Exception
	 */
	private ScheduleMonthActionForm doCancelMonth(Connection dbConn, CommonSession commonSession) throws Exception {
		String userId = commonSession.getViewUserId();
		String viewDate = Utility.getDate(commonSession.getViewDate());

		ScheduleMonthLogic logic = new ScheduleMonthLogic(dbConn);
		ScheduleMonthActionForm f = logic.getForm(userId, viewDate, commonSession);

		return f;
	}

	/**
	 * 週表示
	 * @param dbConn
	 * @param commonSession
	 * @return
	 * @throws Exception
	 */
	private ScheduleWeekActionForm doCancelWeek(Connection dbConn, CommonSession commonSession) throws Exception {
		String userId = commonSession.getUserId();
		String viewDate = Utility.getDate(commonSession.getViewDate());

		ScheduleWeekLogic logic = new ScheduleWeekLogic(dbConn);
		ScheduleWeekActionForm f = logic.getForm(userId, viewDate, commonSession);

		return f;
	}
}
