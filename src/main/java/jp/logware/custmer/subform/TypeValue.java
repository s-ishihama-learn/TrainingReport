package jp.logware.custmer.subform;

/**
 * 分類一覧 アクションフォーム用サブクラス<br>
 * ChartTypeValueクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class TypeValue {
	/**
	 * 分類コード
	 */
	private String typeCode;

	/**
	 * 分類名
	 */
	private String typeName;

	/**
	 * コンストラクタ
	 */
	public TypeValue() {
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

}
