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
import jp.logware.custmer.form.master.TypeEditActionForm;
import jp.logware.custmer.form.master.TypeListActionForm;
import jp.logware.custmer.logic.master.TypeLogic;

/**
 * カルテ部類編集 アクションクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class TypeEditAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConnectionPool connectionPool = ConnectionPool.getInstance(request);
		Connection dbConn = connectionPool.getConnection();
		dbConn.setAutoCommit(false);
		try {
			HttpSession session = request.getSession(true);
			TypeEditActionForm frm = (TypeEditActionForm) form;
			System.out.println("TypeEditAction:" + frm.getButton());

			ActionMessages messages = new ActionMessages();
			ActionErrors errors = new ActionErrors();
			CommonSession commonSession = (CommonSession) session.getAttribute("CommonSession");

			String forward = "error";
			request.setAttribute("TypeEditForm", frm);

			if (commonSession == null) {
				throw new AuthException();

			} else if (frm.getButton() == null || frm.getButton().length() == 0) {
				throw new AuthException();

			} else if (frm.getButton().equals("regist")) {
				TypeListActionForm f = doRegist(dbConn, frm);
				if (f == null) {
					frm.setViewMode("1");
					MessageResources resource = super.getResources(request);
					String para = resource.getMessage("typeEdit.code");
					ActionMessage error = new ActionMessage("errors.key", para);
					errors.add("value", error);
				} else {
					request.setAttribute("TypeListForm", f);
					forward = "regist";
				}

			} else if (frm.getButton().equals("edit")) {
				TypeListActionForm f = doEdit(dbConn, frm);
				request.setAttribute("TypeListForm", f);
				forward = "edit";

			} else if (frm.getButton().equals("delete")) {
				TypeListActionForm f = doDelete(dbConn, frm);
				request.setAttribute("TypeListForm", f);
				forward = "delete";

			} else if (frm.getButton().equals("cancel")) {
				TypeListActionForm f = doCancel(dbConn);
				request.setAttribute("TypeListForm", f);
				forward = "cancel";

			} else {
				request.setAttribute("TypeEditForm", frm);
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
	private TypeListActionForm doRegist(Connection dbConn, TypeEditActionForm frm) throws Exception {
		TypeLogic logic = new TypeLogic(dbConn);
		if (logic.count(frm.getTypeCode()) > 0) {
			return null;
		}
		logic.registData(frm);

		TypeListActionForm f = logic.getListForm();
		return f;
	}

	/**
	 * 編集処理
	 * @param dbConn
	 * @param frm
	 * @return
	 * @throws Exception
	 */
	private TypeListActionForm doEdit(Connection dbConn, TypeEditActionForm frm) throws Exception {
		TypeLogic logic = new TypeLogic(dbConn);
		logic.updateData(frm);

		TypeListActionForm f = logic.getListForm();
		return f;
	}

	/**
	 * 削除処理
	 * @param dbConn
	 * @param frm
	 * @return
	 * @throws Exception
	 */
	private TypeListActionForm doDelete(Connection dbConn, TypeEditActionForm frm) throws Exception {
		TypeLogic logic = new TypeLogic(dbConn);
		logic.removeData(frm);

		TypeListActionForm f = logic.getListForm();
		return f;
	}

	/**
	 * キャンセル
	 * @param dbConn
	 * @return
	 * @throws Exception
	 */
	private TypeListActionForm doCancel(Connection dbConn) throws Exception {
		TypeLogic logic = new TypeLogic(dbConn);
		TypeListActionForm f = logic.getListForm();
		return f;
	}
}
