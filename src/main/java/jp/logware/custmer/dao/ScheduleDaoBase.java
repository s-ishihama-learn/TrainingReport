package jp.logware.custmer.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.logware.custmer.entity.ScheduleEntity;

/**
 * データベースアクセスクラス<br>
 * 予定 テーブル
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class ScheduleDaoBase {
	/**
	 * Connection（データベースコネクション）
	 */
	protected Connection dbConn = null;

	/**
	 * コンストラクタ<BR>
	 * Connectionをセットする
	 *
	 * @param con Connection
	 */
	public ScheduleDaoBase(Connection con) {
		dbConn = con;
	}

	/**
	 * 予定 テーブルから<BR>
	 * 全てのレコードを取得し、Listクラスに<BR>
	 * ScheduleEntityクラスをセットして返す
	 *
	 * @return Listクラス
	 */
	public List<ScheduleEntity> getList() throws SQLException, Exception {
		return getListSort(null);
	}

	/**
	 * 予定 テーブルから<BR>
	 * 全てのレコードを取得し、Listクラスにソート処理して<BR>
	 * ScheduleEntityクラスをセットして返す
	 *
	 * @param order ORDER句文字列
	 * @return Listクラス
	 */
	public List<ScheduleEntity> getListSort(String order) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		List<ScheduleEntity> list = new ArrayList<ScheduleEntity>();

		try {
			sql.append("SELECT ");
			sql.append("USER_ID,");
			sql.append("DATE,");
			sql.append("SEQ,");
			sql.append("START_TIME,");
			sql.append("END_TIME,");
			sql.append("TITLE,");
			sql.append("BODY,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM SCHEDULE");
			if (order != null && order.length() > 0) {
				sql.append(" ORDER BY ");
				sql.append(order);
			}

			stmt = dbConn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();
			while (rs.next()) {
				ScheduleEntity value = new ScheduleEntity();
				value.setUserId(rs.getString("USER_ID"));
				value.setDate(rs.getDate("DATE"));
				value.setSeq(rs.getInt("SEQ"));
				value.setStartTime(rs.getString("START_TIME"));
				value.setEndTime(rs.getString("END_TIME"));
				value.setTitle(rs.getString("TITLE"));
				value.setBody(rs.getString("BODY"));
				value.setIdate(rs.getTimestamp("IDATE"));
				value.setUdate(rs.getTimestamp("UDATE"));
				list.add(value);
			}

		} catch (SQLException e) {
			throw new SQLException(e.getMessage() + " [" + sql.toString() + "]", e);

		} catch (Exception e) {
			throw e;

		} finally {
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception e) {
				}
			}
		}

		return list;
	}

	/**
	 * 予定 テーブルから<BR>
	 * ScheduleEntityクラスのキー項目に一致するレコードを取得し<BR>
	 * ScheduleEntityクラスにセットして返す
	 *
	 * @param keyvalue ScheduleEntityBaseクラス
	 * @return ScheduleEntityBaseクラス
	 */
	public ScheduleEntity getValue(ScheduleEntity keyvalue) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		ScheduleEntity value = null;
		try {
			sql.append("SELECT ");
			sql.append("USER_ID,");
			sql.append("DATE,");
			sql.append("SEQ,");
			sql.append("START_TIME,");
			sql.append("END_TIME,");
			sql.append("TITLE,");
			sql.append("BODY,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM SCHEDULE ");
			sql.append("WHERE ");
			sql.append("USER_ID=? AND ");
			sql.append("DATE=? AND ");
			sql.append("SEQ=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, keyvalue.getUserId());
			stmt.setDate(2, keyvalue.getDate());
			stmt.setInt(3, keyvalue.getSeq());

			rs = stmt.executeQuery();
			if (rs.next()) {
				value = new ScheduleEntity();
				value.setUserId(rs.getString("USER_ID"));
				value.setDate(rs.getDate("DATE"));
				value.setSeq(rs.getInt("SEQ"));
				value.setStartTime(rs.getString("START_TIME"));
				value.setEndTime(rs.getString("END_TIME"));
				value.setTitle(rs.getString("TITLE"));
				value.setBody(rs.getString("BODY"));
				value.setIdate(rs.getTimestamp("IDATE"));
				value.setUdate(rs.getTimestamp("UDATE"));
			}

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(keyvalue.getUserId());
			buf.append(",");
			buf.append(keyvalue.getDate());
			buf.append(",");
			buf.append(keyvalue.getSeq());
			buf.append("]");
			System.out.println(buf.toString());
			throw new SQLException(e.getMessage() + " [" + sql.toString() + "]", e);

		} catch (Exception e) {
			throw e;

		} finally {
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception e) {
				}
			}
		}

		return value;
	}

	/**
	 * 予定 テーブルから<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードを取得し<BR>
	 * ScheduleEntityクラスにセットして返す
	 *
	 * @param user_id ユーザーID
	 * @param date 日付
	 * @param seq 連番
	 * @return ScheduleEntityクラス
	 */
	public ScheduleEntity getValue(String user_id, Date date, int seq) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		ScheduleEntity value = null;
		try {
			sql.append("SELECT ");
			sql.append("USER_ID,");
			sql.append("DATE,");
			sql.append("SEQ,");
			sql.append("START_TIME,");
			sql.append("END_TIME,");
			sql.append("TITLE,");
			sql.append("BODY,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM SCHEDULE ");
			sql.append("WHERE ");
			sql.append("USER_ID=? AND ");
			sql.append("DATE=? AND ");
			sql.append("SEQ=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, user_id);
			stmt.setDate(2, date);
			stmt.setInt(3, seq);

			rs = stmt.executeQuery();
			if (rs.next()) {
				value = new ScheduleEntity();
				value.setUserId(rs.getString("USER_ID"));
				value.setDate(rs.getDate("DATE"));
				value.setSeq(rs.getInt("SEQ"));
				value.setStartTime(rs.getString("START_TIME"));
				value.setEndTime(rs.getString("END_TIME"));
				value.setTitle(rs.getString("TITLE"));
				value.setBody(rs.getString("BODY"));
				value.setIdate(rs.getTimestamp("IDATE"));
				value.setUdate(rs.getTimestamp("UDATE"));
			}

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(user_id);
			buf.append(",");
			buf.append(date);
			buf.append(",");
			buf.append(seq);
			buf.append("]");
			System.out.println(buf.toString());
			throw new SQLException(e.getMessage() + " [" + sql.toString() + "]", e);

		} catch (Exception e) {
			throw e;

		} finally {
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception e) {
				}
			}
		}

		return value;
	}

	/**
	 * 予定 テーブルから<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードを取得し<BR>
	 * ListクラスにScheduleEntityクラスをセットして返す
	 *
	 * @param user_id ユーザーID
	 * @param date 日付
	 * @return Listクラス
	 */
	public List<ScheduleEntity> getList(String user_id, Date date) throws SQLException, Exception {
		return getListSort(user_id, date, null);
	}

	/**
	 * 予定 テーブルから<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードを取得し<BR>
	 * ListクラスにScheduleEntityクラスをセットして返す
	 *
	 * @param user_id ユーザーID
	 * @return Listクラス
	 */
	public List<ScheduleEntity> getList(String user_id) throws SQLException, Exception {
		return getListSort(user_id, null);
	}

	/**
	 * 予定 テーブルから<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードをソートして取得し<BR>
	 * ListクラスにScheduleEntityクラスをセットして返す
	 *
	 * @param user_id ユーザーID
	 * @param date 日付
	 * @param order ORDER句文字列
	 * @return Listクラス
	 */
	public List<ScheduleEntity> getListSort(String user_id, Date date, String order) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		List<ScheduleEntity> list = new ArrayList<ScheduleEntity>();
		try {
			sql.append("SELECT ");
			sql.append("USER_ID,");
			sql.append("DATE,");
			sql.append("SEQ,");
			sql.append("START_TIME,");
			sql.append("END_TIME,");
			sql.append("TITLE,");
			sql.append("BODY,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM SCHEDULE ");
			sql.append("WHERE ");
			sql.append("USER_ID=? AND ");
			sql.append("DATE=?");
			if (order != null && order.length() > 0) {
				sql.append(" ORDER BY ");
				sql.append(order);
			}

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, user_id);
			stmt.setDate(2, date);

			rs = stmt.executeQuery();
			while (rs.next()) {
				ScheduleEntity value = new ScheduleEntity();
				value.setUserId(rs.getString("USER_ID"));
				value.setDate(rs.getDate("DATE"));
				value.setSeq(rs.getInt("SEQ"));
				value.setStartTime(rs.getString("START_TIME"));
				value.setEndTime(rs.getString("END_TIME"));
				value.setTitle(rs.getString("TITLE"));
				value.setBody(rs.getString("BODY"));
				value.setIdate(rs.getTimestamp("IDATE"));
				value.setUdate(rs.getTimestamp("UDATE"));
				list.add(value);
			}

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(user_id);
			buf.append(",");
			buf.append(date);
			buf.append("]");
			System.out.println(buf.toString());
			throw new SQLException(e.getMessage() + " [" + sql.toString() + "]", e);

		} catch (Exception e) {
			throw e;

		} finally {
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception e) {
				}
			}
		}

		return list;
	}

	/**
	 * 予定 テーブルから<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードをソートして取得し<BR>
	 * ListクラスにScheduleEntityクラスをセットして返す
	 *
	 * @param user_id ユーザーID
	 * @param order ORDER句文字列
	 * @return Listクラス
	 */
	public List<ScheduleEntity> getListSort(String user_id, String order) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		List<ScheduleEntity> list = new ArrayList<ScheduleEntity>();
		try {
			sql.append("SELECT ");
			sql.append("USER_ID,");
			sql.append("DATE,");
			sql.append("SEQ,");
			sql.append("START_TIME,");
			sql.append("END_TIME,");
			sql.append("TITLE,");
			sql.append("BODY,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM SCHEDULE ");
			sql.append("WHERE ");
			sql.append("USER_ID=?");
			if (order != null && order.length() > 0) {
				sql.append(" ORDER BY ");
				sql.append(order);
			}

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, user_id);

			rs = stmt.executeQuery();
			while (rs.next()) {
				ScheduleEntity value = new ScheduleEntity();
				value.setUserId(rs.getString("USER_ID"));
				value.setDate(rs.getDate("DATE"));
				value.setSeq(rs.getInt("SEQ"));
				value.setStartTime(rs.getString("START_TIME"));
				value.setEndTime(rs.getString("END_TIME"));
				value.setTitle(rs.getString("TITLE"));
				value.setBody(rs.getString("BODY"));
				value.setIdate(rs.getTimestamp("IDATE"));
				value.setUdate(rs.getTimestamp("UDATE"));
				list.add(value);
			}

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(user_id);
			buf.append("]");
			System.out.println(buf.toString());
			throw new SQLException(e.getMessage() + " [" + sql.toString() + "]", e);

		} catch (Exception e) {
			throw e;

		} finally {
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception e) {
				}
			}
		}

		return list;
	}

	/**
	 * 予定 テーブルから<BR>
	 * WHEREを文字列として渡し、それに一致するレコードを取得し<BR>
	 * ListクラスにScheduleEntityクラスをセットして返す
	 *
	 * @param where WHERE句文字列
	 * @return Listクラス
	 */
	public List<ScheduleEntity> getListWhere(String where) throws SQLException, Exception {
		return getListWhereSort(where, null);
	}

	/**
	 * 予定 テーブルから<BR>
	 * WHEREを文字列として渡し、それに一致するレコードをソートして取得し<BR>
	 * ListクラスにScheduleEntityクラスをセットして返す
	 *
	 * @param where WHERE句文字列
	 * @param order ORDER句文字列
	 * @return Listクラス
	 */
	public List<ScheduleEntity> getListWhereSort(String where, String order) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		List<ScheduleEntity> list = new ArrayList<ScheduleEntity>();
		try {
			sql.append("SELECT ");
			sql.append("USER_ID,");
			sql.append("DATE,");
			sql.append("SEQ,");
			sql.append("START_TIME,");
			sql.append("END_TIME,");
			sql.append("TITLE,");
			sql.append("BODY,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM SCHEDULE ");
			sql.append("WHERE ");
			sql.append(where);
			if (order != null && order.length() > 0) {
				sql.append(" ORDER BY ");
				sql.append(order);
			}

			stmt = dbConn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();
			while (rs.next()) {
				ScheduleEntity value = new ScheduleEntity();
				value.setUserId(rs.getString("USER_ID"));
				value.setDate(rs.getDate("DATE"));
				value.setSeq(rs.getInt("SEQ"));
				value.setStartTime(rs.getString("START_TIME"));
				value.setEndTime(rs.getString("END_TIME"));
				value.setTitle(rs.getString("TITLE"));
				value.setBody(rs.getString("BODY"));
				value.setIdate(rs.getTimestamp("IDATE"));
				value.setUdate(rs.getTimestamp("UDATE"));
				list.add(value);
			}

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Where = [");
			buf.append(where);
			buf.append("]");
			System.out.println(buf.toString());
			throw new SQLException(e.getMessage() + " [" + sql.toString() + "]", e);

		} catch (Exception e) {
			throw e;

		} finally {
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception e) {
				}
			}
		}

		return list;
	}

	/**
	 * 予定 テーブルに<BR>
	 * ScheduleEntityBaseクラスを新規登録する
	 *
	 * @param value ScheduleEntityクラス
	 * @return 登録レコード数
	 */
	public int registValue(ScheduleEntity value) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("INSERT INTO SCHEDULE( ");
			sql.append("USER_ID,");
			sql.append("DATE,");
			sql.append("SEQ,");
			sql.append("START_TIME,");
			sql.append("END_TIME,");
			sql.append("TITLE,");
			sql.append("BODY,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append(") VALUES( ?,?,?,?,?,?,?,?,? )");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, value.getUserId());
			stmt.setDate(2, value.getDate());
			stmt.setInt(3, value.getSeq());
			stmt.setString(4, value.getStartTime());
			stmt.setString(5, value.getEndTime());
			stmt.setString(6, value.getTitle());
			stmt.setString(7, value.getBody());
			stmt.setTimestamp(8, value.getIdate());
			stmt.setTimestamp(9, value.getUdate());

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(value.getUserId());
			buf.append(",");
			buf.append(value.getDate());
			buf.append(",");
			buf.append(value.getSeq());
			buf.append(",");
			buf.append(value.getStartTime());
			buf.append(",");
			buf.append(value.getEndTime());
			buf.append(",");
			buf.append(value.getTitle());
			buf.append(",");
			buf.append(value.getBody());
			buf.append(",");
			buf.append(value.getIdate());
			buf.append(",");
			buf.append(value.getUdate());
			buf.append("]");
			System.out.println(buf.toString());
			throw new SQLException(e.getMessage() + " [" + sql.toString() + "]");

		} catch (Exception e) {
			throw e;

		} finally {
		}

		return count;
	}

	/**
	 * 予定 テーブルに<BR>
	 * ScheduleEntityBaseクラスの値で更新する
	 *
	 * @param value ScheduleEntityクラス
	 * @return 登録レコード数
	 */
	public int updateValue(ScheduleEntity value) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("UPDATE SCHEDULE SET ");
			sql.append("START_TIME=?,");
			sql.append("END_TIME=?,");
			sql.append("TITLE=?,");
			sql.append("BODY=?,");
			sql.append("IDATE=?,");
			sql.append("UDATE=? ");
			sql.append("WHERE ");
			sql.append("USER_ID=? AND ");
			sql.append("DATE=? AND ");
			sql.append("SEQ=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, value.getStartTime());
			stmt.setString(2, value.getEndTime());
			stmt.setString(3, value.getTitle());
			stmt.setString(4, value.getBody());
			stmt.setTimestamp(5, value.getIdate());
			stmt.setTimestamp(6, value.getUdate());
			stmt.setString(7, value.getUserId());
			stmt.setDate(8, value.getDate());
			stmt.setInt(9, value.getSeq());

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(value.getStartTime());
			buf.append(",");
			buf.append(value.getEndTime());
			buf.append(",");
			buf.append(value.getTitle());
			buf.append(",");
			buf.append(value.getBody());
			buf.append(",");
			buf.append(value.getIdate());
			buf.append(",");
			buf.append(value.getUdate());
			buf.append(",");
			buf.append(value.getUserId());
			buf.append(",");
			buf.append(value.getDate());
			buf.append(",");
			buf.append(value.getSeq());
			buf.append("]");
			System.out.println(buf.toString());
			throw new SQLException(e.getMessage() + " [" + sql.toString() + "]");

		} catch (Exception e) {
			throw e;

		} finally {
		}

		return count;
	}

	/**
	 * 予定 テーブルに<BR>
	 * ScheduleEntityBaseクラスを登録する<BR>
	 * 既にレコードが存在する場合はUPDATE、存在しない場合はINSERTする
	 *
	 * @param value ScheduleEntityクラス
	 * @return 更新レコード数
	 */
	public int setValue(ScheduleEntity value) throws SQLException, Exception {
		int count = 0;
		try {
			if (count(value) == 0) {
				count = registValue(value);
			} else {
				count = updateValue(value);
			}

		} catch (SQLException e) {
			throw e;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return count;
	}

	/**
	 * 予定 テーブルに<BR>
	 * Listクラスに格納したScheduleEntityクラスを新規登録する
	 *
	 * @return 更新レコード数
	 */
	public int registList(List<ScheduleEntity> list) throws SQLException, Exception {
		int count = 0;
		try {
			Iterator<ScheduleEntity> it = list.iterator();
			while (it.hasNext()) {
				ScheduleEntity value = (ScheduleEntity) it.next();
				int cnt = registValue(value);
				count += cnt;
			}

		} catch (SQLException e) {
			throw e;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return count;
	}

	/**
	 * 予定 テーブルに<BR>
	 * Listクラスに格納したScheduleEntityクラスの値で更新する<BR>
	 *
	 * @return 更新レコード数
	 */
	public int updateList(List<ScheduleEntity> list) throws SQLException, Exception {
		int count = 0;
		try {
			Iterator<ScheduleEntity> it = list.iterator();
			while (it.hasNext()) {
				ScheduleEntity value = (ScheduleEntity) it.next();
				int cnt = updateValue(value);
				count += cnt;
			}

		} catch (SQLException e) {
			throw e;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return count;
	}

	/**
	 * 予定 テーブルに<BR>
	 * Listクラスに格納したScheduleEntityBaseクラスを登録する<BR>
	 * 既にレコードが存在する場合はUPDATE、存在しない場合はINSERTする
	 *
	 * @return 更新レコード数
	 */
	public int setList(List<ScheduleEntity> list) throws SQLException, Exception {
		int count = 0;
		try {
			Iterator<ScheduleEntity> it = list.iterator();
			while (it.hasNext()) {
				ScheduleEntity value = (ScheduleEntity) it.next();
				int cnt = setValue(value);
				count += cnt;
			}

		} catch (SQLException e) {
			throw e;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return count;
	}

	/**
	 * 予定 テーブルの<BR>
	 * ScheduleEntityBaseクラスのキー項目に一致するレコードを削除する
	 *
	 * @param keyvalue ScheduleEntityクラス
	 * @return 削除レコード数
	 */
	public int removeValue(ScheduleEntity keyvalue) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("DELETE FROM SCHEDULE WHERE ");
			sql.append("USER_ID=? AND ");
			sql.append("DATE=? AND ");
			sql.append("SEQ=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, keyvalue.getUserId());
			stmt.setDate(2, keyvalue.getDate());
			stmt.setInt(3, keyvalue.getSeq());

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(keyvalue.getUserId());
			buf.append(",");
			buf.append(keyvalue.getDate());
			buf.append(",");
			buf.append(keyvalue.getSeq());
			buf.append("]");
			System.out.println(buf.toString());
			throw new SQLException(e.getMessage() + " [" + sql.toString() + "]");

		} catch (Exception e) {
			throw e;

		} finally {
		}

		return count;
	}

	/**
	 * 予定 テーブルの<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードを削除する
	 *
	 * @param user_id ユーザーID
	 * @param date 日付
	 * @param seq 連番
	 * @return 削除レコード数
	 */
	public int removeValue(String user_id, Date date, int seq) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("DELETE FROM SCHEDULE WHERE ");
			sql.append("USER_ID=? AND ");
			sql.append("DATE=? AND ");
			sql.append("SEQ=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, user_id);
			stmt.setDate(2, date);
			stmt.setInt(3, seq);

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(user_id);
			buf.append(",");
			buf.append(date);
			buf.append(",");
			buf.append(seq);
			buf.append("]");
			System.out.println(buf.toString());
			throw new SQLException(e.getMessage() + " [" + sql.toString() + "]");

		} catch (Exception e) {
			throw e;

		} finally {
		}

		return count;
	}

	/**
	 * 予定 テーブルの<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードを削除する
	 *
	 * @param user_id ユーザーID
	 * @param date 日付
	 * @return 削除レコード数
	 */
	public int removeValue(String user_id, Date date) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("DELETE FROM SCHEDULE WHERE ");
			sql.append("USER_ID=? AND ");
			sql.append("DATE=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, user_id);
			stmt.setDate(2, date);

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(user_id);
			buf.append(",");
			buf.append(date);
			buf.append("]");
			System.out.println(buf.toString());
			throw new SQLException(e.getMessage() + " [" + sql.toString() + "]");

		} catch (Exception e) {
			throw e;

		} finally {
		}

		return count;
	}

	/**
	 * 予定 テーブルの<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードを削除する
	 *
	 * @param user_id ユーザーID
	 * @return 削除レコード数
	 */
	public int removeValue(String user_id) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("DELETE FROM SCHEDULE WHERE ");
			sql.append("USER_ID=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, user_id);

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(user_id);
			buf.append("]");
			System.out.println(buf.toString());
			throw new SQLException(e.getMessage() + " [" + sql.toString() + "]");

		} catch (Exception e) {
			throw e;

		} finally {
		}

		return count;
	}

	/**
	 * 予定 テーブルの<BR>
	 * WHERE文字列に一致するレコードを削除する
	 *
	 * @param where WHERE句文字列
	 * @return 削除レコード数
	 */
	public int removeValueWhere(String where) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("DELETE FROM SCHEDULE WHERE ");
			sql.append(where);

			stmt = dbConn.prepareStatement(sql.toString());

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Where = [");
			buf.append(where);
			buf.append("]");
			System.out.println(buf.toString());
			throw new SQLException(e.getMessage() + " [" + sql.toString() + "]");

		} catch (Exception e) {
			throw e;

		} finally {
		}

		return count;
	}

	/**
	 * 予定 テーブルの<BR>
	 * 全レコードを削除する
	 *
	 * @return 削除レコード数
	 */
	public int removeAll() throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("DELETE FROM SCHEDULE");

			stmt = dbConn.prepareStatement(sql.toString());

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			throw new SQLException(e.getMessage() + " [" + sql.toString() + "]");

		} catch (Exception e) {
			throw e;

		} finally {
		}

		return count;
	}

	/**
	 * 予定 テーブルの<BR>
	 * 全レコード数を返す
	 *
	 * @return 全レコード数
	 */
	public int count() throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("SELECT COUNT(*) AS CNT FROM SCHEDULE");

			stmt = dbConn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("CNT");
			}

		} catch (SQLException e) {
			throw new SQLException(e.getMessage() + " [" + sql.toString() + "]", e);

		} catch (Exception e) {
			throw e;

		} finally {
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception e) {
				}
			}
		}

		return count;
	}

	/**
	 * 予定 テーブルの<BR>
	 * ScheduleEntityBaseクラスのキー項目に一致するレコードを取得し<BR>
	 * そのレコード数を返す
	 *
	 * @param keyvalue ScheduleEntityクラス
	 * @return 一致レコード数
	 */
	public int count(ScheduleEntity keyvalue) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("SELECT COUNT(*) AS CNT FROM SCHEDULE");
			sql.append(" WHERE ");
			sql.append("USER_ID=? AND ");
			sql.append("DATE=? AND ");
			sql.append("SEQ=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, keyvalue.getUserId());
			stmt.setDate(2, keyvalue.getDate());
			stmt.setInt(3, keyvalue.getSeq());

			rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("CNT");
			}

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(keyvalue.getUserId());
			buf.append(",");
			buf.append(keyvalue.getDate());
			buf.append(",");
			buf.append(keyvalue.getSeq());
			buf.append("]");
			System.out.println(buf.toString());
			throw new SQLException(e.getMessage() + " [" + sql.toString() + "]", e);

		} catch (Exception e) {
			throw e;

		} finally {
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception e) {
				}
			}
		}

		return count;
	}

	/**
	 * 予定 テーブルの<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコード数を返す
	 *
	 * @param user_id ユーザーID
	 * @param date 日付
	 * @param seq 連番
	 * @return 一致レコード数
	 */
	public int count(String user_id, Date date, int seq) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("SELECT COUNT(*) AS CNT FROM SCHEDULE WHERE ");
			sql.append("USER_ID=? AND ");
			sql.append("DATE=? AND ");
			sql.append("SEQ=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, user_id);
			stmt.setDate(2, date);
			stmt.setInt(3, seq);

			rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("CNT");
			}

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(user_id);
			buf.append(",");
			buf.append(date);
			buf.append(",");
			buf.append(seq);
			buf.append("]");
			System.out.println(buf.toString());
			throw new SQLException(e.getMessage() + " [" + sql.toString() + "]", e);

		} catch (Exception e) {
			throw e;

		} finally {
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception e) {
				}
			}
		}

		return count;
	}

	/**
	 * 予定 テーブルの<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコード数を返す
	 *
	 * @param user_id ユーザーID
	 * @param date 日付
	 * @return 一致レコード数
	 */
	public int count(String user_id, Date date) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("SELECT COUNT(*) AS CNT FROM SCHEDULE WHERE ");
			sql.append("USER_ID=? AND ");
			sql.append("DATE=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, user_id);
			stmt.setDate(2, date);

			rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("CNT");
			}

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(user_id);
			buf.append(",");
			buf.append(date);
			buf.append("]");
			System.out.println(buf.toString());
			throw new SQLException(e.getMessage() + " [" + sql.toString() + "]", e);

		} catch (Exception e) {
			throw e;

		} finally {
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception e) {
				}
			}
		}

		return count;
	}

	/**
	 * 予定 テーブルの<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコード数を返す
	 *
	 * @param user_id ユーザーID
	 * @return 一致レコード数
	 */
	public int count(String user_id) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("SELECT COUNT(*) AS CNT FROM SCHEDULE WHERE ");
			sql.append("USER_ID=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, user_id);

			rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("CNT");
			}

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(user_id);
			buf.append(",");
			buf.append("]");
			System.out.println(buf.toString());
			throw new SQLException(e.getMessage() + " [" + sql.toString() + "]", e);

		} catch (Exception e) {
			throw e;

		} finally {
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception e) {
				}
			}
		}

		return count;
	}

	/**
	 * 予定 テーブルの<BR>
	 * WHERE文字列に一致するレコード数を返す
	 *
	 * @param where WHERE句文字列
	 * @return 一致レコード数
	 */
	public int countWhere(String where) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("SELECT COUNT(*) AS CNT FROM SCHEDULE WHERE ");
			sql.append(where);

			stmt = dbConn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("CNT");
			}

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Where = [");
			buf.append(where);
			buf.append("]");
			System.out.println(buf.toString());
			throw new SQLException(e.getMessage() + " [" + sql.toString() + "]", e);

		} catch (Exception e) {
			throw e;

		} finally {
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception e) {
				}
			}
		}

		return count;
	}
}
