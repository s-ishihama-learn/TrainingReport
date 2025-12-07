package jp.logware.custmer.form.master;

import org.apache.struts.validator.ValidatorForm;

/**
 * 部類編集 アクションフォームクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class TypeEditActionForm extends ValidatorForm {
	/**
	 * シリアルバージョン
	 */
	private static final long serialVersionUID = -710454195054130216L;

	/**
	 * 分類コード
	 */
	private String typeCode;

	/**
	 * 分類名
	 */
	private String typeName;

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
	public TypeEditActionForm() {
		typeCode = "";
		typeName = "";
	}

	/**
	 * 分類コードのゲッター
	 *
	 * @return 分類コード
	 */
	public String getTypeCode() {
		return typeCode;
	}

	/**
	 * 分類コードのセッター
	 *
	 * @param typeCode 分類コード
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	/**
	 * 分類名のゲッター
	 *
	 * @return 分類名
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * 分類名のセッター
	 *
	 * @param typeName 分類名
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
