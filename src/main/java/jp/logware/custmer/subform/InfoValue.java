package jp.logware.custmer.subform;

/**
 * ログイン、お知らせ一覧 アクションフォーム用サブクラス<br>
 * InfoValueクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class InfoValue {
	/**
	 * お知らせ日付
	 */
	private String infoDate;

	/**
	 * お知らせ
	 */
	private String infoBody;

	/**
	 * 連番
	 */
	private String seq;

	/**
	 * コンストラクタ
	 */
	public InfoValue() {
		infoDate = "";
		infoBody = "";
		seq = "";
	}

	/**
	 * お知らせ日付のゲッター
	 *
	 * @return お知らせ日付
	 */
	public String getInfoDate() {
		return infoDate;
	}

	/**
	 * お知らせ日付のセッター
	 *
	 * @param infoDate お知らせ日付
	 */
	public void setInfoDate(String infoDate) {
		this.infoDate = infoDate;
	}
	/**
	 * お知らせのゲッター
	 *
	 * @return お知らせ
	 */
	public String getInfoBody() {
		return infoBody;
	}

	/**
	 * お知らせのセッター
	 *
	 * @param infoBody お知らせ
	 */
	public void setInfoBody(String infoBody) {
		this.infoBody = infoBody;
	}
	/**
	 * 連番のゲッター
	 *
	 * @return 連番
	 */
	public String getSeq() {
		return seq;
	}

	/**
	 * 連番のセッター
	 *
	 * @param seq 連番
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}

}
