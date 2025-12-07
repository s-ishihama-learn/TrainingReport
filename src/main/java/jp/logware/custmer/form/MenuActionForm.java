package jp.logware.custmer.form;

import org.apache.struts.validator.ValidatorForm;

public class MenuActionForm extends ValidatorForm {
	/**
	 * ボタン
	 */
	private String button;

	/**
	 * ボタンのゲッター
	 * @return ボタン
	 */
	public String getButton() {
		return button;
	}

	/**
	 * ボタンのセッター
	 * @param button ボタン
	 */
	public void setButton(String button) {
		this.button = button;
	}
}
