package jp.logware.custmer.dao;

import java.sql.Connection;

/**
 * データベースアクセス拡張クラス<br>
 * 分類 拡張テーブル
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class MTypeDao extends MTypeDaoBase {

	/**
	 * コンストラクタ<BR>
	 * Connectionをセットする
	 *
	 * @param con Connection
	 */
	public MTypeDao(Connection con) {
		super(con);
	}
}
