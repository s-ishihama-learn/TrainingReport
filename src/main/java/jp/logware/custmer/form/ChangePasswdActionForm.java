package jp.logware.custmer.form;

import org.apache.struts.validator.ValidatorForm;

/**
 * パスワード変更 アクションフォームクラス
 * 
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class ChangePasswdActionForm extends ValidatorForm {
	/**
	 * シリアルバージョン
	 */
	private static final long serialVersionUID = 981296703851431644L;

	/**
	 * 旧パスワード
	 */
	private String oldPass;

	/**
	 * 新パスワード
	 */
	private String newPass;

	/**
	 * 確認パスワード
	 */
	private String chkPass;

	/**
	 * ユーザid
	 */
	private String userId;

	/**
	 * ボタン
	 */
	private String button;

	/**
	 * コンストラクタ
	 */
	public ChangePasswdActionForm() {
		oldPass = "";
		newPass = "";
		chkPass = "";
		userId = "";
	}

	/**
	 * 旧パスワードのゲッター
	 * 
	 * @return 旧パスワード
	 */
	public String getOldPass() {
		return oldPass;
	}

	/**
	 * 旧パスワードのセッター
	 * 
	 * @param oldPass 旧パスワード
	 */
	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	/**
	 * 新パスワードのゲッター
	 * 
	 * @return 新パスワード
	 */
	public String getNewPass() {
		return newPass;
	}

	/**
	 * 新パスワードのセッター
	 * 
	 * @param newPass 新パスワード
	 */
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	/**
	 * 確認パスワードのゲッター
	 * 
	 * @return 確認パスワード
	 */
	public String getChkPass() {
		return chkPass;
	}

	/**
	 * 確認パスワードのセッター
	 * 
	 * @param chkPass 確認パスワード
	 */
	public void setChkPass(String chkPass) {
		this.chkPass = chkPass;
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
