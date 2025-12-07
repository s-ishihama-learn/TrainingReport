package jp.logware.custmer.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.logware.custmer.entity.CommentEntity;

/**
 * データベースアクセスクラス<br>
 * 日報コメント テーブル
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class CommentDaoBase {
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
	public CommentDaoBase(Connection con) {
		dbConn = con;
	}

	/**
	 * 日報コメント テーブルから<BR>
	 * 全てのレコードを取得し、Listクラスに<BR>
	 * ChartCommentEntityクラスをセットして返す
	 *
	 * @return Listクラス
	 */
	public List<CommentEntity> getList() throws SQLException, Exception {
		return getListSort(null);
	}

	/**
	 * 日報コメント テーブルから<BR>
	 * 全てのレコードを取得し、Listクラスにソート処理して<BR>
	 * ChartCommentEntityクラスをセットして返す
	 *
	 * @param order ORDER句文字列
	 * @return Listクラス
	 */
	public List<CommentEntity> getListSort(String order) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		List<CommentEntity> list = new ArrayList<CommentEntity>();

		try {
			sql.append("SELECT ");
			sql.append("USER_ID,");
			sql.append("DATE,");
			sql.append("SEQ,");
			sql.append("SEQ_COMMENT,");
			sql.append("REG_USER_ID,");
			sql.append("COMMENT,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM COMMENT");
			if (order != null && order.length() > 0) {
				sql.append(" ORDER BY ");
				sql.append(order);
			}

			stmt = dbConn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();
			while (rs.next()) {
				CommentEntity value = new CommentEntity();
				value.setUserId(rs.getString("USER_ID"));
				value.setDate(rs.getDate("DATE"));
				value.setSeq(rs.getInt("SEQ"));
				value.setSeqComment(rs.getInt("SEQ_COMMENT"));
				value.setRegUserId(rs.getString("REG_USER_ID"));
				value.setComment(rs.getString("COMMENT"));
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
	 * 日報コメント テーブルから<BR>
	 * ChartCommentEntityクラスのキー項目に一致するレコードを取得し<BR>
	 * ChartCommentEntityクラスにセットして返す
	 *
	 * @param keyvalue ChartCommentEntityBaseクラス
	 * @return ChartCommentEntityBaseクラス
	 */
	public CommentEntity getValue(CommentEntity keyvalue) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		CommentEntity value = null;
		try {
			sql.append("SELECT ");
			sql.append("USER_ID,");
			sql.append("DATE,");
			sql.append("SEQ,");
			sql.append("SEQ_COMMENT,");
			sql.append("REG_USER_ID,");
			sql.append("COMMENT,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM COMMENT ");
			sql.append("WHERE ");
			sql.append("USER_ID=? AND ");
			sql.append("DATE=? AND ");
			sql.append("SEQ=? AND ");
			sql.append("SEQ_COMMENT=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, keyvalue.getUserId());
			stmt.setDate(2, keyvalue.getDate());
			stmt.setInt(3, keyvalue.getSeq());
			stmt.setInt(4, keyvalue.getSeqComment());

			rs = stmt.executeQuery();
			if (rs.next()) {
				value = new CommentEntity();
				value.setUserId(rs.getString("USER_ID"));
				value.setDate(rs.getDate("DATE"));
				value.setSeq(rs.getInt("SEQ"));
				value.setSeqComment(rs.getInt("SEQ_COMMENT"));
				value.setRegUserId(rs.getString("REG_USER_ID"));
				value.setComment(rs.getString("COMMENT"));
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
			buf.append(",");
			buf.append(keyvalue.getSeqComment());
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
	 * 日報コメント テーブルから<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードを取得し<BR>
	 * ChartCommentEntityクラスにセットして返す
	 *
	 * @param user_id ユーザーID
	 * @param date 日付
	 * @param seq 連番
	 * @param seq_comment コメント連番
	 * @return ChartCommentEntityクラス
	 */
	public CommentEntity getValue(String user_id, Date date, int seq, int seq_comment) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		CommentEntity value = null;
		try {
			sql.append("SELECT ");
			sql.append("USER_ID,");
			sql.append("DATE,");
			sql.append("SEQ,");
			sql.append("SEQ_COMMENT,");
			sql.append("REG_USER_ID,");
			sql.append("COMMENT,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM COMMENT ");
			sql.append("WHERE ");
			sql.append("USER_ID=? AND ");
			sql.append("DATE=? AND ");
			sql.append("SEQ=? AND ");
			sql.append("SEQ_COMMENT=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, user_id);
			stmt.setDate(2, date);
			stmt.setInt(3, seq);
			stmt.setInt(4, seq_comment);

			rs = stmt.executeQuery();
			if (rs.next()) {
				value = new CommentEntity();
				value.setUserId(rs.getString("USER_ID"));
				value.setDate(rs.getDate("DATE"));
				value.setSeq(rs.getInt("SEQ"));
				value.setSeqComment(rs.getInt("SEQ_COMMENT"));
				value.setRegUserId(rs.getString("REG_USER_ID"));
				value.setComment(rs.getString("COMMENT"));
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
			buf.append(",");
			buf.append(seq_comment);
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
	 * 日報コメント テーブルから<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードを取得し<BR>
	 * ListクラスにChartCommentEntityクラスをセットして返す
	 *
	 * @param user_id ユーザーID
	 * @param date 日付
	 * @param seq 連番
	 * @return Listクラス
	 */
	public List<CommentEntity> getList(String user_id, Date date, int seq) throws SQLException, Exception {
		return getListSort(user_id, date, seq, null);
	}

	/**
	 * 日報コメント テーブルから<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードを取得し<BR>
	 * ListクラスにChartCommentEntityクラスをセットして返す
	 *
	 * @param user_id ユーザーID
	 * @param date 日付
	 * @return Listクラス
	 */
	public List<CommentEntity> getList(String user_id, Date date) throws SQLException, Exception {
		return getListSort(user_id, date, null);
	}

	/**
	 * 日報コメント テーブルから<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードを取得し<BR>
	 * ListクラスにChartCommentEntityクラスをセットして返す
	 *
	 * @param user_id ユーザーID
	 * @return Listクラス
	 */
	public List<CommentEntity> getList(String user_id) throws SQLException, Exception {
		return getListSort(user_id, null);
	}

	/**
	 * 日報コメント テーブルから<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードをソートして取得し<BR>
	 * ListクラスにChartCommentEntityクラスをセットして返す
	 *
	 * @param user_id ユーザーID
	 * @param date 日付
	 * @param seq 連番
	 * @param order ORDER句文字列
	 * @return Listクラス
	 */
	public List<CommentEntity> getListSort(String user_id, Date date, int seq, String order) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		List<CommentEntity> list = new ArrayList<CommentEntity>();
		try {
			sql.append("SELECT ");
			sql.append("USER_ID,");
			sql.append("DATE,");
			sql.append("SEQ,");
			sql.append("SEQ_COMMENT,");
			sql.append("REG_USER_ID,");
			sql.append("COMMENT,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM COMMENT ");
			sql.append("WHERE ");
			sql.append("USER_ID=? AND ");
			sql.append("DATE=? AND ");
			sql.append("SEQ=?");
			if (order != null && order.length() > 0) {
				sql.append(" ORDER BY ");
				sql.append(order);
			}

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, user_id);
			stmt.setDate(2, date);
			stmt.setInt(3, seq);

			rs = stmt.executeQuery();
			while (rs.next()) {
				CommentEntity value = new CommentEntity();
				value.setUserId(rs.getString("USER_ID"));
				value.setDate(rs.getDate("DATE"));
				value.setSeq(rs.getInt("SEQ"));
				value.setSeqComment(rs.getInt("SEQ_COMMENT"));
				value.setRegUserId(rs.getString("REG_USER_ID"));
				value.setComment(rs.getString("COMMENT"));
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

		return list;
	}

	/**
	 * 日報コメント テーブルから<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードをソートして取得し<BR>
	 * ListクラスにChartCommentEntityクラスをセットして返す
	 *
	 * @param user_id ユーザーID
	 * @param date 日付
	 * @param order ORDER句文字列
	 * @return Listクラス
	 */
	public List<CommentEntity> getListSort(String user_id, Date date, String order) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		List<CommentEntity> list = new ArrayList<CommentEntity>();
		try {
			sql.append("SELECT ");
			sql.append("USER_ID,");
			sql.append("DATE,");
			sql.append("SEQ,");
			sql.append("SEQ_COMMENT,");
			sql.append("REG_USER_ID,");
			sql.append("COMMENT,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM COMMENT ");
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
				CommentEntity value = new CommentEntity();
				value.setUserId(rs.getString("USER_ID"));
				value.setDate(rs.getDate("DATE"));
				value.setSeq(rs.getInt("SEQ"));
				value.setSeqComment(rs.getInt("SEQ_COMMENT"));
				value.setRegUserId(rs.getString("REG_USER_ID"));
				value.setComment(rs.getString("COMMENT"));
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
	 * 日報コメント テーブルから<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードをソートして取得し<BR>
	 * ListクラスにChartCommentEntityクラスをセットして返す
	 *
	 * @param user_id ユーザーID
	 * @param order ORDER句文字列
	 * @return Listクラス
	 */
	public List<CommentEntity> getListSort(String user_id, String order) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		List<CommentEntity> list = new ArrayList<CommentEntity>();
		try {
			sql.append("SELECT ");
			sql.append("USER_ID,");
			sql.append("DATE,");
			sql.append("SEQ,");
			sql.append("SEQ_COMMENT,");
			sql.append("REG_USER_ID,");
			sql.append("COMMENT,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM COMMENT ");
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
				CommentEntity value = new CommentEntity();
				value.setUserId(rs.getString("USER_ID"));
				value.setDate(rs.getDate("DATE"));
				value.setSeq(rs.getInt("SEQ"));
				value.setSeqComment(rs.getInt("SEQ_COMMENT"));
				value.setRegUserId(rs.getString("REG_USER_ID"));
				value.setComment(rs.getString("COMMENT"));
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
	 * 日報コメント テーブルから<BR>
	 * WHEREを文字列として渡し、それに一致するレコードを取得し<BR>
	 * ListクラスにChartCommentEntityクラスをセットして返す
	 *
	 * @param where WHERE句文字列
	 * @return Listクラス
	 */
	public List<CommentEntity> getListWhere(String where) throws SQLException, Exception {
		return getListWhereSort(where, null);
	}

	/**
	 * 日報コメント テーブルから<BR>
	 * WHEREを文字列として渡し、それに一致するレコードをソートして取得し<BR>
	 * ListクラスにChartCommentEntityクラスをセットして返す
	 *
	 * @param where WHERE句文字列
	 * @param order ORDER句文字列
	 * @return Listクラス
	 */
	public List<CommentEntity> getListWhereSort(String where, String order) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		List<CommentEntity> list = new ArrayList<CommentEntity>();
		try {
			sql.append("SELECT ");
			sql.append("USER_ID,");
			sql.append("DATE,");
			sql.append("SEQ,");
			sql.append("SEQ_COMMENT,");
			sql.append("REG_USER_ID,");
			sql.append("COMMENT,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append("FROM COMMENT ");
			sql.append("WHERE ");
			sql.append(where);
			if (order != null && order.length() > 0) {
				sql.append(" ORDER BY ");
				sql.append(order);
			}

			stmt = dbConn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();
			while (rs.next()) {
				CommentEntity value = new CommentEntity();
				value.setUserId(rs.getString("USER_ID"));
				value.setDate(rs.getDate("DATE"));
				value.setSeq(rs.getInt("SEQ"));
				value.setSeqComment(rs.getInt("SEQ_COMMENT"));
				value.setRegUserId(rs.getString("REG_USER_ID"));
				value.setComment(rs.getString("COMMENT"));
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
	 * 日報コメント テーブルに<BR>
	 * ChartCommentEntityBaseクラスを新規登録する
	 *
	 * @param value ChartCommentEntityクラス
	 * @return 登録レコード数
	 */
	public int registValue(CommentEntity value) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("INSERT INTO COMMENT( ");
			sql.append("USER_ID,");
			sql.append("DATE,");
			sql.append("SEQ,");
			sql.append("SEQ_COMMENT,");
			sql.append("REG_USER_ID,");
			sql.append("COMMENT,");
			sql.append("IDATE,");
			sql.append("UDATE ");
			sql.append(") VALUES( ?,?,?,?,?,?,?,? )");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, value.getUserId());
			stmt.setDate(2, value.getDate());
			stmt.setInt(3, value.getSeq());
			stmt.setInt(4, value.getSeqComment());
			stmt.setString(5, value.getRegUserId());
			stmt.setString(6, value.getComment());
			stmt.setTimestamp(7, value.getIdate());
			stmt.setTimestamp(8, value.getUdate());

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
			buf.append(value.getSeqComment());
			buf.append(",");
			buf.append(value.getRegUserId());
			buf.append(",");
			buf.append(value.getComment());
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
	 * 日報コメント テーブルに<BR>
	 * ChartCommentEntityBaseクラスの値で更新する
	 *
	 * @param value ChartCommentEntityクラス
	 * @return 登録レコード数
	 */
	public int updateValue(CommentEntity value) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("UPDATE COMMENT SET ");
			sql.append("REG_USER_ID=?,");
			sql.append("COMMENT=?,");
			sql.append("IDATE=?,");
			sql.append("UDATE=? ");
			sql.append("WHERE ");
			sql.append("USER_ID=? AND ");
			sql.append("DATE=? AND ");
			sql.append("SEQ=? AND ");
			sql.append("SEQ_COMMENT=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, value.getRegUserId());
			stmt.setString(2, value.getComment());
			stmt.setTimestamp(3, value.getIdate());
			stmt.setTimestamp(4, value.getUdate());
			stmt.setString(5, value.getUserId());
			stmt.setDate(6, value.getDate());
			stmt.setInt(7, value.getSeq());
			stmt.setInt(8, value.getSeqComment());

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(value.getRegUserId());
			buf.append(",");
			buf.append(value.getComment());
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
			buf.append(",");
			buf.append(value.getSeqComment());
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
	 * 日報コメント テーブルに<BR>
	 * ChartCommentEntityBaseクラスを登録する<BR>
	 * 既にレコードが存在する場合はUPDATE、存在しない場合はINSERTする
	 *
	 * @param value ChartCommentEntityクラス
	 * @return 更新レコード数
	 */
	public int setValue(CommentEntity value) throws SQLException, Exception {
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
	 * 日報コメント テーブルに<BR>
	 * Listクラスに格納したChartCommentEntityクラスを新規登録する
	 *
	 * @return 更新レコード数
	 */
	public int registList(List<CommentEntity> list) throws SQLException, Exception {
		int count = 0;
		try {
			Iterator<CommentEntity> it = list.iterator();
			while (it.hasNext()) {
				CommentEntity value = (CommentEntity) it.next();
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
	 * 日報コメント テーブルに<BR>
	 * Listクラスに格納したChartCommentEntityクラスの値で更新する<BR>
	 *
	 * @return 更新レコード数
	 */
	public int updateList(List<CommentEntity> list) throws SQLException, Exception {
		int count = 0;
		try {
			Iterator<CommentEntity> it = list.iterator();
			while (it.hasNext()) {
				CommentEntity value = (CommentEntity) it.next();
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
	 * 日報コメント テーブルに<BR>
	 * Listクラスに格納したChartCommentEntityBaseクラスを登録する<BR>
	 * 既にレコードが存在する場合はUPDATE、存在しない場合はINSERTする
	 *
	 * @return 更新レコード数
	 */
	public int setList(List<CommentEntity> list) throws SQLException, Exception {
		int count = 0;
		try {
			Iterator<CommentEntity> it = list.iterator();
			while (it.hasNext()) {
				CommentEntity value = (CommentEntity) it.next();
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
	 * 日報コメント テーブルの<BR>
	 * ChartCommentEntityBaseクラスのキー項目に一致するレコードを削除する
	 *
	 * @param keyvalue ChartCommentEntityクラス
	 * @return 削除レコード数
	 */
	public int removeValue(CommentEntity keyvalue) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("DELETE FROM COMMENT WHERE ");
			sql.append("USER_ID=? AND ");
			sql.append("DATE=? AND ");
			sql.append("SEQ=? AND ");
			sql.append("SEQ_COMMENT=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, keyvalue.getUserId());
			stmt.setDate(2, keyvalue.getDate());
			stmt.setInt(3, keyvalue.getSeq());
			stmt.setInt(4, keyvalue.getSeqComment());

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(keyvalue.getUserId());
			buf.append(",");
			buf.append(keyvalue.getDate());
			buf.append(",");
			buf.append(keyvalue.getSeq());
			buf.append(",");
			buf.append(keyvalue.getSeqComment());
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
	 * 日報コメント テーブルの<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコードを削除する
	 *
	 * @param user_id ユーザーID
	 * @param date 日付
	 * @param seq 連番
	 * @param seq_comment コメント連番
	 * @return 削除レコード数
	 */
	public int removeValue(String user_id, Date date, int seq, int seq_comment) throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("DELETE FROM COMMENT WHERE ");
			sql.append("USER_ID=? AND ");
			sql.append("DATE=? AND ");
			sql.append("SEQ=? AND ");
			sql.append("SEQ_COMMENT=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, user_id);
			stmt.setDate(2, date);
			stmt.setInt(3, seq);
			stmt.setInt(4, seq_comment);

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("SQL Param = [");
			buf.append(user_id);
			buf.append(",");
			buf.append(date);
			buf.append(",");
			buf.append(seq);
			buf.append(",");
			buf.append(seq_comment);
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
	 * 日報コメント テーブルの<BR>
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
			sql.append("DELETE FROM COMMENT WHERE ");
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
	 * 日報コメント テーブルの<BR>
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
			sql.append("DELETE FROM COMMENT WHERE ");
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
	 * 日報コメント テーブルの<BR>
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
			sql.append("DELETE FROM COMMENT WHERE ");
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
	 * 日報コメント テーブルの<BR>
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
			sql.append("DELETE FROM COMMENT WHERE ");
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
	 * 日報コメント テーブルの<BR>
	 * 全レコードを削除する
	 *
	 * @return 削除レコード数
	 */
	public int removeAll() throws SQLException, Exception {
		PreparedStatement stmt = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("DELETE FROM COMMENT");

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
	 * 日報コメント テーブルの<BR>
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
			sql.append("SELECT COUNT(*) AS CNT FROM COMMENT");

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
	 * 日報コメント テーブルの<BR>
	 * ChartCommentEntityBaseクラスのキー項目に一致するレコードを取得し<BR>
	 * そのレコード数を返す
	 *
	 * @param keyvalue ChartCommentEntityクラス
	 * @return 一致レコード数
	 */
	public int count(CommentEntity keyvalue) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("SELECT COUNT(*) AS CNT FROM COMMENT");
			sql.append(" WHERE ");
			sql.append("USER_ID=? AND ");
			sql.append("DATE=? AND ");
			sql.append("SEQ=? AND ");
			sql.append("SEQ_COMMENT=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, keyvalue.getUserId());
			stmt.setDate(2, keyvalue.getDate());
			stmt.setInt(3, keyvalue.getSeq());
			stmt.setInt(4, keyvalue.getSeqComment());

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
			buf.append(",");
			buf.append(keyvalue.getSeqComment());
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
	 * 日報コメント テーブルの<BR>
	 * キー項目をパラメータとして渡し、それに一致するレコード数を返す
	 *
	 * @param user_id ユーザーID
	 * @param date 日付
	 * @param seq 連番
	 * @param seq_comment コメント連番
	 * @return 一致レコード数
	 */
	public int count(String user_id, Date date, int seq, int seq_comment) throws SQLException, Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		try {
			sql.append("SELECT COUNT(*) AS CNT FROM COMMENT WHERE ");
			sql.append("USER_ID=? AND ");
			sql.append("DATE=? AND ");
			sql.append("SEQ=? AND ");
			sql.append("SEQ_COMMENT=?");

			stmt = dbConn.prepareStatement(sql.toString());
			stmt.setString(1, user_id);
			stmt.setDate(2, date);
			stmt.setInt(3, seq);
			stmt.setInt(4, seq_comment);

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
			buf.append(",");
			buf.append(seq_comment);
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
	 * 日報コメント テーブルの<BR>
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
			sql.append("SELECT COUNT(*) AS CNT FROM COMMENT WHERE ");
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
	 * 日報コメント テーブルの<BR>
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
			sql.append("SELECT COUNT(*) AS CNT FROM COMMENT WHERE ");
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
	 * 日報コメント テーブルの<BR>
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
			sql.append("SELECT COUNT(*) AS CNT FROM COMMENT WHERE ");
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
	 * 日報コメント テーブルの<BR>
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
			sql.append("SELECT COUNT(*) AS CNT FROM COMMENT WHERE ");
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
