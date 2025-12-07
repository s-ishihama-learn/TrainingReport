package jp.logware.custmer.entity;

import java.sql.Timestamp;

/**
 * データ格納クラス<br>
 * 表示グループ テーブル
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class ViewGroupEntityBase {
	/**
	 * ユーザーid
	 */
	private String user_id;

	/**
	 * 表示ユーザーid
	 */
	private String view_user_id;

	/**
	 * 登録日
	 */
	private Timestamp idate;

	/**
	 * コンストラクタ
	 */
	public ViewGroupEntityBase() {
		user_id = "";
		view_user_id = "";
		idate = null;
	}

	/**
	 * ユーザーidのゲッター
	 *
	 * @return ユーザーid
	 */
	public String getUserId() {
		return user_id;
	}

	/**
	 * ユーザーidのセッター
	 *
	 * @param user_id ユーザーid
	 */
	public void setUserId(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * 表示ユーザーidのゲッター
	 *
	 * @return 表示ユーザーid
	 */
	public String getViewUserId() {
		return view_user_id;
	}

	/**
	 * 表示ユーザーidのセッター
	 *
	 * @param view_user_id 表示ユーザーid
	 */
	public void setViewUserId(String view_user_id) {
		this.view_user_id = view_user_id;
	}

	/**
	 * 登録日のゲッター
	 *
	 * @return 登録日
	 */
	public Timestamp getIdate() {
		return idate;
	}

	/**
	 * 登録日のセッター
	 *
	 * @param idate 登録日
	 */
	public void setIdate(Timestamp idate) {
		this.idate = idate;
	}

}
