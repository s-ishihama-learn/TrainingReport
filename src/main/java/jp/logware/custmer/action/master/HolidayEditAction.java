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
import jp.logware.custmer.form.master.HolidayEditActionForm;
import jp.logware.custmer.form.master.HolidayListActionForm;
import jp.logware.custmer.logic.master.HolidayLogic;

/**
 * 社員編集 アクションクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class HolidayEditAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConnectionPool connectionPool = ConnectionPool.getInstance(request);
		Connection dbConn = connectionPool.getConnection();
		dbConn.setAutoCommit(false);
		try {
			HttpSession session = request.getSession(true);
			HolidayEditActionForm frm = (HolidayEditActionForm) form;
			System.out.println("HolidayEditAction:" + frm.getButton());

			ActionMessages messages = new ActionMessages();
			ActionErrors errors = new ActionErrors();
			CommonSession commonSession = (CommonSession) session.getAttribute("CommonSession");

			String forward = "error";
			request.setAttribute("HolidayEditForm", frm);

			if (commonSession == null) {
				throw new AuthException();

			} else if (frm.getButton() == null || frm.getButton().length() == 0) {
				throw new AuthException();

			} else if (frm.getButton().equals("regist")) {
				HolidayListActionForm f = doRegist(dbConn,commonSession, frm);
				if (f == null) {
					frm.setViewMode("1");
					MessageResources resource = super.getResources(request);
					String para = resource.getMessage("holidayEdit.code");
					ActionMessage error = new ActionMessage("errors.key", para);
					errors.add("value", error);
				} else {
					request.setAttribute("HolidayListForm", f);
					forward = "regist";
				}

			} else if (frm.getButton().equals("edit")) {
				HolidayListActionForm f = doEdit(dbConn, commonSession, frm);
				request.setAttribute("HolidayListForm", f);
				forward = "edit";

			} else if (frm.getButton().equals("delete")) {
				HolidayListActionForm f = doDelete(dbConn, commonSession, frm);
				request.setAttribute("HolidayListForm", f);
				forward = "delete";

			} else if (frm.getButton().equals("cancel")) {
				HolidayListActionForm f = doCancel(dbConn, commonSession);
				request.setAttribute("HolidayListForm", f);
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
	private HolidayListActionForm doRegist(Connection dbConn, CommonSession commonSession, HolidayEditActionForm frm) throws Exception {
		HolidayLogic logic = new HolidayLogic(dbConn);
		if (logic.count(frm.getDate()) > 0) {
			return null;
		}
		logic.registData(frm);

		HolidayListActionForm f = logic.getListForm(commonSession.getHolidayKindSelectBox());
		return f;
	}

	/**
	 * 編集処理
	 * @param dbConn
	 * @param frm
	 * @return
	 * @throws Exception
	 */
	private HolidayListActionForm doEdit(Connection dbConn, CommonSession commonSession, HolidayEditActionForm frm) throws Exception {
		HolidayLogic logic = new HolidayLogic(dbConn);
		logic.updateData(frm);

		HolidayListActionForm f = logic.getListForm(commonSession.getHolidayKindSelectBox());
		return f;
	}

	/**
	 * 削除処理
	 * @param dbConn
	 * @param frm
	 * @return
	 * @throws Exception
	 */
	private HolidayListActionForm doDelete(Connection dbConn, CommonSession commonSession, HolidayEditActionForm frm) throws Exception {
		HolidayLogic logic = new HolidayLogic(dbConn);
		logic.removeData(frm);

		HolidayListActionForm f = logic.getListForm(commonSession.getHolidayKindSelectBox());
		return f;
	}

	/**
	 * キャンセル
	 * @param dbConn
	 * @return
	 * @throws Exception
	 */
	private HolidayListActionForm doCancel(Connection dbConn, CommonSession commonSession) throws Exception {
		HolidayLogic logic = new HolidayLogic(dbConn);
		HolidayListActionForm f = logic.getListForm(commonSession.getHolidayKindSelectBox());
		return f;
	}
}
