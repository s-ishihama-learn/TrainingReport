package jp.logware.custmer.dao;

import java.sql.Connection;

/**
 * データベースアクセス拡張クラス<br>
 * 部署 拡張テーブル
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class MDeptDao extends MDeptDaoBase {

	/**
	 * コンストラクタ<BR>
	 * Connectionをセットする
	 *
	 * @param con Connection
	 */
	public MDeptDao(Connection con) {
		super(con);
	}
}
