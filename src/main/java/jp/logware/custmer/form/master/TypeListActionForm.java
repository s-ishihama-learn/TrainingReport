package jp.logware.custmer.form.master;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.validator.ValidatorForm;

import jp.logware.custmer.subform.TypeValue;

/**
 * 分類一覧 アクションフォームクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class TypeListActionForm extends ValidatorForm {
	/**
	 * 分類コード
	 */
	private List<TypeValue> typeList;

	/**
	 * 分類コード
	 */
	private String typeCode;

	/**
	 * ボタン
	 */
	private String button;

	/**
	 * コンストラクタ
	 */
	public TypeListActionForm() {
		typeList = new ArrayList<TypeValue>();
		typeCode = "";
	}

	/**
	 * 分類コードのゲッター
	 *
	 * @return 分類コードのリスト
	 */
	public List<TypeValue> getTypeList() {
		return typeList;
	}

	/**
	 * 分類コードのセッター
	 *
	 * @param list 分類コードのリスト
	 */
	public void setTypeList(List<TypeValue> list) {
		this.typeList = list;
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
