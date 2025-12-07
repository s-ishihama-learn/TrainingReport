package jp.logware.custmer.form.master;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.validator.ValidatorForm;

import jp.logware.custmer.subform.HolidayValue;

/**
 * 社員一覧 アクションフォームクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class HolidayListActionForm extends ValidatorForm {
	/**
	 * 休日
	 */
	private List<HolidayValue> holidayList;

	/**
	 * 休日
	 */
	private String date;

	/**
	 * ボタン
	 */
	private String button;

	/**
	 * コンストラクタ
	 */
	public HolidayListActionForm() {
		holidayList = new ArrayList<HolidayValue>();
		date = "";
	}

	/**
	 * 休日のゲッター
	 *
	 * @return 休日のリスト
	 */
	public List<HolidayValue> getHolidayList() {
		return holidayList;
	}

	/**
	 * 休日のセッター
	 *
	 * @param list 休日のリスト
	 */
	public void setHolidayList(List<HolidayValue> list) {
		this.holidayList = list;
	}

	/**
	 * 休日のゲッター
	 *
	 * @return 休日
	 */
	public String getDate() {
		return date;
	}

	/**
	 * 休日のセッター
	 *
	 * @param date 休日
	 */
	public void setDate(String date) {
		this.date = date;
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
}
