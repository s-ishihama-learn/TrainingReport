package jp.logware.custmer.subform;

/**
 * 所属一覧 アクションフォーム用サブクラス<br>
 * DeptValueクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class DeptValue {
	/**
	 * 所属コード
	 */
	private String deptCode;

	/**
	 * 所属名
	 */
	private String deptName;

	/**
	 * コンストラクタ
	 */
	public DeptValue() {
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

}
