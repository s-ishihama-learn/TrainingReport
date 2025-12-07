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

import jp.logware.custmer.common.AuthException;
import jp.logware.custmer.common.CommonSession;
import jp.logware.custmer.common.ConnectionPool;
import jp.logware.custmer.form.master.InfoEditActionForm;
import jp.logware.custmer.form.master.InfoListActionForm;
import jp.logware.custmer.logic.master.InfoLogic;

/**
 * お知らせ編集 アクションクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class InfoEditAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConnectionPool connectionPool = ConnectionPool.getInstance(request);
		Connection dbConn = connectionPool.getConnection();
		dbConn.setAutoCommit(false);
		try {
			HttpSession session = request.getSession(true);
			InfoEditActionForm frm = (InfoEditActionForm) form;
			System.out.println("InfoEditAction:" + frm.getButton());

			ActionMessages messages = new ActionMessages();
			ActionErrors errors = new ActionErrors();
			CommonSession commonSession = (CommonSession) session.getAttribute("CommonSession");

			String forward = "error";
			request.setAttribute("InfoEditForm", frm);

			if (commonSession == null) {
				throw new AuthException();

			} else if (frm.getButton() == null || frm.getButton().length() == 0) {
				throw new AuthException();

			} else if (frm.getButton().equals("regist")) {
				InfoListActionForm f = doRegist(dbConn, frm);
				request.setAttribute("InfoListForm", f);
				forward = "regist";

			} else if (frm.getButton().equals("edit")) {
				InfoListActionForm f = doEdit(dbConn, frm);
				request.setAttribute("InfoListForm", f);
				forward = "edit";

			} else if (frm.getButton().equals("delete")) {
				InfoListActionForm f = doDelete(dbConn, frm);
				request.setAttribute("InfoListForm", f);
				forward = "delete";

			} else if (frm.getButton().equals("cancel")) {
				InfoListActionForm f = doCancel(dbConn);
				request.setAttribute("InfoListForm", f);
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
	private InfoListActionForm doRegist(Connection dbConn, InfoEditActionForm frm) throws Exception {
		InfoLogic logic = new InfoLogic(dbConn);
		logic.registData(frm);

		InfoListActionForm f = logic.getListForm();
		return f;
	}

	/**
	 * 編集処理
	 * @param dbConn
	 * @param frm
	 * @return
	 * @throws Exception
	 */
	private InfoListActionForm doEdit(Connection dbConn, InfoEditActionForm frm) throws Exception {
		InfoLogic logic = new InfoLogic(dbConn);
		logic.updateData(frm);

		InfoListActionForm f = logic.getListForm();
		return f;
	}

	/**
	 * 削除処理
	 * @param dbConn
	 * @param frm
	 * @return
	 * @throws Exception
	 */
	private InfoListActionForm doDelete(Connection dbConn, InfoEditActionForm frm) throws Exception {
		InfoLogic logic = new InfoLogic(dbConn);
		logic.removeData(frm);

		InfoListActionForm f = logic.getListForm();
		return f;
	}

	/**
	 * キャンセル
	 * @param dbConn
	 * @return
	 * @throws Exception
	 */
	private InfoListActionForm doCancel(Connection dbConn) throws Exception {
		InfoLogic logic = new InfoLogic(dbConn);
		InfoListActionForm f = logic.getListForm();
		return f;
	}
}
