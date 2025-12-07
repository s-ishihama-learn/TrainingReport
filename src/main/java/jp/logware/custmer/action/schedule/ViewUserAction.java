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
import jp.logware.custmer.common.ConnectionPool;
import jp.logware.custmer.common.Utility;
import jp.logware.custmer.form.schedule.ScheduleMonthActionForm;
import jp.logware.custmer.form.schedule.ViewUserActionForm;
import jp.logware.custmer.logic.MasterLogic;
import jp.logware.custmer.logic.schedule.ScheduleMonthLogic;

/**
 * 表示ユーザ設定 アクションクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class ViewUserAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConnectionPool connectionPool = ConnectionPool.getInstance(request);
		Connection dbConn = connectionPool.getConnection();
		try {
			HttpSession session = request.getSession(true);
			ViewUserActionForm frm = (ViewUserActionForm) form;
			System.out.println("ViewUserAction:" + frm.getButton());

			ActionMessages messages = new ActionMessages();
			ActionErrors errors = new ActionErrors();
			CommonSession commonSession = (CommonSession) session.getAttribute("CommonSession");

			String forward = "error";
			request.setAttribute("ViewUserForm", frm);

			if (commonSession == null) {
				throw new AuthException();

			} else if (frm.getButton() == null || frm.getButton().length() == 0) {
				throw new AuthException();

			} else if (frm.getButton().equals("change")) {
				ScheduleMonthActionForm f = doChange(dbConn, commonSession, frm);
				request.setAttribute("ScheduleMonthForm", f);
				forward = "change";

			} else if (frm.getButton().equals("cancel")) {
				ScheduleMonthActionForm f = doCancel(dbConn, commonSession);
				request.setAttribute("ScheduleMonthForm", f);
				forward = "cancel";

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
	 * 表示利用者変更
	 * @param dbConn
	 * @param frm
	 * @param commonSession
	 * @return
	 * @throws Exception
	 */
	private ScheduleMonthActionForm doChange(Connection dbConn, CommonSession commonSession, ViewUserActionForm frm) throws Exception {
		ScheduleMonthLogic logic = new ScheduleMonthLogic(dbConn);
		MasterLogic masterLogic = new MasterLogic(dbConn);

		String userId = frm.getUserId();
		String viewDate = Utility.getDate(commonSession.getViewDate());
		ScheduleMonthActionForm f = logic.getForm(userId, viewDate, commonSession);

		commonSession.setViewUserId(userId);
		commonSession.setViewUserName(masterLogic.getMasterUserName(userId));

		if (commonSession.getUserId().equals(userId)) {
			commonSession.setItsMe(true);
		} else {
			commonSession.setItsMe(false);
		}

		return f;
	}

	/**
	 * キャンセル処理
	 * @param dbConn
	 * @param commonSession
	 * @return
	 * @throws Exception
	 */
	private ScheduleMonthActionForm doCancel(Connection dbConn, CommonSession commonSession) throws Exception {
		ScheduleMonthLogic logic = new ScheduleMonthLogic(dbConn);
		MasterLogic masterLogic = new MasterLogic(dbConn);

		String userId = commonSession.getViewUserId();
		String viewDate = Utility.getDate(commonSession.getViewDate());
		ScheduleMonthActionForm f = logic.getForm(userId, viewDate, commonSession);
		commonSession.setViewUserName(masterLogic.getMasterUserName(userId));
		return f;
	}
}
