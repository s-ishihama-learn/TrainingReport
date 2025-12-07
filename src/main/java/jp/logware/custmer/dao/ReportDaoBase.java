package jp.logware.custmer.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.logware.custmer.entity.ReportEntity;

/**
 * データベースアクセスクラス<br>
 * 日報 テーブル
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class ReportDaoBase {
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
	public ReportDaoBase(Connection con) {
		dbConn = con;
	}

	/**
	 * 日報 テーブルから<BR>
	 * 全てのレコードを取得し、Listクラスに<BR>
	 * ChartEntityクラスをセットして返す
	 *
	 * @return Listクラス
	 */
	public List<ReportEntity> getList() throws SQLException, Exception {
		return getListSort(null);
	}

	/**
	 * 日報 テーブルから<BR>
	 * 全てのレコードを取得し、Listクラスにソート処理して<BR>
	 * ChartEntityクラスをセットして返す
	 *
	 * @param order ORDER句文字列
	 * @return Listクラス
	 */
	public List<ReportEntity> getListSort(String order) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		List<ReportEntity> list = new ArrayList<ReportEntity>();

		try {
			sql.append("SELECT ");
			sql.append("USER_ID,");
			sql.append("DATE,");
			sql.append("SEQ,");
			sql.append("START_TIME,");
			sql.append("END_TIME,");
			sql.append("PRE_TIME,");
			sql.append("TRV_TIME,");
			sql.append("AFT_TIME,");
			sql.append("TYPE_CODE,");
			sql.append("TITLE,");
			sql.append("CUST_NAME,");
			sql.append("CUST_DEPT,");
			sql.append("CUST_PERSONS,");
			sql.append("FACT,");
			sql.append("GUESS,");
			sql.append("NEXT,");
			sql.append("APL_FLAG,");
			sql.append("APL_USER_ID,");
			sql.append("APL_COMMENT,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM REPORT");
			if (order != null && order.length() > 0) {
				sql.append(" ORDER BY ");
				sql.append(order);
			}

			stmt = dbConn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();
			while (rs.next()) {
				ReportEntity value = new ReportEntity();
				value.setUserId(rs.getString("USER_ID"));
				value.setDate(rs.getDate("DATE"));
				value.setSeq(rs.getInt("SEQ"));
				value.setStartTime(rs.getString("START_TIME"));
				value.setEndTime(rs.getString("END_TIME"));
				value.setPreTime(rs.getDouble("PRE_TIME"));
				value.setTrvTime(rs.getDouble("TRV_TIME"));
				value.setAftTime(rs.getDouble("AFT_TIME"));
				value.setTypeCode(rs.getString("TYPE_CODE"));
				value.setTitle(rs.getString("TITLE"));
				value.setCustName(rs.getString("CUST_NAME"));
				value.setCustDept(rs.getString("CUST_DEPT"));
				value.setCustPersons(rs.getString("CUST_PERSONS"));
				value.setFact(rs.getString("FACT"));
				value.setGuess(rs.getString("GUESS"));
				value.setNext(rs.getString("NEXT"));
				value.setAplFlag(rs.getString("APL_FLAG"));
				value.setAplUserId(rs.getString("APL_USER_ID"));
				value.setAplComment(rs.getString("APL_COMMENT"));
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
	 * 日報 テーブルから<BR>
	 * ChartEntityクラスのキー項目に一致するレコードを取得し<BR>
	 * ChartEntityクラスにセットして返す
	 *
	 * @param keyvalue ChartEntityBaseクラス
	 * @return ChartEntityBaseクラス
	 */
	public ReportEntity getValue(ReportEntity keyvalue) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		ReportEntity value = null;
		try {
			sql.append("SELECT ");
			sql.append("USER_ID,");
			sql.append("DATE,");
			sql.append("SEQ,");
			sql.append("START_TIME,");
			sql.append("END_TIME,");
			sql.append("PRE_TIME,");
			sql.append("TRV_TIME,");
			sql.append("AFT_TIME,");
			sql.append("TYPE_CODE,");
			sql.append("TITLE,");
			sql.append("CUST_NAME,");
			sql.append("CUST_DEPT,");
			sql.append("CUST_PERSONS,");
			sql.append("FACT,");
			sql.append("GUESS,");
			sql.append("NEXT,");
			sql.append("APL_FLAG,");
			sql.append("APL_USER_ID,");
			sql.append("APL_COMMENT,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM REPORT ");
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
				value = new ReportEntity();
				value.setUserId(rs.getString("USER_ID"));
				value.setDate(rs.getDate("DATE"));
				value.setSeq(rs.getInt("SEQ"));
				value.setStartTime(rs.getString("START_TIME"));
				value.setEndTime(rs.getString("END_TIME"));
				value.setPreTime(rs.getDouble("PRE_TIME"));
				value.setTrvTime(rs.getDouble("TRV_TIME"));
				value.setAftTime(rs.getDouble("AFT_TIME"));
				value.setTypeCode(rs.getString("TYPE_CODE"));
				value.setTitle(rs.getString("TITLE"));
				value.setCustName(rs.getString("CUST_NAME"));
				value.setCustDept(rs.getString("CUST_DEPT"));
				value.setCustPersons(rs.getString("CUST_PERSONS"));
				value.setFact(rs.getString("FACT"));
				value.setGuess(rs.getString("GUESS"));
				value.setNext(rs.getString("NEXT"));
				value.setAplFlag(rs.getString("APL_FLAG"));
				value.setAplUserId(rs.getString("APL_USER_ID"));
				value.setAplComment(rs.getString("APL_COMMENT"));
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
	 * 日報 テーブルから<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードを取得し<BR>
	 * ChartEntityクラスにセットして返す
	 *
	 * @param user_id ユーザーID
	 * @param date 日付
	 * @param seq 連番
	 * @return ChartEntityクラス
	 */
	public ReportEntity getValue(String user_id, Date date, int seq) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		ReportEntity value = null;
		try {
			sql.append("SELECT ");
			sql.append("USER_ID,");
			sql.append("DATE,");
			sql.append("SEQ,");
			sql.append("START_TIME,");
			sql.append("END_TIME,");
			sql.append("PRE_TIME,");
			sql.append("TRV_TIME,");
			sql.append("AFT_TIME,");
			sql.append("TYPE_CODE,");
			sql.append("TITLE,");
			sql.append("CUST_NAME,");
			sql.append("CUST_DEPT,");
			sql.append("CUST_PERSONS,");
			sql.append("FACT,");
			sql.append("GUESS,");
			sql.append("NEXT,");
			sql.append("APL_FLAG,");
			sql.append("APL_USER_ID,");
			sql.append("APL_COMMENT,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM REPORT ");
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
				value = new ReportEntity();
				value.setUserId(rs.getString("USER_ID"));
				value.setDate(rs.getDate("DATE"));
				value.setSeq(rs.getInt("SEQ"));
				value.setStartTime(rs.getString("START_TIME"));
				value.setEndTime(rs.getString("END_TIME"));
				value.setPreTime(rs.getDouble("PRE_TIME"));
				value.setTrvTime(rs.getDouble("TRV_TIME"));
				value.setAftTime(rs.getDouble("AFT_TIME"));
				value.setTypeCode(rs.getString("TYPE_CODE"));
				value.setTitle(rs.getString("TITLE"));
				value.setCustName(rs.getString("CUST_NAME"));
				value.setCustDept(rs.getString("CUST_DEPT"));
				value.setCustPersons(rs.getString("CUST_PERSONS"));
				value.setFact(rs.getString("FACT"));
				value.setGuess(rs.getString("GUESS"));
				value.setNext(rs.getString("NEXT"));
				value.setAplFlag(rs.getString("APL_FLAG"));
				value.setAplUserId(rs.getString("APL_USER_ID"));
				value.setAplComment(rs.getString("APL_COMMENT"));
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
	 * 日報 テーブルから<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードを取得し<BR>
	 * ListクラスにChartEntityクラスをセットして返す
	 *
	 * @param user_id ユーザーID
	 * @param date 日付
	 * @return Listクラス
	 */
	public List<ReportEntity> getList(String user_id, Date date) throws SQLException, Exception {
		return getListSort(user_id, date, null);
	}

	/**
	 * 日報 テーブルから<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードを取得し<BR>
	 * ListクラスにChartEntityクラスをセットして返す
	 *
	 * @param user_id ユーザーID
	 * @return Listクラス
	 */
	public List<ReportEntity> getList(String user_id) throws SQLException, Exception {
		return getListSort(user_id, null);
	}

	/**
	 * 日報 テーブルから<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードをソートして取得し<BR>
	 * ListクラスにChartEntityクラスをセットして返す
	 *
	 * @param user_id ユーザーID
	 * @param date 日付
	 * @param order ORDER句文字列
	 * @return Listクラス
	 */
	public List<ReportEntity> getListSort(String user_id, Date date, String order) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		List<ReportEntity> list = new ArrayList<ReportEntity>();
		try {
			sql.append("SELECT ");
			sql.append("USER_ID,");
			sql.append("DATE,");
			sql.append("SEQ,");
			sql.append("START_TIME,");
			sql.append("END_TIME,");
			sql.append("PRE_TIME,");
			sql.append("TRV_TIME,");
			sql.append("AFT_TIME,");
			sql.append("TYPE_CODE,");
			sql.append("TITLE,");
			sql.append("CUST_NAME,");
			sql.append("CUST_DEPT,");
			sql.append("CUST_PERSONS,");
			sql.append("FACT,");
			sql.append("GUESS,");
			sql.append("NEXT,");
			sql.append("APL_FLAG,");
			sql.append("APL_USER_ID,");
			sql.append("APL_COMMENT,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM REPORT ");
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
				ReportEntity value = new ReportEntity();
				value.setUserId(rs.getString("USER_ID"));
				value.setDate(rs.getDate("DATE"));
				value.setSeq(rs.getInt("SEQ"));
				value.setStartTime(rs.getString("START_TIME"));
				value.setEndTime(rs.getString("END_TIME"));
				value.setPreTime(rs.getDouble("PRE_TIME"));
				value.setTrvTime(rs.getDouble("TRV_TIME"));
				value.setAftTime(rs.getDouble("AFT_TIME"));
				value.setTypeCode(rs.getString("TYPE_CODE"));
				value.setTitle(rs.getString("TITLE"));
				value.setCustName(rs.getString("CUST_NAME"));
				value.setCustDept(rs.getString("CUST_DEPT"));
				value.setCustPersons(rs.getString("CUST_PERSONS"));
				value.setFact(rs.getString("FACT"));
				value.setGuess(rs.getString("GUESS"));
				value.setNext(rs.getString("NEXT"));
				value.setAplFlag(rs.getString("APL_FLAG"));
				value.setAplUserId(rs.getString("APL_USER_ID"));
				value.setAplComment(rs.getString("APL_COMMENT"));
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
	 * 日報 テーブルから<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードをソートして取得し<BR>
	 * ListクラスにChartEntityクラスをセットして返す
	 *
	 * @param user_id ユーザーID
	 * @param order ORDER句文字列
	 * @return Listクラス
	 */
	public List<ReportEntity> getListSort(String user_id, String order) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		List<ReportEntity> list = new ArrayList<ReportEntity>();
		try {
			sql.append("SELECT ");
			sql.append("USER_ID,");
			sql.append("DATE,");
			sql.append("SEQ,");
			sql.append("START_TIME,");
			sql.append("END_TIME,");
			sql.append("PRE_TIME,");
			sql.append("TRV_TIME,");
			sql.append("AFT_TIME,");
			sql.append("TYPE_CODE,");
			sql.append("TITLE,");
			sql.append("CUST_NAME,");
			sql.append("CUST_DEPT,");
			sql.append("CUST_PERSONS,");
			sql.append("FACT,");
			sql.append("GUESS,");
			sql.append("NEXT,");
			sql.append("APL_FLAG,");
			sql.append("APL_USER_ID,");
			sql.append("APL_COMMENT,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM REPORT ");
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
				ReportEntity value = new ReportEntity();
				value.setUserId(rs.getString("USER_ID"));
				value.setDate(rs.getDate("DATE"));
				value.setSeq(rs.getInt("SEQ"));
				value.setStartTime(rs.getString("START_TIME"));
				value.setEndTime(rs.getString("END_TIME"));
				value.setPreTime(rs.getDouble("PRE_TIME"));
				value.setTrvTime(rs.getDouble("TRV_TIME"));
				value.setAftTime(rs.getDouble("AFT_TIME"));
				value.setTypeCode(rs.getString("TYPE_CODE"));
				value.setTitle(rs.getString("TITLE"));
				value.setCustName(rs.getString("CUST_NAME"));
				value.setCustDept(rs.getString("CUST_DEPT"));
				value.setCustPersons(rs.getString("CUST_PERSONS"));
				value.setFact(rs.getString("FACT"));
				value.setGuess(rs.getString("GUESS"));
				value.setNext(rs.getString("NEXT"));
				value.setAplFlag(rs.getString("APL_FLAG"));
				value.setAplUserId(rs.getString("APL_USER_ID"));
				value.setAplComment(rs.getString("APL_COMMENT"));
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
	 * 日報 テーブルから<BR>
	 * WHEREを文字列として渡し、それに一致するレコードを取得し<BR>
	 * ListクラスにChartEntityクラスをセットして返す
	 *
	 * @param where WHERE句文字列
	 * @return Listクラス
	 */
	public List<ReportEntity> getListWhere(String where) throws SQLException, Exception {
		return getListWhereSort(where, null);
	}

	/**
	 * 日報 テーブルから<BR>
	 * WHEREを文字列として渡し、それに一致するレコードをソートして取得し<BR>
	 * ListクラスにChartEntityクラスをセットして返す
	 *
	 * @param where WHERE句文字列
	 * @param order ORDER句文字列
	 * @return Listクラス
	 */
	public List<ReportEntity> getListWhereSort(String where, String order) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		List<ReportEntity> list = new ArrayList<ReportEntity>();
		try {
			sql.append("SELECT ");
			sql.append("USER_ID,");
			sql.append("DATE,");
			sql.append("SEQ,");
			sql.append("START_TIME,");
			sql.append("END_TIME,");
			sql.append("PRE_TIME,");
			sql.append("TRV_TIME,");
			sql.append("AFT_TIME,");
			sql.append("TYPE_CODE,");
			sql.append("TITLE,");
			sql.append("CUST_NAME,");
			sql.append("CUST_DEPT,");
			sql.append("CUST_PERSONS,");
			sql.append("FACT,");
			sql.append("GUESS,");
			sql.append("NEXT,");
			sql.append("APL_FLAG,");
			sql.append("APL_USER_ID,");
			sql.append("APL_COMMENT,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM REPORT ");
			sql.append("WHERE ");
			sql.append(where);
			if (order != null && order.length() > 0) {
				sql.append(" ORDER BY ");
				sql.append(order);
			}

			stmt = dbConn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();
			while (rs.next()) {
				ReportEntity value = new ReportEntity();
				value.setUserId(rs.getString("USER_ID"));
				value.setDate(rs.getDate("DATE"));
				value.setSeq(rs.getInt("SEQ"));
				value.setStartTime(rs.getString("START_TIME"));
				value.setEndTime(rs.getString("END_TIME"));
				value.setPreTime(rs.getDouble("PRE_TIME"));
				value.setTrvTime(rs.getDouble("TRV_TIME"));
				value.setAftTime(rs.getDouble("AFT_TIME"));
				value.setTypeCode(rs.getString("TYPE_CODE"));
				value.setTitle(rs.getString("TITLE"));
				value.setCustName(rs.getString("CUST_NAME"));
				value.setCustDept(rs.getString("CUST_DEPT"));
				value.setCustPersons(rs.getString("CUST_PERSONS"));
				value.setFact(rs.getString("FACT"));
				value.setGuess(rs.getString("GUESS"));
				value.setNext(rs.getString("NEXT"));
				value.setAplFlag(rs.getString("APL_FLAG"));
				value.setAplUserId(rs.getString("APL_USER_ID"));
				value.setAplComment(rs.getString("APL_COMMENT"));
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
	 * 日報 テーブルに<BR>
	 * ChartEntityBaseクラスを新規登録する
	 *
	 * @param value ChartEntityクラス
	 * @return 登録レコード数
	 */
	public int registValue(ReportEntity value) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("INSERT INTO REPORT( ");
			sql.append("USER_ID,");
			sql.append("DATE,");
			sql.append("SEQ,");
			sql.append("START_TIME,");
			sql.append("END_TIME,");
			sql.append("PRE_TIME,");
			sql.append("TRV_TIME,");
			sql.append("AFT_TIME,");
			sql.append("TYPE_CODE,");
			sql.append("TITLE,");
			sql.append("CUST_NAME,");
			sql.append("CUST_DEPT,");
			sql.append("CUST_PERSONS,");
			sql.append("FACT,");
			sql.append("GUESS,");
			sql.append("NEXT,");
			sql.append("APL_FLAG,");
			sql.append("APL_USER_ID,");
			sql.append("APL_COMMENT,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append(") VALUES( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, value.getUserId());
			stmt.setDate(2, value.getDate());
			stmt.setInt(3, value.getSeq());
			stmt.setString(4, value.getStartTime());
			stmt.setString(5, value.getEndTime());
			stmt.setDouble(6, value.getPreTime());
			stmt.setDouble(7, value.getTrvTime());
			stmt.setDouble(8, value.getAftTime());
			stmt.setString(9, value.getTypeCode());
			stmt.setString(10, value.getTitle());
			stmt.setString(11, value.getCustName());
			stmt.setString(12, value.getCustDept());
			stmt.setString(13, value.getCustPersons());
			stmt.setString(14, value.getFact());
			stmt.setString(15, value.getGuess());
			stmt.setString(16, value.getNext());
			stmt.setString(17, value.getAplFlag());
			stmt.setString(18, value.getAplUserId());
			stmt.setString(19, value.getAplComment());
			stmt.setTimestamp(20, value.getIdate());
			stmt.setTimestamp(21, value.getUdate());

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
			buf.append(value.getPreTime());
			buf.append(",");
			buf.append(value.getTrvTime());
			buf.append(",");
			buf.append(value.getAftTime());
			buf.append(",");
			buf.append(value.getTypeCode());
			buf.append(",");
			buf.append(value.getTitle());
			buf.append(",");
			buf.append(value.getCustName());
			buf.append(",");
			buf.append(value.getCustDept());
			buf.append(",");
			buf.append(value.getCustPersons());
			buf.append(",");
			buf.append(value.getFact());
			buf.append(",");
			buf.append(value.getGuess());
			buf.append(",");
			buf.append(value.getNext());
			buf.append(",");
			buf.append(value.getAplFlag());
			buf.append(",");
			buf.append(value.getAplUserId());
			buf.append(",");
			buf.append(value.getAplComment());
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
	 * 日報 テーブルに<BR>
	 * ChartEntityBaseクラスの値で更新する
	 *
	 * @param value ChartEntityクラス
	 * @return 登録レコード数
	 */
	public int updateValue(ReportEntity value) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("UPDATE REPORT SET ");
			sql.append("START_TIME=?,");
			sql.append("END_TIME=?,");
			sql.append("PRE_TIME=?,");
			sql.append("TRV_TIME=?,");
			sql.append("AFT_TIME=?,");
			sql.append("TYPE_CODE=?,");
			sql.append("TITLE=?,");
			sql.append("CUST_NAME=?,");
			sql.append("CUST_DEPT=?,");
			sql.append("CUST_PERSONS=?,");
			sql.append("FACT=?,");
			sql.append("GUESS=?,");
			sql.append("NEXT=?,");
			sql.append("APL_FLAG=?,");
			sql.append("APL_USER_ID=?,");
			sql.append("APL_COMMENT=?,");
			sql.append("IDATE=?,");
			sql.append("UDATE=? ");
			sql.append("WHERE ");
			sql.append("USER_ID=? AND ");
			sql.append("DATE=? AND ");
			sql.append("SEQ=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, value.getStartTime());
			stmt.setString(2, value.getEndTime());
			stmt.setDouble(3, value.getPreTime());
			stmt.setDouble(4, value.getTrvTime());
			stmt.setDouble(5, value.getAftTime());
			stmt.setString(6, value.getTypeCode());
			stmt.setString(7, value.getTitle());
			stmt.setString(8, value.getCustName());
			stmt.setString(9, value.getCustDept());
			stmt.setString(10, value.getCustPersons());
			stmt.setString(11, value.getFact());
			stmt.setString(12, value.getGuess());
			stmt.setString(13, value.getNext());
			stmt.setString(14, value.getAplFlag());
			stmt.setString(15, value.getAplUserId());
			stmt.setString(16, value.getAplComment());
			stmt.setTimestamp(17, value.getIdate());
			stmt.setTimestamp(18, value.getUdate());
			stmt.setString(19, value.getUserId());
			stmt.setDate(20, value.getDate());
			stmt.setInt(21, value.getSeq());

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(value.getStartTime());
			buf.append(",");
			buf.append(value.getEndTime());
			buf.append(",");
			buf.append(value.getPreTime());
			buf.append(",");
			buf.append(value.getTrvTime());
			buf.append(",");
			buf.append(value.getAftTime());
			buf.append(",");
			buf.append(value.getTypeCode());
			buf.append(",");
			buf.append(value.getTitle());
			buf.append(",");
			buf.append(value.getCustName());
			buf.append(",");
			buf.append(value.getCustDept());
			buf.append(",");
			buf.append(value.getCustPersons());
			buf.append(",");
			buf.append(value.getFact());
			buf.append(",");
			buf.append(value.getGuess());
			buf.append(",");
			buf.append(value.getNext());
			buf.append(",");
			buf.append(value.getAplFlag());
			buf.append(",");
			buf.append(value.getAplUserId());
			buf.append(",");
			buf.append(value.getAplComment());
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
	 * 日報 テーブルに<BR>
	 * ChartEntityBaseクラスを登録する<BR>
	 * 既にレコードが存在する場合はUPDATE、存在しない場合はINSERTする
	 *
	 * @param value ChartEntityクラス
	 * @return 更新レコード数
	 */
	public int setValue(ReportEntity value) throws SQLException, Exception {
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
	 * 日報 テーブルに<BR>
	 * Listクラスに格納したChartEntityクラスを新規登録する
	 *
	 * @return 更新レコード数
	 */
	public int registList(List<ReportEntity> list) throws SQLException, Exception {
		int count = 0;
		try {
			Iterator<ReportEntity> it = list.iterator();
			while (it.hasNext()) {
				ReportEntity value = (ReportEntity) it.next();
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
	 * 日報 テーブルに<BR>
	 * Listクラスに格納したChartEntityクラスの値で更新する<BR>
	 *
	 * @return 更新レコード数
	 */
	public int updateList(List<ReportEntity> list) throws SQLException, Exception {
		int count = 0;
		try {
			Iterator<ReportEntity> it = list.iterator();
			while (it.hasNext()) {
				ReportEntity value = (ReportEntity) it.next();
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
	 * 日報 テーブルに<BR>
	 * Listクラスに格納したChartEntityBaseクラスを登録する<BR>
	 * 既にレコードが存在する場合はUPDATE、存在しない場合はINSERTする
	 *
	 * @return 更新レコード数
	 */
	public int setList(List<ReportEntity> list) throws SQLException, Exception {
		int count = 0;
		try {
			Iterator<ReportEntity> it = list.iterator();
			while (it.hasNext()) {
				ReportEntity value = (ReportEntity) it.next();
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
	 * 日報 テーブルの<BR>
	 * ChartEntityBaseクラスのキー項目に一致するレコードを削除する
	 *
	 * @param keyvalue ChartEntityクラス
	 * @return 削除レコード数
	 */
	public int removeValue(ReportEntity keyvalue) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("DELETE FROM REPORT WHERE ");
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
	 * 日報 テーブルの<BR>
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
			sql.append("DELETE FROM REPORT WHERE ");
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
	 * 日報 テーブルの<BR>
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
			sql.append("DELETE FROM REPORT WHERE ");
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
	 * 日報 テーブルの<BR>
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
			sql.append("DELETE FROM REPORT WHERE ");
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
	 * 日報 テーブルの<BR>
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
			sql.append("DELETE FROM REPORT WHERE ");
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
	 * 日報 テーブルの<BR>
	 * 全レコードを削除する
	 *
	 * @return 削除レコード数
	 */
	public int removeAll() throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("DELETE FROM REPORT");

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
	 * 日報 テーブルの<BR>
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
			sql.append("SELECT COUNT(*) AS CNT FROM REPORT");

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
	 * 日報 テーブルの<BR>
	 * ChartEntityBaseクラスのキー項目に一致するレコードを取得し<BR>
	 * そのレコード数を返す
	 *
	 * @param keyvalue ChartEntityクラス
	 * @return 一致レコード数
	 */
	public int count(ReportEntity keyvalue) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("SELECT COUNT(*) AS CNT FROM REPORT");
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
	 * 日報 テーブルの<BR>
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
			sql.append("SELECT COUNT(*) AS CNT FROM REPORT WHERE ");
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
	 * 日報 テーブルの<BR>
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
			sql.append("SELECT COUNT(*) AS CNT FROM REPORT WHERE ");
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
	 * 日報 テーブルの<BR>
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
			sql.append("SELECT COUNT(*) AS CNT FROM REPORT WHERE ");
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
	 * 日報 テーブルの<BR>
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
			sql.append("SELECT COUNT(*) AS CNT FROM REPORT WHERE ");
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
