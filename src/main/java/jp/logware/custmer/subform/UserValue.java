package jp.logware.custmer.subform;

/**
 * 表示ユーザ設定、表示グループ設定、社員一覧 アクションフォーム用サブクラス<br>
 * UserValueクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class UserValue {
	/**
	 * 選択
	 */
	private String userId;

	/**
	 * 所属
	 */
	private String deptName;

	/**
	 * 氏名
	 */
	private String userName;

	/**
	 * 所属コード
	 */
	private String deptCode;

	/**
	 * 権限
	 */
	private String userCom;

	/**
	 * コンストラクタ
	 */
	public UserValue() {
		userId = "";
		deptName = "";
		userName = "";
		deptCode = "";
		userCom = "";
	}

	/**
	 * 選択のゲッター
	 *
	 * @return 選択
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 選択のセッター
	 *
	 * @param userId 選択
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 所属のゲッター
	 *
	 * @return 所属
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * 所属のセッター
	 *
	 * @param deptName 所属
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * 氏名のゲッター
	 *
	 * @return 氏名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 氏名のセッター
	 *
	 * @param userName 氏名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	 * 権限を取得します。
	 * @return 権限
	 */
	public String getUserCom() {
	    return userCom;
	}

	/**
	 * 権限を設定します。
	 * @param userCom 権限
	 */
	public void setUserCom(String userCom) {
	    this.userCom = userCom;
	}

}
