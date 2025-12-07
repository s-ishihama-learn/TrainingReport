package jp.logware.custmer.dao;

import java.sql.Connection;

/**
 * データベースアクセス拡張クラス<br>
 * 日報コメント 拡張テーブル
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class CommentDao extends CommentDaoBase {

	/**
	 * コンストラクタ<BR>
	 * Connectionをセットする
	 *
	 * @param con Connection
	 */
	public CommentDao(Connection con) {
		super(con);
	}
}
