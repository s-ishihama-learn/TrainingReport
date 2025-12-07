package jp.logware.custmer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.logware.custmer.entity.InfoEntity;

/**
 * データベースアクセスクラス<br>
 * お知らせ テーブル
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class InfoDaoBase {
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
	public InfoDaoBase(Connection con) {
		dbConn = con;
	}

	/**
	 * お知らせ テーブルから<BR>
	 * 全てのレコードを取得し、Listクラスに<BR>
	 * InfoEntityクラスをセットして返す
	 *
	 * @return Listクラス
	 */
	public List<InfoEntity> getList() throws SQLException, Exception {
		return getListSort(null);
	}

	/**
	 * お知らせ テーブルから<BR>
	 * 全てのレコードを取得し、Listクラスにソート処理して<BR>
	 * InfoEntityクラスをセットして返す
	 *
	 * @param order ORDER句文字列
	 * @return Listクラス
	 */
	public List<InfoEntity> getListSort(String order) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		List<InfoEntity> list = new ArrayList<InfoEntity>();

		try {
			sql.append("SELECT ");
			sql.append("SEQ,");
			sql.append("DATE,");
			sql.append("BODY,");
			sql.append("LIMIT_DATE,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM INFO");
			if (order != null && order.length() > 0) {
				sql.append(" ORDER BY ");
				sql.append(order);
			}

			stmt = dbConn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();
			while (rs.next()) {
				InfoEntity value = new InfoEntity();
				value.setSeq(rs.getInt("SEQ"));
				value.setDate(rs.getDate("DATE"));
				value.setBody(rs.getString("BODY"));
				value.setLimitDate(rs.getDate("LIMIT_DATE"));
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
	 * お知らせ テーブルから<BR>
	 * InfoEntityクラスのキー項目に一致するレコードを取得し<BR>
	 * InfoEntityクラスにセットして返す
	 *
	 * @param keyvalue InfoEntityBaseクラス
	 * @return InfoEntityBaseクラス
	 */
	public InfoEntity getValue(InfoEntity keyvalue) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		InfoEntity value = null;
		try {
			sql.append("SELECT ");
			sql.append("SEQ,");
			sql.append("DATE,");
			sql.append("BODY,");
			sql.append("LIMIT_DATE,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM INFO ");
			sql.append("WHERE ");
			sql.append("SEQ=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setInt(1, keyvalue.getSeq());

			rs = stmt.executeQuery();
			if (rs.next()) {
				value = new InfoEntity();
				value.setSeq(rs.getInt("SEQ"));
				value.setDate(rs.getDate("DATE"));
				value.setBody(rs.getString("BODY"));
				value.setLimitDate(rs.getDate("LIMIT_DATE"));
				value.setIdate(rs.getTimestamp("IDATE"));
				value.setUdate(rs.getTimestamp("UDATE"));
			}

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
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
	 * お知らせ テーブルから<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードを取得し<BR>
	 * InfoEntityクラスにセットして返す
	 *
	 * @param date 表示登録日
	 * @param seq 連番
	 * @return InfoEntityクラス
	 */
	public InfoEntity getValue(int seq) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		InfoEntity value = null;
		try {
			sql.append("SELECT ");
			sql.append("SEQ,");
			sql.append("DATE,");
			sql.append("BODY,");
			sql.append("LIMIT_DATE,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM INFO ");
			sql.append("WHERE ");
			sql.append("SEQ=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setInt(1, seq);

			rs = stmt.executeQuery();
			if (rs.next()) {
				value = new InfoEntity();
				value.setSeq(rs.getInt("SEQ"));
				value.setDate(rs.getDate("DATE"));
				value.setBody(rs.getString("BODY"));
				value.setLimitDate(rs.getDate("LIMIT_DATE"));
				value.setIdate(rs.getTimestamp("IDATE"));
				value.setUdate(rs.getTimestamp("UDATE"));
			}

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
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
	 * お知らせ テーブルから<BR>
	 * WHEREを文字列として渡し、それに一致するレコードを取得し<BR>
	 * ListクラスにInfoEntityクラスをセットして返す
	 *
	 * @param where WHERE句文字列
	 * @return Listクラス
	 */
	public List<InfoEntity> getListWhere(String where) throws SQLException, Exception {
		return getListWhereSort(where, null);
	}

	/**
	 * お知らせ テーブルから<BR>
	 * WHEREを文字列として渡し、それに一致するレコードをソートして取得し<BR>
	 * ListクラスにInfoEntityクラスをセットして返す
	 *
	 * @param where WHERE句文字列
	 * @param order ORDER句文字列
	 * @return Listクラス
	 */
	public List<InfoEntity> getListWhereSort(String where, String order) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		List<InfoEntity> list = new ArrayList<InfoEntity>();
		try {
			sql.append("SELECT ");
			sql.append("SEQ,");
			sql.append("DATE,");
			sql.append("BODY,");
			sql.append("LIMIT_DATE,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM INFO ");
			sql.append("WHERE ");
			sql.append(where);
			if (order != null && order.length() > 0) {
				sql.append(" ORDER BY ");
				sql.append(order);
			}

			stmt = dbConn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();
			while (rs.next()) {
				InfoEntity value = new InfoEntity();
				value.setSeq(rs.getInt("SEQ"));
				value.setDate(rs.getDate("DATE"));
				value.setBody(rs.getString("BODY"));
				value.setLimitDate(rs.getDate("LIMIT_DATE"));
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
	 * お知らせ テーブルに<BR>
	 * InfoEntityBaseクラスを新規登録する
	 *
	 * @param value InfoEntityクラス
	 * @return 登録レコード数
	 */
	public int registValue(InfoEntity value) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("INSERT INTO INFO( ");
			sql.append("SEQ,");
			sql.append("DATE,");
			sql.append("BODY,");
			sql.append("LIMIT_DATE,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append(") VALUES( ?,?,?,?,?,? )");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setInt(1, value.getSeq());
			stmt.setDate(2, value.getDate());
			stmt.setString(3, value.getBody());
			stmt.setDate(4, value.getLimitDate());
			stmt.setTimestamp(5, value.getIdate());
			stmt.setTimestamp(6, value.getUdate());

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(value.getSeq());
			buf.append(",");
			buf.append(value.getDate());
			buf.append(",");
			buf.append(value.getBody());
			buf.append(",");
			buf.append(value.getLimitDate());
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
	 * お知らせ テーブルに<BR>
	 * InfoEntityBaseクラスの値で更新する
	 *
	 * @param value InfoEntityクラス
	 * @return 登録レコード数
	 */
	public int updateValue(InfoEntity value) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("UPDATE INFO SET ");
			sql.append("DATE=?,");
			sql.append("BODY=?,");
			sql.append("LIMIT_DATE=?,");
			sql.append("IDATE=?,");
			sql.append("UDATE=? ");
			sql.append("WHERE ");
			sql.append("SEQ=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setDate(1, value.getDate());
			stmt.setString(2, value.getBody());
			stmt.setDate(3, value.getLimitDate());
			stmt.setTimestamp(4, value.getIdate());
			stmt.setTimestamp(5, value.getUdate());
			stmt.setInt(6, value.getSeq());

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(value.getDate());
			buf.append(",");
			buf.append(value.getBody());
			buf.append(",");
			buf.append(value.getLimitDate());
			buf.append(",");
			buf.append(value.getIdate());
			buf.append(",");
			buf.append(value.getUdate());
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
	 * お知らせ テーブルに<BR>
	 * InfoEntityBaseクラスを登録する<BR>
	 * 既にレコードが存在する場合はUPDATE、存在しない場合はINSERTする
	 *
	 * @param value InfoEntityクラス
	 * @return 更新レコード数
	 */
	public int setValue(InfoEntity value) throws SQLException, Exception {
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
	 * お知らせ テーブルに<BR>
	 * Listクラスに格納したInfoEntityクラスを新規登録する
	 *
	 * @return 更新レコード数
	 */
	public int registList(List<InfoEntity> list) throws SQLException, Exception {
		int count = 0;
		try {
			Iterator<InfoEntity> it = list.iterator();
			while (it.hasNext()) {
				InfoEntity value = (InfoEntity) it.next();
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
	 * お知らせ テーブルに<BR>
	 * Listクラスに格納したInfoEntityクラスの値で更新する<BR>
	 *
	 * @return 更新レコード数
	 */
	public int updateList(List<InfoEntity> list) throws SQLException, Exception {
		int count = 0;
		try {
			Iterator<InfoEntity> it = list.iterator();
			while (it.hasNext()) {
				InfoEntity value = (InfoEntity) it.next();
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
	 * お知らせ テーブルに<BR>
	 * Listクラスに格納したInfoEntityBaseクラスを登録する<BR>
	 * 既にレコードが存在する場合はUPDATE、存在しない場合はINSERTする
	 *
	 * @return 更新レコード数
	 */
	public int setList(List<InfoEntity> list) throws SQLException, Exception {
		int count = 0;
		try {
			Iterator<InfoEntity> it = list.iterator();
			while (it.hasNext()) {
				InfoEntity value = (InfoEntity) it.next();
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
	 * お知らせ テーブルの<BR>
	 * InfoEntityBaseクラスのキー項目に一致するレコードを削除する
	 *
	 * @param keyvalue InfoEntityクラス
	 * @return 削除レコード数
	 */
	public int removeValue(InfoEntity keyvalue) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("DELETE FROM INFO WHERE ");
			sql.append("SEQ=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setInt(1, keyvalue.getSeq());

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
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
	 * お知らせ テーブルの<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードを削除する
	 *
	 * @param seq 連番
	 * @return 削除レコード数
	 */
	public int removeValue(int seq) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("DELETE FROM INFO WHERE ");
			sql.append("SEQ=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setInt(1, seq);

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
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
	 * お知らせ テーブルの<BR>
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
			sql.append("DELETE FROM INFO WHERE ");
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
	 * お知らせ テーブルの<BR>
	 * 全レコードを削除する
	 *
	 * @return 削除レコード数
	 */
	public int removeAll() throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("DELETE FROM INFO");

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
	 * お知らせ テーブルの<BR>
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
			sql.append("SELECT COUNT(*) AS CNT FROM INFO");

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
	 * お知らせ テーブルの<BR>
	 * InfoEntityBaseクラスのキー項目に一致するレコードを取得し<BR>
	 * そのレコード数を返す
	 *
	 * @param keyvalue InfoEntityクラス
	 * @return 一致レコード数
	 */
	public int count(InfoEntity keyvalue) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("SELECT COUNT(*) AS CNT FROM INFO");
			sql.append(" WHERE ");
			sql.append("SEQ=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setInt(1, keyvalue.getSeq());

			rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("CNT");
			}

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
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
	 * お知らせ テーブルの<BR>
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
			sql.append("SELECT COUNT(*) AS CNT FROM INFO WHERE ");
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
