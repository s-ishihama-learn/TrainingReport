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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import jp.logware.custmer.common.AuthException;
import jp.logware.custmer.common.CommonSession;
import jp.logware.custmer.common.CommonUtility;
import jp.logware.custmer.common.ConnectionPool;
import jp.logware.custmer.common.Param;
import jp.logware.custmer.common.Utility;
import jp.logware.custmer.form.schedule.EditScheduleActionForm;
import jp.logware.custmer.form.schedule.ListScheduleActionForm;
import jp.logware.custmer.form.schedule.ScheduleMonthActionForm;
import jp.logware.custmer.form.schedule.ScheduleWeekActionForm;
import jp.logware.custmer.logic.schedule.EditScheduleLogic;
import jp.logware.custmer.logic.schedule.ScheduleMonthLogic;
import jp.logware.custmer.logic.schedule.ScheduleWeekLogic;

/**
 * スケジュール編集 アクションクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class EditScheduleAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("URI: " + request.getRequestURI());
		System.out.println("Method: " + request.getMethod()); // GET か POST か
		System.out.println("Referer: " + request.getHeader("referer")); // どこから来たか		
		ConnectionPool connectionPool = ConnectionPool.getInstance(request);
		Connection dbConn = connectionPool.getConnection();
		dbConn.setAutoCommit(false);
		try {
			HttpSession session = request.getSession(true);
			EditScheduleActionForm frm = (EditScheduleActionForm) form;
			System.out.println("EditScheduleAction:" + frm.getButton());

			ActionMessages messages = new ActionMessages();
			ActionErrors errors = new ActionErrors();
			CommonSession commonSession = (CommonSession) session.getAttribute("CommonSession");

			String forward = "error";
			request.setAttribute("EditScheduleForm", frm);

			if (commonSession == null) {
				throw new AuthException();

			} else if (frm.getButton() == null || frm.getButton().length() == 0) {
				throw new AuthException();

			} else if (frm.getButton().equals("regist")) {
				if (!CommonUtility.checkTimeCompere(frm.getStartTime(), frm.getEndTime())) {
					request.setAttribute("EditScheduleForm", frm);
					ActionMessage error = new ActionMessage("errors.time.compere");
					errors.add("value", error);

				} else {
					ListScheduleActionForm f = doRegist(dbConn, commonSession, frm);
					request.setAttribute("ListScheduleForm", f);
					forward = "regist";
				}

			} else if (frm.getButton().equals("edit")) {
				if (!CommonUtility.checkTimeCompere(frm.getStartTime(), frm.getEndTime())) {
					request.setAttribute("EditScheduleForm", frm);
					ActionMessage error = new ActionMessage("errors.time.compere");
					errors.add("value", error);

				} else {
					ListScheduleActionForm f = doEdit(dbConn, commonSession, frm);
					request.setAttribute("ListScheduleForm", f);
					forward = "edit";
				}

			} else if (frm.getButton().equals("delete")) {
				ListScheduleActionForm f = doDelete(dbConn, commonSession, frm);
				request.setAttribute("ListScheduleForm", f);
				forward = "delete";

			} else if (frm.getButton().equals("cancelNew")) {
				if (commonSession.getViewDisp().equals(Param.DISP_MONTH)) {
					ScheduleMonthActionForm f = doViewMonth(dbConn, commonSession);
					request.setAttribute("ScheduleMonthForm", f);
					forward = "cancelMonth";

				} else if (commonSession.getViewDisp().equals(Param.DISP_WEEK)) {
					ScheduleWeekActionForm f = doViewWeek(dbConn, commonSession);
					request.setAttribute("ScheduleWeekForm", f);
					forward = "cancelWeek";

				} else {
					throw new AuthException();
				}

			} else if (frm.getButton().equals("cancel")) {
				ListScheduleActionForm f = doCancel(dbConn, commonSession, frm);
				request.setAttribute("ListScheduleForm", f);
				forward = "cancel";

			} else {
				throw new AuthException();
			}

			dbConn.commit();

			saveMessages(request, messages);
			request.setAttribute(Globals.ERROR_KEY, errors);

			return mapping.findForward(forward);

		} catch (Exception e) {
			dbConn.rollback();
			e.printStackTrace();
			throw e;

		} finally {
			dbConn.setAutoCommit(true);
			connectionPool.releaseConnection(dbConn);
		}
	}

	/**
	 * 新規登録
	 * @param dbConn
	 * @param commonSession
	 * @param frm
	 * @return
	 * @throws Exception
	 */
	private ListScheduleActionForm doRegist(Connection dbConn, CommonSession commonSession, EditScheduleActionForm frm) throws Exception {
		EditScheduleLogic logic = new EditScheduleLogic(dbConn);
		logic.registData(frm);

		ListScheduleActionForm f = logic.getListForm(commonSession.getViewUserId(), frm.getViewDate());
		return f;
	}

	/**
	 * 更新処理
	 * @param dbConn
	 * @param commonSession
	 * @param frm
	 * @return
	 * @throws Exception
	 */
	private ListScheduleActionForm doEdit(Connection dbConn, CommonSession commonSession, EditScheduleActionForm frm) throws Exception {
		EditScheduleLogic logic = new EditScheduleLogic(dbConn);
		logic.updateData(frm);

		ListScheduleActionForm f = logic.getListForm(commonSession.getViewUserId(), frm.getViewDate());
		return f;
	}

	/**
	 * 削除処理
	 * @param dbConn
	 * @param commonSession
	 * @param frm
	 * @return
	 * @throws Exception
	 */
	private ListScheduleActionForm doDelete(Connection dbConn, CommonSession commonSession, EditScheduleActionForm frm) throws Exception {
		EditScheduleLogic logic = new EditScheduleLogic(dbConn);
		logic.removeData(frm);

		ListScheduleActionForm f = logic.getListForm(commonSession.getViewUserId(), frm.getViewDate());
		return f;
	}

	/**
	 * 月表示
	 * @param dbConn
	 * @param commonSession
	 * @return
	 * @throws Exception
	 */
	private ScheduleMonthActionForm doViewMonth(Connection dbConn, CommonSession commonSession) throws Exception {
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
	private ScheduleWeekActionForm doViewWeek(Connection dbConn, CommonSession commonSession) throws Exception {
		String userId = commonSession.getUserId();
		String viewDate = Utility.getDate(commonSession.getViewDate());

		ScheduleWeekLogic logic = new ScheduleWeekLogic(dbConn);
		ScheduleWeekActionForm f = logic.getForm(userId, viewDate, commonSession);

		return f;
	}

	/**
	 * キャンセル
	 * @param dbConn
	 * @param commonSession
	 * @param frm
	 * @return
	 * @throws Exception
	 */
	private ListScheduleActionForm doCancel(Connection dbConn, CommonSession commonSession, EditScheduleActionForm frm) throws Exception {
		EditScheduleLogic logic = new EditScheduleLogic(dbConn);
		ListScheduleActionForm f = logic.getListForm(commonSession.getViewUserId(), frm.getViewDate());
		return f;
	}
}
