package jp.logware.custmer.logic.report;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import jp.logware.custmer.common.CommonUtility;
import jp.logware.custmer.common.Param;
import jp.logware.custmer.common.Utility;
import jp.logware.custmer.dao.MUserDao;
import jp.logware.custmer.dao.ReportDao;
import jp.logware.custmer.entity.ReportEntity;
import jp.logware.custmer.form.report.ListApprovalActionForm;
import jp.logware.custmer.subform.DayReportValue;

/**
 * 顧客カルテ編集 ロジッククラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */

public class EditApprovalLogic {
	/**
	 * データベースコネクション
	 */
	private Connection dbConn = null;

	/**
	 * コンストラクタ
	 *
	 * @param conn
	 */
	public EditApprovalLogic(Connection conn) {
		dbConn = conn;
	}

	/**
	 * ActionFormクラスへ値を代入
	 *
	 * @param String userId
	 * @param String date
	 * @param String seq
	 * @return EditScheduleActionForm
	 */
	public ListApprovalActionForm getListForm() throws Exception {
		ListApprovalActionForm form = new ListApprovalActionForm();

		MUserDao mUserDao = new MUserDao(dbConn);
		Map<String, String> userNameMap = mUserDao.getMap();

		ReportDao reportDao = new ReportDao(dbConn);
		String where = "(APL_FLAG IS NULL OR APL_FLAG='' OR APL_FLAG='0')";
		List<ReportEntity> reportList = reportDao.getListWhereSort(where, "USER_ID,DATE,START_TIME");
		for (ReportEntity entity : reportList) {
			DayReportValue item = new DayReportValue();
			item.setUserId(entity.getUserId());
			item.setUserName(userNameMap.get(entity.getUserId()));
			item.setViewDate(Utility.getDate(entity.getDate()));
			item.setSeq(String.valueOf(entity.getSeq()));
			item.setStartTime(CommonUtility.getViewTimeZeroString(entity.getStartTime()));
			item.setEndTime(CommonUtility.getViewTimeZeroString(entity.getEndTime()));
			item.setTitle(entity.getTitle());
			item.setCustName(entity.getCustName());
			item.setKind(Param.KIND_REPORT);
			form.getApprovalList().add(item);
		}

		return form;
	}
}
