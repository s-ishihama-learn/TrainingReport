package jp.logware.custmer.form.schedule;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.validator.ValidatorForm;

import jp.logware.custmer.subform.UserValue;

/**
 * 表示グループ設定 アクションフォームクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class ViewGroupUserActionForm extends ValidatorForm {
	/**
	 * シリアルバージョン
	 */
	private static final long serialVersionUID = -5913962797901918035L;

	/**
	 * 選択
	 */
	private List<UserValue> userList;

	/**
	 * 選択チェックボックス選択配列
	 */
	private String checked[];

	/**
	 * 部署コード
	 */
	private String deptCode;

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
	public ViewGroupUserActionForm() {
		userList = new ArrayList<UserValue>();
		deptCode = "";
		userId = "";
	}

	/**
	 * 選択のゲッター
	 *
	 * @return 選択のリスト
	 */
	public List<UserValue> getUserList() {
		return userList;
	}

	/**
	 * 選択のセッター
	 *
	 * @param list 選択のリスト
	 */
	public void setUserList(List<UserValue> list) {
		this.userList = list;
	}

	/**
	 * 選択チェックボックス選択配列のゲッター
	 *
	 * @return 選択のチェックボックス選択配列
	 */
	public String[] getChecked() {
		return checked;
	}

	/**
	 * 選択チェックボックス選択配列のセッター
	 *
	 * @param list 選択のチェックボックス選択配列
	 */
	public void setChecked(String[] checked) {
		this.checked = checked;
	}

	/**
	 * 部署コードのゲッター
	 *
	 * @return 部署コード
	 */
	public String getDeptCode() {
		return deptCode;
	}

	/**
	 * 部署コードのセッター
	 *
	 * @param deptCode 部署コード
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
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
