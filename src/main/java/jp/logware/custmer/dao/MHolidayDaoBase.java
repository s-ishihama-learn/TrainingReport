package jp.logware.custmer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.logware.custmer.common.Utility;
import jp.logware.custmer.entity.MHolidayEntity;

/**
 * データベースアクセスクラス<br>
 * 休日 テーブル
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class MHolidayDaoBase {
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
	public MHolidayDaoBase(Connection con) {
		dbConn = con;
	}

	/**
	 * 休日 テーブルから<BR>
	 * 全てのレコードを取得し、Listクラスに<BR>
	 * MHolidayEntityクラスをセットして返す
	 *
	 * @return Listクラス
	 */
	public List<MHolidayEntity> getList() throws SQLException, Exception {
		return getListSort(null);
	}

	/**
	 * 休日 テーブルから<BR>
	 * 全てのレコードを取得し、Listクラスにソート処理して<BR>
	 * MHolidayEntityクラスをセットして返す
	 *
	 * @param order ORDER句文字列
	 * @return Listクラス
	 */
	public List<MHolidayEntity> getListSort(String order) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		List<MHolidayEntity> list = new ArrayList<MHolidayEntity>();

		try {
			sql.append("SELECT ");
			sql.append("DATE,");
			sql.append("NOTE,");
			sql.append("KIND,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM M_HOLIDAY");
			if (order != null && order.length() > 0) {
				sql.append(" ORDER BY ");
				sql.append(order);
			}

			stmt = dbConn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();
			while (rs.next()) {
				MHolidayEntity value = new MHolidayEntity();
				value.setDate(rs.getString("DATE"));
				value.setNote(rs.getString("NOTE"));
				value.setKind(rs.getString("KIND"));
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
	 * 休日 テーブルから<BR>
	 * MHolidayEntityクラスのキー項目に一致するレコードを取得し<BR>
	 * MHolidayEntityクラスにセットして返す
	 *
	 * @param keyvalue MHolidayEntityBaseクラス
	 * @return MHolidayEntityBaseクラス
	 */
	public MHolidayEntity getValue(MHolidayEntity keyvalue) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		MHolidayEntity value = null;
		try {
			sql.append("SELECT ");
			sql.append("DATE,");
			sql.append("NOTE,");
			sql.append("KIND,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM M_HOLIDAY ");
			sql.append("WHERE ");
			sql.append("DATE=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setDate(1, Utility.toDate(keyvalue.getDate()));

			rs = stmt.executeQuery();
			if (rs.next()) {
				value = new MHolidayEntity();
				value.setDate(rs.getString("DATE"));
				value.setNote(rs.getString("NOTE"));
				value.setKind(rs.getString("KIND"));
				value.setIdate(rs.getTimestamp("IDATE"));
				value.setUdate(rs.getTimestamp("UDATE"));
			}

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(Utility.toDate(keyvalue.getDate()));
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
	 * 休日 テーブルから<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードを取得し<BR>
	 * MHolidayEntityクラスにセットして返す
	 *
	 * @param date 日付
	 * @return MHolidayEntityクラス
	 */
	public MHolidayEntity getValue(String date) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		MHolidayEntity value = null;
		try {
			sql.append("SELECT ");
			sql.append("DATE,");
			sql.append("NOTE,");
			sql.append("KIND,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM M_HOLIDAY ");
			sql.append("WHERE ");
			sql.append("DATE=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setDate(1, Utility.toDate(date));

			rs = stmt.executeQuery();
			if (rs.next()) {
				value = new MHolidayEntity();
				value.setDate(rs.getString("DATE"));
				value.setNote(rs.getString("NOTE"));
				value.setKind(rs.getString("KIND"));
				value.setIdate(rs.getTimestamp("IDATE"));
				value.setUdate(rs.getTimestamp("UDATE"));
			}

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(Utility.toDate(date));
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
	 * 休日 テーブルから<BR>
	 * WHEREを文字列として渡し、それに一致するレコードを取得し<BR>
	 * ListクラスにMHolidayEntityクラスをセットして返す
	 *
	 * @param where WHERE句文字列
	 * @return Listクラス
	 */
	public List<MHolidayEntity> getListWhere(String where) throws SQLException, Exception {
		return getListWhereSort(where, null);
	}

	/**
	 * 休日 テーブルから<BR>
	 * WHEREを文字列として渡し、それに一致するレコードをソートして取得し<BR>
	 * ListクラスにMHolidayEntityクラスをセットして返す
	 *
	 * @param where WHERE句文字列
	 * @param order ORDER句文字列
	 * @return Listクラス
	 */
	public List<MHolidayEntity> getListWhereSort(String where, String order) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		List<MHolidayEntity> list = new ArrayList<MHolidayEntity>();
		try {
			sql.append("SELECT ");
			sql.append("DATE,");
			sql.append("NOTE,");
			sql.append("KIND,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM M_HOLIDAY ");
			sql.append("WHERE ");
			sql.append(where);
			if (order != null && order.length() > 0) {
				sql.append(" ORDER BY ");
				sql.append(order);
			}

			stmt = dbConn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();
			while (rs.next()) {
				MHolidayEntity value = new MHolidayEntity();
				value.setDate(rs.getString("DATE"));
				value.setNote(rs.getString("NOTE"));
				value.setKind(rs.getString("KIND"));
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
	 * 休日 テーブルに<BR>
	 * MHolidayEntityBaseクラスを新規登録する
	 *
	 * @param value MHolidayEntityクラス
	 * @return 登録レコード数
	 */
	public int registValue(MHolidayEntity value) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("INSERT INTO M_HOLIDAY( ");
			sql.append("DATE,");
			sql.append("NOTE,");
			sql.append("KIND,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append(") VALUES( ?,?,?,?,? )");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setDate(1, Utility.toDate(value.getDate()));
			stmt.setString(2, value.getNote());
			stmt.setString(3, value.getKind());
			stmt.setTimestamp(4, value.getIdate());
			stmt.setTimestamp(5, value.getUdate());

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(Utility.toDate(value.getDate()));
			buf.append(",");
			buf.append(value.getNote());
			buf.append(",");
			buf.append(value.getKind());
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
	 * 休日 テーブルに<BR>
	 * MHolidayEntityBaseクラスの値で更新する
	 *
	 * @param value MHolidayEntityクラス
	 * @return 登録レコード数
	 */
	public int updateValue(MHolidayEntity value) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("UPDATE M_HOLIDAY SET ");
			sql.append("NOTE=?,");
			sql.append("KIND=?,");
			sql.append("IDATE=?,");
			sql.append("UDATE=? ");
			sql.append("WHERE ");
			sql.append("DATE=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, value.getNote());
			stmt.setString(2, value.getKind());
			stmt.setTimestamp(3, value.getIdate());
			stmt.setTimestamp(4, value.getUdate());
			stmt.setDate(5, Utility.toDate(value.getDate()));

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(value.getNote());
			buf.append(",");
			buf.append(value.getKind());
			buf.append(",");
			buf.append(value.getIdate());
			buf.append(",");
			buf.append(value.getUdate());
			buf.append(",");
			buf.append(Utility.toDate(value.getDate()));
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
	 * 休日 テーブルに<BR>
	 * MHolidayEntityBaseクラスを登録する<BR>
	 * 既にレコードが存在する場合はUPDATE、存在しない場合はINSERTする
	 *
	 * @param value MHolidayEntityクラス
	 * @return 更新レコード数
	 */
	public int setValue(MHolidayEntity value) throws SQLException, Exception {
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
	 * 休日 テーブルに<BR>
	 * Listクラスに格納したMHolidayEntityクラスを新規登録する
	 *
	 * @return 更新レコード数
	 */
	public int registList(List<MHolidayEntity> list) throws SQLException, Exception {
		int count = 0;
		try {
			Iterator<MHolidayEntity> it = list.iterator();
			while (it.hasNext()) {
				MHolidayEntity value = (MHolidayEntity) it.next();
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
	 * 休日 テーブルに<BR>
	 * Listクラスに格納したMHolidayEntityクラスの値で更新する<BR>
	 *
	 * @return 更新レコード数
	 */
	public int updateList(List<MHolidayEntity> list) throws SQLException, Exception {
		int count = 0;
		try {
			Iterator<MHolidayEntity> it = list.iterator();
			while (it.hasNext()) {
				MHolidayEntity value = (MHolidayEntity) it.next();
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
	 * 休日 テーブルに<BR>
	 * Listクラスに格納したMHolidayEntityBaseクラスを登録する<BR>
	 * 既にレコードが存在する場合はUPDATE、存在しない場合はINSERTする
	 *
	 * @return 更新レコード数
	 */
	public int setList(List<MHolidayEntity> list) throws SQLException, Exception {
		int count = 0;
		try {
			Iterator<MHolidayEntity> it = list.iterator();
			while (it.hasNext()) {
				MHolidayEntity value = (MHolidayEntity) it.next();
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
	 * 休日 テーブルの<BR>
	 * MHolidayEntityBaseクラスのキー項目に一致するレコードを削除する
	 *
	 * @param keyvalue MHolidayEntityクラス
	 * @return 削除レコード数
	 */
	public int removeValue(MHolidayEntity keyvalue) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("DELETE FROM M_HOLIDAY WHERE ");
			sql.append("DATE=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setDate(1, Utility.toDate(keyvalue.getDate()));

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(Utility.toDate(keyvalue.getDate()));
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
	 * 休日 テーブルの<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードを削除する
	 *
	 * @param date 日付
	 * @return 削除レコード数
	 */
	public int removeValue(String date) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("DELETE FROM M_HOLIDAY WHERE ");
			sql.append("DATE=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setDate(1, Utility.toDate(date));

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(Utility.toDate(date));
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
	 * 休日 テーブルの<BR>
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
			sql.append("DELETE FROM M_HOLIDAY WHERE ");
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
	 * 休日 テーブルの<BR>
	 * 全レコードを削除する
	 *
	 * @return 削除レコード数
	 */
	public int removeAll() throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("DELETE FROM M_HOLIDAY");

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
	 * 休日 テーブルの<BR>
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
			sql.append("SELECT COUNT(*) AS CNT FROM M_HOLIDAY");

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
	 * 休日 テーブルの<BR>
	 * MHolidayEntityBaseクラスのキー項目に一致するレコードを取得し<BR>
	 * そのレコード数を返す
	 *
	 * @param keyvalue MHolidayEntityクラス
	 * @return 一致レコード数
	 */
	public int count(MHolidayEntity keyvalue) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("SELECT COUNT(*) AS CNT FROM M_HOLIDAY");
			sql.append(" WHERE ");
			sql.append("DATE=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setDate(1, Utility.toDate(keyvalue.getDate()));

			rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("CNT");
			}

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(Utility.toDate(keyvalue.getDate()));
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
	 * 休日 テーブルの<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコード数を返す
	 *
	 * @param date ユーザーID
	 * @return 一致レコード数
	 */
	public int count(String date) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("SELECT COUNT(*) AS CNT FROM M_HOLIDAY WHERE ");
			sql.append("DATE=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setDate(1, Utility.toDate(date));

			rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("CNT");
			}

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(Utility.toDate(date));
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
	 * 休日 テーブルの<BR>
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
			sql.append("SELECT COUNT(*) AS CNT FROM M_HOLIDAY WHERE ");
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
