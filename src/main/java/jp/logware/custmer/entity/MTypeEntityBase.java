package jp.logware.custmer.entity;

import java.sql.Timestamp;

/**
 * データ格納クラス<br>
 * 分類 テーブル
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class MTypeEntityBase {
	/**
	 * 分類コード
	 */
	private String type_code;

	/**
	 * 分類名
	 */
	private String type_name;

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
	public MTypeEntityBase() {
		type_code = "";
		type_name = "";
		idate = null;
		udate = null;
	}

	/**
	 * 分類コードのゲッター
	 *
	 * @return 分類コード
	 */
	public String getTypeCode() {
		return type_code;
	}

	/**
	 * 分類コードのセッター
	 *
	 * @param type_code 分類コード
	 */
	public void setTypeCode(String type_code) {
		this.type_code = type_code;
	}

	/**
	 * 分類名のゲッター
	 *
	 * @return 分類名
	 */
	public String getTypeName() {
		return type_name;
	}

	/**
	 * 分類名のセッター
	 *
	 * @param type_name 分類名
	 */
	public void setTypeName(String type_name) {
		this.type_name = type_name;
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
