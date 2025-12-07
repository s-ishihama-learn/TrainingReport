package jp.logware.custmer.form.master;

import org.apache.struts.validator.ValidatorForm;

public class UserInitActionForm extends ValidatorForm {
	/**
	 * 社員コード
	 */
	private String userId;

	/**
	 * 社員名
	 */
	private String userName;

	/**
	 * パスワード
	 */
	private String passwd;

	/**
	 * ボタン
	 */
	private String button;

	/**
	 * 社員コードを取得します。
	 * @return 社員コード
	 */
	public String getUserId() {
	    return userId;
	}

	/**
	 * 社員コードを設定します。
	 * @param userId 社員コード
	 */
	public void setUserId(String userId) {
	    this.userId = userId;
	}

	/**
	 * 社員名を取得します。
	 * @return 社員名
	 */
	public String getUserName() {
	    return userName;
	}

	/**
	 * 社員名を設定します。
	 * @param userName 社員名
	 */
	public void setUserName(String userName) {
	    this.userName = userName;
	}

	/**
	 * パスワードを取得します。
	 * @return パスワード
	 */
	public String getPasswd() {
	    return passwd;
	}

	/**
	 * パスワードを設定します。
	 * @param passwd パスワード
	 */
	public void setPasswd(String passwd) {
	    this.passwd = passwd;
	}

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
