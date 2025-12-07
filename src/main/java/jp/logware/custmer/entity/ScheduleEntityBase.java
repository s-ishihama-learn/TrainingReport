package jp.logware.custmer.entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * データ格納クラス<br>
 * 予定 テーブル
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class ScheduleEntityBase {
	/**
	 * ユーザーid
	 */
	private String user_id;

	/**
	 * 日付
	 */
	private Date date;

	/**
	 * 連番
	 */
	private int seq;

	/**
	 * 開始時刻
	 */
	private String start_time;

	/**
	 * 終了時刻
	 */
	private String end_time;

	/**
	 * タイトル
	 */
	private String title;

	/**
	 * 内容
	 */
	private String body;

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
	public ScheduleEntityBase() {
		user_id = "";
		date = null;
		seq = 0;
		start_time = "";
		end_time = "";
		title = "";
		body = "";
		idate = null;
		udate = null;
	}

	/**
	 * ユーザーidのゲッター
	 *
	 * @return ユーザーid
	 */
	public String getUserId() {
		return user_id;
	}

	/**
	 * ユーザーidのセッター
	 *
	 * @param user_id ユーザーid
	 */
	public void setUserId(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * 日付のゲッター
	 *
	 * @return 日付
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * 日付のセッター
	 *
	 * @param date 日付
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
	 * 開始時刻のゲッター
	 *
	 * @return 開始時刻
	 */
	public String getStartTime() {
		return start_time;
	}

	/**
	 * 開始時刻のセッター
	 *
	 * @param start_time 開始時刻
	 */
	public void setStartTime(String start_time) {
		this.start_time = start_time;
	}

	/**
	 * 終了時刻のゲッター
	 *
	 * @return 終了時刻
	 */
	public String getEndTime() {
		return end_time;
	}

	/**
	 * 終了時刻のセッター
	 *
	 * @param end_time 終了時刻
	 */
	public void setEndTime(String end_time) {
		this.end_time = end_time;
	}

	/**
	 * タイトルのゲッター
	 *
	 * @return タイトル
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * タイトルのセッター
	 *
	 * @param title タイトル
	 */
	public void setTitle(String title) {
		this.title = title;
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
