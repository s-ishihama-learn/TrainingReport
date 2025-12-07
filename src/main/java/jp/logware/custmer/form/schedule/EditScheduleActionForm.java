package jp.logware.custmer.form.schedule;

import org.apache.struts.validator.ValidatorForm;

/**
 * スケジュール編集 アクションフォームクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class EditScheduleActionForm extends ValidatorForm {
	/**
	 * ユーザ名
	 */
	private String userName;

	/**
	 * 年月日
	 */
	private String viewDate;

	/**
	 * 開始時刻
	 */
	private String startTime;

	/**
	 * 終了時刻
	 */
	private String endTime;

	/**
	 * 開始時刻（表示用）
	 */
	private String startTimeView;

	/**
	 * 終了時刻（表示用）
	 */
	private String endTimeView;

	/**
	 * タイトル
	 */
	private String title;

	/**
	 * 内容
	 */
	private String body;

	/**
	 * ユーザid
	 */
	private String userId;

	/**
	 * 連番
	 */
	private String seq;

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
	public EditScheduleActionForm() {
		userName = "";
		viewDate = "";
		startTime = "";
		endTime = "";
		startTimeView = "";
		endTimeView = "";
		title = "";
		body = "";
		userId = "";
		seq = "";
	}

	/**
	 * ユーザ名のゲッター
	 *
	 * @return ユーザ名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * ユーザ名のセッター
	 *
	 * @param userName ユーザ名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 年月日のゲッター
	 *
	 * @return 年月日
	 */
	public String getViewDate() {
		return viewDate;
	}

	/**
	 * 年月日のセッター
	 *
	 * @param viewDate 年月日
	 */
	public void setViewDate(String viewDate) {
		this.viewDate = viewDate;
	}

	/**
	 * 開始時刻のゲッター
	 *
	 * @return 開始時刻
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * 開始時刻のセッター
	 *
	 * @param startTime 開始時刻
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * 終了時刻のゲッター
	 *
	 * @return 終了時刻
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * 終了時刻のセッター
	 *
	 * @param endTime 終了時刻
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * 開始時刻（表示用）を取得します。
	 *
	 * @return 開始時刻（表示用）
	 */
	public String getStartTimeView() {
		return startTimeView;
	}

	/**
	 * 開始時刻（表示用）を設定します。
	 *
	 * @param startTimeView 開始時刻（表示用）
	 */
	public void setStartTimeView(String startTimeView) {
		this.startTimeView = startTimeView;
	}

	/**
	 * 終了時刻（表示用）を取得します。
	 *
	 * @return 終了時刻（表示用）
	 */
	public String getEndTimeView() {
		return endTimeView;
	}

	/**
	 * 終了時刻（表示用）を設定します。
	 *
	 * @param endTimeView 終了時刻（表示用）
	 */
	public void setEndTimeView(String endTimeView) {
		this.endTimeView = endTimeView;
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
