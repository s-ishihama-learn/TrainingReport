package jp.logware.custmer.dao;

import java.sql.Connection;

/**
 * データベースアクセス拡張クラス<br>
 * 日報 拡張テーブル
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class ReportDao extends ReportDaoBase {

	/**
	 * コンストラクタ<BR>
	 * Connectionをセットする
	 *
	 * @param con Connection
	 */
	public ReportDao(Connection con) {
		super(con);
	}
}
