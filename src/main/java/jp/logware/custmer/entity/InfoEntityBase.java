package jp.logware.custmer.entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * データ格納クラス<br>
 * お知らせ テーブル
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class InfoEntityBase {
	/**
	 * 表示登録日
	 */
	private Date date;

	/**
	 * 連番
	 */
	private int seq;

	/**
	 * 内容
	 */
	private String body;

	/**
	 * 表示期限
	 */
	private Date limit_date;

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
	public InfoEntityBase() {
		date = null;
		seq = 0;
		body = "";
		limit_date = null;
		idate = null;
		udate = null;
	}

	/**
	 * 表示登録日のゲッター
	 *
	 * @return 表示登録日
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * 表示登録日のセッター
	 *
	 * @param date 表示登録日
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * 連番のゲッター
	 *
	 * @return 連番
	 */
	public int getSeq() {
		return seq;
	}

	/**
	 * 連番のセッター
	 *
	 * @param seq 連番
	 */
	public void setSeq(int seq) {
		this.seq = seq;
	}

	/**
	 * 内容のゲッター
	 *
	 * @return 内容
	 */
	public String getBody() {
		return body;
	}

	/**
	 * 内容のセッター
	 *
	 * @param body 内容
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * 表示期限のゲッター
	 *
	 * @return 表示期限
	 */
	public Date getLimitDate() {
		return limit_date;
	}

	/**
	 * 表示期限のセッター
	 *
	 * @param limit_date 表示期限
	 */
	public void setLimitDate(Date limit_date) {
		this.limit_date = limit_date;
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
