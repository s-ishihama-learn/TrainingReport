package jp.logware.custmer.dao;

import java.sql.Connection;

/**
 * データベースアクセス拡張クラス<br>
 * お知らせ 拡張テーブル
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class InfoDao extends InfoDaoBase {

	/**
	 * コンストラクタ<BR>
	 * Connectionをセットする
	 *
	 * @param con Connection
	 */
	public InfoDao(Connection con) {
		super(con);
	}
}
