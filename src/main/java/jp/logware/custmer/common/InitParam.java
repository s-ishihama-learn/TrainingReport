package jp.logware.custmer.common;

/**
 * 初期パラメータ格納クラス
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class InitParam {
    /**
     * データベースドライバ
     */
    private String jdbcDriverName;

    /**
     * データベース接続文字列
     */
    private String jdbcConnectParam;

    /**
     * データベース接続ユーザ
     */
    private String jdbcUserId;

    /**
     * データベース接続ユーザのパスワード
     */
    private String jdbcPwd;

    /**
     * コネクションプール数
     */
    private int connectionSize;

    /**
     * 全コネクション貸し出し中の待ち時間(msec)
     */
    private int waitTime;

    /**
     * データベースドライバを取得します。
     *
     * @return データベースドライバ
     */
    public String getJdbcDriverName() {
        return jdbcDriverName;
    }

    /**
     * データベースドライバを設定します。
     *
     * @param jdbcDriverName データベースドライバ
     */
    public void setJdbcDriverName(String jdbcDriverName) {
        this.jdbcDriverName = jdbcDriverName;
    }

    /**
     * データベース接続文字列を取得します。
     *
     * @return データベース接続文字列
     */
    public String getJdbcConnectParam() {
        return jdbcConnectParam;
    }

    /**
     * データベース接続文字列を設定します。
     *
     * @param jdbcConnectParam データベース接続文字列
     */
    public void setJdbcConnectParam(String jdbcConnectParam) {
        this.jdbcConnectParam = jdbcConnectParam;
    }

    /**
     * データベース接続ユーザを取得します。
     *
     * @return データベース接続ユーザ
     */
    public String getJdbcUserId() {
        return jdbcUserId;
    }

    /**
     * データベース接続ユーザを設定します。
     *
     * @param jdbcUserId データベース接続ユーザ
     */
    public void setJdbcUserId(String jdbcUserId) {
        this.jdbcUserId = jdbcUserId;
    }

    /**
     * データベース接続ユーザのパスワードを取得します。
     *
     * @return データベース接続ユーザのパスワード
     */
    public String getJdbcPwd() {
        return jdbcPwd;
    }

    /**
     * データベース接続ユーザのパスワードを設定します。
     *
     * @param jdbcPwd データベース接続ユーザのパスワード
     */
    public void setJdbcPwd(String jdbcPwd) {
        this.jdbcPwd = jdbcPwd;
    }

    /**
     * コネクションプール数を取得します。
     *
     * @return コネクションプール数
     */
    public int getConnectionSize() {
        return connectionSize;
    }

    /**
     * コネクションプール数を設定します。
     *
     * @param connectionSize コネクションプール数
     */
    public void setConnectionSize(int connectionSize) {
        this.connectionSize = connectionSize;
    }

    /**
     * 全コネクション貸し出し中の待ち時間(msec)を取得します。
     *
     * @return 全コネクション貸し出し中の待ち時間(msec)
     */
    public int getWaitTime() {
        return waitTime;
    }

    /**
     * 全コネクション貸し出し中の待ち時間(msec)を設定します。
     *
     * @param waitTime 全コネクション貸し出し中の待ち時間(msec)
     */
    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }
}
