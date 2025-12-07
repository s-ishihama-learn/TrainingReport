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
import jp.logware.custmer.form.master.UserEditActionForm;
import jp.logware.custmer.form.master.UserListActionForm;
import jp.logware.custmer.logic.master.UserLogic;

/**
 * 社員編集 アクションクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class UserEditAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConnectionPool connectionPool = ConnectionPool.getInstance(request);
		Connection dbConn = connectionPool.getConnection();
		dbConn.setAutoCommit(false);
		try {
			HttpSession session = request.getSession(true);
			UserEditActionForm frm = (UserEditActionForm) form;
			System.out.println("UserEditAction:" + frm.getButton());

			ActionMessages messages = new ActionMessages();
			ActionErrors errors = new ActionErrors();
			CommonSession commonSession = (CommonSession) session.getAttribute("CommonSession");

			MessageResources resource = super.getResources(request);

			String forward = "error";
			request.setAttribute("UserEditForm", frm);

			if (commonSession == null) {
				throw new AuthException();

			} else if (frm.getButton() == null || frm.getButton().length() == 0) {
				throw new AuthException();

			} else if (frm.getButton().equals("regist")) {
				UserListActionForm f = doRegist(dbConn, resource, frm);
				if (f == null) {
					frm.setViewMode("1");
					String para = resource.getMessage("userEdit.code");
					ActionMessage error = new ActionMessage("errors.key", para);
					errors.add("value", error);
				} else {
					request.setAttribute("UserListForm", f);
					forward = "regist";
				}

			} else if (frm.getButton().equals("edit")) {
				UserListActionForm f = doEdit(dbConn, resource, frm);
				request.setAttribute("UserListForm", f);
				forward = "edit";

			} else if (frm.getButton().equals("delete")) {
				UserListActionForm f = doDelete(dbConn, resource, frm);
				request.setAttribute("UserListForm", f);
				forward = "delete";

			} else if (frm.getButton().equals("cancel")) {
				UserListActionForm f = doCancel(dbConn, resource);
				request.setAttribute("UserListForm", f);
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
	 * @param frm
	 * @return
	 * @throws Exception
	 */
	private UserListActionForm doRegist(Connection dbConn, MessageResources resource, UserEditActionForm frm) throws Exception {
		UserLogic logic = new UserLogic(dbConn, resource);
		if (logic.count(frm.getUserId()) > 0) {
			return null;
		}
		logic.registData(frm);

		UserListActionForm f = logic.getListForm();
		return f;
	}

	/**
	 * 編集処理
	 * @param dbConn
	 * @param frm
	 * @return
	 * @throws Exception
	 */
	private UserListActionForm doEdit(Connection dbConn, MessageResources resource, UserEditActionForm frm) throws Exception {
		UserLogic logic = new UserLogic(dbConn, resource);
		logic.updateData(frm);

		UserListActionForm f = logic.getListForm();
		return f;
	}

	/**
	 * 削除処理
	 * @param dbConn
	 * @param frm
	 * @return
	 * @throws Exception
	 */
	private UserListActionForm doDelete(Connection dbConn, MessageResources resource, UserEditActionForm frm) throws Exception {
		UserLogic logic = new UserLogic(dbConn, resource);
		logic.removeData(frm);

		UserListActionForm f = logic.getListForm();
		return f;
	}

	/**
	 * キャンセル
	 * @param dbConn
	 * @return
	 * @throws Exception
	 */
	private UserListActionForm doCancel(Connection dbConn, MessageResources resource) throws Exception {
		UserLogic logic = new UserLogic(dbConn, resource);
		UserListActionForm f = logic.getListForm();
		return f;
	}
}
