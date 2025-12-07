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
import jp.logware.custmer.form.schedule.ScheduleWeekActionForm;
import jp.logware.custmer.form.schedule.ViewGroupUserActionForm;
import jp.logware.custmer.logic.schedule.ScheduleWeekLogic;
import jp.logware.custmer.logic.schedule.ViewGroupUserLogic;

/**
 * 表示グループ設定 アクションクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class ViewGroupUserAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConnectionPool connectionPool = ConnectionPool.getInstance(request);
		Connection dbConn = connectionPool.getConnection();
		try {
			HttpSession session = request.getSession(true);
			ViewGroupUserActionForm frm = (ViewGroupUserActionForm) form;
			System.out.println("ViewGroupUserAction:" + frm.getButton());

			ActionMessages messages = new ActionMessages();
			ActionErrors errors = new ActionErrors();
			CommonSession commonSession = (CommonSession) session.getAttribute("CommonSession");

			String forward = "error";
			request.setAttribute("ViewGroupUserForm", frm);

			if (commonSession == null) {
				throw new AuthException();

			} else if (frm.getButton() == null || frm.getButton().length() == 0) {
				throw new AuthException();

			} else if (frm.getButton().equals("change")) {
				ViewGroupUserLogic rlogic = new ViewGroupUserLogic(dbConn);
				frm.setUserId(commonSession.getUserId());
				rlogic.removeData(frm);
				rlogic.registData(frm);

				ScheduleWeekLogic logic = new ScheduleWeekLogic(dbConn);
				String userId = commonSession.getUserId();
				String viewDate = Utility.getDate(commonSession.getViewDate());
				ScheduleWeekActionForm f = logic.getForm(userId, viewDate, commonSession);
				request.setAttribute("ScheduleWeekForm", f);
				forward = "change";

			} else if (frm.getButton().equals("cancel")) {
				ScheduleWeekLogic logic = new ScheduleWeekLogic(dbConn);
				String userId = commonSession.getViewUserId();
				String viewDate = Utility.getDate(commonSession.getViewDate());
				ScheduleWeekActionForm f = logic.getForm(userId, viewDate, commonSession);
				request.setAttribute("ScheduleWeekForm", f);
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
}
