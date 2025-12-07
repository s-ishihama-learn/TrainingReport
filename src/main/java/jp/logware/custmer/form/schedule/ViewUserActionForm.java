package jp.logware.custmer.form.schedule;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.validator.ValidatorForm;

import jp.logware.custmer.logic.ValueBean;
import jp.logware.custmer.subform.UserValue;

/**
 * 表示ユーザ設定 アクションフォームクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class ViewUserActionForm extends ValidatorForm {
	/**
	 * シリアルバージョン
	 */
	private static final long serialVersionUID = -2153648680457817048L;

	/**
	 * 選択
	 */
	private List<UserValue> userList;

	/**
	 * 選択ラジオボタンマップ
	 */
	private ValueBean userIdMap;

	/**
	 * 部署コード
	 */
	private String deptCode;

	/**
	 * ユーザ選択値
	 */
	private String userId;

	/**
	 * ボタン
	 */
	private String button;

	/**
	 * コンストラクタ
	 */
	public ViewUserActionForm() {
		userList = new ArrayList<UserValue>();
		userIdMap = new ValueBean();
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
	 * 選択ラジオボタンマップのゲッター
	 *
	 * @return 選択のラジオボタンマップ
	 */
	public ValueBean getUserIdMap() {
		return userIdMap;
	}

	/**
	 * 選択ラジオボタンマップのセッター
	 *
	 * @param list 選択のラジオボタンマップ
	 */
	public void setUserIdMap(ValueBean map) {
		this.userIdMap = map;
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
	 * ユーザ選択値のゲッター
	 *
	 * @return ユーザ選択値
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザ選択値のセッター
	 *
	 * @param userId ユーザ選択値
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
