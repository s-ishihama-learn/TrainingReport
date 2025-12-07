package jp.logware.custmer.form.master;

import org.apache.struts.validator.ValidatorForm;

/**
 * 所属編集 アクションフォームクラス
 * 
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class DeptEditActionForm extends ValidatorForm {
	/**
	 * シリアルバージョン
	 */
	private static final long serialVersionUID = 4014709116359932084L;

	/**
	 * 所属コード
	 */
	private String deptCode;

	/**
	 * 所属名
	 */
	private String deptName;

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
	public DeptEditActionForm() {
		deptCode = "";
		deptName = "";
	}

	/**
	 * 所属コードのゲッター
	 * 
	 * @return 所属コード
	 */
	public String getDeptCode() {
		return deptCode;
	}

	/**
	 * 所属コードのセッター
	 * 
	 * @param deptCode 所属コード
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	/**
	 * 所属名のゲッター
	 * 
	 * @return 所属名
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * 所属名のセッター
	 * 
	 * @param deptName 所属名
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
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
