package jp.logware.custmer.action.report;

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
import jp.logware.custmer.common.Param;
import jp.logware.custmer.common.Utility;
import jp.logware.custmer.form.report.EditReportActionForm;
import jp.logware.custmer.form.report.ListApprovalActionForm;
import jp.logware.custmer.form.report.ListReportActionForm;
import jp.logware.custmer.form.schedule.ScheduleMonthActionForm;
import jp.logware.custmer.form.schedule.ScheduleWeekActionForm;
import jp.logware.custmer.logic.report.EditApprovalLogic;
import jp.logware.custmer.logic.report.EditReportLogic;
import jp.logware.custmer.logic.schedule.ScheduleMonthLogic;
import jp.logware.custmer.logic.schedule.ScheduleWeekLogic;

/**
 * 日報編集 アクションクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class EditReportAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConnectionPool connectionPool = ConnectionPool.getInstance(request);
		Connection dbConn = connectionPool.getConnection();
		dbConn.setAutoCommit(false);
		try {
			HttpSession session = request.getSession(true);
			EditReportActionForm frm = (EditReportActionForm) form;
			System.out.println("EditReportAction:" + frm.getButton());

			ActionMessages messages = new ActionMessages();
			ActionErrors errors = new ActionErrors();
			CommonSession commonSession = (CommonSession) session.getAttribute("CommonSession");

			String forward = "error";
			session.setAttribute("EditReportForm", frm);

			if (commonSession == null) {
				throw new AuthException();

			} else if (frm.getButton() == null || frm.getButton().length() == 0) {
				throw new AuthException();

			} else if (frm.getButton().equals("regist")) {
				ListReportActionForm f = doRegist(dbConn, commonSession, frm);
//				request.setAttribute("ListReportForm", f);
//				forward = "regist";
				if (commonSession.getViewDisp().equals(Param.DISP_MONTH)) {
					ScheduleMonthActionForm f2 = doViewMonth(dbConn, commonSession);
					request.setAttribute("ScheduleMonthForm", f2);
					forward = "cancelMonth";

				} else if (commonSession.getViewDisp().equals(Param.DISP_WEEK)) {
					ScheduleWeekActionForm f2 = doViewWeek(dbConn, commonSession);
					request.setAttribute("ScheduleWeekForm", f2);
					forward = "cancelWeek";

				} else {
					throw new AuthException();
				}

			} else if (frm.getButton().equals("edit")) {
				ListReportActionForm f = doEdit(dbConn, commonSession, frm);
//				request.setAttribute("ListReportForm", f);
//				forward = "edit";
				if (commonSession.getViewDisp().equals(Param.DISP_MONTH)) {
					ScheduleMonthActionForm f2 = doViewMonth(dbConn, commonSession);
					request.setAttribute("ScheduleMonthForm", f2);
					forward = "cancelMonth";

				} else if (commonSession.getViewDisp().equals(Param.DISP_WEEK)) {
					ScheduleWeekActionForm f2 = doViewWeek(dbConn, commonSession);
					request.setAttribute("ScheduleWeekForm", f2);
					forward = "cancelWeek";

				} else {
					throw new AuthException();
				}

			} else if (frm.getButton().equals("delete")) {
				ListReportActionForm f = doDelete(dbConn, commonSession, frm);
//				request.setAttribute("ListReportForm", f);
//				forward = "delete";
				if (commonSession.getViewDisp().equals(Param.DISP_MONTH)) {
					ScheduleMonthActionForm f2 = doViewMonth(dbConn, commonSession);
					request.setAttribute("ScheduleMonthForm", f2);
					forward = "cancelMonth";

				} else if (commonSession.getViewDisp().equals(Param.DISP_WEEK)) {
					ScheduleWeekActionForm f2 = doViewWeek(dbConn, commonSession);
					request.setAttribute("ScheduleWeekForm", f2);
					forward = "cancelWeek";

				} else {
					throw new AuthException();
				}

			} else if (frm.getButton().equals("apploval")) {
				ListApprovalActionForm f = doApproval(dbConn, commonSession, frm);
				request.setAttribute("ListApprovalForm", f);
				forward = "apploval";

			} else if (frm.getButton().equals("comment")) {
				ListReportActionForm f = doComment(dbConn, commonSession, frm);
				request.setAttribute("ListReportForm", f);
				forward = "comment";

			} else if (frm.getButton().equals("release")) {
				ListReportActionForm f = doRelease(dbConn, commonSession, frm);
				request.setAttribute("ListReportForm", f);
				forward = "release";

			} else if (frm.getButton().equals("cancelNew")) {
				if (commonSession.getViewDisp().equals(Param.DISP_MONTH)) {
					ScheduleMonthActionForm f = doViewMonth(dbConn, commonSession);
					request.setAttribute("ScheduleMonthForm", f);
					forward = "cancelMonth";

				} else if (commonSession.getViewDisp().equals(Param.DISP_WEEK)) {
					ScheduleWeekActionForm f = doViewWeek(dbConn, commonSession);
					request.setAttribute("ScheduleWeekForm", f);
					forward = "cancelWeek";

				} else {
					throw new AuthException();
				}

			} else if (frm.getButton().equals("cancel")) {
				if (commonSession.getViewDisp().equals(Param.DISP_MONTH)) {
					ScheduleMonthActionForm f = doViewMonth(dbConn, commonSession);
					request.setAttribute("ScheduleMonthForm", f);
					forward = "cancelMonth";

				} else if (commonSession.getViewDisp().equals(Param.DISP_WEEK)) {
					ScheduleWeekActionForm f = doViewWeek(dbConn, commonSession);
					request.setAttribute("ScheduleWeekForm", f);
					forward = "cancelWeek";

				} else {
					throw new AuthException();
				}

			} else if (frm.getButton().equals("cancel2")) {
				ListApprovalActionForm f = doCancel2(dbConn, commonSession);
				request.setAttribute("ListApprovalForm", f);
				forward = "cancel2";

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
	 * @param commonSession
	 * @return
	 * @throws Exception
	 */
	private ListReportActionForm doRegist(Connection dbConn, CommonSession commonSession, EditReportActionForm frm) throws Exception {
		EditReportLogic logic = new EditReportLogic(dbConn);
		frm.setApplyFlag(Param.MODE_OFF);
		logic.registData(frm);

		ListReportActionForm f = logic.getListForm(commonSession.getViewUserId(), frm.getViewDate());
		return f;
	}

	/**
	 * 編集
	 * @param dbConn
	 * @param frm
	 * @param commonSession
	 * @return
	 * @throws Exception
	 */
	private ListReportActionForm doEdit(Connection dbConn, CommonSession commonSession, EditReportActionForm frm) throws Exception {
		EditReportLogic logic = new EditReportLogic(dbConn);
		frm.setApplyFlag(Param.MODE_OFF);
		logic.updateData(frm);

		ListReportActionForm f = logic.getListForm(commonSession.getViewUserId(), frm.getViewDate());
		return f;
	}

	/**
	 * 削除
	 * @param dbConn
	 * @param frm
	 * @param commonSession
	 * @return
	 * @throws Exception
	 */
	private ListReportActionForm doDelete(Connection dbConn, CommonSession commonSession, EditReportActionForm frm) throws Exception {
		EditReportLogic logic = new EditReportLogic(dbConn);
		logic.removeData(frm);

		ListReportActionForm f = logic.getListForm(commonSession.getViewUserId(), frm.getViewDate());
		return f;
	}

	/**
	 * 承認
	 * @param dbConn
	 * @param frm
	 * @param commonSession
	 * @return
	 * @throws Exception
	 */
	private ListApprovalActionForm doApproval(Connection dbConn, CommonSession commonSession, EditReportActionForm frm) throws Exception {
		EditReportLogic logic = new EditReportLogic(dbConn);
		frm.setApplovalUserId(commonSession.getUserId());
		frm.setApplyFlag(Param.MODE_ON);
		logic.updateData(frm);

		EditApprovalLogic flogic = new EditApprovalLogic(dbConn);
		ListApprovalActionForm f = flogic.getListForm();
		return f;
	}

	/**
	 * コメント
	 * @param dbConn
	 * @param frm
	 * @return
	 * @throws Exception
	 */
	private ListReportActionForm doComment(Connection dbConn, CommonSession commonSession, EditReportActionForm frm) throws Exception {
		EditReportLogic logic = new EditReportLogic(dbConn);
		frm.setCommentUser(commonSession.getUserId());
		logic.setComment(frm);

		ListReportActionForm f = logic.getListForm(frm.getUserId(), frm.getViewDate());
		if (commonSession.getViewDisp().equals(Param.DISP_WEEK)) {
			f = logic.getListForm(frm.getUserId(), frm.getViewDate());
		}
		return f;
	}

	/**
	 * 承認解除
	 * @param dbConn
	 * @param frm
	 * @return
	 * @throws Exception
	 */
	private ListReportActionForm doRelease(Connection dbConn, CommonSession commonSession, EditReportActionForm frm) throws Exception {
		EditReportLogic logic = new EditReportLogic(dbConn);
		logic.setRelease(frm);

		ListReportActionForm f = logic.getListForm(frm.getUserId(), frm.getViewDate());
		if (commonSession.getViewDisp().equals(Param.DISP_WEEK)) {
			f = logic.getListForm(frm.getUserId(), frm.getViewDate());
		}
		return f;
	}

	/**
	 * 新規キャンセル（月表示）
	 * @param dbConn
	 * @param commonSession
	 * @return
	 * @throws Exception
	 */
	private ScheduleMonthActionForm doViewMonth(Connection dbConn, CommonSession commonSession) throws Exception {
		String userId = commonSession.getViewUserId();
		String viewDate = Utility.getDate(commonSession.getViewDate());

		ScheduleMonthLogic logic = new ScheduleMonthLogic(dbConn);
		ScheduleMonthActionForm f = logic.getForm(userId, viewDate, commonSession);

		return f;
	}

	/**
	 * 新規キャンセル（週表示）
	 * @param dbConn
	 * @param commonSession
	 * @return
	 * @throws Exception
	 */
	private ScheduleWeekActionForm doViewWeek(Connection dbConn, CommonSession commonSession) throws Exception {
		String userId = commonSession.getUserId();
		String viewDate = Utility.getDate(commonSession.getViewDate());

		ScheduleWeekLogic logic = new ScheduleWeekLogic(dbConn);
		ScheduleWeekActionForm f = logic.getForm(userId, viewDate, commonSession);

		return f;
	}

	/**
	 * 未承認一覧
	 * @param dbConn
	 * @param commonSession
	 * @return
	 */
	private ListApprovalActionForm doCancel2(Connection dbConn, CommonSession commonSession) throws Exception {
		EditApprovalLogic logic = new EditApprovalLogic(dbConn);
		ListApprovalActionForm f = logic.getListForm();
		return f;
	}

	/**
	 * 月表示
	 * @param dbConn
	 * @param commonSession
	 * @return
	 * @throws Exception
	 */
	private ScheduleMonthActionForm doCancelMonth(Connection dbConn, CommonSession commonSession) throws Exception {
		String userId = commonSession.getViewUserId();
		String viewDate = Utility.getDate(commonSession.getViewDate());

		ScheduleMonthLogic logic = new ScheduleMonthLogic(dbConn);
		ScheduleMonthActionForm f = logic.getForm(userId, viewDate, commonSession);

		return f;
	}

	/**
	 * 週表示
	 * @param dbConn
	 * @param commonSession
	 * @return
	 * @throws Exception
	 */
	private ScheduleWeekActionForm doCancelWeek(Connection dbConn, CommonSession commonSession) throws Exception {
		String userId = commonSession.getUserId();
		String viewDate = Utility.getDate(commonSession.getViewDate());

		ScheduleWeekLogic logic = new ScheduleWeekLogic(dbConn);
		ScheduleWeekActionForm f = logic.getForm(userId, viewDate, commonSession);

		return f;
	}
}
