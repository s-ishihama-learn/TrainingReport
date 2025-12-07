package jp.logware.custmer.form.master;

import org.apache.struts.validator.ValidatorForm;

/**
 * 社員編集 アクションフォームクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class UserEditActionForm extends ValidatorForm {
	/**
	 * シリアルバージョン
	 */
	private static final long serialVersionUID = -5182679770312258276L;

	/**
	 * 社員コード
	 */
	private String userId;

	/**
	 * 所属名
	 */
	private String deptCode;

	/**
	 * 社員名
	 */
	private String userName;

	/**
	 * パスワード
	 */
	private String passwd;

	/**
	 * 権限
	 */
	private String userCom;

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
	public UserEditActionForm() {
		userId = "";
		deptCode = "";
		userName = "";
		passwd = "";
		userCom = "";
	}

	/**
	 * 社員コードのゲッター
	 *
	 * @return 社員コード
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 社員コードのセッター
	 *
	 * @param userId 社員コード
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 所属名のゲッター
	 *
	 * @return 所属名
	 */
	public String getDeptCode() {
		return deptCode;
	}

	/**
	 * 所属名のセッター
	 *
	 * @param deptCode 所属名
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	/**
	 * 社員名のゲッター
	 *
	 * @return 社員名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 社員名のセッター
	 *
	 * @param userName 社員名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * パスワードのゲッター
	 *
	 * @return パスワード
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * パスワードのセッター
	 *
	 * @param passwd パスワード
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	/**
	 * 権限のゲッター
	 *
	 * @return 権限
	 */
	public String getUserCom() {
		return userCom;
	}

	/**
	 * 権限のセッター
	 *
	 * @param userCom 権限
	 */
	public void setUserCom(String userCom) {
		this.userCom = userCom;
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
