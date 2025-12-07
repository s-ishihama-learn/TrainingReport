package jp.logware.custmer.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * コネクションを保持し管理するクラス
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class ConnectionPool {
	/**
	 * データベースコネクション
	 */
	private static final List<Connection> connections = Collections.synchronizedList(new ArrayList<Connection>());

	/**
	 * 唯一のインスタンス
	 */
	private static ConnectionPool connectionPool = null;

	/**
	 * コネクションプール数
	 */
	private static int connectionSize;

	/**
	 * コネクション使用時の待ち時間
	 */
	private static int waitTime;

	/**
	 * ConnectionPoolのインスタンスを取得する
	 */
	public static synchronized ConnectionPool getInstance(HttpServletRequest request) throws Exception {
		if (ConnectionPool.connectionPool == null) {
			ConnectionPool.connectionPool = new ConnectionPool(request);
		}
		ConnectionPool cp = ConnectionPool.connectionPool;
		Connection conn = cp.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT NOW()");
			stmt.executeQuery();
			stmt.close();
			stmt = null;
		} catch (SQLException e) {
			System.out.println("DB reconnect !");
			clearInstance();
			cp = new ConnectionPool(request);
		} finally {
			cp.releaseConnection(conn);
		}
		return cp;
	}

	/**
	 * 全てのコネクションを開放する
	 */
	public static void clearInstance() {
		try {
			for (Connection connection : connections) {
				connection.close();
				connection = null;
			}
			connections.clear();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * インスタンスの生成。privateなので外部からnewで生成はできない。
	 */
	private ConnectionPool(HttpServletRequest request) throws Exception {
		ServletContext ctx = request.getSession().getServletContext();
		InitParam param = (InitParam) ctx.getAttribute("InitParam");

		connectionSize = param.getConnectionSize();
		waitTime = param.getWaitTime();

		Class.forName(param.getJdbcDriverName());
		for (int i = 0; i < connectionSize; i++) {
			Connection connection = DriverManager.getConnection(param.getJdbcConnectParam(), param.getJdbcUserId(), param.getJdbcPwd());
			connections.add(connection);
		}
	}

	/**
	 * コネクションを取得する。 再試行上限数、付加文字列固定の簡易版。
	 *
	 * @return コネクション
	 */
	public Connection getConnection() {
		Connection conn = getConnection(connectionSize);
		return conn;
	}

	/**
	 * コネクションを返却する。
	 *
	 * @param connection 返却するコネクション
	 */
	public synchronized void releaseConnection(Connection connection) {
		if (connection == null) {
			return;
		}
		connections.add(connection);
	}

	/**
	 * コネクションを取得する。取得できない場合は引数で与えられた 回数だけ指定ミリ秒おきにトライする。<br>
	 * それでも取得できなかった た場合はnullを返す
	 *
	 * @param count 残りの試行回数
	 * @return コネクション
	 */
	private synchronized Connection getConnection(int count) {
		Connection con = null;
		try {
			if (count < 1) {
				// もし残りの試行回数が1以下だったら null を返す
				System.out.println("有効な接続を返せませんでした。");

			} else {
				// コネクションが残っていればプールから1つ取り出して提供する
				if (connections.size() > 0) {
					Connection connection = connections.get(0);
					connections.remove(connection);
					con = connection;

				} else {
					// コネクションが残っていなければ待機
					wait(waitTime);

					// 試行回数を1つ減らして再度取得を実行
					con = getConnection(count - 1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
}
