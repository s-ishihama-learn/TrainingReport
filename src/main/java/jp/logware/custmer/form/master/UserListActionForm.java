package jp.logware.custmer.form.master;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.validator.ValidatorForm;

import jp.logware.custmer.subform.UserValue;

/**
 * 社員一覧 アクションフォームクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class UserListActionForm extends ValidatorForm {
	/**
	 * 社員コード
	 */
	private List<UserValue> userList;

	/**
	 * 社員コード
	 */
	private String userId;

	/**
	 * ボタン
	 */
	private String button;

	/**
	 * コンストラクタ
	 */
	public UserListActionForm() {
		userList = new ArrayList<UserValue>();
		userId = "";
	}

	/**
	 * 社員コードのゲッター
	 *
	 * @return 社員コードのリスト
	 */
	public List<UserValue> getUserList() {
		return userList;
	}

	/**
	 * 社員コードのセッター
	 *
	 * @param list 社員コードのリスト
	 */
	public void setUserList(List<UserValue> list) {
		this.userList = list;
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
