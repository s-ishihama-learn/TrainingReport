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
public class ListReportActionForm extends ValidatorForm {
	/**
	 * 日報
	 */
	private List<DayReportValue> reportList;

	/**
	 * ユーザID
	 */
	private String userId;

	/**
	 * 表示日付
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
	public ListReportActionForm() {
		reportList = new ArrayList<DayReportValue>();
		userId = "";
		viewDate = "";
		seq = "";
	}

	/**
	 * 日報のゲッター
	 *
	 * @return 日報のリスト
	 */
	public List<DayReportValue> getReportList() {
		return reportList;
	}

	/**
	 * 日報のセッター
	 *
	 * @param list 日報のリスト
	 */
	public void setReportList(List<DayReportValue> list) {
		this.reportList = list;
	}

	/**
	 * ユーザIDを取得します。
	 * @return ユーザID
	 */
	public String getUserId() {
	    return userId;
	}

	/**
	 * ユーザIDを設定します。
	 * @param userId ユーザID
	 */
	public void setUserId(String userId) {
	    this.userId = userId;
	}

	/**
	 * 表示日付を取得します。
	 * @return 表示日付
	 */
	public String getViewDate() {
	    return viewDate;
	}

	/**
	 * 表示日付を設定します。
	 * @param viewDate 表示日付
	 */
	public void setViewDate(String viewDate) {
	    this.viewDate = viewDate;
	}

	/**
	 * 連番のゲッター
	 *
	 * @return 連番
	 */
	public String getSeq() {
		return seq;
	}

	/**
	 * 連番のセッター
	 *
	 * @param seq 連番
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}

	/**
	 * ボタンのゲッター
	 *
	 * @return ボタン
	 */
	public String getButton() {
		return button;
	}

	/**
	 * ボタンのセッター
	 *
	 * @param button ボタン
	 */
	public void setButton(String button) {
		this.button = button;
	}
}
