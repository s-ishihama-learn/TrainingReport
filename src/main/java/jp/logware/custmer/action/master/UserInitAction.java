package jp.logware.custmer.action.master;

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
import org.apache.struts.util.MessageResources;

import jp.logware.custmer.common.AuthException;
import jp.logware.custmer.common.CommonSession;
import jp.logware.custmer.common.ConnectionPool;
import jp.logware.custmer.form.master.UserInitActionForm;
import jp.logware.custmer.form.master.UserListActionForm;
import jp.logware.custmer.logic.master.UserLogic;

public class UserInitAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConnectionPool connectionPool = ConnectionPool.getInstance(request);
		Connection dbConn = connectionPool.getConnection();
		dbConn.setAutoCommit(false);
		try {
			HttpSession session = request.getSession(true);
			UserInitActionForm frm = (UserInitActionForm) form;
			System.out.println("UserInitAction:" + frm.getButton());

			ActionMessages messages = new ActionMessages();
			ActionErrors errors = new ActionErrors();
			CommonSession commonSession = (CommonSession) session.getAttribute("CommonSession");

			MessageResources resource = super.getResources(request);

			String forward = "error";
			request.setAttribute("UserInitForm", frm);

			if (commonSession == null) {
				throw new AuthException();

			} else if (frm.getButton() == null || frm.getButton().length() == 0) {
				throw new AuthException();

			} else if (frm.getButton().equals("regist")) {
				UserListActionForm f = doRegist(dbConn, resource, frm);
				if (f == null) {
					String para = resource.getMessage("userEdit.code");
					ActionMessage error = new ActionMessage("errors.key", para);
					errors.add("value", error);
				} else {
					forward = "regist";
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
	 * 新規登録
	 * @param dbConn
	 * @param frm
	 * @return
	 * @throws Exception
	 */
	private UserListActionForm doRegist(Connection dbConn, MessageResources resource, UserInitActionForm frm) throws Exception {
		UserLogic logic = new UserLogic(dbConn, resource);
		if (logic.count(frm.getUserId()) > 0) {
			return null;
		}
		logic.initUser(frm.getUserId(), frm.getUserName(), frm.getPasswd());

		UserListActionForm f = logic.getListForm();
		return f;
	}
}
