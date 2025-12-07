package jp.logware.custmer.dao;

import java.sql.Connection;

/**
 * データベースアクセス拡張クラス<br>
 * 予定 拡張テーブル
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class ScheduleDao extends ScheduleDaoBase {

	/**
	 * コンストラクタ<BR>
	 * Connectionをセットする
	 *
	 * @param con Connection
	 */
	public ScheduleDao(Connection con) {
		super(con);
	}
}
