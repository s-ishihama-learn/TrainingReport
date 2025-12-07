package jp.logware.custmer.common;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 共通セッションクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class CommonSession {
	/**
	 * ユーザid
	 */
	private String userId;

	/**
	 * ユーザ名
	 */
	private String userName;

	/**
	 * ユーザ権限
	 */
	private String userCom;

	/**
	 * 表示ユーザid
	 */
	private String viewUserId;

	/**
	 * 表示ユーザ名
	 */
	private String viewUserName;

	/**
	 * 表示日付
	 */
	private Date viewDate;

	/**
	 * 表示月（画面表示用）
	 */
	private String viewMonthDisp;

	/**
	 * 表示日付（画面表示用）
	 */
	private String viewDateDisp;

	/**
	 * 表示画面
	 */
	private String viewDisp;

	/**
	 * 表示画面（マスタ管理用）
	 */
	private String masterViewDisp;

	/**
	 * 表示者がログインユーザかを示すフラグ true:本人
	 */
	private boolean itsMe;

	/**
	 * 編集可フラグ true:編集可
	 */
	private boolean editMode;

	/**
	 * 新規ボタン用スクリプト文字列
	 */
	private String[] newLinkString;

	/**
	 * 所属名のセレクトボックス
	 */
	private LinkedHashMap<String, Object> depSelectBox;

	/**
	 * 権限のセレクトボックス
	 */
	private LinkedHashMap<String, Object> userComSelectBox;

	/**
	 * 開始時刻のセレクトボックス
	 */
	private LinkedHashMap<String, Object> startTimeSelectBox;

	/**
	 * 終了時刻のセレクトボックス
	 */
	private LinkedHashMap<String, Object> endTimeSelectBox;

	/**
	 * 分類のセレクトボックス
	 */
	private LinkedHashMap<String, Object> typeSelectBox;

	/**
	 * 休日種別のセレクトボックス
	 */
	private LinkedHashMap<String, Object> holidayKindSelectBox;

	/**
	 * 休日の背景色マップ
	 */
	private Map<String,String> holidayColorMap;

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
	 * ユーザ権限を取得します。
	 * @return ユーザ権限
	 */
	public String getUserCom() {
	    return userCom;
	}

	/**
	 * ユーザ権限を設定します。
	 * @param userCom ユーザ権限
	 */
	public void setUserCom(String userCom) {
	    this.userCom = userCom;
	}

	/**
	 * 表示ユーザidのゲッター
	 *
	 * @return 表示ユーザid
	 */
	public String getViewUserId() {
		return viewUserId;
	}

	/**
	 * 表示ユーザidのセッター
	 *
	 * @param viewUserId 表示ユーザid
	 */
	public void setViewUserId(String viewUserId) {
		this.viewUserId = viewUserId;
	}

	/**
	 * 表示ユーザ名のゲッター
	 *
	 * @return 表示ユーザ名
	 */
	public String getViewUserName() {
		return viewUserName;
	}

	/**
	 * 表示ユーザ名のセッター
	 *
	 * @param viewUserName 表示ユーザ名
	 */
	public void setViewUserName(String viewUserName) {
		this.viewUserName = viewUserName;
	}

	/**
	 * 表示日付のゲッター
	 *
	 * @return 表示日付
	 */
	public Date getViewDate() {
		return viewDate;
	}

	/**
	 * 表示日付のセッター
	 *
	 * @param viewDate 表示日付
	 */
	public void setViewDate(Date viewDate) {
		this.viewDate = viewDate;
	}

	/**
	 * 表示月（画面表示用）を取得します。
	 * @return 表示月（画面表示用）
	 */
	public String getViewMonthDisp() {
	    return viewMonthDisp;
	}

	/**
	 * 表示月（画面表示用）を設定します。
	 * @param viewMonthDisp 表示月（画面表示用）
	 */
	public void setViewMonthDisp(String viewMonthDisp) {
	    this.viewMonthDisp = viewMonthDisp;
	}

	/**
	 * 表示日付（画面表示用）を取得します。
	 *
	 * @return 表示日付（画面表示用）
	 */
	public String getViewDateDisp() {
		return viewDateDisp;
	}

	/**
	 * 表示日付（画面表示用）を設定します。
	 *
	 * @param viewDateDisp 表示日付（画面表示用）
	 */
	public void setViewDateDisp(String viewDateDisp) {
		this.viewDateDisp = viewDateDisp;
	}

	/**
	 * 表示画面を取得します。
	 * @return 表示画面
	 */
	public String getViewDisp() {
	    return viewDisp;
	}

	/**
	 * 表示画面を設定します。
	 * @param viewDisp 表示画面
	 */
	public void setViewDisp(String viewDisp) {
	    this.viewDisp = viewDisp;
	}

	/**
	 * 編集可フラグ true:編集可を取得します。
	 * @return 編集可フラグ true:編集可
	 */
	public boolean isEditMode() {
	    return editMode;
	}

	/**
	 * 編集可フラグ true:編集可を設定します。
	 * @param editMode 編集可フラグ true:編集可
	 */
	public void setEditMode(boolean editMode) {
	    this.editMode = editMode;
	}

	/**
	 * 新規ボタン用スクリプト文字列を取得します。
	 *
	 * @return 新規ボタン用スクリプト文字列
	 */
	public String[] getNewLinkString() {
		return newLinkString;
	}

	/**
	 * 新規ボタン用スクリプト文字列を設定します。
	 *
	 * @param newLinkString 新規ボタン用スクリプト文字列
	 */
	public void setNewLinkString(String[] newLinkString) {
		this.newLinkString = newLinkString;
	}

	/**
	 * 表示画面（マスタ管理用）を取得します。
	 * @return 表示画面（マスタ管理用）
	 */
	public String getMasterViewDisp() {
	    return masterViewDisp;
	}

	/**
	 * 表示画面（マスタ管理用）を設定します。
	 * @param masterViewDisp 表示画面（マスタ管理用）
	 */
	public void setMasterViewDisp(String masterViewDisp) {
	    this.masterViewDisp = masterViewDisp;
	}

	/**
	 * 表示者がログインユーザかを示すフラグを取得します。
	 *
	 * @return 表示者がログインユーザかを示すフラグ
	 */
	public boolean isItsMe() {
		return itsMe;
	}

	/**
	 * 表示者がログインユーザかを示すフラグを設定します。
	 *
	 * @param itsMe 表示者がログインユーザかを示すフラグ
	 */
	public void setItsMe(boolean itsMe) {
		this.itsMe = itsMe;
	}

	/**
	 * 所属名のセレクトボックスのゲッター
	 *
	 * @return 所属名のセレクトボックス
	 */
	public LinkedHashMap<String, Object> getDeptSelectBox() {
		return depSelectBox;
	}

	/**
	 * 所属名のセレクトボックスのセッター
	 *
	 * @param deptSelectBox 所属名のセレクトボックス
	 */
	public void setDeptSelectBox(LinkedHashMap<String, Object> deptSelectBox) {
		this.depSelectBox = deptSelectBox;
	}

	/**
	 * 権限のセレクトボックスのゲッター
	 *
	 * @return 権限のセレクトボックス
	 */
	public LinkedHashMap<String, Object> getUserComSelectBox() {
		return userComSelectBox;
	}

	/**
	 * 権限のセレクトボックスのセッター
	 *
	 * @param userComSelectBox 権限のセレクトボックス
	 */
	public void setUserComSelectBox(LinkedHashMap<String, Object> userComSelectBox) {
		this.userComSelectBox = userComSelectBox;
	}

	/**
	 * 開始時刻のセレクトボックスのゲッター
	 *
	 * @return 開始時刻のセレクトボックス
	 */
	public LinkedHashMap<String, Object> getStartTimeSelectBox() {
		return startTimeSelectBox;
	}

	/**
	 * 開始時刻のセレクトボックスのセッター
	 *
	 * @param startTimeSelectBox 開始時刻のセレクトボックス
	 */
	public void setStartTimeSelectBox(LinkedHashMap<String, Object> startTimeSelectBox) {
		this.startTimeSelectBox = startTimeSelectBox;
	}

	/**
	 * 終了時刻のセレクトボックスのゲッター
	 *
	 * @return 終了時刻のセレクトボックス
	 */
	public LinkedHashMap<String, Object> getEndTimeSelectBox() {
		return endTimeSelectBox;
	}

	/**
	 * 終了時刻のセレクトボックスのセッター
	 *
	 * @param endTimeSelectBox 終了時刻のセレクトボックス
	 */
	public void setEndTimeSelectBox(LinkedHashMap<String, Object> endTimeSelectBox) {
		this.endTimeSelectBox = endTimeSelectBox;
	}

	/**
	 * 分類のセレクトボックスのゲッター
	 *
	 * @return 分類のセレクトボックス
	 */
	public LinkedHashMap<String, Object> getTypeSelectBox() {
		return typeSelectBox;
	}

	/**
	 * 分類のセレクトボックスのセッター
	 *
	 * @param typeSelectBox 分類のセレクトボックス
	 */
	public void setTypeSelectBox(LinkedHashMap<String, Object> typeSelectBox) {
		this.typeSelectBox = typeSelectBox;
	}

	/**
	 * 休日種別のセレクトボックスを取得します。
	 * @return 休日種別のセレクトボックス
	 */
	public LinkedHashMap<String,Object> getHolidayKindSelectBox() {
	    return holidayKindSelectBox;
	}

	/**
	 * 休日種別のセレクトボックスを設定します。
	 * @param holidayKindSelectBox 休日種別のセレクトボックス
	 */
	public void setHolidayKindSelectBox(LinkedHashMap<String,Object> holidayKindSelectBox) {
	    this.holidayKindSelectBox = holidayKindSelectBox;
	}

	/**
	 * 休日の背景色マップを取得します。
	 * @return 休日の背景色マップ
	 */
	public Map<String,String> getHolidayColorMap() {
	    return holidayColorMap;
	}

	/**
	 * 休日の背景色マップを設定します。
	 * @param holidayColorMap 休日の背景色マップ
	 */
	public void setHolidayColorMap(Map<String,String> holidayColorMap) {
	    this.holidayColorMap = holidayColorMap;
	}
}
