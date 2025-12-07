package jp.logware.custmer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.logware.custmer.entity.MDeptEntity;

/**
 * データベースアクセスクラス<br>
 * 部署 テーブル
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class MDeptDaoBase {
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
	public MDeptDaoBase(Connection con) {
		dbConn = con;
	}

	/**
	 * 部署 テーブルから<BR>
	 * 全てのレコードを取得し、Listクラスに<BR>
	 * MDeptEntityクラスをセットして返す
	 *
	 * @return Listクラス
	 */
	public List<MDeptEntity> getList() throws SQLException, Exception {
		return getListSort(null);
	}

	/**
	 * 部署 テーブルから<BR>
	 * 全てのレコードを取得し、Listクラスにソート処理して<BR>
	 * MDeptEntityクラスをセットして返す
	 *
	 * @param order ORDER句文字列
	 * @return Listクラス
	 */
	public List<MDeptEntity> getListSort(String order) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		List<MDeptEntity> list = new ArrayList<MDeptEntity>();

		try {
			sql.append("SELECT ");
			sql.append("DEPT_CODE,");
			sql.append("DEPT_NAME,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM M_DEPT");
			if (order != null && order.length() > 0) {
				sql.append(" ORDER BY ");
				sql.append(order);
			}

			stmt = dbConn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();
			while (rs.next()) {
				MDeptEntity value = new MDeptEntity();
				value.setDeptCode(rs.getString("DEPT_CODE"));
				value.setDeptName(rs.getString("DEPT_NAME"));
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
	 * 部署 テーブルから<BR>
	 * MDeptEntityクラスのキー項目に一致するレコードを取得し<BR>
	 * MDeptEntityクラスにセットして返す
	 *
	 * @param keyvalue MDeptEntityBaseクラス
	 * @return MDeptEntityBaseクラス
	 */
	public MDeptEntity getValue(MDeptEntity keyvalue) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		MDeptEntity value = null;
		try {
			sql.append("SELECT ");
			sql.append("DEPT_CODE,");
			sql.append("DEPT_NAME,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM M_DEPT ");
			sql.append("WHERE ");
			sql.append("DEPT_CODE=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, keyvalue.getDeptCode());

			rs = stmt.executeQuery();
			if (rs.next()) {
				value = new MDeptEntity();
				value.setDeptCode(rs.getString("DEPT_CODE"));
				value.setDeptName(rs.getString("DEPT_NAME"));
				value.setIdate(rs.getTimestamp("IDATE"));
				value.setUdate(rs.getTimestamp("UDATE"));
			}

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(keyvalue.getDeptCode());
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
	 * 部署 テーブルから<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードを取得し<BR>
	 * MDeptEntityクラスにセットして返す
	 *
	 * @param dept_code 部署コード
	 * @return MDeptEntityクラス
	 */
	public MDeptEntity getValue(String dept_code) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		MDeptEntity value = null;
		try {
			sql.append("SELECT ");
			sql.append("DEPT_CODE,");
			sql.append("DEPT_NAME,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM M_DEPT ");
			sql.append("WHERE ");
			sql.append("DEPT_CODE=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, dept_code);

			rs = stmt.executeQuery();
			if (rs.next()) {
				value = new MDeptEntity();
				value.setDeptCode(rs.getString("DEPT_CODE"));
				value.setDeptName(rs.getString("DEPT_NAME"));
				value.setIdate(rs.getTimestamp("IDATE"));
				value.setUdate(rs.getTimestamp("UDATE"));
			}

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(dept_code);
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
	 * 部署 テーブルから<BR>
	 * WHEREを文字列として渡し、それに一致するレコードを取得し<BR>
	 * ListクラスにMDeptEntityクラスをセットして返す
	 *
	 * @param where WHERE句文字列
	 * @return Listクラス
	 */
	public List<MDeptEntity> getListWhere(String where) throws SQLException, Exception {
		return getListWhereSort(where, null);
	}

	/**
	 * 部署 テーブルから<BR>
	 * WHEREを文字列として渡し、それに一致するレコードをソートして取得し<BR>
	 * ListクラスにMDeptEntityクラスをセットして返す
	 *
	 * @param where WHERE句文字列
	 * @param order ORDER句文字列
	 * @return Listクラス
	 */
	public List<MDeptEntity> getListWhereSort(String where, String order) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		List<MDeptEntity> list = new ArrayList<MDeptEntity>();
		try {
			sql.append("SELECT ");
			sql.append("DEPT_CODE,");
			sql.append("DEPT_NAME,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM M_DEPT ");
			sql.append("WHERE ");
			sql.append(where);
			if (order != null && order.length() > 0) {
				sql.append(" ORDER BY ");
				sql.append(order);
			}

			stmt = dbConn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();
			while (rs.next()) {
				MDeptEntity value = new MDeptEntity();
				value.setDeptCode(rs.getString("DEPT_CODE"));
				value.setDeptName(rs.getString("DEPT_NAME"));
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
	 * 部署 テーブルに<BR>
	 * MDeptEntityBaseクラスを新規登録する
	 *
	 * @param value MDeptEntityクラス
	 * @return 登録レコード数
	 */
	public int registValue(MDeptEntity value) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("INSERT INTO M_DEPT( ");
			sql.append("DEPT_CODE,");
			sql.append("DEPT_NAME,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append(") VALUES( ?,?,?,? )");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, value.getDeptCode());
			stmt.setString(2, value.getDeptName());
			stmt.setTimestamp(3, value.getIdate());
			stmt.setTimestamp(4, value.getUdate());

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(value.getDeptCode());
			buf.append(",");
			buf.append(value.getDeptName());
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
	 * 部署 テーブルに<BR>
	 * MDeptEntityBaseクラスの値で更新する
	 *
	 * @param value MDeptEntityクラス
	 * @return 登録レコード数
	 */
	public int updateValue(MDeptEntity value) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("UPDATE M_DEPT SET ");
			sql.append("DEPT_NAME=?,");
			sql.append("IDATE=?,");
			sql.append("UDATE=? ");
			sql.append("WHERE ");
			sql.append("DEPT_CODE=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, value.getDeptName());
			stmt.setTimestamp(2, value.getIdate());
			stmt.setTimestamp(3, value.getUdate());
			stmt.setString(4, value.getDeptCode());

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(value.getDeptName());
			buf.append(",");
			buf.append(value.getIdate());
			buf.append(",");
			buf.append(value.getUdate());
			buf.append(",");
			buf.append(value.getDeptCode());
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
	 * 部署 テーブルに<BR>
	 * MDeptEntityBaseクラスを登録する<BR>
	 * 既にレコードが存在する場合はUPDATE、存在しない場合はINSERTする
	 *
	 * @param value MDeptEntityクラス
	 * @return 更新レコード数
	 */
	public int setValue(MDeptEntity value) throws SQLException, Exception {
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
	 * 部署 テーブルに<BR>
	 * Listクラスに格納したMDeptEntityクラスを新規登録する
	 *
	 * @return 更新レコード数
	 */
	public int registList(List<MDeptEntity> list) throws SQLException, Exception {
		int count = 0;
		try {
			Iterator<MDeptEntity> it = list.iterator();
			while (it.hasNext()) {
				MDeptEntity value = (MDeptEntity) it.next();
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
	 * 部署 テーブルに<BR>
	 * Listクラスに格納したMDeptEntityクラスの値で更新する<BR>
	 *
	 * @return 更新レコード数
	 */
	public int updateList(List<MDeptEntity> list) throws SQLException, Exception {
		int count = 0;
		try {
			Iterator<MDeptEntity> it = list.iterator();
			while (it.hasNext()) {
				MDeptEntity value = (MDeptEntity) it.next();
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
	 * 部署 テーブルに<BR>
	 * Listクラスに格納したMDeptEntityBaseクラスを登録する<BR>
	 * 既にレコードが存在する場合はUPDATE、存在しない場合はINSERTする
	 *
	 * @return 更新レコード数
	 */
	public int setList(List<MDeptEntity> list) throws SQLException, Exception {
		int count = 0;
		try {
			Iterator<MDeptEntity> it = list.iterator();
			while (it.hasNext()) {
				MDeptEntity value = (MDeptEntity) it.next();
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
	 * 部署 テーブルの<BR>
	 * MDeptEntityBaseクラスのキー項目に一致するレコードを削除する
	 *
	 * @param keyvalue MDeptEntityクラス
	 * @return 削除レコード数
	 */
	public int removeValue(MDeptEntity keyvalue) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("DELETE FROM M_DEPT WHERE ");
			sql.append("DEPT_CODE=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, keyvalue.getDeptCode());

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(keyvalue.getDeptCode());
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
	 * 部署 テーブルの<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードを削除する
	 *
	 * @param dept_code 部署コード
	 * @return 削除レコード数
	 */
	public int removeValue(String dept_code) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("DELETE FROM M_DEPT WHERE ");
			sql.append("DEPT_CODE=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, dept_code);

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(dept_code);
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
	 * 部署 テーブルの<BR>
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
			sql.append("DELETE FROM M_DEPT WHERE ");
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
	 * 部署 テーブルの<BR>
	 * 全レコードを削除する
	 *
	 * @return 削除レコード数
	 */
	public int removeAll() throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("DELETE FROM M_DEPT");

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
	 * 部署 テーブルの<BR>
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
			sql.append("SELECT COUNT(*) AS CNT FROM M_DEPT");

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
	 * 部署 テーブルの<BR>
	 * MDeptEntityBaseクラスのキー項目に一致するレコードを取得し<BR>
	 * そのレコード数を返す
	 *
	 * @param keyvalue MDeptEntityクラス
	 * @return 一致レコード数
	 */
	public int count(MDeptEntity keyvalue) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("SELECT COUNT(*) AS CNT FROM M_DEPT");
			sql.append(" WHERE ");
			sql.append("DEPT_CODE=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, keyvalue.getDeptCode());

			rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("CNT");
			}

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(keyvalue.getDeptCode());
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
	 * 部署 テーブルの<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコード数を返す
	 *
	 * @param dept_code 部署コード
	 * @return 一致レコード数
	 */
	public int count(String dept_code) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("SELECT COUNT(*) AS CNT FROM M_DEPT WHERE ");
			sql.append("DEPT_CODE=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, dept_code);

			rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("CNT");
			}

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(dept_code);
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
	 * 部署 テーブルの<BR>
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
			sql.append("SELECT COUNT(*) AS CNT FROM M_DEPT WHERE ");
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
