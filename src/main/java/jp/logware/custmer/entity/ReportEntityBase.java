package jp.logware.custmer.entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * データ格納クラス<br>
 * 日報 テーブル
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class ReportEntityBase {
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
	 * 準備時間
	 */
	private double pre_time;

	/**
	 * 移動時間
	 */
	private double trv_time;

	/**
	 * 後処理時間
	 */
	private double aft_time;

	/**
	 * 分類コード
	 */
	private String typeCode;

	/**
	 * タイトル
	 */
	private String title;

	/**
	 * 顧客名
	 */
	private String cust_name;

	/**
	 * 顧客部署名
	 */
	private String cust_dept;

	/**
	 * 顧客担当者名
	 */
	private String cust_persons;

	/**
	 * 事実
	 */
	private String fact;

	/**
	 * 推察
	 */
	private String guess;

	/**
	 * 次回
	 */
	private String next;

	/**
	 * 承認フラグ
	 */
	private String apl_flag;

	/**
	 * 承認者ユーザid
	 */
	private String apl_user_id;

	/**
	 * 承認コメント
	 */
	private String apl_comment;

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
	public ReportEntityBase() {
		user_id = "";
		date = null;
		seq = 0;
		start_time = "";
		end_time = "";
		pre_time = 0.0;
		trv_time = 0.0;
		aft_time = 0.0;
		typeCode = "";
		title = "";
		cust_name = "";
		cust_dept = "";
		cust_persons = "";
		fact = "";
		guess = "";
		next = "";
		apl_flag = "";
		apl_user_id = "";
		apl_comment = "";
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
	 * 準備時間のゲッター
	 *
	 * @return 準備時間
	 */
	public double getPreTime() {
		return pre_time;
	}

	/**
	 * 準備時間のセッター
	 *
	 * @param pre_time 準備時間
	 */
	public void setPreTime(double pre_time) {
		this.pre_time = pre_time;
	}

	/**
	 * 移動時間のゲッター
	 *
	 * @return 移動時間
	 */
	public double getTrvTime() {
		return trv_time;
	}

	/**
	 * 移動時間のセッター
	 *
	 * @param trv_time 移動時間
	 */
	public void setTrvTime(double trv_time) {
		this.trv_time = trv_time;
	}

	/**
	 * 後処理時間のゲッター
	 *
	 * @return 後処理時間
	 */
	public double getAftTime() {
		return aft_time;
	}

	/**
	 * 後処理時間のセッター
	 *
	 * @param aft_time 後処理時間
	 */
	public void setAftTime(double aft_time) {
		this.aft_time = aft_time;
	}

	/**
	 * 分類コードのゲッター
	 *
	 * @return 分類コード
	 */
	public String getTypeCode() {
		return typeCode;
	}

	/**
	 * 分類コードのセッター
	 *
	 * @param typeCode 分類コード
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
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
	 * 顧客名のゲッター
	 *
	 * @return 顧客名
	 */
	public String getCustName() {
		return cust_name;
	}

	/**
	 * 顧客名のセッター
	 *
	 * @param cust_name 顧客名
	 */
	public void setCustName(String cust_name) {
		this.cust_name = cust_name;
	}

	/**
	 * 顧客部署名のゲッター
	 *
	 * @return 顧客部署名
	 */
	public String getCustDept() {
		return cust_dept;
	}

	/**
	 * 顧客部署名のセッター
	 *
	 * @param cust_dept 顧客部署名
	 */
	public void setCustDept(String cust_dept) {
		this.cust_dept = cust_dept;
	}

	/**
	 * 顧客担当者名のゲッター
	 *
	 * @return 顧客担当者名
	 */
	public String getCustPersons() {
		return cust_persons;
	}

	/**
	 * 顧客担当者名のセッター
	 *
	 * @param cust_persons 顧客担当者名
	 */
	public void setCustPersons(String cust_persons) {
		this.cust_persons = cust_persons;
	}

	/**
	 * 事実のゲッター
	 *
	 * @return 事実
	 */
	public String getFact() {
		return fact;
	}

	/**
	 * 事実のセッター
	 *
	 * @param fact 事実
	 */
	public void setFact(String fact) {
		this.fact = fact;
	}

	/**
	 * 推察のゲッター
	 *
	 * @return 推察
	 */
	public String getGuess() {
		return guess;
	}

	/**
	 * 推察のセッター
	 *
	 * @param guess 推察
	 */
	public void setGuess(String guess) {
		this.guess = guess;
	}

	/**
	 * 次回のゲッター
	 *
	 * @return 次回
	 */
	public String getNext() {
		return next;
	}

	/**
	 * 次回のセッター
	 *
	 * @param next 次回
	 */
	public void setNext(String next) {
		this.next = next;
	}

	/**
	 * 承認フラグのゲッター
	 *
	 * @return 承認フラグ
	 */
	public String getAplFlag() {
		return apl_flag;
	}

	/**
	 * 承認フラグのセッター
	 *
	 * @param apl_flag 承認フラグ
	 */
	public void setAplFlag(String apl_flag) {
		this.apl_flag = apl_flag;
	}

	/**
	 * 承認者ユーザidのゲッター
	 *
	 * @return 承認者ユーザid
	 */
	public String getAplUserId() {
		return apl_user_id;
	}

	/**
	 * 承認者ユーザidのセッター
	 *
	 * @param apl_user_id 承認者ユーザid
	 */
	public void setAplUserId(String apl_user_id) {
		this.apl_user_id = apl_user_id;
	}

	/**
	 * 承認コメントのゲッター
	 *
	 * @return 承認コメント
	 */
	public String getAplComment() {
		return apl_comment;
	}

	/**
	 * 承認コメントのセッター
	 *
	 * @param apl_comment 承認コメント
	 */
	public void setAplComment(String apl_comment) {
		this.apl_comment = apl_comment;
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
