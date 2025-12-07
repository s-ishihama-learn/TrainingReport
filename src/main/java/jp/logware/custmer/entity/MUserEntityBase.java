package jp.logware.custmer.entity;

import java.sql.Timestamp;

/**
 * データ格納クラス<br>
 * 利用者 テーブル
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class MUserEntityBase {
	/**
	 * ユーザーid
	 */
	private String user_id;

	/**
	 * パスワード
	 */
	private String user_pass;

	/**
	 * 利用者氏名
	 */
	private String user_name;

	/**
	 * 所属コード
	 */
	private String dept_code;

	/**
	 * ユーザー権限
	 */
	private String user_com;

	/**
	 * メールアドレス
	 */
	private String mail_addr;

	/**
	 * パスワード有効期限
	 */
	private Timestamp pass_limit;

	/**
	 * 削除フラグ
	 */
	private String del_flag;

	/**
	 * 登録日
	 */
	private Timestamp idate;

	/**
	 * 更新日
	 */
	private Timestamp udate;

	/**
	 * コンストラクタ
	 */
	public MUserEntityBase() {
		user_id = "";
		user_pass = "";
		user_name = "";
		dept_code = "";
		user_com = "";
		mail_addr = "";
		pass_limit = null;
		del_flag = "";
		idate = null;
		udate = null;
	}

	/**
	 * ユーザーidのゲッター
	 *
	 * @return ユーザーid
	 */
	public String getUserId() {
		return user_id;
	}

	/**
	 * ユーザーidのセッター
	 *
	 * @param user_id ユーザーid
	 */
	public void setUserId(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * パスワードのゲッター
	 *
	 * @return パスワード
	 */
	public String getUserPass() {
		return user_pass;
	}

	/**
	 * パスワードのセッター
	 *
	 * @param user_pass パスワード
	 */
	public void setUserPass(String user_pass) {
		this.user_pass = user_pass;
	}

	/**
	 * 利用者氏名のゲッター
	 *
	 * @return 利用者氏名
	 */
	public String getUserName() {
		return user_name;
	}

	/**
	 * 利用者氏名のセッター
	 *
	 * @param user_name 利用者氏名
	 */
	public void setUserName(String user_name) {
		this.user_name = user_name;
	}

	/**
	 * 所属コードのゲッター
	 *
	 * @return 所属コード
	 */
	public String getDeptCode() {
		return dept_code;
	}

	/**
	 * 所属コードのセッター
	 *
	 * @param dept_code 所属コード
	 */
	public void setDeptCode(String dept_code) {
		this.dept_code = dept_code;
	}

	/**
	 * ユーザー権限のゲッター
	 *
	 * @return ユーザー権限
	 */
	public String getUserCom() {
		return user_com;
	}

	/**
	 * ユーザー権限のセッター
	 *
	 * @param user_com ユーザー権限
	 */
	public void setUserCom(String user_com) {
		this.user_com = user_com;
	}

	/**
	 * メールアドレスのゲッター
	 *
	 * @return メールアドレス
	 */
	public String getMailAddr() {
		return mail_addr;
	}

	/**
	 * メールアドレスのセッター
	 *
	 * @param mail_addr メールアドレス
	 */
	public void setMailAddr(String mail_addr) {
		this.mail_addr = mail_addr;
	}

	/**
	 * パスワード有効期限のゲッター
	 *
	 * @return パスワード有効期限
	 */
	public Timestamp getPassLimit() {
		return pass_limit;
	}

	/**
	 * パスワード有効期限のセッター
	 *
	 * @param pass_limit パスワード有効期限
	 */
	public void setPassLimit(Timestamp pass_limit) {
		this.pass_limit = pass_limit;
	}

	/**
	 * 削除フラグのゲッター
	 *
	 * @return 削除フラグ
	 */
	public String getDelFlag() {
		return del_flag;
	}

	/**
	 * 削除フラグのセッター
	 *
	 * @param del_flag 削除フラグ
	 */
	public void setDelFlag(String del_flag) {
		this.del_flag = del_flag;
	}

	/**
	 * 登録日のゲッター
	 *
	 * @return 登録日
	 */
	public Timestamp getIdate() {
		return idate;
	}

	/**
	 * 登録日のセッター
	 *
	 * @param idate 登録日
	 */
	public void setIdate(Timestamp idate) {
		this.idate = idate;
	}

	/**
	 * 更新日のゲッター
	 *
	 * @return 更新日
	 */
	public Timestamp getUdate() {
		return udate;
	}

	/**
	 * 更新日のセッター
	 *
	 * @param udate 更新日
	 */
	public void setUdate(Timestamp udate) {
		this.udate = udate;
	}

}
