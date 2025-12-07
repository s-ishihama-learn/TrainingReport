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
import org.apache.struts.action.ActionMessages;

import jp.logware.custmer.common.AuthException;
import jp.logware.custmer.common.CommonSession;
import jp.logware.custmer.common.ConnectionPool;
import jp.logware.custmer.form.ChangePasswdActionForm;
import jp.logware.custmer.form.LoginActionForm;
import jp.logware.custmer.form.MenuActionForm;
import jp.logware.custmer.logic.LoginLogic;

public class MenuAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConnectionPool connectionPool = ConnectionPool.getInstance(request);
		Connection dbConn = connectionPool.getConnection();
		try {
			HttpSession session = request.getSession(true);
			MenuActionForm frm = (MenuActionForm) form;
			System.out.println("MenuAction:" + frm.getButton());

			ActionMessages messages = new ActionMessages();
			ActionErrors errors = new ActionErrors();
			CommonSession commonSession = (CommonSession) session.getAttribute("CommonSession");

			String forward = "error";
			request.setAttribute("MenuForm", frm);

			if (commonSession == null) {
				throw new AuthException();

			} else if (commonSession.getUserCom() == null || commonSession.getUserCom().length() == 0) {
				throw new AuthException();

			} else if (frm.getButton() == null || frm.getButton().length() == 0) {
				throw new AuthException();

			} else if (frm.getButton().equals("passwd")) {
				ChangePasswdActionForm f = doPassword(commonSession);
				request.setAttribute("ChangePasswdForm", f);
				forward = "passwd";

			} else if (frm.getButton().equals("logout")) {
				LoginActionForm f = doLogout(dbConn, commonSession);
				request.setAttribute("LoginForm", f);
				forward = "logout";

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
	 * パスワード変更
	 * @param commonSession
	 * @return
	 */
	private ChangePasswdActionForm doPassword(CommonSession commonSession) {
		ChangePasswdActionForm f = new ChangePasswdActionForm();
		f.setUserId(commonSession.getUserId());
		return f;
	}

	/**
	 * ログアウト
	 * @param dbConn
	 * @param commonSession
	 * @return
	 * @throws Exception
	 */
	private LoginActionForm doLogout(Connection dbConn, CommonSession commonSession) throws Exception {
		commonSession = null;
		LoginLogic logic = new LoginLogic(dbConn);
		LoginActionForm f = logic.getForm();
		return f;
	}
}
