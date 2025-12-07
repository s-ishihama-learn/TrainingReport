package jp.logware.custmer.entity;

import java.sql.Timestamp;

/**
 * データ格納クラス<br>
 * 部署 テーブル
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class MDeptEntityBase {
	/**
	 * 部署コード
	 */
	private String dept_code;

	/**
	 * 部署名
	 */
	private String dept_name;

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
	public MDeptEntityBase() {
		dept_code = "";
		dept_name = "";
		idate = null;
		udate = null;
	}

	/**
	 * 部署コードのゲッター
	 *
	 * @return 部署コード
	 */
	public String getDeptCode() {
		return dept_code;
	}

	/**
	 * 部署コードのセッター
	 *
	 * @param dept_code 部署コード
	 */
	public void setDeptCode(String dept_code) {
		this.dept_code = dept_code;
	}

	/**
	 * 部署名のゲッター
	 *
	 * @return 部署名
	 */
	public String getDeptName() {
		return dept_name;
	}

	/**
	 * 部署名のセッター
	 *
	 * @param dept_name 部署名
	 */
	public void setDeptName(String dept_name) {
		this.dept_name = dept_name;
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
