package jp.logware.custmer.form.report;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.validator.ValidatorForm;

import jp.logware.custmer.subform.DayReportValue;

/**
 * 日報一覧 アクションフォームクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class ListApprovalActionForm extends ValidatorForm {
	/**
	 * 日報
	 */
	private List<DayReportValue> approvalList;

	/**
	 * ユーザid
	 */
	private String userId;

	/**
	 * 作業日
	 */
	private String viewDate;

	/**
	 * 連番
	 */
	private String seq;

	/**
	 * ボタン
	 */
	private String button;

	/**
	 * コンストラクタ
	 */
	public ListApprovalActionForm() {
		approvalList = new ArrayList<DayReportValue>();
		userId = "";
		viewDate = "";
		seq = "";
	}

	/**
	 * 日報を取得します。
	 * @return 日報
	 */
	public List<DayReportValue> getApprovalList() {
	    return approvalList;
	}

	/**
	 * 日報を設定します。
	 * @param reportList 日報
	 */
	public void setApprovalList(List<DayReportValue> approvalList) {
	    this.approvalList = approvalList;
	}

	/**
	 * ユーザidを取得します。
	 * @return ユーザid
	 */
	public String getUserId() {
	    return userId;
	}

	/**
	 * ユーザidを設定します。
	 * @param userId ユーザid
	 */
	public void setUserId(String userId) {
	    this.userId = userId;
	}

	/**
	 * 作業日を取得します。
	 * @return 作業日
	 */
	public String getViewDate() {
	    return viewDate;
	}

	/**
	 * 作業日を設定します。
	 * @param viewDate 作業日
	 */
	public void setViewDate(String viewDate) {
	    this.viewDate = viewDate;
	}

	/**
	 * 連番を取得します。
	 * @return 連番
	 */
	public String getSeq() {
	    return seq;
	}

	/**
	 * 連番を設定します。
	 * @param seq 連番
	 */
	public void setSeq(String seq) {
	    this.seq = seq;
	}

	/**
	 * ボタンを取得します。
	 * @return ボタン
	 */
	public String getButton() {
	    return button;
	}

	/**
	 * ボタンを設定します。
	 * @param button ボタン
	 */
	public void setButton(String button) {
	    this.button = button;
	}
}
