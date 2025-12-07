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
import jp.logware.custmer.form.master.HolidayEditActionForm;
import jp.logware.custmer.form.master.HolidayListActionForm;
import jp.logware.custmer.logic.master.HolidayLogic;

/**
 * 社員一覧 アクションクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class HolidayListAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConnectionPool connectionPool = ConnectionPool.getInstance(request);
		Connection dbConn = connectionPool.getConnection();
		try {
			HttpSession session = request.getSession(true);
			HolidayListActionForm frm = (HolidayListActionForm) form;
			System.out.println("HolidayListAction:" + frm.getButton());

			ActionMessages messages = new ActionMessages();
			ActionErrors errors = new ActionErrors();
			CommonSession commonSession = (CommonSession) session.getAttribute("CommonSession");

			String forward = "error";
			request.setAttribute("HolidayListForm", frm);

			if (commonSession == null) {
				throw new AuthException();

			} else if (frm.getButton() == null || frm.getButton().length() == 0) {
				throw new AuthException();

			} else if (frm.getButton().equals("new")) {
				HolidayEditActionForm f = doNew();
				request.setAttribute("HolidayEditForm", f);
				forward = "new";

			} else if (frm.getButton().equals("date")) {
				HolidayEditActionForm f = doDate(dbConn, frm);
				request.setAttribute("HolidayEditForm", f);
				forward = "date";

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
	private HolidayEditActionForm doNew() throws Exception {
		HolidayEditActionForm f = new HolidayEditActionForm();
		f.setViewMode("1");
		return f;
	}

	/**
	 * 編集画面へ
	 * @param dbConn
	 * @param frm
	 * @return
	 * @throws Exception
	 */
	private HolidayEditActionForm doDate(Connection dbConn, HolidayListActionForm frm) throws Exception {
		HolidayLogic logic = new HolidayLogic(dbConn);
		HolidayEditActionForm f = logic.getEditForm(frm.getDate());
		f.setViewMode("2");
		return f;
	}
}
