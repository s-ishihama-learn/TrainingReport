package jp.logware.custmer.form;

import org.apache.struts.validator.ValidatorForm;

public class ErrorActionForm extends ValidatorForm {
	/**
	 * ボタン
	 */
	private String button;

	/**
	 * ボタンを取得します。
	 * @return ボタン
	 */
	public String getButton() {
	    return button;
	}

	/**
	 * ボタンを設定します。
	 * @param button ボタン
	 */
	public void setButton(String button) {
	    this.button = button;
	}
}
