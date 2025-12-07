package jp.logware.custmer.subform;

/**
 * 休日マスタ アクションフォーム用サブクラス<br>
 * HolidayValueクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class HolidayValue {
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
	 * 休日種別名
	 */
	private String kindName;

	/**
	 * コンストラクタ
	 */
	public HolidayValue() {
		date = "";
		note = "";
		kind = "";
		kindName = "";
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
	 * 休日種別名を取得します。
	 * @return 休日種別名
	 */
	public String getKindName() {
	    return kindName;
	}

	/**
	 * 休日種別名を設定します。
	 * @param kindName 休日種別名
	 */
	public void setKindName(String kindName) {
	    this.kindName = kindName;
	}
}
