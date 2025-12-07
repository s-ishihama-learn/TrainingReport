package jp.logware.custmer.action.report;

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
import jp.logware.custmer.common.ConnectionPool;
import jp.logware.custmer.common.Param;
import jp.logware.custmer.common.Utility;
import jp.logware.custmer.form.report.EditReportActionForm;
import jp.logware.custmer.form.report.ListReportActionForm;
import jp.logware.custmer.form.schedule.ScheduleMonthActionForm;
import jp.logware.custmer.form.schedule.ScheduleWeekActionForm;
import jp.logware.custmer.logic.report.EditReportLogic;
import jp.logware.custmer.logic.schedule.ScheduleMonthLogic;
import jp.logware.custmer.logic.schedule.ScheduleWeekLogic;

/**
 * カルテ分類一覧 アクションクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class ListReportAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConnectionPool connectionPool = ConnectionPool.getInstance(request);
		Connection dbConn = connectionPool.getConnection();
		try {
			HttpSession session = request.getSession(true);
			ListReportActionForm frm = (ListReportActionForm) form;
			System.out.println("ListReportAction:" + frm.getButton());

			ActionMessages messages = new ActionMessages();
			ActionErrors errors = new ActionErrors();
			CommonSession commonSession = (CommonSession) session.getAttribute("CommonSession");

			String forward = "error";
			request.setAttribute("ListReportForm", frm);

			if (commonSession == null) {
				throw new AuthException();

			} else if (frm.getButton() == null || frm.getButton().length() == 0) {
				throw new AuthException();

			} else if (frm.getButton().equals("seq")) {
				EditReportActionForm f = doSeq(dbConn, commonSession, frm);
				session.setAttribute("EditReportForm", f);
				forward = "seq";

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
	 * 編集画面へ
	 * @param dbConn
	 * @param frm
	 * @return
	 * @throws Exception
	 */
	private EditReportActionForm doSeq(Connection dbConn, CommonSession commonSession, ListReportActionForm frm) throws Exception {
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
