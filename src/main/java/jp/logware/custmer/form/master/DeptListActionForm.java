package jp.logware.custmer.form.master;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.validator.ValidatorForm;

import jp.logware.custmer.subform.DeptValue;

/**
 * 所属一覧 アクションフォームクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class DeptListActionForm extends ValidatorForm {
	/**
	 * 所属コード
	 */
	private List<DeptValue> deptList;

	/**
	 * 所属コード
	 */
	private String deptCode;

	/**
	 * ボタン
	 */
	private String button;

	/**
	 * コンストラクタ
	 */
	public DeptListActionForm() {
		deptList = new ArrayList<DeptValue>();
		deptCode = "";
	}

	/**
	 * 所属コードのゲッター
	 *
	 * @return 所属コードのリスト
	 */
	public List<DeptValue> getDeptList() {
		return deptList;
	}

	/**
	 * 所属コードのセッター
	 *
	 * @param list 所属コードのリスト
	 */
	public void setDeptList(List<DeptValue> list) {
		this.deptList = list;
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
