package jp.logware.custmer.form.master;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.validator.ValidatorForm;

import jp.logware.custmer.subform.InfoValue;

/**
 * お知らせ一覧 アクションフォームクラス
 * 
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class InfoListActionForm extends ValidatorForm {
	/**
	 * シリアルバージョン
	 */
	private static final long serialVersionUID = 8748138563450322573L;

	/**
	 * 日付
	 */
	private List<InfoValue> infomList;

	/**
	 * 日付
	 */
	private String infoDate;

	/**
	 * 連番
	 */
	private String seq;

	/**
	 * ボタン
	 */
	private String button;

	/**
	 * コンストラクタ
	 */
	public InfoListActionForm() {
		infomList = new ArrayList<InfoValue>();
		infoDate = "";
		seq = "";
	}

	/**
	 * 日付のゲッター
	 * 
	 * @return 日付のリスト
	 */
	public List<InfoValue> getInfomList() {
		return infomList;
	}

	/**
	 * 日付のセッター
	 * 
	 * @param list 日付のリスト
	 */
	public void setInfomList(List<InfoValue> list) {
		this.infomList = list;
	}

	/**
	 * 日付のゲッター
	 * 
	 * @return 日付
	 */
	public String getInfoDate() {
		return infoDate;
	}

	/**
	 * 日付のセッター
	 * 
	 * @param infoDate 日付
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
}
