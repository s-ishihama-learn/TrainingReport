package jp.logware.custmer.form.report;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.validator.ValidatorForm;

import jp.logware.custmer.common.Param;
import jp.logware.custmer.subform.CommentValue;

/**
 * 顧客カルテ編集 アクションフォームクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class EditReportActionForm extends ValidatorForm {
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
	 * 訪問準備
	 */
	private String beforeTime;

	/**
	 * 移動時間
	 */
	private String moveTime;

	/**
	 * 訪問後処理
	 */
	private String afterTime;

	/**
	 * 日報分類
	 */
	private String reportType;

	/**
	 * タイトル
	 */
	private String title;

	/**
	 * 顧客名
	 */
	private String custName;

	/**
	 * 顧客部署名
	 */
	private String custDept;

	/**
	 * 顧客担当者名
	 */
	private String custUserName;

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
	 * 承認者
	 */
	private String applovalUser;

	/**
	 * 承認メッセージ
	 */
	private String applovalMessage;

	/**
	 * コメント投稿者
	 */
	private List<CommentValue> commentList;

	/**
	 * 新規コメント投稿者
	 */
	private String commentUser;

	/**
	 * 新規コメント投稿者表示名
	 */
	private String commentUserName;

	/**
	 * 新規コメント
	 */
	private String commentMessage;

	/**
	 * ユーザid
	 */
	private String userId;

	/**
	 * 連番
	 */
	private String seq;

	/**
	 * 承認フラグ
	 */
	private String applyFlag;

	/**
	 * 承認者ユーザid
	 */
	private String applovalUserId;

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
	public EditReportActionForm() {
		userName = "";
		viewDate = "";
		startTime = "";
		endTime = "";
		startTimeView = "";
		endTimeView = "";
		beforeTime = "";
		moveTime = "";
		afterTime = "";
		reportType = "";
		title = "";
		custName = "";
		custDept = "";
		custUserName = "";
		fact = "";
		guess = "";
		next = "";
		applovalUser = "";
		applovalMessage = "";
		commentList = new ArrayList<CommentValue>();
		userId = "";
		seq = "";
		applyFlag = Param.MODE_OFF;
		applovalUserId = "";
		viewMode = "";
	}

	/**
	 * ユーザ名を取得します。
	 *
	 * @return ユーザ名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * ユーザ名を設定します。
	 *
	 * @param userName ユーザ名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 年月日を取得します。
	 *
	 * @return 年月日
	 */
	public String getViewDate() {
		return viewDate;
	}

	/**
	 * 年月日を設定します。
	 *
	 * @param viewDate 年月日
	 */
	public void setViewDate(String viewDate) {
		this.viewDate = viewDate;
	}

	/**
	 * 開始時刻を取得します。
	 *
	 * @return 開始時刻
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * 開始時刻を設定します。
	 *
	 * @param startTime 開始時刻
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * 終了時刻を取得します。
	 *
	 * @return 終了時刻
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * 終了時刻を設定します。
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
	 * 訪問準備を取得します。
	 *
	 * @return 訪問準備
	 */
	public String getBeforeTime() {
		return beforeTime;
	}

	/**
	 * 訪問準備を設定します。
	 *
	 * @param beforeTime 訪問準備
	 */
	public void setBeforeTime(String beforeTime) {
		this.beforeTime = beforeTime;
	}

	/**
	 * 移動時間を取得します。
	 *
	 * @return 移動時間
	 */
	public String getMoveTime() {
		return moveTime;
	}

	/**
	 * 移動時間を設定します。
	 *
	 * @param moveTime 移動時間
	 */
	public void setMoveTime(String moveTime) {
		this.moveTime = moveTime;
	}

	/**
	 * 訪問後処理を取得します。
	 *
	 * @return 訪問後処理
	 */
	public String getAfterTime() {
		return afterTime;
	}

	/**
	 * 訪問後処理を設定します。
	 *
	 * @param afterTime 訪問後処理
	 */
	public void setAfterTime(String afterTime) {
		this.afterTime = afterTime;
	}

	/**
	 * 日報分類を取得します。
	 *
	 * @return 日報分類
	 */
	public String getReportType() {
		return reportType;
	}

	/**
	 * 日報分類を設定します。
	 *
	 * @param reportType 日報分類
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	/**
	 * タイトルを取得します。
	 *
	 * @return タイトル
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * タイトルを設定します。
	 *
	 * @param title タイトル
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 顧客名を取得します。
	 *
	 * @return 顧客名
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * 顧客名を設定します。
	 *
	 * @param custName 顧客名
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * 顧客部署名を取得します。
	 *
	 * @return 顧客部署名
	 */
	public String getCustDept() {
		return custDept;
	}

	/**
	 * 顧客部署名を設定します。
	 *
	 * @param custDept 顧客部署名
	 */
	public void setCustDept(String custDept) {
		this.custDept = custDept;
	}

	/**
	 * 顧客担当者名を取得します。
	 *
	 * @return 顧客担当者名
	 */
	public String getCustUserName() {
		return custUserName;
	}

	/**
	 * 顧客担当者名を設定します。
	 *
	 * @param custUserName 顧客担当者名
	 */
	public void setCustUserName(String custUserName) {
		this.custUserName = custUserName;
	}

	/**
	 * 事実を取得します。
	 *
	 * @return 事実
	 */
	public String getFact() {
		return fact;
	}

	/**
	 * 事実を設定します。
	 *
	 * @param fact 事実
	 */
	public void setFact(String fact) {
		this.fact = fact;
	}

	/**
	 * 推察を取得します。
	 *
	 * @return 推察
	 */
	public String getGuess() {
		return guess;
	}

	/**
	 * 推察を設定します。
	 *
	 * @param guess 推察
	 */
	public void setGuess(String guess) {
		this.guess = guess;
	}

	/**
	 * 次回を取得します。
	 *
	 * @return 次回
	 */
	public String getNext() {
		return next;
	}

	/**
	 * 次回を設定します。
	 *
	 * @param next 次回
	 */
	public void setNext(String next) {
		this.next = next;
	}

	/**
	 * 承認者を取得します。
	 *
	 * @return 承認者
	 */
	public String getApplovalUser() {
		return applovalUser;
	}

	/**
	 * 承認者を設定します。
	 *
	 * @param applovalUser 承認者
	 */
	public void setApplovalUser(String applovalUser) {
		this.applovalUser = applovalUser;
	}

	/**
	 * 承認メッセージを取得します。
	 *
	 * @return 承認メッセージ
	 */
	public String getApplovalMessage() {
		return applovalMessage;
	}

	/**
	 * 承認メッセージを設定します。
	 *
	 * @param applovalMessage 承認メッセージ
	 */
	public void setApplovalMessage(String applovalMessage) {
		this.applovalMessage = applovalMessage;
	}

	/**
	 * コメント投稿者を取得します。
	 *
	 * @return コメント投稿者
	 */
	public List<CommentValue> getCommentList() {
		return commentList;
	}

	/**
	 * コメント投稿者を設定します。
	 *
	 * @param list コメント投稿者
	 */
	public void setCommentList(List<CommentValue> list) {
		this.commentList = list;
	}

	/**
	 * 新規コメント投稿者を取得します。
	 *
	 * @return 新規コメント投稿者
	 */
	public String getCommentUser() {
		return commentUser;
	}

	/**
	 * 新規コメント投稿者を設定します。
	 *
	 * @param commentUser 新規コメント投稿者
	 */
	public void setCommentUser(String commentUser) {
		this.commentUser = commentUser;
	}

	/**
	 * 新規コメント投稿者表示名を取得します。
	 *
	 * @return 新規コメント投稿者表示名
	 */
	public String getCommentUserName() {
		return commentUserName;
	}

	/**
	 * 新規コメント投稿者表示名を設定します。
	 *
	 * @param commentUserName 新規コメント投稿者表示名
	 */
	public void setCommentUserName(String commentUserName) {
		this.commentUserName = commentUserName;
	}

	/**
	 * 新規コメントを取得します。
	 *
	 * @return 新規コメント
	 */
	public String getCommentMessage() {
		return commentMessage;
	}

	/**
	 * 新規コメントを設定します。
	 *
	 * @param commentMessage 新規コメント
	 */
	public void setCommentMessage(String commentMessage) {
		this.commentMessage = commentMessage;
	}

	/**
	 * ユーザidを取得します。
	 *
	 * @return ユーザid
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザidを設定します。
	 *
	 * @param userId ユーザid
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 連番を取得します。
	 *
	 * @return 連番
	 */
	public String getSeq() {
		return seq;
	}

	/**
	 * 連番を設定します。
	 *
	 * @param seq 連番
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}

	/**
	 * 承認フラグを取得します。
	 *
	 * @return 承認フラグ
	 */
	public String getApplyFlag() {
		return applyFlag;
	}

	/**
	 * 承認フラグを設定します。
	 *
	 * @param applyFlag 承認フラグ
	 */
	public void setApplyFlag(String applyFlag) {
		this.applyFlag = applyFlag;
	}

	/**
	 * 承認者ユーザidを取得します。
	 *
	 * @return 承認者ユーザid
	 */
	public String getApplovalUserId() {
		return applovalUserId;
	}

	/**
	 * 承認者ユーザidを設定します。
	 *
	 * @param applovalUserId 承認者ユーザid
	 */
	public void setApplovalUserId(String applovalUserId) {
		this.applovalUserId = applovalUserId;
	}

	/**
	 * ボタンを取得します。
	 *
	 * @return ボタン
	 */
	public String getButton() {
		return button;
	}

	/**
	 * ボタンを設定します。
	 *
	 * @param button ボタン
	 */
	public void setButton(String button) {
		this.button = button;
	}

	/**
	 * 表示条件値を取得します。
	 *
	 * @return 表示条件値
	 */
	public String getViewMode() {
		return viewMode;
	}

	/**
	 * 表示条件値を設定します。
	 *
	 * @param viewMode 表示条件値
	 */
	public void setViewMode(String viewMode) {
		this.viewMode = viewMode;
	}
}
