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
import jp.logware.custmer.form.master.DeptEditActionForm;
import jp.logware.custmer.form.master.DeptListActionForm;
import jp.logware.custmer.logic.master.DeptLogic;

/**
 * 所属編集 アクションクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class DeptEditAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConnectionPool connectionPool = ConnectionPool.getInstance(request);
		Connection dbConn = connectionPool.getConnection();
		dbConn.setAutoCommit(false);
		try {
			HttpSession session = request.getSession(true);
			DeptEditActionForm frm = (DeptEditActionForm) form;
			System.out.println("DeptEditAction:" + frm.getButton());

			ActionMessages messages = new ActionMessages();
			ActionErrors errors = new ActionErrors();
			CommonSession commonSession = (CommonSession) session.getAttribute("CommonSession");

			String forward = "error";
			request.setAttribute("DeptEditForm", frm);

			if (commonSession == null) {
				throw new AuthException();

			} else if (frm.getButton() == null || frm.getButton().length() == 0) {
				throw new AuthException();

			} else if (frm.getButton().equals("regist")) {
				DeptListActionForm f = doRegist(dbConn, frm);
				if (f == null) {
					frm.setViewMode("1");
					MessageResources resource = super.getResources(request);
					String para = resource.getMessage("deptEdit.code");
					ActionMessage error = new ActionMessage("errors.key", para);
					errors.add("value", error);
				} else {
					request.setAttribute("DeptListForm", f);
					forward = "regist";
				}

			} else if (frm.getButton().equals("edit")) {
				DeptListActionForm f = doEdit(dbConn, frm);
				request.setAttribute("DeptListForm", f);
				forward = "edit";

			} else if (frm.getButton().equals("delete")) {
				DeptListActionForm f = doDelete(dbConn, frm);
				request.setAttribute("DeptListForm", f);
				forward = "delete";

			} else if (frm.getButton().equals("cancel")) {
				DeptListActionForm f = doCancel(dbConn);
				request.setAttribute("DeptListForm", f);
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
	private DeptListActionForm doRegist(Connection dbConn, DeptEditActionForm frm) throws Exception {
		DeptLogic logic = new DeptLogic(dbConn);
		if (logic.count(frm.getDeptCode()) > 0) {
			return null;
		}
		logic.registData(frm);

		DeptListActionForm f = logic.getListForm();
		return f;
	}

	/**
	 * 編集処理
	 * @param dbConn
	 * @param frm
	 * @return
	 * @throws Exception
	 */
	private DeptListActionForm doEdit(Connection dbConn, DeptEditActionForm frm) throws Exception {
		DeptLogic logic = new DeptLogic(dbConn);
		logic.updateData(frm);

		DeptListActionForm f = logic.getListForm();
		return f;
	}

	/**
	 * 削除処理
	 * @param dbConn
	 * @param frm
	 * @return
	 * @throws Exception
	 */
	private DeptListActionForm doDelete(Connection dbConn, DeptEditActionForm frm) throws Exception {
		DeptLogic logic = new DeptLogic(dbConn);
		logic.removeData(frm);

		DeptListActionForm f = logic.getListForm();
		return f;
	}

	/**
	 * キャンセル処理
	 * @param dbConn
	 * @return
	 * @throws Exception
	 */
	private DeptListActionForm doCancel(Connection dbConn) throws Exception {
		DeptLogic logic = new DeptLogic(dbConn);
		DeptListActionForm f = logic.getListForm();
		return f;
	}
}
