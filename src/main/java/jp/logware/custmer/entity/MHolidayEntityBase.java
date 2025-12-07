package jp.logware.custmer.entity;

import java.sql.Timestamp;

/**
 * データ格納クラス<br>
 * 休日 テーブル
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class MHolidayEntityBase {
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
	 * 登録日
	 */
	private Timestamp idate;

	/**
	 * 更新日
	 */
	private Timestamp udate;

	/**
	 * コンストラクタ
	 */
	public MHolidayEntityBase() {
		date = "";
		note = "";
		kind = "";
		idate = null;
		udate = null;
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
	 * 登録日のゲッター
	 *
	 * @return 登録日
	 */
	public Timestamp getIdate() {
		return idate;
	}

	/**
	 * 登録日のセッター
	 *
	 * @param idate 登録日
	 */
	public void setIdate(Timestamp idate) {
		this.idate = idate;
	}

	/**
	 * 更新日のゲッター
	 *
	 * @return 更新日
	 */
	public Timestamp getUdate() {
		return udate;
	}

	/**
	 * 更新日のセッター
	 *
	 * @param udate 更新日
	 */
	public void setUdate(Timestamp udate) {
		this.udate = udate;
	}

}
