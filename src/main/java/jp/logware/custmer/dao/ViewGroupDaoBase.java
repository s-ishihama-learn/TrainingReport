package jp.logware.custmer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.logware.custmer.entity.ViewGroupEntity;

/**
 * データベースアクセスクラス<br>
 * 表示グループ テーブル
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class ViewGroupDaoBase {
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
	public ViewGroupDaoBase(Connection con) {
		dbConn = con;
	}

	/**
	 * 表示グループ テーブルから<BR>
	 * 全てのレコードを取得し、Listクラスに<BR>
	 * ViewGroupEntityクラスをセットして返す
	 *
	 * @return Listクラス
	 */
	public List<ViewGroupEntity> getList() throws SQLException, Exception {
		return getListSort(null);
	}

	/**
	 * 表示グループ テーブルから<BR>
	 * 全てのレコードを取得し、Listクラスにソート処理して<BR>
	 * ViewGroupEntityクラスをセットして返す
	 *
	 * @param order ORDER句文字列
	 * @return Listクラス
	 */
	public List<ViewGroupEntity> getListSort(String order) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		List<ViewGroupEntity> list = new ArrayList<ViewGroupEntity>();

		try {
			sql.append("SELECT ");
			sql.append("USER_ID,");
			sql.append("VIEW_USER_ID,");
			sql.append("IDATE ");
			sql.append("FROM VIEW_GROUP");
			if (order != null && order.length() > 0) {
				sql.append(" ORDER BY ");
				sql.append(order);
			}

			stmt = dbConn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();
			while (rs.next()) {
				ViewGroupEntity value = new ViewGroupEntity();
				value.setUserId(rs.getString("USER_ID"));
				value.setViewUserId(rs.getString("VIEW_USER_ID"));
				value.setIdate(rs.getTimestamp("IDATE"));
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
	 * 表示グループ テーブルから<BR>
	 * ViewGroupEntityクラスのキー項目に一致するレコードを取得し<BR>
	 * ViewGroupEntityクラスにセットして返す
	 *
	 * @param keyvalue ViewGroupEntityBaseクラス
	 * @return ViewGroupEntityBaseクラス
	 */
	public ViewGroupEntity getValue(ViewGroupEntity keyvalue) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		ViewGroupEntity value = null;
		try {
			sql.append("SELECT ");
			sql.append("USER_ID,");
			sql.append("VIEW_USER_ID,");
			sql.append("IDATE ");
			sql.append("FROM VIEW_GROUP ");
			sql.append("WHERE ");
			sql.append("USER_ID=? AND ");
			sql.append("VIEW_USER_ID=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, keyvalue.getUserId());
			stmt.setString(2, keyvalue.getViewUserId());

			rs = stmt.executeQuery();
			if (rs.next()) {
				value = new ViewGroupEntity();
				value.setUserId(rs.getString("USER_ID"));
				value.setViewUserId(rs.getString("VIEW_USER_ID"));
				value.setIdate(rs.getTimestamp("IDATE"));
			}

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(keyvalue.getUserId());
			buf.append(",");
			buf.append(keyvalue.getViewUserId());
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
	 * 表示グループ テーブルから<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードを取得し<BR>
	 * ViewGroupEntityクラスにセットして返す
	 *
	 * @param user_id ユーザーID
	 * @param view_user_id 表示ユーザーID
	 * @return ViewGroupEntityクラス
	 */
	public ViewGroupEntity getValue(String user_id, String view_user_id) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		ViewGroupEntity value = null;
		try {
			sql.append("SELECT ");
			sql.append("USER_ID,");
			sql.append("VIEW_USER_ID,");
			sql.append("IDATE ");
			sql.append("FROM VIEW_GROUP ");
			sql.append("WHERE ");
			sql.append("USER_ID=? AND ");
			sql.append("VIEW_USER_ID=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, user_id);
			stmt.setString(2, view_user_id);

			rs = stmt.executeQuery();
			if (rs.next()) {
				value = new ViewGroupEntity();
				value.setUserId(rs.getString("USER_ID"));
				value.setViewUserId(rs.getString("VIEW_USER_ID"));
				value.setIdate(rs.getTimestamp("IDATE"));
			}

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(user_id);
			buf.append(",");
			buf.append(view_user_id);
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
	 * 表示グループ テーブルから<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードを取得し<BR>
	 * ListクラスにViewGroupEntityクラスをセットして返す
	 *
	 * @param user_id ユーザーID
	 * @return Listクラス
	 */
	public List<ViewGroupEntity> getList(String user_id) throws SQLException, Exception {
		return getListSort(user_id, null);
	}

	/**
	 * 表示グループ テーブルから<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードをソートして取得し<BR>
	 * ListクラスにViewGroupEntityクラスをセットして返す
	 *
	 * @param user_id ユーザーID
	 * @param order ORDER句文字列
	 * @return Listクラス
	 */
	public List<ViewGroupEntity> getListSort(String user_id, String order) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		List<ViewGroupEntity> list = new ArrayList<ViewGroupEntity>();
		try {
			sql.append("SELECT ");
			sql.append("USER_ID,");
			sql.append("VIEW_USER_ID,");
			sql.append("IDATE ");
			sql.append("FROM VIEW_GROUP ");
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
				ViewGroupEntity value = new ViewGroupEntity();
				value.setUserId(rs.getString("USER_ID"));
				value.setViewUserId(rs.getString("VIEW_USER_ID"));
				value.setIdate(rs.getTimestamp("IDATE"));
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
	 * 表示グループ テーブルから<BR>
	 * WHEREを文字列として渡し、それに一致するレコードを取得し<BR>
	 * ListクラスにViewGroupEntityクラスをセットして返す
	 *
	 * @param where WHERE句文字列
	 * @return Listクラス
	 */
	public List<ViewGroupEntity> getListWhere(String where) throws SQLException, Exception {
		return getListWhereSort(where, null);
	}

	/**
	 * 表示グループ テーブルから<BR>
	 * WHEREを文字列として渡し、それに一致するレコードをソートして取得し<BR>
	 * ListクラスにViewGroupEntityクラスをセットして返す
	 *
	 * @param where WHERE句文字列
	 * @param order ORDER句文字列
	 * @return Listクラス
	 */
	public List<ViewGroupEntity> getListWhereSort(String where, String order) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		List<ViewGroupEntity> list = new ArrayList<ViewGroupEntity>();
		try {
			sql.append("SELECT ");
			sql.append("USER_ID,");
			sql.append("VIEW_USER_ID,");
			sql.append("IDATE ");
			sql.append("FROM VIEW_GROUP ");
			sql.append("WHERE ");
			sql.append(where);
			if (order != null && order.length() > 0) {
				sql.append(" ORDER BY ");
				sql.append(order);
			}

			stmt = dbConn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();
			while (rs.next()) {
				ViewGroupEntity value = new ViewGroupEntity();
				value.setUserId(rs.getString("USER_ID"));
				value.setViewUserId(rs.getString("VIEW_USER_ID"));
				value.setIdate(rs.getTimestamp("IDATE"));
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
	 * 表示グループ テーブルに<BR>
	 * ViewGroupEntityBaseクラスを新規登録する
	 *
	 * @param value ViewGroupEntityクラス
	 * @return 登録レコード数
	 */
	public int registValue(ViewGroupEntity value) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("INSERT INTO VIEW_GROUP( ");
			sql.append("USER_ID,");
			sql.append("VIEW_USER_ID,");
			sql.append("IDATE ");
			sql.append(") VALUES( ?,?,? )");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, value.getUserId());
			stmt.setString(2, value.getViewUserId());
			stmt.setTimestamp(3, value.getIdate());

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(value.getUserId());
			buf.append(",");
			buf.append(value.getViewUserId());
			buf.append(",");
			buf.append(value.getIdate());
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
	 * 表示グループ テーブルに<BR>
	 * ViewGroupEntityBaseクラスの値で更新する
	 *
	 * @param value ViewGroupEntityクラス
	 * @return 登録レコード数
	 */
	public int updateValue(ViewGroupEntity value) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("UPDATE VIEW_GROUP SET ");
			sql.append("IDATE=? ");
			sql.append("WHERE ");
			sql.append("USER_ID=? AND ");
			sql.append("VIEW_USER_ID=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setTimestamp(1, value.getIdate());
			stmt.setString(2, value.getUserId());
			stmt.setString(3, value.getViewUserId());

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(value.getIdate());
			buf.append(",");
			buf.append(value.getUserId());
			buf.append(",");
			buf.append(value.getViewUserId());
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
	 * 表示グループ テーブルに<BR>
	 * ViewGroupEntityBaseクラスを登録する<BR>
	 * 既にレコードが存在する場合はUPDATE、存在しない場合はINSERTする
	 *
	 * @param value ViewGroupEntityクラス
	 * @return 更新レコード数
	 */
	public int setValue(ViewGroupEntity value) throws SQLException, Exception {
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
	 * 表示グループ テーブルに<BR>
	 * Listクラスに格納したViewGroupEntityクラスを新規登録する
	 *
	 * @return 更新レコード数
	 */
	public int registList(List<ViewGroupEntity> list) throws SQLException, Exception {
		int count = 0;
		try {
			Iterator<ViewGroupEntity> it = list.iterator();
			while (it.hasNext()) {
				ViewGroupEntity value = (ViewGroupEntity) it.next();
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
	 * 表示グループ テーブルに<BR>
	 * Listクラスに格納したViewGroupEntityクラスの値で更新する<BR>
	 *
	 * @return 更新レコード数
	 */
	public int updateList(List<ViewGroupEntity> list) throws SQLException, Exception {
		int count = 0;
		try {
			Iterator<ViewGroupEntity> it = list.iterator();
			while (it.hasNext()) {
				ViewGroupEntity value = (ViewGroupEntity) it.next();
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
	 * 表示グループ テーブルに<BR>
	 * Listクラスに格納したViewGroupEntityBaseクラスを登録する<BR>
	 * 既にレコードが存在する場合はUPDATE、存在しない場合はINSERTする
	 *
	 * @return 更新レコード数
	 */
	public int setList(List<ViewGroupEntity> list) throws SQLException, Exception {
		int count = 0;
		try {
			Iterator<ViewGroupEntity> it = list.iterator();
			while (it.hasNext()) {
				ViewGroupEntity value = (ViewGroupEntity) it.next();
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
	 * 表示グループ テーブルの<BR>
	 * ViewGroupEntityBaseクラスのキー項目に一致するレコードを削除する
	 *
	 * @param keyvalue ViewGroupEntityクラス
	 * @return 削除レコード数
	 */
	public int removeValue(ViewGroupEntity keyvalue) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("DELETE FROM VIEW_GROUP WHERE ");
			sql.append("USER_ID=? AND ");
			sql.append("VIEW_USER_ID=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, keyvalue.getUserId());
			stmt.setString(2, keyvalue.getViewUserId());

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(keyvalue.getUserId());
			buf.append(",");
			buf.append(keyvalue.getViewUserId());
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
	 * 表示グループ テーブルの<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードを削除する
	 *
	 * @param user_id ユーザーID
	 * @param view_user_id 表示ユーザーID
	 * @return 削除レコード数
	 */
	public int removeValue(String user_id, String view_user_id) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("DELETE FROM VIEW_GROUP WHERE ");
			sql.append("USER_ID=? AND ");
			sql.append("VIEW_USER_ID=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, user_id);
			stmt.setString(2, view_user_id);

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(user_id);
			buf.append(",");
			buf.append(view_user_id);
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
	 * 表示グループ テーブルの<BR>
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
			sql.append("DELETE FROM VIEW_GROUP WHERE ");
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
	 * 表示グループ テーブルの<BR>
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
			sql.append("DELETE FROM VIEW_GROUP WHERE ");
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
	 * 表示グループ テーブルの<BR>
	 * 全レコードを削除する
	 *
	 * @return 削除レコード数
	 */
	public int removeAll() throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("DELETE FROM VIEW_GROUP");

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
	 * 表示グループ テーブルの<BR>
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
			sql.append("SELECT COUNT(*) AS CNT FROM VIEW_GROUP");

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
	 * 表示グループ テーブルの<BR>
	 * ViewGroupEntityBaseクラスのキー項目に一致するレコードを取得し<BR>
	 * そのレコード数を返す
	 *
	 * @param keyvalue ViewGroupEntityクラス
	 * @return 一致レコード数
	 */
	public int count(ViewGroupEntity keyvalue) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("SELECT COUNT(*) AS CNT FROM VIEW_GROUP");
			sql.append(" WHERE ");
			sql.append("USER_ID=? AND ");
			sql.append("VIEW_USER_ID=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, keyvalue.getUserId());
			stmt.setString(2, keyvalue.getViewUserId());

			rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("CNT");
			}

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(keyvalue.getUserId());
			buf.append(",");
			buf.append(keyvalue.getViewUserId());
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
	 * 表示グループ テーブルの<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコード数を返す
	 *
	 * @param user_id ユーザーID
	 * @param view_user_id 表示ユーザーID
	 * @return 一致レコード数
	 */
	public int count(String user_id, String view_user_id) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("SELECT COUNT(*) AS CNT FROM VIEW_GROUP WHERE ");
			sql.append("USER_ID=? AND ");
			sql.append("VIEW_USER_ID=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, user_id);
			stmt.setString(2, view_user_id);

			rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("CNT");
			}

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(user_id);
			buf.append(",");
			buf.append(view_user_id);
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
	 * 表示グループ テーブルの<BR>
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
			sql.append("SELECT COUNT(*) AS CNT FROM VIEW_GROUP WHERE ");
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
	 * 表示グループ テーブルの<BR>
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
			sql.append("SELECT COUNT(*) AS CNT FROM VIEW_GROUP WHERE ");
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
