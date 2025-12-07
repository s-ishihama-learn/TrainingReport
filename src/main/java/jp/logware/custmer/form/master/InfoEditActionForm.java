package jp.logware.custmer.form.master;

import org.apache.struts.validator.ValidatorForm;

/**
 * お知らせ編集 アクションフォームクラス
 * 
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class InfoEditActionForm extends ValidatorForm {
	/**
	 * シリアルバージョン
	 */
	private static final long serialVersionUID = 8760399981352673886L;

	/**
	 * 日時
	 */
	private String infoDate;

	/**
	 * 連番
	 */
	private String seq;

	/**
	 * 内容
	 */
	private String infoBody;

	/**
	 * 表示期限
	 */
	private String limiDate;

	/**
	 * ボタン
	 */
	private String button;

	/**
	 * 表示条件値
	 */
	private String viewMode;

	/**
	 * コンストラクタ
	 */
	public InfoEditActionForm() {
		infoDate = "";
		seq = "";
		infoBody = "";
		limiDate = "";
	}

	/**
	 * 日時のゲッター
	 * 
	 * @return 日時
	 */
	public String getInfoDate() {
		return infoDate;
	}

	/**
	 * 日時のセッター
	 * 
	 * @param infoDate 日時
	 */
	public void setInfoDate(String infoDate) {
		this.infoDate = infoDate;
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

	/**
	 * 内容のゲッター
	 * 
	 * @return 内容
	 */
	public String getInfoBody() {
		return infoBody;
	}

	/**
	 * 内容のセッター
	 * 
	 * @param infoBody 内容
	 */
	public void setInfoBody(String infoBody) {
		this.infoBody = infoBody;
	}

	/**
	 * 表示期限のゲッター
	 * 
	 * @return 表示期限
	 */
	public String getLimiDate() {
		return limiDate;
	}

	/**
	 * 表示期限のセッター
	 * 
	 * @param limiDate 表示期限
	 */
	public void setLimiDate(String limiDate) {
		this.limiDate = limiDate;
	}

	/**
	 * ボタンのゲッター
	 * 
	 * @return ボタン
	 */
	public String getButton() {
		return button;
	}

	/**
	 * ボタンのセッター
	 * 
	 * @param button ボタン
	 */
	public void setButton(String button) {
		this.button = button;
	}

	/**
	 * 表示条件値のゲッター
	 * 
	 * @return ボタン
	 */
	public String getViewMode() {
		return viewMode;
	}

	/**
	 * 表示条件値のセッター
	 * 
	 * @param button ボタン
	 */
	public void setViewMode(String viewMode) {
		this.viewMode = viewMode;
	}
}
