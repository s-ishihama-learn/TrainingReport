package jp.logware.custmer.entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * データ格納クラス<br>
 * 日報コメント テーブル
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class CommentEntityBase {
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
	 * コメント連番
	 */
	private int seq_comment;

	/**
	 * 登録ユーザid
	 */
	private String reg_user_id;

	/**
	 * コメント
	 */
	private String comment;

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
	public CommentEntityBase() {
		user_id = "";
		date = null;
		seq = 0;
		seq_comment = 0;
		reg_user_id = "";
		comment = "";
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
	 * コメント連番のゲッター
	 *
	 * @return コメント連番
	 */
	public int getSeqComment() {
		return seq_comment;
	}

	/**
	 * コメント連番のセッター
	 *
	 * @param seq_comment コメント連番
	 */
	public void setSeqComment(int seq_comment) {
		this.seq_comment = seq_comment;
	}

	/**
	 * 登録ユーザidのゲッター
	 *
	 * @return 登録ユーザid
	 */
	public String getRegUserId() {
		return reg_user_id;
	}

	/**
	 * 登録ユーザidのセッター
	 *
	 * @param reg_user_id 登録ユーザid
	 */
	public void setRegUserId(String reg_user_id) {
		this.reg_user_id = reg_user_id;
	}

	/**
	 * コメントのゲッター
	 *
	 * @return コメント
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * コメントのセッター
	 *
	 * @param comment コメント
	 */
	public void setComment(String comment) {
		this.comment = comment;
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
