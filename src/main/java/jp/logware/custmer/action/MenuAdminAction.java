package jp.logware.custmer.action;

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
import jp.logware.custmer.common.CommonUtility;
import jp.logware.custmer.common.ConnectionPool;
import jp.logware.custmer.common.Param;
import jp.logware.custmer.common.Utility;
import jp.logware.custmer.form.MenuActionForm;
import jp.logware.custmer.form.master.DeptListActionForm;
import jp.logware.custmer.form.master.HolidayListActionForm;
import jp.logware.custmer.form.master.InfoListActionForm;
import jp.logware.custmer.form.master.TypeListActionForm;
import jp.logware.custmer.form.master.UserListActionForm;
import jp.logware.custmer.form.schedule.ScheduleMonthActionForm;
import jp.logware.custmer.form.schedule.ScheduleWeekActionForm;
import jp.logware.custmer.form.schedule.ViewGroupUserActionForm;
import jp.logware.custmer.form.schedule.ViewUserActionForm;
import jp.logware.custmer.logic.master.DeptLogic;
import jp.logware.custmer.logic.master.HolidayLogic;
import jp.logware.custmer.logic.master.InfoLogic;
import jp.logware.custmer.logic.master.TypeLogic;
import jp.logware.custmer.logic.master.UserLogic;
import jp.logware.custmer.logic.schedule.ScheduleMonthLogic;
import jp.logware.custmer.logic.schedule.ScheduleWeekLogic;
import jp.logware.custmer.logic.schedule.ViewGroupUserLogic;
import jp.logware.custmer.logic.schedule.ViewUserLogic;


public class MenuAdminAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConnectionPool connectionPool = ConnectionPool.getInstance(request);
		Connection dbConn = connectionPool.getConnection();
		try {
			HttpSession session = request.getSession(true);
			MenuActionForm frm = (MenuActionForm) form;
			System.out.println("MenuAdminAction:" + frm.getButton());

			ActionMessages messages = new ActionMessages();
			ActionErrors errors = new ActionErrors();
			CommonSession commonSession = (CommonSession) session.getAttribute("CommonSession");

			String forward = "error";
			request.setAttribute("MenuForm", frm);

			if (commonSession == null) {
				throw new AuthException();

			} else if (commonSession.getUserCom() == null || commonSession.getUserCom().length() == 0 || !commonSession.getUserCom().equals(Param.USER_ADMIN)) {
				throw new AuthException();

			} else if (frm.getButton() == null || frm.getButton().length() == 0) {
				throw new AuthException();

			} else if (frm.getButton().equals("viewMonth")) {
				commonSession.setMasterViewDisp(Param.DISP_MONTH);
				ScheduleMonthActionForm f = doViewMonth(dbConn, commonSession);
				request.setAttribute("ScheduleMonthForm", f);
				forward = "viewMonth";

			} else if (frm.getButton().equals("viewUser")) {
				ViewUserActionForm f = doViewUser(dbConn, commonSession);
				request.setAttribute("ViewUserForm", f);
				forward = "viewUser";

			} else if (frm.getButton().equals("viewWeek")) {
				commonSession.setMasterViewDisp(Param.DISP_WEEK);
				ScheduleWeekActionForm f = doViewWeek(dbConn, commonSession);
				request.setAttribute("ScheduleWeekForm", f);
				forward = "viewWeek";

			} else if (frm.getButton().equals("viewGroup")) {
				ViewGroupUserActionForm f = doViewGroup(dbConn, commonSession);
				request.setAttribute("ViewGroupUserForm", f);
				forward = "viewGroup";

			} else if (frm.getButton().equals("deptList")) {
				changeViewMode(commonSession);
				DeptListActionForm f = doDeptList(dbConn);
				request.setAttribute("DeptListForm", f);
				forward = "deptList";

			} else if (frm.getButton().equals("userList")) {
				changeViewMode(commonSession);
				UserListActionForm f = doUserList(dbConn, super.getResources(request));
				request.setAttribute("UserListForm", f);
				forward = "userList";

			} else if (frm.getButton().equals("typeList")) {
				changeViewMode(commonSession);
				TypeListActionForm f = doTypeList(dbConn);
				request.setAttribute("TypeListForm", f);
				forward = "typeList";

			} else if (frm.getButton().equals("infoList")) {
				changeViewMode(commonSession);
				InfoListActionForm f = doInfoList(dbConn);
				request.setAttribute("InfoListForm", f);
				forward = "infoList";

			} else if (frm.getButton().equals("holidayList")) {
				changeViewMode(commonSession);
				HolidayListActionForm f = doHolidayList(dbConn, commonSession);
				request.setAttribute("HolidayListForm", f);
				forward = "holidayList";

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
	 * 月表示
	 * @param dbConn
	 * @param commonSession
	 * @return
	 * @throws Exception
	 */
	private ScheduleMonthActionForm doViewMonth(Connection dbConn, CommonSession commonSession) throws Exception {
		String userId = commonSession.getViewUserId();
		String viewDate = Utility.getDate(commonSession.getViewDate());

		commonSession.setNewLinkString(CommonUtility.getNewLinkStringMonthSchedule(userId, viewDate));
		commonSession.setViewDisp(Param.DISP_MONTH);

		ScheduleMonthLogic logic = new ScheduleMonthLogic(dbConn);
		ScheduleMonthActionForm f = logic.getForm(userId, viewDate, commonSession);
		return f;
	}

	/**
	 * 表示ユーザ変更
	 * @param dbConn
	 * @param commonSession
	 * @return
	 * @throws Exception
	 */
	private ViewUserActionForm doViewUser(Connection dbConn, CommonSession commonSession) throws Exception {
		ViewUserLogic logic = new ViewUserLogic(dbConn);
		ViewUserActionForm f = logic.getForm();
		f.setUserId(commonSession.getViewUserId());
		return f;
	}

	/**
	 * 週表示
	 * @param dbConn
	 * @param commonSession
	 * @return
	 * @throws Exception
	 */
	private ScheduleWeekActionForm doViewWeek(Connection dbConn, CommonSession commonSession) throws Exception {
		String userId = commonSession.getUserId();
		String viewDate = Utility.getDate(commonSession.getViewDate());

		commonSession.setNewLinkString(CommonUtility.getNewLinkStringWeekSchedule(userId, viewDate));
		commonSession.setViewDisp(Param.DISP_WEEK);

		ScheduleWeekLogic logic = new ScheduleWeekLogic(dbConn);
		ScheduleWeekActionForm f = logic.getForm(userId, viewDate, commonSession);
		return f;
	}

	/**
	 * 表示グループ設定
	 * @param dbConn
	 * @param commonSession
	 * @return
	 * @throws Exception
	 */
	private ViewGroupUserActionForm doViewGroup(Connection dbConn, CommonSession commonSession) throws Exception {
		ViewGroupUserLogic logic = new ViewGroupUserLogic(dbConn);
		String userId = commonSession.getUserId();
		ViewGroupUserActionForm f = logic.getForm(userId);
		return f;
	}

	/**
	 * 部署マスタ編集
	 * @param dbConn
	 * @return
	 * @throws Exception
	 */
	private DeptListActionForm doDeptList(Connection dbConn) throws Exception {
		DeptLogic logic = new DeptLogic(dbConn);
		DeptListActionForm f = logic.getListForm();

		return f;
	}

	/**
	 * ユーザ編集
	 * @param dbConn
	 * @return
	 * @throws Exception
	 */
	private UserListActionForm doUserList(Connection dbConn, MessageResources resource) throws Exception {
		UserLogic logic = new UserLogic(dbConn, resource);
		UserListActionForm f = logic.getListForm();
		return f;
	}

	/**
	 * 種別マスタ編集
	 * @param dbConn
	 * @return
	 * @throws Exception
	 */
	private TypeListActionForm doTypeList(Connection dbConn) throws Exception {
		TypeLogic logic = new TypeLogic(dbConn);
		TypeListActionForm f = logic.getListForm();
		return f;
	}

	/**
	 * お知らせ編集
	 * @param dbConn
	 * @return
	 * @throws Exception
	 */
	private InfoListActionForm doInfoList(Connection dbConn) throws Exception {
		InfoLogic logic = new InfoLogic(dbConn);
		InfoListActionForm f = logic.getListForm();
		return f;
	}

	/**
	 * 休日編集
	 * @param dbConn
	 * @return
	 * @throws Exception
	 */
	private HolidayListActionForm doHolidayList(Connection dbConn, CommonSession commonSession) throws Exception {
		HolidayLogic logic = new HolidayLogic(dbConn);
		HolidayListActionForm f = logic.getListForm(commonSession.getHolidayKindSelectBox());
		return f;
	}

	/**
	 * 表示モードの変更
	 * @param commonSession
	 */
	private void changeViewMode(CommonSession commonSession){
		if( commonSession.getMasterViewDisp().equals(Param.DISP_WEEK)){
			commonSession.setViewDisp(Param.DISP_MONTH);
		} else {
			commonSession.setViewDisp(Param.DISP_WEEK);
		}
	}
}
