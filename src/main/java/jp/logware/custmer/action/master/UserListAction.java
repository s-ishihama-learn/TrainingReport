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
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import jp.logware.custmer.common.AuthException;
import jp.logware.custmer.common.CommonSession;
import jp.logware.custmer.common.ConnectionPool;
import jp.logware.custmer.common.Param;
import jp.logware.custmer.form.master.UserEditActionForm;
import jp.logware.custmer.form.master.UserListActionForm;
import jp.logware.custmer.logic.master.UserLogic;

/**
 * 社員一覧 アクションクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class UserListAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConnectionPool connectionPool = ConnectionPool.getInstance(request);
		Connection dbConn = connectionPool.getConnection();
		try {
			HttpSession session = request.getSession(true);
			UserListActionForm frm = (UserListActionForm) form;
			System.out.println("UserListAction:" + frm.getButton());

			ActionMessages messages = new ActionMessages();
			ActionErrors errors = new ActionErrors();
			CommonSession commonSession = (CommonSession) session.getAttribute("CommonSession");

			MessageResources resource = super.getResources(request);

			String forward = "error";
			request.setAttribute("UserListForm", frm);

			if (commonSession == null) {
				throw new AuthException();

			} else if (frm.getButton() == null || frm.getButton().length() == 0) {
				throw new AuthException();

			} else if (frm.getButton().equals("new")) {
				UserEditActionForm f = doNew(dbConn, resource);
				request.setAttribute("UserEditForm", f);
				forward = "new";

			} else if (frm.getButton().equals("name")) {
				UserEditActionForm f = doName(dbConn, resource, frm);
				request.setAttribute("UserEditForm", f);
				forward = "name";

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
	 * 新規登録
	 * @param dbConn
	 * @return
	 * @throws Exception
	 */
	private UserEditActionForm doNew(Connection dbConn, MessageResources resource) throws Exception {
		UserLogic logic = new UserLogic(dbConn, resource);
		UserEditActionForm f = logic.getEditForm("");
		f.setViewMode(Param.MODE_NEW);
		return f;
	}

	/**
	 * 編集画面へ
	 * @param dbConn
	 * @param frm
	 * @return
	 * @throws Exception
	 */
	private UserEditActionForm doName(Connection dbConn, MessageResources resource, UserListActionForm frm) throws Exception {
		UserLogic logic = new UserLogic(dbConn, resource);
		UserEditActionForm f = logic.getEditForm(frm.getUserId());
		f.setViewMode(Param.MODE_EDIT);
		return f;
	}
}
