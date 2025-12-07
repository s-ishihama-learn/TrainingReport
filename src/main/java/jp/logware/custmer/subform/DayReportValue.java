package jp.logware.custmer.subform;

/**
 * 日報一覧表示 アクションフォーム用サブクラス<br>
 * UserChartValueクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class DayReportValue {
	/**
	 * ユーザid
	 */
	private String userId;

	/**
	 * 利用者名
	 */
	private String userName;

	/**
	 * 作業日
	 */
	private String viewDate;

	/**
	 * 連番
	 */
	private String seq;

	/**
	 * 予定開始時刻
	 */
	private String startTime;

	/**
	 * 予定終了時刻
	 */
	private String endTime;

	/**
	 * タイトル
	 */
	private String title;

	/**
	 * 顧客名
	 */
	private String custName;

	/**
	 * 種別
	 */
	private String kind;

	/**
	 * コンストラクタ
	 */
	public DayReportValue() {
		startTime = "";
		endTime = "";
		title = "";
		userId = "";
		viewDate = "";
		seq = "";
		custName = "";
		kind = "";
	}

	/**
	 * 予定開始時刻のゲッター
	 *
	 * @return 予定開始時刻
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * 予定開始時刻のセッター
	 *
	 * @param startTime 予定開始時刻
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * 予定終了時刻を取得します。
	 * @return 予定終了時刻
	 */
	public String getEndTime() {
	    return endTime;
	}

	/**
	 * 予定終了時刻を設定します。
	 * @param endTime 予定終了時刻
	 */
	public void setEndTime(String endTime) {
	    this.endTime = endTime;
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
	 * ユーザidのゲッター
	 *
	 * @return ユーザid
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザidのセッター
	 *
	 * @param userId ユーザid
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 利用者名を取得します。
	 * @return 利用者名
	 */
	public String getUserName() {
	    return userName;
	}

	/**
	 * 利用者名を設定します。
	 * @param userName 利用者名
	 */
	public void setUserName(String userName) {
	    this.userName = userName;
	}

	/**
	 * 作業日のゲッター
	 *
	 * @return 作業日
	 */
	public String getViewDate() {
		return viewDate;
	}

	/**
	 * 作業日のセッター
	 *
	 * @param viewDate 作業日
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
	 * 顧客名を取得します。
	 * @return 顧客名
	 */
	public String getCustName() {
	    return custName;
	}

	/**
	 * 顧客名を設定します。
	 * @param custName 顧客名
	 */
	public void setCustName(String custName) {
	    this.custName = custName;
	}

	/**
	 * 種別を取得します。
	 * @return 種別
	 */
	public String getKind() {
	    return kind;
	}

	/**
	 * 種別を設定します。
	 * @param kind 種別
	 */
	public void setKind(String kind) {
	    this.kind = kind;
	}

}
