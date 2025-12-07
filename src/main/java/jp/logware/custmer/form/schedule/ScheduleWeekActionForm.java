package jp.logware.custmer.form.schedule;

import java.util.List;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;

/**
 * スケジュールグループ表示 アクションフォームクラス
 * 
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class ScheduleWeekActionForm extends ValidatorForm {
	/**
	 * シリアルバージョン
	 */
	private static final long serialVersionUID = -3693590076280582200L;

	/**
	 * ユーザ名
	 */
	private String[] userName;

	/**
	 * 表示ユーザ名
	 */
	private String viewUserName;

	/**
	 * 年月
	 */
	private String nowDate;

	/**
	 * ユーザid
	 */
	private String userId;

	/**
	 * 作業日
	 */
	private String viewDate;

	/**
	 * 連番
	 */
	private String seq;

	/**
	 * ボタン
	 */
	private String button;

	/**
	 * 月表示
	 */
	private List<Object> groupItem;

	/**
	 * 週の日
	 */
	private String[] days;

	/**
	 * 休日背景色
	 */
	private Map<String,String> holidayColorMap;

	/**
	 * 表示月
	 */
	private String viewMonth;

	/**
	 * コンストラクタ
	 */
	public ScheduleWeekActionForm() {
		nowDate = "";
		userId = "";
		viewDate = "";
		seq = "";
	}

	/**
	 * ユーザ名のゲッター
	 * 
	 * @return ユーザ名
	 */
	public String[] getUserName() {
		return userName;
	}

	/**
	 * ユーザ名のセッター
	 * 
	 * @param userName ユーザ名
	 */
	public void setUserName(String[] userName) {
		this.userName = userName;
	}

	/**
	 * 表示ユーザ名を取得します。
	 * 
	 * @return 表示ユーザ名
	 */
	public String getViewUserName() {
		return viewUserName;
	}

	/**
	 * 表示ユーザ名を設定します。
	 * 
	 * @param viewUserName 表示ユーザ名
	 */
	public void setViewUserName(String viewUserName) {
		this.viewUserName = viewUserName;
	}

	/**
	 * 年月のゲッター
	 * 
	 * @return 年月
	 */
	public String getNowDate() {
		return nowDate;
	}

	/**
	 * 年月のセッター
	 * 
	 * @param nowDate 年月
	 */
	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
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
	 * 月表示を取得します。
	 * 
	 * @return 月表示
	 */
	public List<Object> getGroupItem() {
		return groupItem;
	}

	/**
	 * 月表示を設定します。
	 * 
	 * @param groupItem 月表示
	 */
	public void setGroupItem(List<Object> groupItem) {
		this.groupItem = groupItem;
	}

	/**
	 * 週の日を取得します。
	 * 
	 * @return 週の日
	 */
	public String[] getDays() {
		return days;
	}

	/**
	 * 週の日を設定します。
	 * 
	 * @param days 週の日
	 */
	public void setDays(String[] days) {
		this.days = days;
	}

	/**
	 * 休日背景色を取得します。
	 * @return 休日背景色
	 */
	public Map<String,String> getHolidayColorMap() {
	    return holidayColorMap;
	}

	/**
	 * 休日背景色を設定します。
	 * @param holidayColorMap 休日背景色
	 */
	public void setHolidayColorMap(Map<String,String> holidayColorMap) {
	    this.holidayColorMap = holidayColorMap;
	}

	/**
	 * 表示月を取得します。
	 * @return 表示月
	 */
	public String getViewMonth() {
	    return viewMonth;
	}

	/**
	 * 表示月を設定します。
	 * @param viewMonth 表示月
	 */
	public void setViewMonth(String viewMonth) {
	    this.viewMonth = viewMonth;
	}
}
