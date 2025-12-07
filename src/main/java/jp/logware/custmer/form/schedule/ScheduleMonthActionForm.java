package jp.logware.custmer.form.schedule;

import java.util.List;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;

/**
 * スケジュール月表示 アクションフォームクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class ScheduleMonthActionForm extends ValidatorForm {
	/**
	 * シリアルバージョン
	 */
	private static final long serialVersionUID = -993825160530984948L;

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
	private List<Object> monthItem;

	/**
	 * 月の開始までの日数（マイナス値）
	 */
	private int startDay;

	/**
	 * 月末日
	 */
	private int lastDay;

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
	public ScheduleMonthActionForm() {
		userId = "";
		viewDate = "";
		seq = "";
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
	public List<Object> getMonthItem() {
		return monthItem;
	}

	/**
	 * 月表示を設定します。
	 *
	 * @param monthItem 月表示
	 */
	public void setMonthItem(List<Object> monthItem) {
		this.monthItem = monthItem;
	}

	/**
	 * 月の開始までの日数（マイナス値）を取得します。
	 *
	 * @return 月の開始までの日数（マイナス値）
	 */
	public int getStartDay() {
		return startDay;
	}

	/**
	 * 月の開始までの日数（マイナス値）を設定します。
	 *
	 * @param startDay 月の開始までの日数（マイナス値）
	 */
	public void setStartDay(int startDay) {
		this.startDay = startDay;
	}

	/**
	 * 月末日を取得します。
	 *
	 * @return 月末日
	 */
	public int getLastDay() {
		return lastDay;
	}

	/**
	 * 月末日を設定します。
	 *
	 * @param lastDay 月末日
	 */
	public void setLastDay(int lastDay) {
		this.lastDay = lastDay;
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
