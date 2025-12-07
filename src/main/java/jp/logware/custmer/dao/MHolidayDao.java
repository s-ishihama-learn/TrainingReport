package jp.logware.custmer.dao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.logware.custmer.entity.MHolidayEntity;

/**
 * データベースアクセス拡張クラス<br>
 * 休日 拡張テーブル
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class MHolidayDao extends MHolidayDaoBase {

	/**
	 * コンストラクタ<BR>
	 * Connectionをセットする
	 *
	 * @param con Connection
	 */
	public MHolidayDao(Connection con) {
		super(con);
	}

	public Map<String, String> getMap() {
		Map<String, String> map = new HashMap<String, String>();
		try {
			List<MHolidayEntity> list = super.getList();
			for (MHolidayEntity entity : list) {
				map.put(entity.getDate(), entity.getKind());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
