package jp.logware.custmer.action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import javax.servlet.ServletContext;
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
import jp.logware.custmer.common.CommonUtility;
import jp.logware.custmer.common.ConnectionPool;
import jp.logware.custmer.common.Param;
import jp.logware.custmer.common.Utility;
import jp.logware.custmer.form.LoginActionForm;
import jp.logware.custmer.form.master.UserEditActionForm;
import jp.logware.custmer.form.master.UserInitActionForm;
import jp.logware.custmer.form.schedule.ScheduleMonthActionForm;
import jp.logware.custmer.logic.LoginLogic;
import jp.logware.custmer.logic.SelectBoxLogic;
import jp.logware.custmer.logic.master.UserLogic;
import jp.logware.custmer.logic.schedule.ScheduleMonthLogic;
import jp.logware.custmer.subform.DeptCodeSelectBox;
import jp.logware.custmer.subform.EndTimeSelectBox;
import jp.logware.custmer.subform.HolidayKindSelectBox;
import jp.logware.custmer.subform.StartTimeSelectBox;
import jp.logware.custmer.subform.TypeSelectBox;
import jp.logware.custmer.subform.UserComSelectBox;

/**
 * ログイン アクションクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class LoginAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConnectionPool connectionPool = ConnectionPool.getInstance(request);
		Connection dbConn = connectionPool.getConnection();
		try {
			HttpSession session = request.getSession(true);
			LoginActionForm frm = (LoginActionForm) form;
			System.out.println("LoginAction:" + frm.getButton());

			ActionMessages messages = new ActionMessages();
			ActionErrors errors = new ActionErrors();

			CommonSession commonSession = new CommonSession();
			session.setAttribute("CommonSession", commonSession);
			MessageResources resource = super.getResources(request);

			String forward = "error";
			request.setAttribute("LoginForm", frm);

			if (frm.getButton() == null || frm.getButton().length() == 0) {
				throw new AuthException();

			} else if (frm.getButton().equals("login")) {
				LoginLogic loginLogic = new LoginLogic(dbConn);
				if (loginLogic.authUser(frm.getUserId(), frm.getPasswd())) {
					// セレクトボックス生成
					SelectBoxLogic selectBoxLogic = new SelectBoxLogic(dbConn, resource);
					commonSession.setDeptSelectBox((new DeptCodeSelectBox(selectBoxLogic.getMasterDeptNameMap())).getValues());
					commonSession.setUserComSelectBox((new UserComSelectBox(selectBoxLogic.getUserComSelectBox())).getValues());
					commonSession.setTypeSelectBox((new TypeSelectBox(selectBoxLogic.getTypeSelectBox())).getValues());

					Properties prop = getProperties(request);
					commonSession.setHolidayKindSelectBox((new HolidayKindSelectBox(selectBoxLogic.getHolidayKindSelectBox(prop))).getValues());
					commonSession.setHolidayColorMap(selectBoxLogic.getHolidayColor(prop));
					commonSession.setStartTimeSelectBox((new StartTimeSelectBox(selectBoxLogic.getStartTimeselectBox(prop))).getValues());
					commonSession.setEndTimeSelectBox((new EndTimeSelectBox(selectBoxLogic.getEndTimeselectBox(prop))).getValues());

					ScheduleMonthLogic logic = new ScheduleMonthLogic(dbConn);
					String userId = frm.getUserId();
					String nowDate = Utility.getNowDate();
					ScheduleMonthActionForm f = logic.getForm(userId, nowDate, commonSession);

					UserLogic userLogic = new UserLogic(dbConn, resource);
					UserEditActionForm fu = userLogic.getEditForm(userId);
					commonSession.setUserId(frm.getUserId());
					commonSession.setUserCom(fu.getUserCom());
					commonSession.setUserName(fu.getUserName());
					commonSession.setViewUserId(frm.getUserId());
					commonSession.setViewUserName(fu.getUserName());
					commonSession.setViewDate(Utility.getDateNow());
					commonSession.setViewMonthDisp(CommonUtility.getViewDate(nowDate));
					commonSession.setViewDateDisp(CommonUtility.getViewDay(nowDate));
					commonSession.setNewLinkString(CommonUtility.getNewLinkStringMonthSchedule(userId, nowDate));
					commonSession.setItsMe(true);
					commonSession.setViewDisp(Param.DISP_MONTH);
					commonSession.setMasterViewDisp(Param.DISP_MONTH);

					request.setAttribute("ScheduleMonthForm", f);
					forward = "scheduleMonth";

				} else if (loginLogic.initUser(frm.getUserId())) {
					UserInitActionForm f = new UserInitActionForm();
					request.setAttribute("UserInitForm", f);
					forward = "initUser";

				} else {
					ActionMessage error = new ActionMessage("invalid.login");
					errors.add("value", error);
					LoginActionForm f = doInvalidLogin(dbConn);
					request.setAttribute("LoginForm", f);
					forward = "login";

				}

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
	 * 認証失敗
	 * @return
	 */
	private LoginActionForm doInvalidLogin(Connection dbConn) throws Exception {
		LoginLogic logic = new LoginLogic(dbConn);
		LoginActionForm f = logic.getForm();
		return f;
	}

	/**
	 * system.propertiesの読み込み
	 * @param path
	 * @return
	 */
	private Properties getProperties(HttpServletRequest request) {
		ServletContext context = request.getServletContext();
		String path = context.getRealPath("/WEB-INF/system.properties");

		InputStream in = null;
		Properties prop = null;
		try {
			in = new FileInputStream(path);
			prop = new Properties();
			prop.load(in);

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
		}

		return prop;
	}
}
