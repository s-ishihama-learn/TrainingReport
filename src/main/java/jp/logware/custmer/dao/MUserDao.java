package jp.logware.custmer.dao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.logware.custmer.entity.MUserEntity;

/**
 * データベースアクセス拡張クラス<br>
 * 利用者 拡張テーブル
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class MUserDao extends MUserDaoBase {

	/**
	 * コンストラクタ<BR>
	 * Connectionをセットする
	 *
	 * @param con Connection
	 */
	public MUserDao(Connection con) {
		super(con);
	}

	public Map<String, String> getMap() {
		Map<String, String> map = new HashMap<String, String>();
		try {
			List<MUserEntity> list = super.getList();
			for (MUserEntity user : list) {
				map.put(user.getUserId(), user.getUserName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
