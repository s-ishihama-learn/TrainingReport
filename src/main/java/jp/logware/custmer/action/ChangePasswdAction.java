package jp.logware.custmer.action;

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
import jp.logware.custmer.common.ConnectionPool;
import jp.logware.custmer.common.Param;
import jp.logware.custmer.common.Utility;
import jp.logware.custmer.form.ChangePasswdActionForm;
import jp.logware.custmer.form.LoginActionForm;
import jp.logware.custmer.form.schedule.ScheduleMonthActionForm;
import jp.logware.custmer.form.schedule.ScheduleWeekActionForm;
import jp.logware.custmer.logic.ChangePasswdLogic;
import jp.logware.custmer.logic.LoginLogic;
import jp.logware.custmer.logic.schedule.ScheduleMonthLogic;
import jp.logware.custmer.logic.schedule.ScheduleWeekLogic;

/**
 * パスワード変更 アクションクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class ChangePasswdAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConnectionPool connectionPool = ConnectionPool.getInstance(request);
		Connection dbConn = connectionPool.getConnection();
		dbConn.setAutoCommit(false);
		try {
			HttpSession session = request.getSession(true);
			ChangePasswdActionForm frm = (ChangePasswdActionForm) form;
			System.out.println("ChangePasswdAction:" + frm.getButton());

			ActionMessages messages = new ActionMessages();
			ActionErrors errors = new ActionErrors();
			CommonSession commonSession = (CommonSession) session.getAttribute("CommonSession");

			String forward = "error";
			request.setAttribute("ChangePasswdForm", frm);

			if (commonSession == null) {
				throw new AuthException();

			} else if (frm.getButton() == null || frm.getButton().length() == 0) {
				throw new AuthException();

			} else if (frm.getButton().equals("change")) {
				ChangePasswdLogic plogic = new ChangePasswdLogic(dbConn);
				ChangePasswdActionForm pf = plogic.getForm(commonSession.getUserId());
				if (!frm.getNewPass().equals(frm.getChkPass())) {
					ChangePasswdActionForm f = new ChangePasswdActionForm();
					f.setUserId(commonSession.getUserId());
					request.setAttribute("ChangePasswdForm", f);
					ActionMessage error = new ActionMessage("pass.change.new");
					errors.add("value", error);

				} else if (!frm.getOldPass().equals(pf.getOldPass())) {
					ChangePasswdActionForm f = new ChangePasswdActionForm();
					f.setUserId(commonSession.getUserId());
					request.setAttribute("ChangePasswdForm", f);
					ActionMessage error = new ActionMessage("pass.change.old");
					errors.add("value", error);

				} else {
					LoginActionForm f = changePasswd(dbConn, frm);
					request.setAttribute("LoginActionForm", f);
					forward = "change";
				}

			} else if (frm.getButton().equals("cancel")) {
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
	 * パスワード変更
	 * @param dbConn
	 * @param frm
	 * @param commonSession
	 * @return
	 * @throws Exception
	 */
	private LoginActionForm changePasswd(Connection dbConn, ChangePasswdActionForm frm) throws Exception {
		ChangePasswdLogic rlogic = new ChangePasswdLogic(dbConn);
		rlogic.updateData(frm);

		LoginLogic logic = new LoginLogic(dbConn);
		LoginActionForm f = logic.getForm();
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
}
