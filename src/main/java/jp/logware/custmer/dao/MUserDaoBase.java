package jp.logware.custmer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.logware.custmer.entity.MUserEntity;

/**
 * データベースアクセスクラス<br>
 * 利用者 テーブル
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class MUserDaoBase {
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
	public MUserDaoBase(Connection con) {
		dbConn = con;
	}

	/**
	 * 利用者 テーブルから<BR>
	 * 全てのレコードを取得し、Listクラスに<BR>
	 * MUserEntityクラスをセットして返す
	 *
	 * @return Listクラス
	 */
	public List<MUserEntity> getList() throws SQLException, Exception {
		return getListSort(null);
	}

	/**
	 * 利用者 テーブルから<BR>
	 * 全てのレコードを取得し、Listクラスにソート処理して<BR>
	 * MUserEntityクラスをセットして返す
	 *
	 * @param order ORDER句文字列
	 * @return Listクラス
	 */
	public List<MUserEntity> getListSort(String order) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		List<MUserEntity> list = new ArrayList<MUserEntity>();

		try {
			sql.append("SELECT ");
			sql.append("USER_ID,");
			sql.append("USER_PASS,");
			sql.append("USER_NAME,");
			sql.append("DEPT_CODE,");
			sql.append("USER_COM,");
			sql.append("MAIL_ADDR,");
			sql.append("PASS_LIMIT,");
			sql.append("DEL_FLAG,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM M_USER");
			if (order != null && order.length() > 0) {
				sql.append(" ORDER BY ");
				sql.append(order);
			}

			stmt = dbConn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();
			while (rs.next()) {
				MUserEntity value = new MUserEntity();
				value.setUserId(rs.getString("USER_ID"));
				value.setUserPass(rs.getString("USER_PASS"));
				value.setUserName(rs.getString("USER_NAME"));
				value.setDeptCode(rs.getString("DEPT_CODE"));
				value.setUserCom(rs.getString("USER_COM"));
				value.setMailAddr(rs.getString("MAIL_ADDR"));
				value.setPassLimit(rs.getTimestamp("PASS_LIMIT"));
				value.setDelFlag(rs.getString("DEL_FLAG"));
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
	 * 利用者 テーブルから<BR>
	 * MUserEntityクラスのキー項目に一致するレコードを取得し<BR>
	 * MUserEntityクラスにセットして返す
	 *
	 * @param keyvalue MUserEntityBaseクラス
	 * @return MUserEntityBaseクラス
	 */
	public MUserEntity getValue(MUserEntity keyvalue) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		MUserEntity value = null;
		try {
			sql.append("SELECT ");
			sql.append("USER_ID,");
			sql.append("USER_PASS,");
			sql.append("USER_NAME,");
			sql.append("DEPT_CODE,");
			sql.append("USER_COM,");
			sql.append("MAIL_ADDR,");
			sql.append("PASS_LIMIT,");
			sql.append("DEL_FLAG,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM M_USER ");
			sql.append("WHERE ");
			sql.append("USER_ID=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, keyvalue.getUserId());

			rs = stmt.executeQuery();
			if (rs.next()) {
				value = new MUserEntity();
				value.setUserId(rs.getString("USER_ID"));
				value.setUserPass(rs.getString("USER_PASS"));
				value.setUserName(rs.getString("USER_NAME"));
				value.setDeptCode(rs.getString("DEPT_CODE"));
				value.setUserCom(rs.getString("USER_COM"));
				value.setMailAddr(rs.getString("MAIL_ADDR"));
				value.setPassLimit(rs.getTimestamp("PASS_LIMIT"));
				value.setDelFlag(rs.getString("DEL_FLAG"));
				value.setIdate(rs.getTimestamp("IDATE"));
				value.setUdate(rs.getTimestamp("UDATE"));
			}

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(keyvalue.getUserId());
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
	 * 利用者 テーブルから<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードを取得し<BR>
	 * MUserEntityクラスにセットして返す
	 *
	 * @param user_id ユーザーID
	 * @return MUserEntityクラス
	 */
	public MUserEntity getValue(String user_id) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		MUserEntity value = null;
		try {
			sql.append("SELECT ");
			sql.append("USER_ID,");
			sql.append("USER_PASS,");
			sql.append("USER_NAME,");
			sql.append("DEPT_CODE,");
			sql.append("USER_COM,");
			sql.append("MAIL_ADDR,");
			sql.append("PASS_LIMIT,");
			sql.append("DEL_FLAG,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM M_USER ");
			sql.append("WHERE ");
			sql.append("USER_ID=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, user_id);

			rs = stmt.executeQuery();
			if (rs.next()) {
				value = new MUserEntity();
				value.setUserId(rs.getString("USER_ID"));
				value.setUserPass(rs.getString("USER_PASS"));
				value.setUserName(rs.getString("USER_NAME"));
				value.setDeptCode(rs.getString("DEPT_CODE"));
				value.setUserCom(rs.getString("USER_COM"));
				value.setMailAddr(rs.getString("MAIL_ADDR"));
				value.setPassLimit(rs.getTimestamp("PASS_LIMIT"));
				value.setDelFlag(rs.getString("DEL_FLAG"));
				value.setIdate(rs.getTimestamp("IDATE"));
				value.setUdate(rs.getTimestamp("UDATE"));
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

		return value;
	}

	/**
	 * 利用者 テーブルから<BR>
	 * WHEREを文字列として渡し、それに一致するレコードを取得し<BR>
	 * ListクラスにMUserEntityクラスをセットして返す
	 *
	 * @param where WHERE句文字列
	 * @return Listクラス
	 */
	public List<MUserEntity> getListWhere(String where) throws SQLException, Exception {
		return getListWhereSort(where, null);
	}

	/**
	 * 利用者 テーブルから<BR>
	 * WHEREを文字列として渡し、それに一致するレコードをソートして取得し<BR>
	 * ListクラスにMUserEntityクラスをセットして返す
	 *
	 * @param where WHERE句文字列
	 * @param order ORDER句文字列
	 * @return Listクラス
	 */
	public List<MUserEntity> getListWhereSort(String where, String order) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		List<MUserEntity> list = new ArrayList<MUserEntity>();
		try {
			sql.append("SELECT ");
			sql.append("USER_ID,");
			sql.append("USER_PASS,");
			sql.append("USER_NAME,");
			sql.append("DEPT_CODE,");
			sql.append("USER_COM,");
			sql.append("MAIL_ADDR,");
			sql.append("PASS_LIMIT,");
			sql.append("DEL_FLAG,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM M_USER ");
			sql.append("WHERE ");
			sql.append(where);
			if (order != null && order.length() > 0) {
				sql.append(" ORDER BY ");
				sql.append(order);
			}

			stmt = dbConn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();
			while (rs.next()) {
				MUserEntity value = new MUserEntity();
				value.setUserId(rs.getString("USER_ID"));
				value.setUserPass(rs.getString("USER_PASS"));
				value.setUserName(rs.getString("USER_NAME"));
				value.setDeptCode(rs.getString("DEPT_CODE"));
				value.setUserCom(rs.getString("USER_COM"));
				value.setMailAddr(rs.getString("MAIL_ADDR"));
				value.setPassLimit(rs.getTimestamp("PASS_LIMIT"));
				value.setDelFlag(rs.getString("DEL_FLAG"));
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
	 * 利用者 テーブルに<BR>
	 * MUserEntityBaseクラスを新規登録する
	 *
	 * @param value MUserEntityクラス
	 * @return 登録レコード数
	 */
	public int registValue(MUserEntity value) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("INSERT INTO M_USER( ");
			sql.append("USER_ID,");
			sql.append("USER_PASS,");
			sql.append("USER_NAME,");
			sql.append("DEPT_CODE,");
			sql.append("USER_COM,");
			sql.append("MAIL_ADDR,");
			sql.append("PASS_LIMIT,");
			sql.append("DEL_FLAG,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append(") VALUES( ?,?,?,?,?,?,?,?,?,? )");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, value.getUserId());
			stmt.setString(2, value.getUserPass());
			stmt.setString(3, value.getUserName());
			stmt.setString(4, value.getDeptCode());
			stmt.setString(5, value.getUserCom());
			stmt.setString(6, value.getMailAddr());
			stmt.setTimestamp(7, value.getPassLimit());
			stmt.setString(8, value.getDelFlag());
			stmt.setTimestamp(9, value.getIdate());
			stmt.setTimestamp(10, value.getUdate());

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(value.getUserId());
			buf.append(",");
			buf.append(value.getUserPass());
			buf.append(",");
			buf.append(value.getUserName());
			buf.append(",");
			buf.append(value.getDeptCode());
			buf.append(",");
			buf.append(value.getUserCom());
			buf.append(",");
			buf.append(value.getMailAddr());
			buf.append(",");
			buf.append(value.getPassLimit());
			buf.append(",");
			buf.append(value.getDelFlag());
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
	 * 利用者 テーブルに<BR>
	 * MUserEntityBaseクラスの値で更新する
	 *
	 * @param value MUserEntityクラス
	 * @return 登録レコード数
	 */
	public int updateValue(MUserEntity value) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("UPDATE M_USER SET ");
			sql.append("USER_PASS=?," );
			sql.append("USER_NAME=?,");
			sql.append("DEPT_CODE=?,");
			sql.append("USER_COM=?,");
			sql.append("MAIL_ADDR=?,");
			sql.append("PASS_LIMIT=?,");
			sql.append("DEL_FLAG=?,");
			sql.append("IDATE=?,");
			sql.append("UDATE=? ");
			sql.append("WHERE ");
			sql.append("USER_ID=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, value.getUserPass());
			stmt.setString(2, value.getUserName());
			stmt.setString(3, value.getDeptCode());
			stmt.setString(4, value.getUserCom());
			stmt.setString(5, value.getMailAddr());
			stmt.setTimestamp(6, value.getPassLimit());
			stmt.setString(7, value.getDelFlag());
			stmt.setTimestamp(8, value.getIdate());
			stmt.setTimestamp(9, value.getUdate());
			stmt.setString(10, value.getUserId());

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(value.getUserPass());
			buf.append(",");
			buf.append(value.getUserName());
			buf.append(",");
			buf.append(value.getDeptCode());
			buf.append(",");
			buf.append(value.getUserCom());
			buf.append(",");
			buf.append(value.getMailAddr());
			buf.append(",");
			buf.append(value.getPassLimit());
			buf.append(",");
			buf.append(value.getDelFlag());
			buf.append(",");
			buf.append(value.getIdate());
			buf.append(",");
			buf.append(value.getUdate());
			buf.append(",");
			buf.append(value.getUserId());
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
	 * 利用者 テーブルに<BR>
	 * MUserEntityBaseクラスを登録する<BR>
	 * 既にレコードが存在する場合はUPDATE、存在しない場合はINSERTする
	 *
	 * @param value MUserEntityクラス
	 * @return 更新レコード数
	 */
	public int setValue(MUserEntity value) throws SQLException, Exception {
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
	 * 利用者 テーブルに<BR>
	 * Listクラスに格納したMUserEntityクラスを新規登録する
	 *
	 * @return 更新レコード数
	 */
	public int registList(List<MUserEntity> list) throws SQLException, Exception {
		int count = 0;
		try {
			Iterator<MUserEntity> it = list.iterator();
			while (it.hasNext()) {
				MUserEntity value = (MUserEntity) it.next();
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
	 * 利用者 テーブルに<BR>
	 * Listクラスに格納したMUserEntityクラスの値で更新する<BR>
	 *
	 * @return 更新レコード数
	 */
	public int updateList(List<MUserEntity> list) throws SQLException, Exception {
		int count = 0;
		try {
			Iterator<MUserEntity> it = list.iterator();
			while (it.hasNext()) {
				MUserEntity value = (MUserEntity) it.next();
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
	 * 利用者 テーブルに<BR>
	 * Listクラスに格納したMUserEntityBaseクラスを登録する<BR>
	 * 既にレコードが存在する場合はUPDATE、存在しない場合はINSERTする
	 *
	 * @return 更新レコード数
	 */
	public int setList(List<MUserEntity> list) throws SQLException, Exception {
		int count = 0;
		try {
			Iterator<MUserEntity> it = list.iterator();
			while (it.hasNext()) {
				MUserEntity value = (MUserEntity) it.next();
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
	 * 利用者 テーブルの<BR>
	 * MUserEntityBaseクラスのキー項目に一致するレコードを削除する
	 *
	 * @param keyvalue MUserEntityクラス
	 * @return 削除レコード数
	 */
	public int removeValue(MUserEntity keyvalue) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("DELETE FROM M_USER WHERE ");
			sql.append("USER_ID=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, keyvalue.getUserId());

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(keyvalue.getUserId());
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
	 * 利用者 テーブルの<BR>
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
			sql.append("DELETE FROM M_USER WHERE ");
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
	 * 利用者 テーブルの<BR>
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
			sql.append("DELETE FROM M_USER WHERE ");
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
	 * 利用者 テーブルの<BR>
	 * 全レコードを削除する
	 *
	 * @return 削除レコード数
	 */
	public int removeAll() throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("DELETE FROM M_USER");

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
	 * 利用者 テーブルの<BR>
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
			sql.append("SELECT COUNT(*) AS CNT FROM M_USER");

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
	 * 利用者 テーブルの<BR>
	 * MUserEntityBaseクラスのキー項目に一致するレコードを取得し<BR>
	 * そのレコード数を返す
	 *
	 * @param keyvalue MUserEntityクラス
	 * @return 一致レコード数
	 */
	public int count(MUserEntity keyvalue) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("SELECT COUNT(*) AS CNT FROM M_USER");
			sql.append(" WHERE ");
			sql.append("USER_ID=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, keyvalue.getUserId());

			rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("CNT");
			}

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(keyvalue.getUserId());
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
	 * 利用者 テーブルの<BR>
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
			sql.append("SELECT COUNT(*) AS CNT FROM M_USER WHERE ");
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
	 * 利用者 テーブルの<BR>
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
			sql.append("SELECT COUNT(*) AS CNT FROM M_USER WHERE ");
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
