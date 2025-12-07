package jp.logware.custmer.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.validator.ValidatorForm;

import jp.logware.custmer.subform.InfoValue;

/**
 * ログイン アクションフォームクラス
 * 
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class LoginActionForm extends ValidatorForm {
	/**
	 * シリアルバージョン
	 */
	private static final long serialVersionUID = -8427652978314972321L;

	/**
	 * お知らせ日付
	 */
	private List<InfoValue> infoList;

	/**
	 * ユーザid
	 */
	private String userId;

	/**
	 * パスワード
	 */
	private String passwd;

	/**
	 * お知らせ有効期限
	 */
	private String limitDate;

	/**
	 * ボタン
	 */
	private String button;

	/**
	 * コンストラクタ
	 */
	public LoginActionForm() {
		infoList = new ArrayList<InfoValue>();
		userId = "";
		passwd = "";
		limitDate = "";
	}

	/**
	 * お知らせ日付のゲッター
	 * 
	 * @return お知らせ日付のリスト
	 */
	public List<InfoValue> getInfoList() {
		return infoList;
	}

	/**
	 * お知らせ日付のセッター
	 * 
	 * @param list お知らせ日付のリスト
	 */
	public void setInfoList(List<InfoValue> list) {
		this.infoList = list;
	}

	/**
	 * ユーザidのゲッター
	 * 
	 * @return ユーザid
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザidのセッター
	 * 
	 * @param userId ユーザid
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * パスワードのゲッター
	 * 
	 * @return パスワード
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * パスワードのセッター
	 * 
	 * @param passwd パスワード
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	/**
	 * お知らせ有効期限のゲッター
	 * 
	 * @return お知らせ有効期限
	 */
	public String getLimitDate() {
		return limitDate;
	}

	/**
	 * お知らせ有効期限のセッター
	 * 
	 * @param limitDate お知らせ有効期限
	 */
	public void setLimitDate(String limitDate) {
		this.limitDate = limitDate;
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
