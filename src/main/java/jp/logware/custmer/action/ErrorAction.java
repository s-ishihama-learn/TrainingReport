package jp.logware.custmer.action;

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
import jp.logware.custmer.form.ErrorActionForm;
import jp.logware.custmer.form.LoginActionForm;

public class ErrorAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(true);
			ErrorActionForm frm = (ErrorActionForm) form;
			System.out.println("ErrorAction:" + frm.getButton());

			ActionMessages messages = new ActionMessages();
			ActionErrors errors = new ActionErrors();
			session.setAttribute("CommonSession", null);

			String forward = "error";
			request.setAttribute("ErrorForm", frm);

			if (frm.getButton() == null || frm.getButton().length() == 0) {
				throw new AuthException();

			} else if (frm.getButton().equals("login")) {
				LoginActionForm f = new LoginActionForm();
				request.setAttribute("LoginForm", f);
				forward = "login";

			} else {
				throw new AuthException();
			}

			saveMessages(request, messages);
			request.setAttribute(Globals.ERROR_KEY, errors);

			return mapping.findForward(forward);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
