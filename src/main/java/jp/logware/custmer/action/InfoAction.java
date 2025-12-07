package jp.logware.custmer.action;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import jp.logware.custmer.common.ConnectionPool;
import jp.logware.custmer.form.master.InfoListActionForm;
import jp.logware.custmer.logic.master.InfoLogic;
import jp.logware.custmer.subform.InfoValue;

public class InfoAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConnectionPool connectionPool = ConnectionPool.getInstance(request);
		Connection dbConn = connectionPool.getConnection();
		try {
			System.out.println("InfoAction:");

			response.setContentType("text/text;charset=utf-8");
			response.setHeader("cache-control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print(getInfoHtml(dbConn));
			writer.close();

			return null;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		} finally {
			connectionPool.releaseConnection(dbConn);
		}
	}

	/**
	 * お知らせHTMLの生成
	 * @param dbConn
	 * @return
	 * @throws Exception
	 */
	private String getInfoHtml(Connection dbConn) throws Exception {
		InfoLogic logic = new InfoLogic(dbConn);
		InfoListActionForm f = logic.getInfoForm();
		StringBuffer buf = new StringBuffer();
		buf.append("<table class=\"table table-striped\">");
		for (InfoValue info : f.getInfomList()) {
			buf.append("<tr>");
			buf.append("<td class=\"col-sm-2\"><strong>");
			buf.append(info.getInfoDate());
			buf.append("</strong></td>");
			buf.append("<td class=\"col-sm-10\">");
			buf.append(info.getInfoBody().replaceAll("\r\n", "<br>").replaceAll("\n", "<br>").replaceAll("\\r", "<br>"));
			buf.append("</td>");
			buf.append("</tr>");
		}
		buf.append("</table>");
		return buf.toString();
	}
}
