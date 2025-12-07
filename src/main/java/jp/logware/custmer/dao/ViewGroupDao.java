package jp.logware.custmer.dao;

import java.sql.Connection;

/**
 * データベースアクセス拡張クラス<br>
 * 表示グループ 拡張テーブル
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class ViewGroupDao extends ViewGroupDaoBase {

	/**
	 * コンストラクタ<BR>
	 * Connectionをセットする
	 *
	 * @param con Connection
	 */
	public ViewGroupDao(Connection con) {
		super(con);
	}
}
