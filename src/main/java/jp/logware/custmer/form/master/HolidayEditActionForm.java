package jp.logware.custmer.form.master;

import org.apache.struts.validator.ValidatorForm;

/**
 * 休日編集 アクションフォームクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class HolidayEditActionForm extends ValidatorForm {
	/**
	 * 日付
	 */
	private String date;

	/**
	 * 備考
	 */
	private String note;

	/**
	 * 休日種別
	 */
	private String kind;

	/**
	 * ボタン
	 */
	private String button;

	/**
	 * 表示条件値
	 */
	private String viewMode;

	/**
	 * コンストラクタ
	 */
	public HolidayEditActionForm() {
		date = "";
		note = "";
		kind = "";
	}

	/**
	 * 日付のゲッター
	 *
	 * @return 日付
	 */
	public String getDate() {
		return date;
	}

	/**
	 * 日付のセッター
	 *
	 * @param date 日付
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * 備考のゲッター
	 *
	 * @return 備考
	 */
	public String getNote() {
		return note;
	}

	/**
	 * 備考のセッター
	 *
	 * @param note 備考
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * 休日種別のゲッター
	 *
	 * @return 休日種別
	 */
	public String getKind() {
		return kind;
	}

	/**
	 * 休日種別のセッター
	 *
	 * @param kind 休日種別
	 */
	public void setKind(String kind) {
		this.kind = kind;
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

	/**
	 * 表示条件値のゲッター
	 *
	 * @return ボタン
	 */
	public String getViewMode() {
		return viewMode;
	}

	/**
	 * 表示条件値のセッター
	 *
	 * @param button ボタン
	 */
	public void setViewMode(String viewMode) {
		this.viewMode = viewMode;
	}
}
